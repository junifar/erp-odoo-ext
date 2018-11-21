package com.prasetia.erp.model.corrective

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class CorrectiveYear(
        @Id
        val id: Long,
        val customer_id:Long,
        val code:String?,
        val jumlah_site: Long?,
        val year_project: String?,
        val nilai_po: Long?,
        val nilai_inv: Long?,
        val realisasi_budget: Long?,
        val nilai_budget: Long?,
        val percentage:Float?,
        val persent_budget:Float?,
        val profit:Long?,
        val profit_percentage:Float?
){
    constructor(): this(0,  0, "",0, "", 0, 0, 0,0,0f, 0f,0,0f)
}