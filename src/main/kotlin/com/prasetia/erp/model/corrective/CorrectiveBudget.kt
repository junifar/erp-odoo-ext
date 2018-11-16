package com.prasetia.erp.model.corrective

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class CorrectiveBudget(
        @Id
        val id:Long,
        val budget_id:Long?,
        val customer_id:Long?,
        val year_project:Long?,
        val site_name:String?,
        val project_id:String?,
        val nomor_budget:String?,
        val nilai_budget:Long?,
        val realisasi_budget:Long?,
        val persent_budget:Float?

){
    constructor(): this(0,0,0,0,"","","",0,0,0f)
}