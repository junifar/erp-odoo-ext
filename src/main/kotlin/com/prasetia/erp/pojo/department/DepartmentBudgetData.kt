package com.prasetia.erp.pojo.department

import com.prasetia.erp.model.department.DepartmentBudget

class DepartmentBudgetYearData(
        val id:Long,
        val department_name:String?,
        val department_id:Long?,
        val nilai_budget:Long?,
        val realisasi_budget:Long?,
        val persent_budget: Float?,
        var department_budget: MutableList<DepartmentBudgetData>?
){
    constructor(): this(0,"", 0,0,0,0f, null)
}

class DepartmentBudgetData(
        val id:Long,
        val name:String?,
        val nilai_budget:Long?,
        val realisasiBudget: Long?,
        val persent_budget: Float?
){
    constructor(): this(0,"",0,0,0f)
}