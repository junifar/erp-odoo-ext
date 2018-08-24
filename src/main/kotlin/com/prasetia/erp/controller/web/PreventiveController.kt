package com.prasetia.erp.controller.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.prasetia.erp.constant.GlobalConstant.Companion.BASE_URL
import com.prasetia.erp.pojo.PreventiveCustomerYear
import com.prasetia.erp.pojo.preventive.PreventiveCustomerDetailHeader
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import java.net.URL

@Controller("Preventive Web Controller")
class PreventiveController{

    @RequestMapping("/preventive")
    fun indexPreventive(model:Model): String{
        val objectMapper = ObjectMapper()
        val url = URL(BASE_URL + "api/preventive_customer")
//        val preventiveDataList: List<PreventiveCustomer> = objectMapper.readValue(url)
//        preventiveDataList.forEach{
//            println(it.customer_name)
//        }
        val preventiveDataList: List<PreventiveCustomerYear> = objectMapper.readValue(url)
        model.addAttribute("preventiveDataList", preventiveDataList)
        return "preventive/index"
    }

    fun getTotalPO(data:List<PreventiveCustomerDetailHeader>, month:String):Long{
        var total:Long = 0
        data.forEach {
            items->
            items.sale_order?.forEach {
                item_details->
                when (month){
                    "i" -> item_details.i?.let { total = total.plus(it) }
                    "ii" -> item_details.ii?.let { total =  total.plus(it) }
                    "iii" -> item_details.iii?.let { total =  total.plus(it) }
                    "iv" -> item_details.iv?.let { total = total.plus(it) }
                    "v" -> item_details.v?.let { total =  total.plus(it) }
                    "vi" -> item_details.vi?.let { total =  total.plus(it) }
                    "vii" -> item_details.vii?.let { total = total.plus(it) }
                    "viii" -> item_details.viii?.let { total = total.plus(it) }
                    "ix" -> item_details.ix?.let { total = total.plus(it) }
                    "x" -> item_details.x?.let { total = total.plus(it) }
                    "xi" -> item_details.xi?.let { total = total.plus(it) }
                    "xii" -> item_details.xii?.let { total = total.plus(it) }
                    "total" -> item_details.total?.let { total = total.plus(it) }
                }
            }

        }
        return total
    }

    fun getTotalInvoice(data:List<PreventiveCustomerDetailHeader>, month:String):Long{
        var total:Long = 0
        data.forEach {
            items->
            items.invoice?.forEach {
                item_details->
                when (month){
                    "i" -> item_details.i?.let { total = total.plus(it) }
                    "ii" -> item_details.ii?.let { total =  total.plus(it) }
                    "iii" -> item_details.iii?.let { total =  total.plus(it) }
                    "iv" -> item_details.iv?.let { total = total.plus(it) }
                    "v" -> item_details.v?.let { total =  total.plus(it) }
                    "vi" -> item_details.vi?.let { total =  total.plus(it) }
                    "vii" -> item_details.vii?.let { total = total.plus(it) }
                    "viii" -> item_details.viii?.let { total = total.plus(it) }
                    "ix" -> item_details.ix?.let { total = total.plus(it) }
                    "x" -> item_details.x?.let { total = total.plus(it) }
                    "xi" -> item_details.xi?.let { total = total.plus(it) }
                    "xii" -> item_details.xii?.let { total = total.plus(it) }
                    "total" -> item_details.total?.let { total = total.plus(it) }
                }
            }

        }
        return total
    }

    fun getTotalBudget(data:List<PreventiveCustomerDetailHeader>, month:String):Long{
        var total:Long = 0
        data.forEach {
            items->
            items.budget?.forEach {
                item_details->
                when (month){
                    "i" -> item_details.i?.let { total = total.plus(it) }
                    "ii" -> item_details.ii?.let { total =  total.plus(it) }
                    "iii" -> item_details.iii?.let { total =  total.plus(it) }
                    "iv" -> item_details.iv?.let { total = total.plus(it) }
                    "v" -> item_details.v?.let { total =  total.plus(it) }
                    "vi" -> item_details.vi?.let { total =  total.plus(it) }
                    "vii" -> item_details.vii?.let { total = total.plus(it) }
                    "viii" -> item_details.viii?.let { total = total.plus(it) }
                    "ix" -> item_details.ix?.let { total = total.plus(it) }
                    "x" -> item_details.x?.let { total = total.plus(it) }
                    "xi" -> item_details.xi?.let { total = total.plus(it) }
                    "xii" -> item_details.xii?.let { total = total.plus(it) }
                    "total" -> item_details.total?.let { total = total.plus(it) }
                }
            }

        }
        return total
    }

    fun getTotalRealisasiBudget(data:List<PreventiveCustomerDetailHeader>, month:String):Long{
        var total:Long = 0
        data.forEach {
            items->
            items.realisasi_budget?.forEach {
                item_details->
                when (month){
                    "i" -> item_details.i?.let { total = total.plus(it) }
                    "ii" -> item_details.ii?.let { total =  total.plus(it) }
                    "iii" -> item_details.iii?.let { total =  total.plus(it) }
                    "iv" -> item_details.iv?.let { total = total.plus(it) }
                    "v" -> item_details.v?.let { total =  total.plus(it) }
                    "vi" -> item_details.vi?.let { total =  total.plus(it) }
                    "vii" -> item_details.vii?.let { total = total.plus(it) }
                    "viii" -> item_details.viii?.let { total = total.plus(it) }
                    "ix" -> item_details.ix?.let { total = total.plus(it) }
                    "x" -> item_details.x?.let { total = total.plus(it) }
                    "xi" -> item_details.xi?.let { total = total.plus(it) }
                    "xii" -> item_details.xii?.let { total = total.plus(it) }
                    "total" -> item_details.total?.let { total = total.plus(it) }
                }
            }

        }
        return total
    }

