package com.prasetia.erp.controller.api.corrective

import com.prasetia.erp.pojo.corrective.CorrectiveCustomerSummaryData
import com.prasetia.erp.repository.corrective.CorrectiveSummaryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CorrectiveController{

    @Autowired
    lateinit var repository: CorrectiveSummaryRepository

    @RequestMapping("/api/corrective_summary")
//    fun getAllData(): Iterable<CorrectiveSummary> = repository.getCorrectiveSummary()
    fun getAllData(): MutableList<CorrectiveCustomerSummaryData>{
        val data = repository.getCorrectiveSummary()
        val correctiveCustomerSummaryData:MutableList<CorrectiveCustomerSummaryData> = mutableListOf()
        data.forEach {
            correctiveCustomerSummaryData.add(CorrectiveCustomerSummaryData(it.id, it.jumlah_site,
                    it.year_project, it.nilai_po, it.nilai_inv, it.realisasi_budget, it.percentage, it.profit,
                    it.profit_percentage))
        }
        return correctiveCustomerSummaryData
    }
}