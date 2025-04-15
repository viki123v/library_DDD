package org.viktor_company.library_managment.domain.books

import org.axonframework.modelling.command.AggregateIdentifier

class Book(@AggregateIdentifier val id: BookID, val description: String){

}