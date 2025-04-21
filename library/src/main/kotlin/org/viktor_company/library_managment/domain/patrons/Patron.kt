package org.viktor_company.library_managment.domain.patrons

import jakarta.persistence.EmbeddedId
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import org.axonframework.modelling.command.AggregateIdentifier
import org.viktor_company.library_managment.domain.patrons.varaints.OrdinaryPatronType
import org.viktor_company.library_managment.domain.patrons.varaints.PatronType
import org.viktor_company.library_managment.domain.patrons.varaints.ResearchPatronType

enum class PatronVariantType{
    ORDINARY,
    RESEARCH
}

class Patron(){
    @EmbeddedId
    @AggregateIdentifier
    lateinit var id: PatronID
    lateinit var name:String
    lateinit var surname:String

    @Enumerated(EnumType.STRING)
    lateinit var patronType:PatronVariantType

    @Transient
    var variant: PatronType? = null

    constructor(id: PatronID, name:String, surname:String) : this() {
        this.id =id
        this.name = name
        this.surname = surname
    }

    fun getVariantStrategy():PatronType{
        if(this.variant != null){
           return this.variant!!
        }

        if(this.patronType != PatronVariantType.ORDINARY){
           this.variant=OrdinaryPatronType()
        }else{
            this.variant =ResearchPatronType()
        }

        return this.variant!!
    }
}