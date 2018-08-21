package com.prasetia.erp.model.preventive

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class PreventiveCustomer(
        @Id
        val id: Long,
        val tahun: String,
        val customer_name: String,
        val area: String,
        val nilai_po: Long?,
        val nilai_penagihan: Long?,
        val nilai_budget: Long?,
        val realisasi_budget: Long?,
        val laba_rugi: Long?,
        val customer_id:Long?,
        val persent_penagihan:Long?,
        val persent_budget:Long?,
        val persent_laba_rugi:Long?,
        val area_id:Long?
){
    constructor(): this(0, "", "", "", 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0)
}