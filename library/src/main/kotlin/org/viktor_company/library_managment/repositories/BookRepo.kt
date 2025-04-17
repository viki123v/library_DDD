package org.viktor_company.library_managment.repositories

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.viktor_company.library_managment.domain.books.Book
import org.viktor_company.library_managment.domain.books.BookID

interface BookRepo : JpaRepository<Book, BookID> {

    @Query("""
      select b from Book b 
        where (:author is NULL or b.id.autor=:author) and 
        (:title is NULL or b.id.title=:title) and 
        b.id.libBranchID.cityName=:city and b.id.libBranchID.branchName=:branchName
    """)
    fun findAllMatchingForLibBranch(
        page: Pageable,
        @Param("author") author : String?,
        @Param("title") title : String?,
        @Param("city") city:String,
        @Param("branchName") branchName:String
    ): Page<Book>
}