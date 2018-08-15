package com.prasetia.erp.model.preventive

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class PreventiveCustomer(
        @Id
        val id: Long,
        val project_id_id: Long,
        val tahun: String,
        val site_name: String,
        val project_id: String,
        val state: String,
        val customer_name: String,
        val project_type: String,
        val area: String,
        val area_detail_id: Long?,
        val area_detail: String,
        val nilai_po: Long?,
        val nilai_penagihan: Long?,
        val nilai_budget: Long?,
        val realisasi_budget: Long?,
        val laba_rugi: Long?,
        val customer_id:Long?
){
    constructor(): this(0, 0, "", "", "", "", "", "", "",
            0, "", 0, 0, 0, 0, 0, 0)
}