package org.viktor_company.library_managment.domain.books_copies

import jakarta.persistence.*
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.Repository
import org.viktor_company.library_managment.domain.books_copies.value_objects.Fee
import org.viktor_company.library_managment.domain.books_copies.variants.BookCopyVariant
import org.viktor_company.library_managment.domain.books_copies.variants.CirculatingBookCopyVariant
import org.viktor_company.library_managment.domain.books_copies.variants.RestrictedBookCopyVariant
import org.viktor_company.library_managment.domain.lends.Lend
import org.viktor_company.library_managment.domain.patrons.Patron
import kotlin.jvm.Transient

enum class BookCopyVariantType {
    CIRCULATING,
    RESTRICTED
}

@Entity
class BookCopy() {
    @EmbeddedId
    @AggregateIdentifier
    lateinit var id: BookCopyID

    @Enumerated(EnumType.STRING)
    @Column(name="book_type")
    lateinit var variantType: BookCopyVariantType

    var fee: Fee = Fee(0.0)

    @Transient
    var variant: BookCopyVariant? = null

    @OneToOne(mappedBy = "id.bookCopyID", cascade = [CascadeType.REMOVE])
    var lendId: Lend? = null

    @CommandHandler
    constructor(cmd: CreateCopyVerified): this() {
        id= BookCopyID(cmd.isbn,cmd.bookID)
        variantType= cmd.variant
        fee = cmd.fee
    }

    fun getVariantStrategy() : BookCopyVariant{
        if(variant!=null){
            return variant!!
        }

        if(variantType == BookCopyVariantType.RESTRICTED){
            variant=RestrictedBookCopyVariant()
        }else{
            variant=CirculatingBookCopyVariant()
        }

        return variant!!
    }


    @CommandHandler
    fun handle(
        cmd: RequestHold,
        patronRepo:Repository<Patron>
    ){
        val requester=patronRepo.load(cmd.patronID.toString())
        // Terbat da pratish i holdot koga ke zavrshit za da ja klajsh vo taken
    }

    @CommandHandler
    fun handle(command: RequestLend){

    }
    //Imame problem so to sho ojdovme na JPA nachin a ustvari trebashe sive ovie relationships
    // da gi napreme so primitives a posle da injectvime repo od asocijatacijata pa da go
    // dobieme neshtovo preku nego

    @CommandHandler
    fun handle(command: ReturnLend){

    }

}