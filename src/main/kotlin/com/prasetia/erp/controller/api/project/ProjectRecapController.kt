package com.prasetia.erp.controller.api.project

import com.prasetia.erp.pojo.project.ProjectRecapAgingData
import com.prasetia.erp.pojo.project.ProjectRecapData
import com.prasetia.erp.repository.project.ProjectRecapAgingRepository
import com.prasetia.erp.repository.project.ProjectRecapRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProjectRecapController{

    @Autowired
    lateinit var repository: ProjectRecapRepository

    @Autowired
    lateinit var repositoryProjectRecapAging: ProjectRecapAgingRepository

    @RequestMapping("api/project/recap")
    fun getAllData():MutableList<ProjectRecapData>{
        val data = repository.getAllRecapData()
        val projectRecapData:MutableList<ProjectRecapData> = mutableListOf()
        data.forEach {
            projectRecapData.add(ProjectRecapData(it.id, it.site_type,
                    it.po, it.invoiced,
                    it.bast, it.paid,
                    it.nilai_po, it.realisasi_budget,
                    it.invoice_open, it.invoice_paid))
        }
        return projectRecapData
    }

    @RequestMapping("api/project/recap_aging")
    fun getAllDataAging():MutableList<ProjectRecapAgingData>{
        val data = repositoryProjectRecapAging.getAllRecapAgingData()
        val projectRecapAgingData:MutableList<ProjectRecapAgingData> = mutableListOf()
        data.forEach {
            projectRecapAgingData.add((ProjectRecapAgingData(it.id, it.site_type, it.greater_60, it.between_30_60, it.less_30)))
        }
        return projectRecapAgingData
    }
}
