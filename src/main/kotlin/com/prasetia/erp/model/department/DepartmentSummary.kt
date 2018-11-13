package com.prasetia.erp.model.department

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class DepartmentSummary(
        @Id
        val id:Long,
        val periode:Long?,
        val nilai_budget:Long?,
        val realisasi_budget:Long?,
        val persent_budget:Float?
){
    constructor(): this(0,0,0,0,0f)
}