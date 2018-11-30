package com.prasetia.erp.pojo.department

import javax.persistence.Entity
import javax.persistence.Id

class DepartmentSummaryData(
        val id:Long,
        val periode:Long?,
        val nilai_budget:Double?,
        val realisasi_budget:Double?,
        val persent_budget:Double?
){
    constructor(): this(0,0, 0.0,0.0,0.0)
}