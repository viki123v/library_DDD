package org.viktor_company.library_managment.domain.books_copies

import org.axonframework.modelling.command.TargetAggregateIdentifier
import org.viktor_company.library_managment.domain.books.BookID
import org.viktor_company.library_managment.domain.books_copies.value_objects.Fee
import org.viktor_company.library_managment.domain.books_copies.variants.BookCopyVariant
import org.viktor_company.library_managment.domain.holds.HoldStrategyType
import org.viktor_company.library_managment.domain.lends.LendID
import org.viktor_company.library_managment.domain.patrons.PatronID
import java.time.LocalDateTime

data class CreateCopy(
    val bookID: BookID,
    val isbn:ULong,
    val variant: BookCopyVariant,
    val fee: Fee
)

data class RequestHold(
    val bookCopyID:BookCopyID,
    val patronID: PatronID,
    val strategy: HoldStrategyType,
    val submittedAt:LocalDateTime=LocalDateTime.now()
)

data class RequestLend(
    val patronID: PatronID,
    val bookID: BookCopyID,
    val successful: LocalDateTime=LocalDateTime.now()
)

data class ReturnLend(
    @TargetAggregateIdentifier val lendID: LendID,
)