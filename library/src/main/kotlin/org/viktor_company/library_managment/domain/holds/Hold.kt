package org.viktor_company.library_managment.domain.holds

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import org.axonframework.modelling.command.AggregateIdentifier
import org.viktor_company.library_managment.domain.holds.variants.CloseHoldStrategy
import org.viktor_company.library_managment.domain.holds.variants.HoldStrategy
import org.viktor_company.library_managment.domain.holds.variants.OpenHoldStrategy

enum class HoldStrategyType {
    OPEN,
    CLOSE
}

@Entity
class Hold()
{
    @EmbeddedId
    @AggregateIdentifier
    lateinit var id: HoldID

   @Enumerated(EnumType.STRING)
   lateinit var holdStrategyType: HoldStrategyType

   @Transient
   var holdStrategy: HoldStrategy? = null

   constructor(id: HoldID,type:HoldStrategyType):this(){
        this.id = id
        this.holdStrategyType = type
    }

   fun getStrategyType() : HoldStrategy{
       if(this.holdStrategy != null){
           return this.holdStrategy!!
       }

        if(holdStrategyType == HoldStrategyType.CLOSE){
            this.holdStrategy=CloseHoldStrategy()
        }else{
            this.holdStrategy=OpenHoldStrategy()
        }

        return this.holdStrategy!!
   }
}