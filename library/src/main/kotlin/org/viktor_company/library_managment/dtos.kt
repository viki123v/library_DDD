package org.viktor_company.library_managment

import org.springframework.stereotype.Service
import org.viktor_company.library_managment.domain.books.Book
import org.viktor_company.library_managment.domain.books_copies.BookCopy
import org.viktor_company.library_managment.domain.books_copies.value_objects.Fee
import org.viktor_company.library_managment.domain.lib_branches.LibBranch

data class LibBranchDTO(
    val city:String,
    val name:String
)

data class BookDTO(
    val title:String,
    val author:String,
    val description:String
)

data class BookCopyMinimalDataDTO(
    val fee: Fee,
    val isbn:Long,
)

data class BookCopyDTO(
    val author:String,
    val title:String,
    val copies:List<BookCopyMinimalDataDTO>
)

@Service
class BookCopyConverter{
    fun toDTO(copies:List<BookCopy>) : List<BookCopyDTO>{
       return copies.groupBy { "${it.id.bookID.autor}-${it.id.bookID.title}" }
           .entries
           .map{
               val (author,title) = it.key.split("-")
               val copies = it.value.map{
                   BookCopyMinimalDataDTO(it.fee,it.id.isbn!!)
               }
               BookCopyDTO(author,title,copies)
           }
    }
}

@Service
class LibBranchConverter{
    fun toDTO(branch:LibBranch):LibBranchDTO{
        return LibBranchDTO(
            branch.id.cityName,
            branch.id.branchName
        )
    }
}

@Service
class BookConverter{
    fun toDTO(book: Book): BookDTO {
        return BookDTO(
            book.id.title,
            book.id.autor,
            book.desc,
        )
    }
}