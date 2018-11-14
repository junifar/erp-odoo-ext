package com.prasetia.erp.controller.api.department

import com.prasetia.erp.model.department.DepartmentBudget
import com.prasetia.erp.pojo.department.*
import com.prasetia.erp.repository.department.DepartmentBudgetRepository
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

    @Autowired
    lateinit var repositoryDepartmentBudgetRepository: DepartmentBudgetRepository

    lateinit var departmentBudgetData: Iterable<DepartmentBudget>

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
            departmentYearData.add(DepartmentYearData(it.id, it.department_name, it.department_id, it.nilai_budget,
                    it.realisasi_budget, it.persent_budget))
        }
        return departmentYearData
    }

    fun getDepartmentBudgetData(): MutableList<DepartmentBudgetData> {
        val data = departmentBudgetData
        val departmentBudgetData: MutableList<DepartmentBudgetData> = mutableListOf()
        data.forEach {
            departmentBudgetData.add(DepartmentBudgetData(it.id, it.name, it.nilai_budget, it.realisasiBudget,
                    it.persent_budget))
        }
        return departmentBudgetData
    }

    @RequestMapping("/api/department_budget/{tahun}/{department_id}")
    fun getDepartmentBudget(@PathVariable("tahun") tahun:Long, @PathVariable("department_id") department_id:Long):MutableList<DepartmentBudgetYearData>{
        val data = repositoryDepartmentYear.getDepartmentYearDeptID(tahun, department_id)
        departmentBudgetData = repositoryDepartmentBudgetRepository.getDepartmentBudget(tahun, department_id)
        val departmentBudgetYearData: MutableList<DepartmentBudgetYearData> = mutableListOf()
        data.forEach {
            departmentBudgetYearData.add(DepartmentBudgetYearData(it.id, it.department_name,it.department_id,
                    it.nilai_budget, it.realisasi_budget, it.persent_budget, getDepartmentBudgetData()))
        }
        return departmentBudgetYearData
    }


}