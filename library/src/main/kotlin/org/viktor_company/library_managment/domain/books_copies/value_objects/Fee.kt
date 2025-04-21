package org.viktor_company.library_managment.domain.books_copies.value_objects

import jakarta.persistence.Embeddable

@Embeddable
data class Fee(val value:Double=0.0) {
    init{
        if(value<0){
            throw IllegalArgumentException("Fee can not be less than zero")
        }
    }
}