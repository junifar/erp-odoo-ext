package com.prasetia.erp.controller.api.preventive

import com.prasetia.erp.pojo.preventive.preventiveCustomerDetailHeader
import com.prasetia.erp.repository.preventive.PreventiveCustomerDetailRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PreventiveDetailController{

    @Autowired
    lateinit var repository: PreventiveCustomerDetailRepository

//    @RequestMapping("/api/preventive_by_customer_year_area/{customer_id}/{tahun}/{area_id}")
//    fun getDataByCustomerYearArea(@PathVariable("customer_id") customer_id: Int,
//                                  @PathVariable("tahun") tahun: String, @PathVariable("area_id") area_id:String) = if (area_id != "null")
//        repository.getDataByCustomerYearArea(customer_id, tahun, area_id.toInt()) else repository.getDataByCustomerYearArea(customer_id, tahun)

    @RequestMapping("/api/preventive_by_customer_year_area/{customer_id}/{tahun}/{area_id}")
    fun getDataByCustomerYearArea(@PathVariable("customer_id") customer_id: Int,
                                  @PathVariable("tahun") tahun: String, @PathVariable("area_id") area_id:String): MutableList<preventiveCustomerDetailHeader> {
        val data = if (area_id != "null") repository.getDataByCustomerYearArea(customer_id, tahun, area_id.toInt()) else repository.getDataByCustomerYearArea(customer_id, tahun)
        var headerGroup: MutableList<preventiveCustomerDetailHeader> = mutableListOf()
        var id:Long=1
        data.forEach{
            item->
            var found = false
            headerGroup.forEach {
                itemDetail->
                if((itemDetail.area_id == item.area_id) and (item.customer_id == itemDetail.customer_id) and (itemDetail.tahun == item.tahun.toString())) found=true
            }
            if(!found) headerGroup.add(preventiveCustomerDetailHeader(id++, item.customer_id, item.customer_name, item.area, item.area_id, item.tahun.toString()))
        }
        return headerGroup
    }
}