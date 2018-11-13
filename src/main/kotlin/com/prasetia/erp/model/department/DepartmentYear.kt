package com.prasetia.erp.model.department

import javax.persistence.Id

class DepartmentYear(
        @Id
        val id:Long,
        val department_name:String?,
        val nilai_budget:Long?,
        val realisasi_budget:Long?,
        val persent_budget: Float?
)
