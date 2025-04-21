package org.viktor_company.library_managment.domain.holds

import jakarta.persistence.Embeddable
import org.viktor_company.library_managment.domain.books_copies.BookCopyID
import org.viktor_company.library_managment.domain.lib_branches.LibBranchID
import org.viktor_company.library_managment.domain.patrons.PatronID

@Embeddable
class HoldID(){
    lateinit var libBranchID: LibBranchID
    lateinit var patronID: PatronID
    lateinit var bookCopyID:BookCopyID

    constructor(
        libBranchID: LibBranchID,
        patronID: PatronID,
        bookCopyID: BookCopyID
    ):this(){
       this.patronID = patronID
       this.libBranchID= libBranchID
       this.bookCopyID= bookCopyID
    }
}