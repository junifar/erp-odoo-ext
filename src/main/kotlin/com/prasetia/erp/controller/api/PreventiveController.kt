package com.prasetia.erp.controller.api

import com.prasetia.erp.pojo.PreventiveCustomerMonth
import com.prasetia.erp.pojo.PreventiveCustomerYear
import com.prasetia.erp.pojo.PreventiveCustomerYearDetail
import com.prasetia.erp.repository.PreventiveCustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PreventiveController{

    @Autowired
    lateinit var repository: PreventiveCustomerRepository

    @RequestMapping("/api/preventive_customer")
    fun getAllData(): MutableList<PreventiveCustomerYear> {
        var preventiveCustomerYear = mutableListOf<PreventiveCustomerYear>()
        val data = repository.getAllData()
        data.forEach {
            items->
            var check = false
            preventiveCustomerYear.forEach {if (it.tahun == items.tahun) check=true}
            if (!check) preventiveCustomerYear.add(PreventiveCustomerYear(items.tahun, null))

            preventiveCustomerYear.forEach {
                val listDetail = mutableListOf<PreventiveCustomerMonth>()
                data.forEach{
                    detail ->
                    if(it.tahun == detail.tahun){
                        var check = false
                        listDetail.forEach { detailCheck -> if (detail.bulan == detailCheck.month) check=true }
                        if (!check) listDetail.add(PreventiveCustomerMonth(detail.bulan, null))
                    }
                }
                it.detail = listDetail
            }

            preventiveCustomerYear.forEach {
                it.detail?.forEach {
                    it_detail->
                    val listDetail = mutableListOf<PreventiveCustomerYearDetail>()
                    data.forEach{ detail -> if ((it.tahun == detail.tahun) and (it_detail.month == detail.bulan)) listDetail.add(PreventiveCustomerYearDetail(detail.customer_name, detail.customer_id, detail.nilai_po)) }
                    it_detail.detail = listDetail
                }
            }
        }
        return preventiveCustomerYear
    }
}