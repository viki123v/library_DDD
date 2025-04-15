package org.viktor_company.library_managment.domain.lib_branches

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.modelling.command.AggregateIdentifier

class LibBranch(@AggregateIdentifier val id: LibBranchID)
{
    @CommandHandler
    fun handle(command: AddBookToCatalog){

    }
}