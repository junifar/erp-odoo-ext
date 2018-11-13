package com.prasetia.erp.controller.api.department

import com.prasetia.erp.pojo.department.DepartmentSummaryData
import com.prasetia.erp.repository.department.DepartmentSummaryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DepartmentController{

    @Autowired
    lateinit var repository: DepartmentSummaryRepository

    @RequestMapping("/api/department_summary")
    fun getDepartmentSummary():MutableList<DepartmentSummaryData>{
        val data = repository.getDepartmentSummary()
        val departmentSummaryData: MutableList<DepartmentSummaryData> = mutableListOf()
        data.forEach {
            departmentSummaryData.add((DepartmentSummaryData(it.id, it.periode, it.nilai_budget, it.persent_budget)))
        }
        return departmentSummaryData
    }
}