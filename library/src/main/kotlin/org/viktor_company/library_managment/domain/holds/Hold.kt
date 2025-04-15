package org.viktor_company.library_managment.domain.holds

import org.axonframework.modelling.command.AggregateIdentifier
import org.viktor_company.library_managment.domain.holds.variants.HoldStrategy

enum class HoldStrategyType {
    OPEN,
    CLOSE
}

class Hold(@AggregateIdentifier val id: HoldID,val holdStrategy: HoldStrategy)
{

}