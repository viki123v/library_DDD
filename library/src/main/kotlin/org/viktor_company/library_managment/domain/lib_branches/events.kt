package org.viktor_company.library_managment.domain.lib_branches

import org.viktor_company.library_managment.domain.books.BookID

data class CreatedLibBranch(
    val id:LibBranchID
)

data class CreatedBookInCatalog(
    val id:BookID,
    val description:String,
)
