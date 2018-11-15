package com.prasetia.erp.model.department

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class DepartmentBudgetDetail(
        @Id
        val id:Long,
        val line_id:Long?,
        val code:String?,
        val budget_item_view:String?,
        val nilai_budget:Long?,
        val realisasi_budget:Long?,
        val persent_budget: Float?
){
    constructor(): this(0,0,"","", 0,0,0f)
}