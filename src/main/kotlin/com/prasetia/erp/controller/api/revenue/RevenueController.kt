package com.prasetia.erp.controller.api.revenue

import com.prasetia.erp.model.revenue.RevenueYearDetail
import com.prasetia.erp.pojo.revenue.RevenueYearData
import com.prasetia.erp.pojo.revenue.RevenueYearDetailData
import com.prasetia.erp.pojo.revenue.RevenueYearHeaderData
import com.prasetia.erp.repository.revenue.RevenueYearDetailRepository
import com.prasetia.erp.repository.revenue.RevenueYearRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RevenueController{

    @Autowired
    lateinit var repository: RevenueYearRepository

    @Autowired
    lateinit var repositoryRevenueYearDetail: RevenueYearDetailRepository

    @RequestMapping("/api/revenue")
    fun getAllData():MutableList<RevenueYearData>{
        val data = repository.getRevenueYear()
        val revenueYearData:MutableList<RevenueYearData> = mutableListOf()
        data.forEach {
            revenueYearData.add(RevenueYearData(it.id, it.tahun, it.nilai_po, it.invoiced, it.paid, it.total, it.target))
        }
        return revenueYearData
    }

    fun getDetailDataByYear(data: Iterable<RevenueYearDetail>): MutableList<RevenueYearDetailData>{
        val revenueYearDetailData:MutableList<RevenueYearDetailData> = mutableListOf()
        data.forEach {
            revenueYearDetailData.add(RevenueYearDetailData(it.id, it.customer_id,
                    it.code, it.jumlah_site,
                    it.tahun, it.nilai_po,
                    it.invoiced, it.paid,
                    it.total, it.target))
        }
        return revenueYearDetailData
    }

    @RequestMapping("/api/revenue/{tahun}")
    fun getDataByYear(@PathVariable("tahun") tahun:Long):MutableList<RevenueYearHeaderData>{
        val data = repository.getRevenueYear().filter { it.tahun == tahun.toInt() }
        val revenueYearDetailRepository = repositoryRevenueYearDetail.getRevenueYearDetailByYear(tahun)

        val revenueYearHeaderData:MutableList<RevenueYearHeaderData> = mutableListOf()

        data.forEach {
            revenueYearHeaderData.add(RevenueYearHeaderData(it.id, it.tahun,
                    it.nilai_po, it.invoiced,
                    it.paid, it.total,
                    it.target, getDetailDataByYear(revenueYearDetailRepository)))
        }
        return revenueYearHeaderData
    }
}
