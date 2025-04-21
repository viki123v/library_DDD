package org.viktor_company.library_managment.domain.books_copies.erros

import org.viktor_company.library_managment.domain.books_copies.BookCopyID

class BookCopyWithSameID(tmpID: BookCopyID)
    : RuntimeException(tmpID.toString())
