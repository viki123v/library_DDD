package org.viktor_company.library_managment.domain.patrons

import org.axonframework.modelling.command.AggregateIdentifier

class Patron(
    @AggregateIdentifier val id: PatronID,
    val name:String,
    val surname:String
)