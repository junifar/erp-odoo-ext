package com.prasetia.erp.model.preventive

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class PreventiveCustomer(
        @Id
        val id:Long,
        val customer_id:Long,
        val customer_name: String,
        val tahun: String,
        val bulan: Int?,
        val nilai_po: Long
){
    constructor(): this(0, 0, "", "", 0, 0)
}