package org.viktor_company.library_managment.domain.books

import org.viktor_company.library_managment.domain.lib_branches.LibBranchID

data class BookID(
    val title:String,
    val autor:String,
    var libBranchID:LibBranchID
)