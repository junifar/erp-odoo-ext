package com.prasetia.erp.model.department

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class DepartmentBudgetRealisasi(
        @Id
        val id:Long,
        val budget_id:Long?,
        val parent_id:Long?,
        val ref:String?,
        val narration:String?,
        val budget_realisasi:Long?
){
    constructor(): this(0,0,0,"","",0)
}