    @RequestMapping("/preventive/detail/{customer_id}/{tahun}/{area_id}")
    fun detailPreventive(model: Model, @PathVariable("customer_id") customer_id: Int,
                         @PathVariable("tahun") tahun: Int,
                         @PathVariable("area_id") area_id: String): String{
        val objectMapper = ObjectMapper()
        val url = URL(BASE_URL + "api/preventive_by_customer_year_area/%d/%d/%s".format(customer_id,tahun,area_id))
        val preventiveDetailDataList:List<PreventiveCustomerDetailHeader> = objectMapper.readValue(url)

        val totalNilaiPO = longArrayOf(
                getTotalPO(preventiveDetailDataList, "i"),
                getTotalPO(preventiveDetailDataList, "ii"),
                getTotalPO(preventiveDetailDataList, "iii"),
                getTotalPO(preventiveDetailDataList, "iv"),
                getTotalPO(preventiveDetailDataList, "v"),
                getTotalPO(preventiveDetailDataList, "vi"),
                getTotalPO(preventiveDetailDataList, "vii"),
                getTotalPO(preventiveDetailDataList, "viii"),
                getTotalPO(preventiveDetailDataList, "ix"),
                getTotalPO(preventiveDetailDataList, "x"),
                getTotalPO(preventiveDetailDataList, "xi"),
                getTotalPO(preventiveDetailDataList, "xii"),
                getTotalPO(preventiveDetailDataList, "total"))

        val totalNilaiInvoice = longArrayOf(
                getTotalInvoice(preventiveDetailDataList, "i"),
                getTotalInvoice(preventiveDetailDataList, "ii"),
                getTotalInvoice(preventiveDetailDataList, "iii"),
                getTotalInvoice(preventiveDetailDataList, "iv"),
                getTotalInvoice(preventiveDetailDataList, "v"),
                getTotalInvoice(preventiveDetailDataList, "vi"),
                getTotalInvoice(preventiveDetailDataList, "vii"),
                getTotalInvoice(preventiveDetailDataList, "viii"),
                getTotalInvoice(preventiveDetailDataList, "ix"),
                getTotalInvoice(preventiveDetailDataList, "x"),
                getTotalInvoice(preventiveDetailDataList, "xi"),
                getTotalInvoice(preventiveDetailDataList, "xii"),
                getTotalInvoice(preventiveDetailDataList, "total"))

        val totalNilaiBudget = longArrayOf(
                getTotalBudget(preventiveDetailDataList, "i"),
                getTotalBudget(preventiveDetailDataList, "ii"),
                getTotalBudget(preventiveDetailDataList, "iii"),
                getTotalBudget(preventiveDetailDataList, "iv"),
                getTotalBudget(preventiveDetailDataList, "v"),
                getTotalBudget(preventiveDetailDataList, "vi"),
                getTotalBudget(preventiveDetailDataList, "vii"),
                getTotalBudget(preventiveDetailDataList, "viii"),
                getTotalBudget(preventiveDetailDataList, "ix"),
                getTotalBudget(preventiveDetailDataList, "x"),
                getTotalBudget(preventiveDetailDataList, "xi"),
                getTotalBudget(preventiveDetailDataList, "xii"),
                getTotalBudget(preventiveDetailDataList, "total"))

        val totalNilaiRealisasiBudget = longArrayOf(
                getTotalRealisasiBudget(preventiveDetailDataList, "i"),
                getTotalRealisasiBudget(preventiveDetailDataList, "ii"),
                getTotalRealisasiBudget(preventiveDetailDataList, "iii"),
                getTotalRealisasiBudget(preventiveDetailDataList, "iv"),
                getTotalRealisasiBudget(preventiveDetailDataList, "v"),
                getTotalRealisasiBudget(preventiveDetailDataList, "vi"),
                getTotalRealisasiBudget(preventiveDetailDataList, "vii"),
                getTotalRealisasiBudget(preventiveDetailDataList, "viii"),
                getTotalRealisasiBudget(preventiveDetailDataList, "ix"),
                getTotalRealisasiBudget(preventiveDetailDataList, "x"),
                getTotalRealisasiBudget(preventiveDetailDataList, "xi"),
                getTotalRealisasiBudget(preventiveDetailDataList, "xii"),
                getTotalRealisasiBudget(preventiveDetailDataList, "total"))



        model.addAttribute("preventiveDetailDataList", preventiveDetailDataList)
        model.addAttribute("total_nilai_po", totalNilaiPO)
        model.addAttribute("total_nilai_invoice", totalNilaiInvoice)
        model.addAttribute("total_nilai_budget", totalNilaiBudget)
        model.addAttribute("total_nilai_realisasi_budget", totalNilaiRealisasiBudget)
        return "preventive/detail"
    }

}
