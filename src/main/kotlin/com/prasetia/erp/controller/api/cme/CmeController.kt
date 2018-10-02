package com.prasetia.erp.controller.api.cme

import com.prasetia.erp.pojo.cme.CmeSummaryYearData
import com.prasetia.erp.repository.cme.CmeSummaryYearRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CmeController{

    @Autowired
    lateinit var repositoryCmeSummary: CmeSummaryYearRepository

    @RequestMapping("/api/project_summary_year")
    fun getSummaryCmeByYear():MutableList<CmeSummaryYearData>{
        val data = repositoryCmeSummary.getCmeSummaryYear()
        val cmeSummaryYearData:MutableList<CmeSummaryYearData> = mutableListOf()
        data.forEach {
            val percentage= if (it.nilai_po == 0.toLong()) 0.toFloat() else it.nilai_invoice.toFloat().div(it.nilai_po.toFloat())
            val remainingInvoice = it.nilai_po - it.nilai_invoice
            val percentageRealization = if (it.nilai_budget == 0.toLong()) 0.toFloat() else it.realisasi_budget.toFloat().div(it.nilai_budget.toFloat())
            val profitLoss = it.nilai_invoice - it.realisasi_budget
            val percentageProfitRealization = if (it.realisasi_budget == 0.toLong()) 0.toFloat() else profitLoss.toFloat().div(it.realisasi_budget)
            val percentageProfitPO = if(it.nilai_po == 0.toLong()) 0.toFloat() else profitLoss.toFloat().div(it.nilai_po)

            cmeSummaryYearData.add(CmeSummaryYearData(it.id, it.year_project, it.jumlah_site, it.site_cancel,
                    it.nilai_po, it.nilai_invoice, it.nilai_budget, it.realisasi_budget, it.estimate_po, percentage, remainingInvoice,
                    percentageRealization, profitLoss, percentageProfitRealization, percentageProfitPO))
        }
        return cmeSummaryYearData
    }
}