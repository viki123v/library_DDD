package org.viktor_company.library_managment.domain.books_copies

import jakarta.persistence.Embeddable
import org.viktor_company.library_managment.domain.books.BookID

@Embeddable
class BookCopyID(){
    var isbn:Long? = null
    lateinit var bookID:BookID

    constructor(
        isbn:Long,
        bookID: BookID,
    ):this(){
        this.isbn = isbn
        this.bookID = bookID
    }

    override fun toString(): String {
        return "$isbn-$bookID"
    }
}
