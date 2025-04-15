package org.viktor_company.library_managment.domain.lends

import org.viktor_company.library_managment.domain.books_copies.BookCopyID
import org.viktor_company.library_managment.domain.patrons.PatronID

data class LendID(val patronID:PatronID, val bookCopyID:BookCopyID) {
}