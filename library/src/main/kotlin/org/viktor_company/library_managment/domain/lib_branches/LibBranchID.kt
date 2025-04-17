package org.viktor_company.library_managment.domain.lib_branches

import jakarta.persistence.Embeddable
import lombok.Getter
import lombok.Setter

@Embeddable
@Getter
@Setter
class LibBranchID(){
    lateinit var cityName:String
    lateinit var branchName:String

    constructor(cityName:String, branchName:String) : this(){
        this.cityName = cityName
        this.branchName = branchName
    }

    override fun toString(): String {
        return  "$cityName-$branchName"
    }

}