package org.viktor_company.library_managment.domain.patrons

import jakarta.persistence.Embeddable

@Embeddable
class PatronID()
{
    lateinit var username:String

    constructor(username:String):this(){
        this.username = username
    }
}