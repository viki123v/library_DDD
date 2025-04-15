package org.viktor_company.library_managment.domain.books_copies

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.viktor_company.library_managment.domain.books_copies.value_objects.Fee
import org.viktor_company.library_managment.domain.books_copies.variants.BookCopyVariant


class BookCopy(
    @AggregateIdentifier val id: BookCopyID,
    val variant: BookCopyVariant,
    val fee: Fee = Fee(0.0)
) {

    @CommandHandler
    fun handle(command: RequestHold){

    }

    @CommandHandler
    fun handle(command: RequestLend){

    }

    @CommandHandler
    fun handle(command: ReturnLend){

    }

}