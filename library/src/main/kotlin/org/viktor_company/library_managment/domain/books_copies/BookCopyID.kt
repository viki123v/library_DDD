package org.viktor_company.library_managment.domain.books_copies

import org.viktor_company.library_managment.domain.books.BookID

data class BookCopyID(
    val isbn:ULong,
    val bookID:BookID
)
