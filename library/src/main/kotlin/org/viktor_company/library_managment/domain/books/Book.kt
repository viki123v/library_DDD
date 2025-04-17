package org.viktor_company.library_managment.domain.books

import org.axonframework.eventsourcing.EventSourcingHandler
import org.springframework.stereotype.Component
import org.viktor_company.library_managment.domain.lib_branches.CreatedBookInCatalog

@Component
class Book(){
    private lateinit var id:BookID
    private lateinit var desc:String

    @EventSourcingHandler
    fun on(cmd: CreatedBookInCatalog){
        id=cmd.id
        desc = cmd.description
    }
}