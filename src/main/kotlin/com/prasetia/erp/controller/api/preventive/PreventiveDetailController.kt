package com.prasetia.erp.controller.api.preventive

import com.prasetia.erp.model.preventive.PreventiveSaleOrder
import com.prasetia.erp.pojo.preventive.PreventiveBudget
import com.prasetia.erp.pojo.preventive.PreventiveCustomerDetailHeader
import com.prasetia.erp.pojo.preventive.PreventiveInvoice
import com.prasetia.erp.pojo.preventive.PreventiveRealisasiBudget
import com.prasetia.erp.repository.preventive.*
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

    @Autowired
    lateinit var repositoryPreventiveInvoice: PreventiveInvoiceRepository

    @Autowired
    lateinit var repositoryBudget: PreventiveBudgetRepository

    @Autowired
    lateinit var repositoryRealisasiBudget: PreventiveBudgetRealisasiRepository

    @RequestMapping("/api/preventive_by_customer_year_area/{customer_id}/{tahun}/{area_id}")
    fun getDataByCustomerYearArea(@PathVariable("customer_id") customer_id: Int,
                                  @PathVariable("tahun") tahun: String, @PathVariable("area_id") area_id:String): MutableList<PreventiveCustomerDetailHeader> {

        val data = if (area_id != "null") repository.getDataByCustomerYearArea(customer_id, tahun, area_id.toInt()) else repository.getDataByCustomerYearArea(customer_id, tahun)
        val dataPreventiveSaleOrder = if (area_id != "null") repositoryPreventiveSaleOrder.getPreventiveSaleOrder(customer_id, tahun, area_id.toInt()) else repositoryPreventiveSaleOrder.getPreventiveSaleOrderNullArea(customer_id, tahun)
        val dataPreventiveInvoice = if (area_id != "null") repositoryPreventiveInvoice.getPreventiveInvoice(customer_id, tahun, area_id.toInt()) else repositoryPreventiveInvoice.getPreventiveInvoiceNullArea(customer_id, tahun)
        val dataPreventiveBudget = if (area_id != "null") repositoryBudget.getPreventiveBudget(customer_id, tahun, area_id.toInt()) else repositoryBudget.getPreventiveBudgetNullArea(customer_id, tahun)
        val dataPreventiveRealisasiBudget = if (area_id != "null") repositoryRealisasiBudget.getPreventiveRealisasiBudget(customer_id, tahun, area_id.toInt()) else repositoryRealisasiBudget.getPreventiveRealisasiBudgetNullArea(customer_id, tahun)

        var headerGroup: MutableList<PreventiveCustomerDetailHeader> = mutableListOf()
        var id:Long=1
        data.forEach{
            item->
            headerGroup.add(PreventiveCustomerDetailHeader(id++, item.customer_id,
                    item.customer_name, item.area, item.area_id, item.tahun.toString(),
                    getPreventiveSaleOrder(customer_id, tahun, area_id, dataPreventiveSaleOrder),
                    getPreventiveInvoice(customer_id, tahun, area_id, dataPreventiveInvoice),
                    getPreventiveBudget(customer_id, tahun, area_id, dataPreventiveBudget),
                    getPreventiveRealisasiBudget(customer_id, tahun, area_id, dataPreventiveRealisasiBudget)))
        }
        return headerGroup
    }

    fun getPreventiveSaleOrder(customer_id: Int, tahun: String, area_id: String, data:Iterable<PreventiveSaleOrder>): MutableList<com.prasetia.erp.pojo.preventive.PreventiveSaleOrder>{
        val preventiveSaleOrder:MutableList<com.prasetia.erp.pojo.preventive.PreventiveSaleOrder> = mutableListOf()
        data.forEach {
            item->
            if((item.tahun.toString() == tahun) and (item.customer_id == customer_id.toLong()) and (item.area_id.toString() == area_id)){
                var found = false
                preventiveSaleOrder.forEach {
                    itemDetail ->
                    if (itemDetail.client_order_ref == item.client_order_ref) found = true
                }
                if (!found)
                {
                    val i = getPreventiveSaleOrderNilaiPO(customer_id, tahun, area_id, item.client_order_ref, 1, data)
                    val ii = getPreventiveSaleOrderNilaiPO(customer_id, tahun, area_id, item.client_order_ref, 2, data)
                    val iii = getPreventiveSaleOrderNilaiPO(customer_id, tahun, area_id, item.client_order_ref, 3, data)
                    val iv = getPreventiveSaleOrderNilaiPO(customer_id, tahun, area_id, item.client_order_ref, 4, data)
                    val v = getPreventiveSaleOrderNilaiPO(customer_id, tahun, area_id, item.client_order_ref, 5, data)
                    val vi = getPreventiveSaleOrderNilaiPO(customer_id, tahun, area_id, item.client_order_ref, 6, data)
                    val vii = getPreventiveSaleOrderNilaiPO(customer_id, tahun, area_id, item.client_order_ref, 7, data)
                    val viii = getPreventiveSaleOrderNilaiPO(customer_id, tahun, area_id, item.client_order_ref, 8, data)
                    val ix = getPreventiveSaleOrderNilaiPO(customer_id, tahun, area_id, item.client_order_ref, 9, data)
                    val x = getPreventiveSaleOrderNilaiPO(customer_id, tahun, area_id, item.client_order_ref, 10, data)
                    val xi = getPreventiveSaleOrderNilaiPO(customer_id, tahun, area_id, item.client_order_ref, 11, data)
                    val xii = getPreventiveSaleOrderNilaiPO(customer_id, tahun, area_id, item.client_order_ref, 12, data)
                    val total = i + ii + iii + iv + v + vi + vii + vii + ix + x + xi + xii

                    preventiveSaleOrder.add(com.prasetia.erp.pojo.preventive.PreventiveSaleOrder(
                            item.id,
                            item.client_order_ref,
                            i, ii, iii,
                            iv, v, vi,
                            vii, viii, ix,
                            x, xi, xii,
                            total))
                }

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

    fun getPreventiveSaleOrderNilaiInvoice(customer_id: Int, tahun: String, area_id: String, clientOrderRef: String, bulan: Long, data:Iterable<com.prasetia.erp.model.preventive.PreventiveInvoice>): Long{
        var nilai_po:Long = 0
        data.forEach {
            item->
            if((item.tahun.toString() == tahun) and
                    (item.customer_id == customer_id.toLong()) and
                    (item.area_id.toString() == area_id) and
                    (item.client_order_ref == clientOrderRef) and
                    (item.bulan == bulan)){
                if(item.nilai_invoice != null){
                    nilai_po += item.nilai_invoice
                }
            }
        }
        return nilai_po
    }

    fun getPreventiveInvoice(customer_id: Int, tahun: String, area_id: String, data:Iterable<com.prasetia.erp.model.preventive.PreventiveInvoice>): MutableList<PreventiveInvoice>{
        val preventiveInvoice:MutableList<PreventiveInvoice> = mutableListOf()
        data.forEach {
            item->
            if((item.tahun.toString() == tahun) and (item.customer_id == customer_id.toLong()) and (item.area_id.toString() == area_id)){
                var found = false
                preventiveInvoice.forEach {
                    itemDetail ->
                    if (itemDetail.client_order_ref == item.client_order_ref) found = true
                }
                if (!found){
                    val i = getPreventiveSaleOrderNilaiInvoice(customer_id, tahun, area_id, item.client_order_ref, 1, data)
                    val ii = getPreventiveSaleOrderNilaiInvoice(customer_id, tahun, area_id, item.client_order_ref, 2, data)
                    val iii = getPreventiveSaleOrderNilaiInvoice(customer_id, tahun, area_id, item.client_order_ref, 3, data)
                    val iv = getPreventiveSaleOrderNilaiInvoice(customer_id, tahun, area_id, item.client_order_ref, 4, data)
                    val v = getPreventiveSaleOrderNilaiInvoice(customer_id, tahun, area_id, item.client_order_ref, 5, data)
                    val vi = getPreventiveSaleOrderNilaiInvoice(customer_id, tahun, area_id, item.client_order_ref, 6, data)
                    val vii = getPreventiveSaleOrderNilaiInvoice(customer_id, tahun, area_id, item.client_order_ref, 7, data)
                    val viii = getPreventiveSaleOrderNilaiInvoice(customer_id, tahun, area_id, item.client_order_ref, 8, data)
                    val ix = getPreventiveSaleOrderNilaiInvoice(customer_id, tahun, area_id, item.client_order_ref, 9, data)
                    val x = getPreventiveSaleOrderNilaiInvoice(customer_id, tahun, area_id, item.client_order_ref, 10, data)
                    val xi = getPreventiveSaleOrderNilaiInvoice(customer_id, tahun, area_id, item.client_order_ref, 11, data)
                    val xii = getPreventiveSaleOrderNilaiInvoice(customer_id, tahun, area_id, item.client_order_ref, 12, data)
                    val total = i + ii + iii + iv + v + vi + vii + viii + ix + x + xi + xii
                    preventiveInvoice.add(PreventiveInvoice(item.id, item.client_order_ref,
                            i, ii, iii,
                            iv, v, vi,
                            vii, viii, ix,
                            x, xi, xii, total))
                }
            }
        }
        return preventiveInvoice
    }

    fun getPreventiveNilaiBudget(customer_id: Int, tahun: String, area_id: String, name: String, bulan: Long, data:Iterable<com.prasetia.erp.model.preventive.PreventiveBudget>): Long{
        var nilai_budget:Long = 0
        data.forEach {
            item->
            if((item.tahun.toString() == tahun) and
                    (item.customer_id == customer_id.toLong()) and
                    (item.area_id.toString() == area_id) and
                    (item.name == name) and
                    (item.bulan == bulan)){
                if(item.nilai_budget != null){
                    nilai_budget += item.nilai_budget
                }
            }
        }
        return nilai_budget
    }

    fun getPreventiveBudget(customer_id: Int, tahun: String, area_id: String, data:Iterable<com.prasetia.erp.model.preventive.PreventiveBudget>):MutableList<PreventiveBudget>{
        val preventiveBudget:MutableList<PreventiveBudget> = mutableListOf()
        data.forEach {
            item->
            if((item.tahun.toString() == tahun) and (item.customer_id == customer_id.toLong()) and (item.area_id.toString() == area_id)){
                var found = false
                preventiveBudget.forEach {
                    itemDetail ->
                    if(itemDetail.name == item.name) found = true
                }
                if(!found){
                    val i = getPreventiveNilaiBudget(customer_id, tahun, area_id, item.name, 1, data)
                    val ii = getPreventiveNilaiBudget(customer_id, tahun, area_id, item.name, 2, data)
                    val iii = getPreventiveNilaiBudget(customer_id, tahun, area_id, item.name, 3, data)
                    val iv = getPreventiveNilaiBudget(customer_id, tahun, area_id, item.name, 4, data)
                    val v = getPreventiveNilaiBudget(customer_id, tahun, area_id, item.name, 5, data)
                    val vi = getPreventiveNilaiBudget(customer_id, tahun, area_id, item.name, 6, data)
                    val vii = getPreventiveNilaiBudget(customer_id, tahun, area_id, item.name, 7, data)
                    val viii = getPreventiveNilaiBudget(customer_id, tahun, area_id, item.name, 8, data)
                    val ix = getPreventiveNilaiBudget(customer_id, tahun, area_id, item.name, 9, data)
                    val x = getPreventiveNilaiBudget(customer_id, tahun, area_id, item.name, 10, data)
                    val xi = getPreventiveNilaiBudget(customer_id, tahun, area_id, item.name, 11, data)
                    val xii = getPreventiveNilaiBudget(customer_id, tahun, area_id, item.name, 12, data)
                    val total = i + ii + iii + iv + v + vi + vii + viii + ix + x + xi + xii

                    preventiveBudget.add(PreventiveBudget(item.id, item.name,
                            i, ii, iii, iv,
                            v, vi, vii, viii,
                            ix, x, xi, xii, total))
                }
            }
        }
        return preventiveBudget
    }

    fun getPreventiveNilaiRealisasiBudget(customer_id: Int, tahun: String, area_id: String, name: String, bulan: Long, data:Iterable<com.prasetia.erp.model.preventive.PreventiveRealisasiBudget>): Long{
        var nilai_budget:Long = 0
        data.forEach {
            item->
            if((item.tahun.toString() == tahun) and
                    (item.customer_id == customer_id.toLong()) and
                    (item.area_id.toString() == area_id) and
                    (item.name == name) and
                    (item.bulan == bulan)){
                if(item.realisasi_budget != null){
                    nilai_budget += item.realisasi_budget
                }
            }
        }
        return nilai_budget
    }

    fun getPreventiveRealisasiBudget(customer_id: Int, tahun: String, area_id: String, data:Iterable<com.prasetia.erp.model.preventive.PreventiveRealisasiBudget>):MutableList<PreventiveRealisasiBudget>{
        val preventiveRealisasiBudget:MutableList<PreventiveRealisasiBudget> = mutableListOf()
        data.forEach {
            item->
            if((item.tahun.toString() == tahun) and (item.customer_id == customer_id.toLong()) and (item.area_id.toString() == area_id)){
                var found = false
                preventiveRealisasiBudget.forEach {
                    itemDetail ->
                    if(itemDetail.name == item.name) found = true
                }
                if(!found){
                    val i = getPreventiveNilaiRealisasiBudget(customer_id, tahun, area_id, item.name, 1, data)
                    val ii = getPreventiveNilaiRealisasiBudget(customer_id, tahun, area_id, item.name, 2, data)
                    val iii = getPreventiveNilaiRealisasiBudget(customer_id, tahun, area_id, item.name, 3, data)
                    val iv = getPreventiveNilaiRealisasiBudget(customer_id, tahun, area_id, item.name, 4, data)
                    val v = getPreventiveNilaiRealisasiBudget(customer_id, tahun, area_id, item.name, 5, data)
                    val vi = getPreventiveNilaiRealisasiBudget(customer_id, tahun, area_id, item.name, 6, data)
                    val vii = getPreventiveNilaiRealisasiBudget(customer_id, tahun, area_id, item.name, 7, data)
                    val viii = getPreventiveNilaiRealisasiBudget(customer_id, tahun, area_id, item.name, 8, data)
                    val ix = getPreventiveNilaiRealisasiBudget(customer_id, tahun, area_id, item.name, 9, data)
                    val x = getPreventiveNilaiRealisasiBudget(customer_id, tahun, area_id, item.name, 10, data)
                    val xi = getPreventiveNilaiRealisasiBudget(customer_id, tahun, area_id, item.name, 11, data)
                    val xii = getPreventiveNilaiRealisasiBudget(customer_id, tahun, area_id, item.name, 12, data)
                    val total = i + ii + iii + iv + v + vi + vii + viii + ix + x + xi + xii

                    preventiveRealisasiBudget.add(PreventiveRealisasiBudget(item.id, item.name,
                            i, ii, iii, iv,
                            v, vi, vii, viii,
                            ix, x, xi, xii, total))
                }
            }
        }
        return preventiveRealisasiBudget
    }
}