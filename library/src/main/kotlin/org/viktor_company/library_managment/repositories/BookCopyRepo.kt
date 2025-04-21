package org.viktor_company.library_managment.repositories

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.viktor_company.library_managment.domain.books.BookID
import org.viktor_company.library_managment.domain.books_copies.BookCopy
import org.viktor_company.library_managment.domain.books_copies.BookCopyID
import org.viktor_company.library_managment.domain.lib_branches.LibBranchID

interface BookCopyRepo : JpaRepository<BookCopy,BookCopyID>{

    @Query("""
        select b from BookCopy b
        where b.id.bookID = :bookID
    """)
    fun findAll(@Param("bookID") bookID: BookID, page:Pageable): Page<BookCopy>

    @Query("""
        select b from BookCopy b
         where b.id.bookID.libBranchID=:libBranchID
    """)
    fun findAll(@Param("libBranchID") libBranchID: LibBranchID,page:Pageable): Page<BookCopy>
}