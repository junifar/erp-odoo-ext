package com.prasetia.erp.controller.api.preventive

import com.prasetia.erp.model.preventive.PreventiveSaleOrder
import com.prasetia.erp.pojo.preventive.PreventiveClientOrderRef
import com.prasetia.erp.pojo.preventive.PreventiveCustomerDetailHeader
import com.prasetia.erp.repository.preventive.PreventiveCustomerDetailRepository
import com.prasetia.erp.repository.preventive.PreventiveSaleOrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PreventiveDetailController{

    @Autowired
    lateinit var repository: PreventiveCustomerDetailRepository

    @Autowired
    lateinit var repositoryPreventiveSaleOrder: PreventiveSaleOrderRepository

    @RequestMapping("/api/preventive_by_customer_year_area/{customer_id}/{tahun}/{area_id}")
    fun getDataByCustomerYearArea(@PathVariable("customer_id") customer_id: Int,
                                  @PathVariable("tahun") tahun: String, @PathVariable("area_id") area_id:String): MutableList<PreventiveCustomerDetailHeader> {
        println(area_id)
        val data = if (area_id != "null") repository.getDataByCustomerYearArea(customer_id, tahun, area_id.toInt()) else repository.getDataByCustomerYearArea(customer_id, tahun)
        val dataPreventiveSaleOrder = if (area_id != "null") repositoryPreventiveSaleOrder.getPreventiveSaleOrder(customer_id, tahun, area_id.toInt()) else repositoryPreventiveSaleOrder.getPreventiveSaleOrderNullArea(customer_id, tahun)
        var headerGroup: MutableList<PreventiveCustomerDetailHeader> = mutableListOf()
        var id:Long=1
        data.forEach{
            item->
            headerGroup.add(PreventiveCustomerDetailHeader(id++, item.customer_id, item.customer_name, item.area, item.area_id, item.tahun.toString(), getPreventiveClientOrderRef(customer_id, tahun, area_id, dataPreventiveSaleOrder)))
        }
        return headerGroup
    }

    fun getPreventiveClientOrderRef(customer_id: Int, tahun: String, area_id: String, data:Iterable<PreventiveSaleOrder>): MutableList<PreventiveClientOrderRef>{
        val preventiveClientOrderRef:MutableList<PreventiveClientOrderRef> = mutableListOf()
        data.forEach {
            item ->
            if((item.tahun.toString() == tahun) and (item.customer_id == customer_id.toLong()) and (item.area_id.toString() == area_id)){
                var found = false
                var id:Long = 1
                preventiveClientOrderRef.forEach {
                    itemDetail -> if (itemDetail.client_order_ref == item.client_order_ref) found = true
                }
                if (!found) preventiveClientOrderRef.add(PreventiveClientOrderRef(id++, item.client_order_ref, getPreventiveSaleOrder(customer_id, tahun, area_id, item.client_order_ref, data)))
            }
        }
        return  preventiveClientOrderRef
    }

    fun getPreventiveSaleOrder(customer_id: Int, tahun: String, area_id: String, clientOrderRef: String, data:Iterable<PreventiveSaleOrder>): MutableList<com.prasetia.erp.pojo.preventive.PreventiveSaleOrder>{
        val preventiveSaleOrder:MutableList<com.prasetia.erp.pojo.preventive.PreventiveSaleOrder> = mutableListOf()
        data.forEach {
            item->
            if((item.tahun.toString() == tahun) and (item.customer_id == customer_id.toLong()) and (item.area_id.toString() == area_id) and (item.client_order_ref == clientOrderRef)){
                var found = false
                var nilaiPO:Long = 0
                preventiveSaleOrder.forEach {
                    itemDetail ->
                    if (itemDetail.bulan == item.bulan){
                        found = true
                    }
                }
//                if (!found) preventiveSaleOrder.add(com.prasetia.erp.pojo.preventive.PreventiveSaleOrder(item.id, item.nilai_po, item.bulan))
                if (!found) preventiveSaleOrder.add(com.prasetia.erp.pojo.preventive.PreventiveSaleOrder(item.bulan, getPreventiveSaleOrderNilaiPO(customer_id, tahun, area_id, clientOrderRef, item.bulan, data)))
            }
        }
        return preventiveSaleOrder
    }

    fun getPreventiveSaleOrderNilaiPO(customer_id: Int, tahun: String, area_id: String, clientOrderRef: String, bulan: Long, data:Iterable<PreventiveSaleOrder>): Long{
        var nilai_po:Long = 0
        data.forEach {
            item->
            if((item.tahun.toString() == tahun) and
                    (item.customer_id == customer_id.toLong()) and
                    (item.area_id.toString() == area_id) and
                    (item.client_order_ref == clientOrderRef) and
                    (item.bulan == bulan)){
                if(item.nilai_po != null){
                    nilai_po += item.nilai_po
                }
            }
        }
        return nilai_po
    }
}