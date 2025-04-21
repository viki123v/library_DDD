package org.viktor_company.library_managment.domain.lends

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import org.axonframework.modelling.command.AggregateIdentifier

@Entity
class Lend() {
    @EmbeddedId
    @AggregateIdentifier
    lateinit var id:LendID

    constructor(id:LendID) : this() {
        this.id = id
    }
}