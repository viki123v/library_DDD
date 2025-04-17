package org.viktor_company.library_managment.domain.lib_branches

import jakarta.persistence.CascadeType
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.spring.stereotype.Aggregate
import org.viktor_company.library_managment.domain.books.Book
import org.viktor_company.library_managment.domain.books.BookID

@Aggregate(repository = "axonLibBranchRepo")
@Entity
class LibBranch() {
    @AggregateIdentifier
    @EmbeddedId
    lateinit var id: LibBranchID

    @OneToMany(mappedBy = "id.libBranchID", cascade = [CascadeType.ALL])
    val books = mutableListOf<Book>()

    @CommandHandler
    constructor(cmd: CreateLibBranch) : this() {
        id = cmd.libBranchID
    }

    @CommandHandler
    fun handle(cmd: CreateBookInCatalog) {
        books.addLast(
            Book(
                BookID(
                    cmd.title,
                    cmd.author,
                    cmd.libBranchID
                ),
                cmd.description,
            )
        )
    }

}