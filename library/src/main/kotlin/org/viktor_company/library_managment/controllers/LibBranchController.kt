package org.viktor_company.library_managment.controllers

import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriBuilder
import org.viktor_company.library_managment.BookDTO
import org.viktor_company.library_managment.LibBranchDTO
import org.viktor_company.library_managment.domain.books.Book
import org.viktor_company.library_managment.domain.lib_branches.CreateBookInCatalog
import org.viktor_company.library_managment.domain.lib_branches.CreateLibBranch
import org.viktor_company.library_managment.domain.lib_branches.LibBranch
import org.viktor_company.library_managment.domain.lib_branches.LibBranchID

//TODO: vidi za queries
//TODO: vidi dali imash klaeno foreign keys

@Controller
@RequestMapping("/api/v1")
class LibBranchController(
    private val commandGateway: CommandGateway,
    private val uriBuilder:UriBuilder,
    private val queryGateway: QueryGateway
) {
    private fun headers(
        vararg pairs:Pair<String,String>
    ): HttpHeaders{
        return HttpHeaders(
            MultiValueMap.fromSingleValue(pairs.toMap())
        )
    }

    @PostMapping(
        path=["branch"],
        consumes=[MediaType.APPLICATION_JSON_VALUE],
    )
    fun createBranch(
        @RequestBody libBranch: LibBranchDTO,
    ) : ResponseEntity<Unit>{
        commandGateway.sendAndWait<CreateLibBranch>(
            CreateLibBranch(
                LibBranchID(
                    libBranch.city,
                    libBranch.name,
                )
            )
        )
        return ResponseEntity<Unit>(
            headers(
                "location" to uriBuilder.path("/branch")
                    .queryParam("city",libBranch.city)
                    .queryParam("name",libBranch.name)
                    .build()
                    .toString(),
            ),
            HttpStatus.CREATED
        )
    }

    @PostMapping(
        path=["/city/{cityName}/branch/{branchName}"],
        consumes=[MediaType.APPLICATION_JSON_VALUE]
    )
    fun createBookInCatalog(
        @PathVariable("cityName") cityName: String,
        @PathVariable("branchName") branchName: String,
        @RequestBody book: BookDTO,
    ): ResponseEntity<Unit>{
        commandGateway.sendAndWait<CreateBookInCatalog>(
            CreateBookInCatalog(
                book.title,
                book.author,
                book.description,
                LibBranchID(cityName, branchName)
            )
        )
        return ResponseEntity<Unit>(
            headers(
                "Location" to uriBuilder.path("/city/{city}/branch/{branch}")
                    .queryParam("title",book.title)
                    .queryParam("author",book.author)
                    .build(cityName,branchName)
                    .toString(),
            ),
            HttpStatus.CREATED
        )
    }

    @GetMapping("branch")
    fun getBranches(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(required = false,name="city") cityName:String?,
        @RequestParam(required = false, name = "branch") branchName:String?
    ):ResponseEntity<List<LibBranch>> {
        throw NotImplementedError()
    }

    @GetMapping("city/{cityName}/branch/{branchName}/catalog")
    fun getCatalog(
        @PathVariable("cityName") cityName: String,
        @PathVariable("branchName") branchName: String,
        @RequestParam(required = false) title: String?,
        @RequestParam(required = false) author: String?
    ) : ResponseEntity<List<Book>>
    {
        throw NotImplementedError()
    }
}