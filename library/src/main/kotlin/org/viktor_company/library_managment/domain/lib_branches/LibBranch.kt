package org.viktor_company.library_managment.domain.lib_branches

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import org.viktor_company.library_managment.domain.books.BookID

@Aggregate
class LibBranch()
{
    @AggregateIdentifier
    lateinit var id: LibBranchID
    val books= mutableListOf<BookID>()

    @CommandHandler
    constructor(cmd:CreateLibBranch) : this(){
        AggregateLifecycle.apply(CreatedLibBranch(cmd.libBranchID))
    }

    @EventSourcingHandler
    fun on(e: CreatedLibBranch):Unit{
        id=e.id
    }

    @CommandHandler
    fun handle(cmd: CreateBookInCatalog) {
        val id=BookID(
            cmd.title,
            cmd.author,
            cmd.libBranchID,
        )
        AggregateLifecycle.apply {
            CreatedBookInCatalog(
                id,
                cmd.description
            )
        }.andThen {
            books.add(id)
        }
    }


}