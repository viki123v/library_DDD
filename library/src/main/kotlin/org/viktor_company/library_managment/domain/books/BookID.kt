package org.viktor_company.library_managment.domain.books

import jakarta.persistence.Embeddable
import org.viktor_company.library_managment.domain.lib_branches.LibBranchID

@Embeddable
class BookID(){
    lateinit var title:String
    lateinit var autor:String
    lateinit var libBranchID: LibBranchID

    constructor(
        title:String,
        autor:String,
        libBranchID: LibBranchID
    ) : this(){
        this.title = title
        this.autor = autor
        this.libBranchID = libBranchID
    }
}