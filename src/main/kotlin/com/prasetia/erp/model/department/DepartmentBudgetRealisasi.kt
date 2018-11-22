package com.prasetia.erp.model.department

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class DepartmentBudgetRealisasi(
        @Id
        val id:Long,
        val date: Date?,
        val budget_id:Long?,
        val parent_id:Long?,
        val ref:String?,
        val narration:String?,
        val budget_realisasi:Long?
){
    constructor(): this(0,null,0,0,"","",0)
}