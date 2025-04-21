package org.viktor_company.library_managment.domain.lends

import jakarta.persistence.Embeddable
import org.viktor_company.library_managment.domain.books_copies.BookCopyID
import org.viktor_company.library_managment.domain.patrons.PatronID

@Embeddable
class LendID() {
    lateinit var patronID:PatronID
    lateinit var bookCopyID:BookCopyID

    constructor(patronID:PatronID,bookCopyID: BookCopyID) : this() {
        this.patronID = patronID
        this.bookCopyID = bookCopyID
    }
}