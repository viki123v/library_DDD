package org.viktor_company.library_managment

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventhandling.gateway.EventGateway
import org.axonframework.modelling.command.AggregateNotFoundException
import org.axonframework.modelling.command.Repository
import org.springframework.stereotype.Component
import org.viktor_company.library_managment.domain.books_copies.BookCopy
import org.viktor_company.library_managment.domain.books_copies.BookCopyID
import org.viktor_company.library_managment.domain.books_copies.CreateCopy
import org.viktor_company.library_managment.domain.books_copies.CreateCopyVerified
import org.viktor_company.library_managment.domain.books_copies.erros.BookCopyWithSameID

@Component
class BookCopyFactory(
    private val gateway: EventGateway
) {
    @CommandHandler
    fun handle(cmd:CreateCopy,repo:Repository<BookCopy>){
        val tmpId= BookCopyID(cmd.isbn,cmd.bookID)
        try{
            repo.load(tmpId.toString())
            throw BookCopyWithSameID(tmpId)
        }catch(e: AggregateNotFoundException){
            gateway.publish(CreateCopyVerified(cmd))
        }
    }
}