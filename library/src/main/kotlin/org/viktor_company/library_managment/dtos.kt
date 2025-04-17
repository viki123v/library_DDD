package org.viktor_company.library_managment

import org.springframework.stereotype.Service
import org.viktor_company.library_managment.domain.books.Book
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