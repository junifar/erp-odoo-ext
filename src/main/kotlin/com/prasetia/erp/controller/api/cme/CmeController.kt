package com.prasetia.erp.controller.api.cme

import com.prasetia.erp.model.cme.CmeProjectDetail
import com.prasetia.erp.pojo.cme.CmeSummaryYearData
import com.prasetia.erp.pojo.cme.CmeSummaryYearProjectTypeCustData
import com.prasetia.erp.pojo.cme.CmeSummaryYearProjectTypeData
import com.prasetia.erp.pojo.cme.CmeYearProjectTypeCustProjectDetailData
import com.prasetia.erp.repository.cme.CmeProjectDetailRepository
import com.prasetia.erp.repository.cme.CmeSummaryYearProjectTypeCustRepository
import com.prasetia.erp.repository.cme.CmeSummaryYearProjectTypeRepository
import com.prasetia.erp.repository.cme.CmeSummaryYearRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CmeController{

    @Autowired
    lateinit var repositoryCmeSummary: CmeSummaryYearRepository

    @Autowired
    lateinit var repositoryCmeSummaryYearProjectType: CmeSummaryYearProjectTypeRepository

    @Autowired
    lateinit var repositoryCmeSummaryYearProjectTypeCust: CmeSummaryYearProjectTypeCustRepository

    @Autowired
    lateinit var repositoryCmeProjectDetail: CmeProjectDetailRepository

    lateinit var cmeProjectDetailDataRepository: Iterable<CmeProjectDetail>

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

    @RequestMapping("/api/project_summary_year/{tahun}")
    fun getSummaryCmeByYearProjectType(@PathVariable("tahun") tahun:Long): MutableList<CmeSummaryYearProjectTypeData>{
        val data = repositoryCmeSummaryYearProjectType.getCmeSummaryYearProjectType(tahun)
        val cmeSummaryYearProjectTypeData:MutableList<CmeSummaryYearProjectTypeData> = mutableListOf()
        data.forEach {
            val percentage= if (it.nilai_po == 0.toLong()) 0.toFloat() else it.nilai_invoice.toFloat().div(it.nilai_po.toFloat())
            val remainingInvoice = it.nilai_po - it.nilai_invoice
            val percentageRealization = if (it.nilai_budget == 0.toLong()) 0.toFloat() else it.realisasi_budget.toFloat().div(it.nilai_budget.toFloat())
            val profitLoss = it.nilai_invoice - it.realisasi_budget
            val percentageProfitRealization = if (it.realisasi_budget == 0.toLong()) 0.toFloat() else profitLoss.toFloat().div(it.realisasi_budget)
            val percentageProfitPO = if(it.nilai_po == 0.toLong()) 0.toFloat() else profitLoss.toFloat().div(it.nilai_po)

            cmeSummaryYearProjectTypeData.add(CmeSummaryYearProjectTypeData(it.id, it.year_project, it.jumlah_site, it.project_type, it.site_cancel,
                    it.nilai_po, it.nilai_invoice, it.nilai_budget, it.realisasi_budget, it.estimate_po, it.site_type_id, percentage, remainingInvoice,
                    percentageRealization, profitLoss, percentageProfitRealization, percentageProfitPO))
        }
        return cmeSummaryYearProjectTypeData
    }

    @RequestMapping("/api/project_summary_year/{tahun}/{site_type_id}")
    fun getSummaryCmeByYearProjectTypeCust(@PathVariable("tahun") tahun:Long, @PathVariable("site_type_id") site_type_id: Long): MutableList<CmeSummaryYearProjectTypeCustData>{
        val data = repositoryCmeSummaryYearProjectTypeCust.getCmeSummaryYearProjectTypeCust(tahun, site_type_id)
        val cmeSummaryYearProjectTypeCustData:MutableList<CmeSummaryYearProjectTypeCustData> = mutableListOf()

        cmeProjectDetailDataRepository = repositoryCmeProjectDetail.getCmeProjectDetailRepository(tahun, site_type_id)

        data.forEach {
            val percentage= if (it.nilai_po == 0.toLong()) 0.toFloat() else it.nilai_invoice.toFloat().div(it.nilai_po.toFloat())
            val remainingInvoice = it.nilai_po - it.nilai_invoice
            val percentageRealization = if (it.nilai_budget == 0.toLong()) 0.toFloat() else it.realisasi_budget.toFloat().div(it.nilai_budget.toFloat())
            val profitLoss = it.nilai_invoice - it.realisasi_budget
            val percentageProfitRealization = if (it.realisasi_budget == 0.toLong()) 0.toFloat() else profitLoss.toFloat().div(it.realisasi_budget)
            val percentageProfitPO = if(it.nilai_po == 0.toLong()) 0.toFloat() else profitLoss.toFloat().div(it.nilai_po)
            cmeSummaryYearProjectTypeCustData.add(CmeSummaryYearProjectTypeCustData(it.id, it.year_project, it.jumlah_site, it.project_type,
                    it.site_cancel, it.nilai_po, it.nilai_invoice, it.nilai_budget, it.realisasi_budget, it.estimate_po,
                    it.site_type_id, it.customer, it.customer_id, percentage, remainingInvoice, percentageRealization,
                    profitLoss, percentageProfitRealization, percentageProfitPO, getCmeProjectDetail(tahun, site_type_id, it.customer_id)))
        }
        return cmeSummaryYearProjectTypeCustData
    }

    fun getCmeProjectDetail(tahun:Long, site_type_id: Long, customer_id: Long?): MutableList<CmeYearProjectTypeCustProjectDetailData>{
        val data = cmeProjectDetailDataRepository
        val cmeYearProjectTypeCustProjectDetailData: MutableList<CmeYearProjectTypeCustProjectDetailData> = mutableListOf()
        data.forEach {
            if((it.year_project == tahun) and (it.site_type_id == site_type_id) and (it.customer_id == customer_id))
                cmeYearProjectTypeCustProjectDetailData.add(CmeYearProjectTypeCustProjectDetailData(it.id,
                        it.name, it.year_project, it.project_type, it.project_id, it.nilai_po, it.no_po, it.nilai_invoice,
                        it.nilai_budget, it.realisasi_budget, it.estimate_po, it.customer, it.customer_id, it.site_type_id))
        }
        return cmeYearProjectTypeCustProjectDetailData
    }
}