package com.prasetia.erp.controller.api.preventive

import com.prasetia.erp.model.preventive.PreventiveSaleOrder
import com.prasetia.erp.model.preventive.PreventiveSaleOrderInvoice
import com.prasetia.erp.pojo.preventive.*
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

    @Autowired
    lateinit var repositorySaleOrderInvoice: PreventiveSaleOrderInvoiceRepository

    fun getPreventivePOInvoiceSaleOrder(data:Iterable<PreventiveSaleOrderInvoice>, clientOrderRef: String, month:Int): Long{
        var value:Long = 0
        data.forEach {
            if ((it.client_order_ref == clientOrderRef) and (it.bulan_po == month.toLong())) value += it.nilai_invoice
        }
        return value
    }

    fun getPreventivePOInvoiceSaleOrderVal(data:Iterable<PreventiveSaleOrderInvoice>, clientOrderRef: String, month:Int): String{
        var value:String = ""
        data.forEach {
            if ((it.client_order_ref == clientOrderRef) and (it.bulan_po == month.toLong())) value += " ${it.name} "
        }
        return value
    }

    fun getPreventivePOInvoiceSaleOrderState(data:Iterable<PreventiveSaleOrderInvoice>, clientOrderRef: String, month:Int): String{
        var value:String = ""
        data.forEach {
            if ((it.client_order_ref == clientOrderRef) and (it.bulan_po == month.toLong())) value += " ${it.state} "
        }
        return value
    }

    fun getPreventivePOInvoice(clientOrderRef: String, data:Iterable<PreventiveSaleOrderInvoice>):MutableList<com.prasetia.erp.pojo.preventive.PreventiveSaleOrderInvoice>{
        val preventiveSaleOrderInvoice:MutableList<com.prasetia.erp.pojo.preventive.PreventiveSaleOrderInvoice> = mutableListOf()
        data.forEach {
            item->
            if(item.client_order_ref == clientOrderRef){
                var found = false

                preventiveSaleOrderInvoice.forEach {
                    if(it.client_order_ref == clientOrderRef) found = true
                }

                if(!found){
                    val i = getPreventivePOInvoiceSaleOrder(data, clientOrderRef, 1)
                    val ii = getPreventivePOInvoiceSaleOrder(data, clientOrderRef, 2)
                    val iii = getPreventivePOInvoiceSaleOrder(data, clientOrderRef, 3)
                    val iv = getPreventivePOInvoiceSaleOrder(data, clientOrderRef, 4)
                    val v = getPreventivePOInvoiceSaleOrder(data, clientOrderRef, 5)
                    val vi = getPreventivePOInvoiceSaleOrder(data, clientOrderRef, 6)
                    val vii = getPreventivePOInvoiceSaleOrder(data, clientOrderRef, 7)
                    val viii = getPreventivePOInvoiceSaleOrder(data, clientOrderRef, 8)
                    val ix = getPreventivePOInvoiceSaleOrder(data, clientOrderRef, 9)
                    val x = getPreventivePOInvoiceSaleOrder(data, clientOrderRef, 10)
                    val xi = getPreventivePOInvoiceSaleOrder(data, clientOrderRef, 11)
                    val xii = getPreventivePOInvoiceSaleOrder(data, clientOrderRef, 12)
                    val total = i + ii + iii + iv + v + vi + vii + viii + ix + x + xi + xii
                    val i_val = getPreventivePOInvoiceSaleOrderVal(data, clientOrderRef, 1)
                    val ii_val = getPreventivePOInvoiceSaleOrderVal(data, clientOrderRef, 2)
                    val iii_val = getPreventivePOInvoiceSaleOrderVal(data, clientOrderRef, 3)
                    val iv_val = getPreventivePOInvoiceSaleOrderVal(data, clientOrderRef, 4)
                    val v_val = getPreventivePOInvoiceSaleOrderVal(data, clientOrderRef, 5)
                    val vi_val = getPreventivePOInvoiceSaleOrderVal(data, clientOrderRef, 6)
                    val vii_val = getPreventivePOInvoiceSaleOrderVal(data, clientOrderRef, 7)
                    val viii_val = getPreventivePOInvoiceSaleOrderVal(data, clientOrderRef, 8)
                    val ix_val = getPreventivePOInvoiceSaleOrderVal(data, clientOrderRef, 9)
                    val x_val = getPreventivePOInvoiceSaleOrderVal(data, clientOrderRef, 10)
                    val xi_val = getPreventivePOInvoiceSaleOrderVal(data, clientOrderRef, 11)
                    val xii_val = getPreventivePOInvoiceSaleOrderVal(data, clientOrderRef, 12)

                    val i_state = getPreventivePOInvoiceSaleOrderState(data, clientOrderRef, 1)
                    val ii_state = getPreventivePOInvoiceSaleOrderState(data, clientOrderRef, 2)
                    val iii_state = getPreventivePOInvoiceSaleOrderState(data, clientOrderRef, 3)
                    val iv_state = getPreventivePOInvoiceSaleOrderState(data, clientOrderRef, 4)
                    val v_state = getPreventivePOInvoiceSaleOrderState(data, clientOrderRef, 5)
                    val vi_state = getPreventivePOInvoiceSaleOrderState(data, clientOrderRef, 6)
                    val vii_state = getPreventivePOInvoiceSaleOrderState(data, clientOrderRef, 7)
                    val viii_state = getPreventivePOInvoiceSaleOrderState(data, clientOrderRef, 8)
                    val ix_state = getPreventivePOInvoiceSaleOrderState(data, clientOrderRef, 9)
                    val x_state = getPreventivePOInvoiceSaleOrderState(data, clientOrderRef, 10)
                    val xi_state = getPreventivePOInvoiceSaleOrderState(data, clientOrderRef, 11)
                    val xii_state = getPreventivePOInvoiceSaleOrderState(data, clientOrderRef, 12)

                    preventiveSaleOrderInvoice.add(com.prasetia.erp.pojo.preventive.PreventiveSaleOrderInvoice(item.id, item.client_order_ref, item.state, i, ii, iii,
                            iv, v, vi, vii, viii, ix, x, xi, xii, i_val, ii_val, iii_val, iv_val,
                            v_val, vi_val, vii_val, viii_val, ix_val, x_val, xi_val, xii_val, i_state, ii_state, iii_state, iv_state,
                            v_state, vi_state, vii_state, viii_state, ix_state, x_state, xi_state, xii_state, total))
                }
            }
        }
        return preventiveSaleOrderInvoice
    }

    @RequestMapping("/api/preventive_by_customer_year_area/{customer_id}/{tahun}/{area_id}")
    fun getDataByCustomerYearArea(@PathVariable("customer_id") customer_id: Int,
                                  @PathVariable("tahun") tahun: String, @PathVariable("area_id") area_id:String): MutableList<PreventiveCustomerDetailHeader> {

        val data = if (area_id != "null") repository.getDataByCustomerYearArea(customer_id, tahun, area_id.toInt()) else repository.getDataByCustomerYearArea(customer_id, tahun)
        val dataPreventiveSaleOrder = if (area_id != "null") repositoryPreventiveSaleOrder.getPreventiveSaleOrder(customer_id, tahun, area_id.toInt()) else repositoryPreventiveSaleOrder.getPreventiveSaleOrderNullArea(customer_id, tahun)
        val dataPreventiveInvoice = if (area_id != "null") repositoryPreventiveInvoice.getPreventiveInvoice(customer_id, tahun, area_id.toInt()) else repositoryPreventiveInvoice.getPreventiveInvoiceNullArea(customer_id, tahun)
        val dataPreventiveBudget = if (area_id != "null") repositoryBudget.getPreventiveBudget(customer_id, tahun, area_id.toInt()) else repositoryBudget.getPreventiveBudgetNullArea(customer_id, tahun)
        val dataPreventiveRealisasiBudget = if (area_id != "null") repositoryRealisasiBudget.getPreventiveRealisasiBudget(customer_id, tahun, area_id.toInt()) else repositoryRealisasiBudget.getPreventiveRealisasiBudgetNullArea(customer_id, tahun)
        val dataPreventiveSaleOrderInvoice = if(area_id != "null") repositorySaleOrderInvoice.getPreventiveSaleOrderInvoice(customer_id, tahun, area_id.toInt()) else repositorySaleOrderInvoice.getPreventiveSaleOrderInvoiceNullArea(customer_id, tahun)

        var headerGroup: MutableList<PreventiveCustomerDetailHeader> = mutableListOf()
        var id:Long=1
        data.forEach{
            item->
            headerGroup.add(PreventiveCustomerDetailHeader(id++, item.customer_id,
                    item.customer_name, item.area, item.area_id, item.tahun.toString(),
                    getPreventiveSaleOrder(customer_id, tahun, area_id, dataPreventiveSaleOrder, dataPreventiveSaleOrderInvoice),
                    getPreventiveInvoice(customer_id, tahun, area_id, dataPreventiveInvoice),
                    getPreventiveBudgetArea(customer_id, tahun, area_id, dataPreventiveBudget, dataPreventiveRealisasiBudget),
                    getPreventiveRealisasiBudgetArea(customer_id, tahun, area_id, dataPreventiveRealisasiBudget)))
        }
        return headerGroup
    }

    fun getPreventiveSaleOrder(customer_id: Int, tahun: String, area_id: String, data:Iterable<PreventiveSaleOrder>, dataSaleOrder:Iterable<PreventiveSaleOrderInvoice>): MutableList<com.prasetia.erp.pojo.preventive.PreventiveSaleOrder>{
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
                            total, getPreventivePOInvoice(item.client_order_ref, dataSaleOrder)))
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

    fun getPreventiveBudget(customer_id: Int, tahun: String, area_id: String, sub_area: String?,
                            data:Iterable<com.prasetia.erp.model.preventive.PreventiveBudget>,
                            dataRealisasiBudget: Iterable<com.prasetia.erp.model.preventive.PreventiveRealisasiBudget>):MutableList<PreventiveBudget>{
        val preventiveBudget:MutableList<PreventiveBudget> = mutableListOf()
        data.forEach {
            item->
            if((item.tahun.toString() == tahun) and (item.customer_id == customer_id.toLong()) and (item.area_id.toString() == area_id) and (item.area_detail == sub_area)){
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
                            ix, x, xi, xii, total,
                            getPreventiveBudgetWithRealisasiBudget(customer_id, tahun, area_id, sub_area,
                                    item.id, dataRealisasiBudget)))
                }
            }
        }
        return preventiveBudget
    }

    fun getPreventiveBudgetWithRealisasiBudget(customer_id: Int, tahun: String, area_id: String, sub_area: String?,
                                               budget_realisasi_id: Long,
                                               data:Iterable<com.prasetia.erp.model.preventive.PreventiveRealisasiBudget>): MutableList<PreventiveRealisasiBudget> {
        val preventiveRealisasiBudget:MutableList<PreventiveRealisasiBudget> = mutableListOf()
        data.forEach {
            item->
            if((item.tahun.toString() == tahun) and
                    (item.customer_id == customer_id.toLong()) and
                    (item.area_id.toString() == area_id) and (item.area_detail == sub_area) and
                    (item.id == budget_realisasi_id)){
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

    fun getPreventiveBudgetArea(customer_id: Int, tahun: String, area_id: String,
                                data:Iterable<com.prasetia.erp.model.preventive.PreventiveBudget>,
                                dataRealisasiBudget: Iterable<com.prasetia.erp.model.preventive.PreventiveRealisasiBudget>):MutableList<PreventiveBudgetArea>{
        val preventiveBudgetArea:MutableList<PreventiveBudgetArea> = mutableListOf()
        var id:Long= 0
        data.forEach {
            item ->
            if((item.tahun.toString() == tahun) and (item.customer_id == customer_id.toLong()) and (item.area_id.toString() == area_id)){
                var found = false
                preventiveBudgetArea.forEach {
                    itemDetail->
                    if(itemDetail.area_detail == item.area_detail?: "-") found = true
                }
                if (!found){
                    preventiveBudgetArea.add(PreventiveBudgetArea(id++, item.area_detail?: "-", getPreventiveBudget(customer_id, tahun, area_id, item.area_detail , data, dataRealisasiBudget)))
                }
            }
        }
        return preventiveBudgetArea
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

    fun getPreventiveRealisasiBudget(customer_id: Int, tahun: String, area_id: String, sub_area: String?, data:Iterable<com.prasetia.erp.model.preventive.PreventiveRealisasiBudget>):MutableList<PreventiveRealisasiBudget>{
        val preventiveRealisasiBudget:MutableList<PreventiveRealisasiBudget> = mutableListOf()
        data.forEach {
            item->
            if((item.tahun.toString() == tahun) and (item.customer_id == customer_id.toLong()) and (item.area_id.toString() == area_id) and (item.area_detail == sub_area)){
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

    fun getPreventiveRealisasiBudgetArea(customer_id: Int, tahun: String, area_id: String, data:Iterable<com.prasetia.erp.model.preventive.PreventiveRealisasiBudget>): MutableList<PreventiveRealisasiBudgetArea>{
        val preventiveRealisasiBudgetArea:MutableList<PreventiveRealisasiBudgetArea> = mutableListOf()
        var id:Long = 0
        data.forEach {
            item->
            if((item.tahun.toString() == tahun) and (item.customer_id == customer_id.toLong()) and (item.area_id.toString() == area_id)){
                var found = false
                preventiveRealisasiBudgetArea.forEach {
                    itemDetail->
                    if (itemDetail.area_detail == item.area_detail?: "-") found = true
                }
                if(!found){
                    preventiveRealisasiBudgetArea.add(PreventiveRealisasiBudgetArea(id++, item.area_detail?: "-", getPreventiveRealisasiBudget(customer_id, tahun, area_id, item.area_detail, data)))
                }
            }
        }
        return preventiveRealisasiBudgetArea
    }
}