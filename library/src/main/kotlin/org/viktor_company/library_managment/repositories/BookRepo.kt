package org.viktor_company.library_managment.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.viktor_company.library_managment.domain.books.Book
import org.viktor_company.library_managment.domain.books.BookID

interface BookRepo : JpaRepository<Book, BookID> {

}