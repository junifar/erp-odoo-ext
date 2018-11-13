package com.prasetia.erp.controller.api.department

import com.prasetia.erp.pojo.department.DepartmentSummaryData
import com.prasetia.erp.pojo.department.DepartmentYearData
import com.prasetia.erp.repository.department.DepartmentSummaryRepository
import com.prasetia.erp.repository.department.DepartmentYearRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DepartmentController{

    @Autowired
    lateinit var repository: DepartmentSummaryRepository

    @Autowired
    lateinit var repositoryDepartmentYear: DepartmentYearRepository

    @RequestMapping("/api/department_summary")
    fun getDepartmentSummary():MutableList<DepartmentSummaryData>{
        val data = repository.getDepartmentSummary()
        val departmentSummaryData: MutableList<DepartmentSummaryData> = mutableListOf()
        data.forEach {
            departmentSummaryData.add((DepartmentSummaryData(it.id, it.periode, it.nilai_budget, it.realisasi_budget, it.persent_budget)))
        }
        return departmentSummaryData
    }

    @RequestMapping("/api/department_year/{tahun}")
    fun getDepartmentYear(@PathVariable("tahun") tahun:Long):MutableList<DepartmentYearData>{
        val data = repositoryDepartmentYear.getDepartmentYear(tahun)
        val departmentYearData:MutableList<DepartmentYearData> = mutableListOf()
        data.forEach {
            departmentYearData.add(DepartmentYearData(it.id, it.department_name, it.nilai_budget,
                    it.realisasi_budget, it.persent_budget))
        }
        return departmentYearData
    }


}