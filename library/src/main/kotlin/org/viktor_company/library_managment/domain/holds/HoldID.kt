package org.viktor_company.library_managment.domain.holds

import org.viktor_company.library_managment.domain.books_copies.BookCopyID
import org.viktor_company.library_managment.domain.lib_branches.LibBranchID
import org.viktor_company.library_managment.domain.patrons.PatronID

data class HoldID(
    val libBranchID: LibBranchID,
    val patronID: PatronID,
    val bookCopyID:BookCopyID
)