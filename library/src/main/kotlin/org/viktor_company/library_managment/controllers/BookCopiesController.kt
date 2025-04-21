package org.viktor_company.library_managment.controllers

import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriBuilder
import org.viktor_company.library_managment.BookCopyConverter
import org.viktor_company.library_managment.BookCopyDTO
import org.viktor_company.library_managment.domain.books.BookID
import org.viktor_company.library_managment.domain.books_copies.BookCopyVariantType
import org.viktor_company.library_managment.domain.books_copies.CreateCopy
import org.viktor_company.library_managment.domain.books_copies.erros.BookCopyWithSameID
import org.viktor_company.library_managment.domain.lib_branches.LibBranchID
import org.viktor_company.library_managment.repositories.BookCopyRepo

@Controller
@RequestMapping("/api/v1")
class BookCopiesController(
    val cmdGateway:CommandGateway,
    val uriBuilder: UriBuilder,
) {

    @ExceptionHandler(BookCopyWithSameID::class)
    fun sameIDForCopies(){
        throw NotImplementedError()
    }

    @PostMapping("/city/{cityName}/branch/{branchName}")
    fun createCopy(
        @RequestParam title:String,
        @RequestParam author:String,
        @PathVariable cityName:String,
        @PathVariable branchName:String,
        @RequestParam isbn:Long,
        @RequestParam variant: BookCopyVariantType,
        @RequestParam fee: Double
    ) : ResponseEntity<Unit>{
        cmdGateway.sendAndWait<CreateCopy>(
            CreateCopy(
                BookID(
                    title,
                    author,
                    LibBranchID(cityName,branchName)
                ),
                isbn,
                variant,
                fee
            )
        )
        return ResponseEntity<Unit>(
            HttpHeaders(
               MultiValueMap.fromSingleValue(
                   mapOf(
                       "location" to uriBuilder.path("/city/{cityName}/branch/{branchName}/book/$author-$title")
                       .queryParam("isbn",isbn)
                       .build(cityName,branchName)
                       .toString()
                   )
               )
           ),
            HttpStatus.CREATED
        )
    }

    @GetMapping("/city/{cityName}/branch/{branchName}/book/{bookIDStr:\\w+:\\w+|all}/copies")
    fun getCopies(
        @PathVariable cityName: String,
        @PathVariable branchName: String,
        @PathVariable bookIDStr: String,
        @RequestParam(required = false) isbn:Long,
        @RequestParam(name="size", defaultValue = "20") pageSize:Int,
        @RequestParam(name="page", defaultValue = "0") pageNum: Int,
        bookCopyRepo:BookCopyRepo,
        bookCopyConverter: BookCopyConverter,
     ): ResponseEntity<List<BookCopyDTO>> {
        val pageRequest=PageRequest.of(pageNum,pageSize)
        val libBranchID=LibBranchID(cityName,branchName)
        if(bookIDStr=="all"){
           return ResponseEntity.ok(
               bookCopyConverter.toDTO(
                   bookCopyRepo.findAll(libBranchID,pageRequest).toList()
               )
           )
        }else{
           val (author,title) = bookIDStr.split("-")
           val bookID=BookID(author,title,libBranchID)
           return ResponseEntity.ok(bookCopyConverter.toDTO(
               bookCopyRepo.findAll(bookID,pageRequest).toList()
           ))
        }
    }
}