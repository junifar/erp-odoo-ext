package com.prasetia.erp.controller.api.preventive

import com.prasetia.erp.model.preventive.PreventiveCustomer
import com.prasetia.erp.pojo.PreventiveCustomerGroup
import com.prasetia.erp.pojo.PreventiveCustomerGroupDetail
import com.prasetia.erp.pojo.PreventiveCustomerYear
import com.prasetia.erp.repository.preventive.PreventiveCustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PreventiveController{

    @Autowired
    lateinit var repository: PreventiveCustomerRepository

//    @Autowired
//    lateinit var repositorybyCustomerYearArea: PreventiveCustomerDetail

//    @RequestMapping("/api/preventive_customer1")
//    fun getAllData()= repository.getAllData()

//    @RequestMapping("/api/preventive_by_customer_year_area/{customer_id}/{tahun}/{area_id}")
//    fun getDataByCustomerYearArea(@PathVariable("customer_id") customer_id: Long, @PathVariable("tahun") tahun: String,
//                                  @PathVariable("area_id") area_id: String)

    @RequestMapping("/api/preventive_customer")
    fun getAllData(): MutableList<PreventiveCustomerYear> {
        val data = repository.getAllData()
        var yearGroup: MutableList<PreventiveCustomerYear> = mutableListOf()
        var id:Long = 1
        data.forEach {
            item->
            var found = false
            yearGroup.forEach {
                itemDetail ->
                if(itemDetail.tahun == item.tahun) found = true
            }
            if (!found) yearGroup.add(PreventiveCustomerYear(id++, item.tahun, getCustomerItemDetail(item.tahun, data)))
        }
        return  yearGroup
    }

    fun getCustomerItemDetail(tahun: String, data:Iterable<PreventiveCustomer>):MutableList<PreventiveCustomerGroup>{
        val customerItemDetail:MutableList<PreventiveCustomerGroup> = mutableListOf()
        var id:Long = 1
        data.forEach {
            item->
            if(item.tahun == tahun){
                var found = false
                customerItemDetail.forEach {
                    itemDetail ->
                    if (itemDetail.customer_id == item.customer_id) found = true
                }
                if (!found) customerItemDetail.add(PreventiveCustomerGroup(id++, item.customer_name, item.customer_id, getCustomerSubItemDetail(tahun, item.customer_id, data)))
            }
        }
        return customerItemDetail
    }
    fun getCustomerSubItemDetail(tahun: String, customer_id: Long?, data: Iterable<PreventiveCustomer>):MutableList<PreventiveCustomerGroupDetail>{
        val customerSubItemDetail: MutableList<PreventiveCustomerGroupDetail> = mutableListOf()
        data.forEach {
            item->
            if((item.tahun == tahun) and (item.customer_id == customer_id)){
                customerSubItemDetail.add(PreventiveCustomerGroupDetail(item.id, item.area, item.nilai_po, item.nilai_penagihan, item.nilai_budget, item.realisasi_budget, item.laba_rugi, item.persent_penagihan, item.persent_budget, item.persent_laba_rugi, item.area_id))
            }
        }
        return customerSubItemDetail
    }
}