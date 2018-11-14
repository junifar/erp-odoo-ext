package com.prasetia.erp.model.department

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class DepartmentBudget(
       @Id
       val id:Long,
       val name:String?,
       val nilai_budget:Long?,
       val realisasiBudget: Long?,
       val persent_budget: Float?
){
    constructor(): this(0,"", 0,0,0f)
}