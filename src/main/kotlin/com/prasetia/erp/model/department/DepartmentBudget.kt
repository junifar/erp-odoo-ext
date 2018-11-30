package com.prasetia.erp.model.department

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class DepartmentBudget(
       @Id
       val id:Long,
       val name:String?,
       val nilai_budget:Double?,
       val realisasi_budget: Double?,
       val persent_budget: Double?
){
    constructor(): this(0,"", 0.0,0.0,0.0)
}