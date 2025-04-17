package org.viktor_company.library_managment.domain.lib_branches

import org.axonframework.modelling.command.TargetAggregateIdentifier


data class CreateBookInCatalog(
    val title: String,
    val author: String,
    val description:String,
    @TargetAggregateIdentifier val libBranchID: LibBranchID
)

data class CreateLibBranch(
   val libBranchID: LibBranchID,
)