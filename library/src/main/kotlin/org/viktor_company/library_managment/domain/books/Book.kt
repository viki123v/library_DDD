package org.viktor_company.library_managment.domain.books

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import lombok.Getter
import lombok.Setter

@Entity
@Getter
@Setter
class Book(){
    @EmbeddedId
    lateinit var id:BookID
    lateinit var desc:String

    constructor(id:BookID,desc:String) : this(){
        this.id=id
        this.desc=desc
    }
}