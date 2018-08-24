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

        val totalLabaRugi = longArrayOf(
                totalNilaiInvoice[0] - totalNilaiBudget[0],
                totalNilaiInvoice[1] - totalNilaiBudget[1],
                totalNilaiInvoice[2] - totalNilaiBudget[2],
                totalNilaiInvoice[3] - totalNilaiBudget[3],
                totalNilaiInvoice[4] - totalNilaiBudget[4],
                totalNilaiInvoice[5] - totalNilaiBudget[5],
                totalNilaiInvoice[6] - totalNilaiBudget[6],
                totalNilaiInvoice[7] - totalNilaiBudget[7],
                totalNilaiInvoice[8] - totalNilaiBudget[8],
                totalNilaiInvoice[9] - totalNilaiBudget[9],
                totalNilaiInvoice[10] - totalNilaiBudget[10],
                totalNilaiInvoice[11] - totalNilaiBudget[11],
                totalNilaiInvoice[12] - totalNilaiBudget[12]
        )

        val totalRealisasiVsBudget = floatArrayOf(
                if (totalNilaiBudget[0] > 0) totalNilaiRealisasiBudget[0].toFloat() * 100 / totalNilaiBudget[0] else (0).toFloat(),
                if (totalNilaiBudget[1] > 0) totalNilaiRealisasiBudget[1].toFloat() * 100 / totalNilaiBudget[1] else (0).toFloat(),
                if (totalNilaiBudget[2] > 0) totalNilaiRealisasiBudget[2].toFloat() * 100 / totalNilaiBudget[2] else (0).toFloat(),
                if (totalNilaiBudget[3] > 0) totalNilaiRealisasiBudget[3].toFloat() * 100 / totalNilaiBudget[3] else (0).toFloat(),
                if (totalNilaiBudget[4] > 0) totalNilaiRealisasiBudget[4].toFloat() * 100 / totalNilaiBudget[4] else (0).toFloat(),
                if (totalNilaiBudget[5] > 0) totalNilaiRealisasiBudget[5].toFloat() * 100 / totalNilaiBudget[5] else (0).toFloat(),
                if (totalNilaiBudget[6] > 0) totalNilaiRealisasiBudget[6].toFloat() * 100 / totalNilaiBudget[6] else (0).toFloat(),
                if (totalNilaiBudget[7] > 0) totalNilaiRealisasiBudget[7].toFloat() * 100 / totalNilaiBudget[7] else (0).toFloat(),
                if (totalNilaiBudget[8] > 0) totalNilaiRealisasiBudget[8].toFloat() * 100 / totalNilaiBudget[8] else (0).toFloat(),
                if (totalNilaiBudget[9] > 0) totalNilaiRealisasiBudget[9].toFloat() * 100 / totalNilaiBudget[9] else (0).toFloat(),
                if (totalNilaiBudget[10] > 0) totalNilaiRealisasiBudget[10].toFloat() * 100 / totalNilaiBudget[10] else (0).toFloat(),
                if (totalNilaiBudget[11] > 0) totalNilaiRealisasiBudget[11].toFloat() * 100 / totalNilaiBudget[11] else (0).toFloat(),
                if (totalNilaiBudget[12] > 0) totalNilaiRealisasiBudget[12].toFloat() * 100 / totalNilaiBudget[12] else (0).toFloat()
        )

        val totalInvoiceVsNilaiPO = floatArrayOf(
                if(totalNilaiInvoice[0] > 0) totalNilaiPO[0].toFloat() / totalNilaiInvoice[0] * 100 else (0).toFloat(),
                if(totalNilaiInvoice[1] > 0) totalNilaiPO[1].toFloat() / totalNilaiInvoice[1] * 100 else (0).toFloat(),
                if(totalNilaiInvoice[2] > 0) totalNilaiPO[2].toFloat() / totalNilaiInvoice[2] * 100 else (0).toFloat(),
                if(totalNilaiInvoice[3] > 0) totalNilaiPO[3].toFloat() / totalNilaiInvoice[3] * 100 else (0).toFloat(),
                if(totalNilaiInvoice[4] > 0) totalNilaiPO[4].toFloat() / totalNilaiInvoice[4] * 100 else (0).toFloat(),
                if(totalNilaiInvoice[5] > 0) totalNilaiPO[5].toFloat() / totalNilaiInvoice[5] * 100 else (0).toFloat(),
                if(totalNilaiInvoice[6] > 0) totalNilaiPO[6].toFloat() / totalNilaiInvoice[6] * 100 else (0).toFloat(),
                if(totalNilaiInvoice[7] > 0) totalNilaiPO[7].toFloat() / totalNilaiInvoice[7] * 100 else (0).toFloat(),
                if(totalNilaiInvoice[8] > 0) totalNilaiPO[8].toFloat() / totalNilaiInvoice[8] * 100 else (0).toFloat(),
                if(totalNilaiInvoice[9] > 0) totalNilaiPO[9].toFloat() / totalNilaiInvoice[9] * 100 else (0).toFloat(),
                if(totalNilaiInvoice[10] > 0) totalNilaiPO[10].toFloat() / totalNilaiInvoice[10] * 100 else (0).toFloat(),
                if(totalNilaiInvoice[11] > 0) totalNilaiPO[11].toFloat() / totalNilaiInvoice[11] * 100 else (0).toFloat(),
                if(totalNilaiInvoice[12] > 0) totalNilaiPO[12].toFloat() / totalNilaiInvoice[12] * 100 else (0).toFloat()
        )

        val totalRealisasiBudgetVsNilaiPO = floatArrayOf(
                if(totalNilaiRealisasiBudget[0] > 0) totalNilaiPO[0].toFloat() / totalNilaiRealisasiBudget[0] * 100 else (0).toFloat(),
                if(totalNilaiRealisasiBudget[1] > 0) totalNilaiPO[1].toFloat() / totalNilaiRealisasiBudget[1] * 100 else (0).toFloat(),
                if(totalNilaiRealisasiBudget[2] > 0) totalNilaiPO[2].toFloat() / totalNilaiRealisasiBudget[2] * 100 else (0).toFloat(),
                if(totalNilaiRealisasiBudget[3] > 0) totalNilaiPO[3].toFloat() / totalNilaiRealisasiBudget[3] * 100 else (0).toFloat(),
                if(totalNilaiRealisasiBudget[4] > 0) totalNilaiPO[4].toFloat() / totalNilaiRealisasiBudget[4] * 100 else (0).toFloat(),
                if(totalNilaiRealisasiBudget[5] > 0) totalNilaiPO[5].toFloat() / totalNilaiRealisasiBudget[5] * 100 else (0).toFloat(),
                if(totalNilaiRealisasiBudget[6] > 0) totalNilaiPO[6].toFloat() / totalNilaiRealisasiBudget[6] * 100 else (0).toFloat(),
                if(totalNilaiRealisasiBudget[7] > 0) totalNilaiPO[7].toFloat() / totalNilaiRealisasiBudget[7] * 100 else (0).toFloat(),
                if(totalNilaiRealisasiBudget[8] > 0) totalNilaiPO[8].toFloat() / totalNilaiRealisasiBudget[8] * 100 else (0).toFloat(),
                if(totalNilaiRealisasiBudget[9] > 0) totalNilaiPO[9].toFloat() / totalNilaiRealisasiBudget[9] * 100 else (0).toFloat(),
                if(totalNilaiRealisasiBudget[10] > 0) totalNilaiPO[10].toFloat() / totalNilaiRealisasiBudget[10] * 100 else (0).toFloat(),
                if(totalNilaiRealisasiBudget[11] > 0) totalNilaiPO[11].toFloat() / totalNilaiRealisasiBudget[11] * 100 else (0).toFloat(),
                if(totalNilaiRealisasiBudget[12] > 0) totalNilaiPO[12].toFloat() / totalNilaiRealisasiBudget[12] * 100 else (0).toFloat()
        )

        val totalBudgetVsNilaiPO = floatArrayOf(
                if(totalNilaiBudget[0] > 0) totalNilaiPO[0].toFloat() / totalNilaiBudget[0] * 100 else (0).toFloat(),
                if(totalNilaiBudget[1] > 0) totalNilaiPO[1].toFloat() / totalNilaiBudget[1] * 100 else (0).toFloat(),
                if(totalNilaiBudget[2] > 0) totalNilaiPO[2].toFloat() / totalNilaiBudget[2] * 100 else (0).toFloat(),
                if(totalNilaiBudget[3] > 0) totalNilaiPO[3].toFloat() / totalNilaiBudget[3] * 100 else (0).toFloat(),
                if(totalNilaiBudget[4] > 0) totalNilaiPO[4].toFloat() / totalNilaiBudget[4] * 100 else (0).toFloat(),
                if(totalNilaiBudget[5] > 0) totalNilaiPO[5].toFloat() / totalNilaiBudget[5] * 100 else (0).toFloat(),
                if(totalNilaiBudget[6] > 0) totalNilaiPO[6].toFloat() / totalNilaiBudget[6] * 100 else (0).toFloat(),
                if(totalNilaiBudget[7] > 0) totalNilaiPO[7].toFloat() / totalNilaiBudget[7] * 100 else (0).toFloat(),
                if(totalNilaiBudget[8] > 0) totalNilaiPO[8].toFloat() / totalNilaiBudget[8] * 100 else (0).toFloat(),
                if(totalNilaiBudget[9] > 0) totalNilaiPO[9].toFloat() / totalNilaiBudget[9] * 100 else (0).toFloat(),
                if(totalNilaiBudget[10] > 0) totalNilaiPO[10].toFloat() / totalNilaiBudget[10] * 100 else (0).toFloat(),
                if(totalNilaiBudget[11] > 0) totalNilaiPO[11].toFloat() / totalNilaiBudget[11] * 100 else (0).toFloat(),
                if(totalNilaiBudget[12] > 0) totalNilaiPO[12].toFloat() / totalNilaiBudget[12] * 100 else (0).toFloat()
        )

        model.addAttribute("preventiveDetailDataList", preventiveDetailDataList)
        model.addAttribute("total_nilai_po", totalNilaiPO)
        model.addAttribute("total_nilai_invoice", totalNilaiInvoice)
        model.addAttribute("total_nilai_budget", totalNilaiBudget)
        model.addAttribute("total_nilai_realisasi_budget", totalNilaiRealisasiBudget)
        model.addAttribute("total_laba_rugi", totalLabaRugi)
        model.addAttribute("total_realisasi_vs_budget", totalRealisasiVsBudget)
        model.addAttribute("total_invoice_vs_nilai_po", totalInvoiceVsNilaiPO)
        model.addAttribute("total_realisasi_budget_vs_nilai_po", totalRealisasiBudgetVsNilaiPO)
        model.addAttribute("total_budget_vs_nilai_po", totalBudgetVsNilaiPO)
        return "preventive/detail"
    }

}
