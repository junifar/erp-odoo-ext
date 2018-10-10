package com.prasetia.erp.model.cme

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class CmeProjectDetail(
        @Id
        val id: Long,
        val name:String,
        val year_project: Long,
        val project_type: String,
        val project_id: String,
        val nilai_po: Long,
        val no_po: String?,
        val nilai_invoice: Long,
        val nilai_budget: Long,
        val realisasi_budget: Long,
        val estimate_po: Long,
        val customer:String?,
        val customer_id:Long?,
        val site_type_id: Long?,
        val area: String?
){
    constructor(): this(0,"",0,"","",0,"",
            0,0,0,0,"", 0, 0, "")
}