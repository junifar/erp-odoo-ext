package com.prasetia.erp.controller.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.prasetia.erp.constant.GlobalConstant.Companion.BASE_URL
import com.prasetia.erp.pojo.PreventiveCustomerYear
import com.prasetia.erp.pojo.preventive.PreventiveCustomerDetailHeader
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import java.net.URL
import javax.servlet.http.HttpServletResponse

@Controller("Preventive Web Controller")
class PreventiveController{

    @Throws(IllegalStateException::class)
    @RequestMapping("/preventive/download/xls")
    fun downloadExcel(model:Model, response: HttpServletResponse): String{
        response.setHeader("Content-Disposition", "attachment; filename=\"budget-preventive-file.xls\"")
        response.contentType = "application/vnd.ms-excel"

        val workbook = HSSFWorkbook()
        val sheet = workbook.createSheet("Preventive")

        var header = sheet.createRow(1)
        header.createCell(1).setCellValue("Tipe Proyek")
        header.createCell(2).setCellValue("FLM (First Line Maintenance)")

        header = sheet.createRow(3)
        header.createCell(1).setCellValue("Customer")
        header.createCell(2).setCellValue("DMT")

        val out = response.outputStream
        workbook.write(out)

        out.flush()
        out.close()
        workbook.close()

        return ""
    }

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
            items.budget_area?.forEach {
                item_details->
                item_details.budget?.forEach {
                    item_sub_details ->
                    when (month){
                        "i" -> item_sub_details.i?.let { total = total.plus(it) }
                        "ii" -> item_sub_details.ii?.let { total =  total.plus(it) }
                        "iii" -> item_sub_details.iii?.let { total =  total.plus(it) }
                        "iv" -> item_sub_details.iv?.let { total = total.plus(it) }
                        "v" -> item_sub_details.v?.let { total =  total.plus(it) }
                        "vi" -> item_sub_details.vi?.let { total =  total.plus(it) }
                        "vii" -> item_sub_details.vii?.let { total = total.plus(it) }
                        "viii" -> item_sub_details.viii?.let { total = total.plus(it) }
                        "ix" -> item_sub_details.ix?.let { total = total.plus(it) }
                        "x" -> item_sub_details.x?.let { total = total.plus(it) }
                        "xi" -> item_sub_details.xi?.let { total = total.plus(it) }
                        "xii" -> item_sub_details.xii?.let { total = total.plus(it) }
                        "total" -> item_sub_details.total?.let { total = total.plus(it) }
                    }
                }
            }

        }
        return total
    }

    fun getTotalRealisasiBudget(data:List<PreventiveCustomerDetailHeader>, month:String):Long{
        var total:Long = 0
        data.forEach {
            items->
            items.realisasi_budget_area?.forEach {
                item_details->
                item_details.realisasi_budget?.forEach {
                    item_sub_details->
                    when (month){
                        "i" -> item_sub_details.i?.let { total = total.plus(it) }
                        "ii" -> item_sub_details.ii?.let { total =  total.plus(it) }
                        "iii" -> item_sub_details.iii?.let { total =  total.plus(it) }
                        "iv" -> item_sub_details.iv?.let { total = total.plus(it) }
                        "v" -> item_sub_details.v?.let { total =  total.plus(it) }
                        "vi" -> item_sub_details.vi?.let { total =  total.plus(it) }
                        "vii" -> item_sub_details.vii?.let { total = total.plus(it) }
                        "viii" -> item_sub_details.viii?.let { total = total.plus(it) }
                        "ix" -> item_sub_details.ix?.let { total = total.plus(it) }
                        "x" -> item_sub_details.x?.let { total = total.plus(it) }
                        "xi" -> item_sub_details.xi?.let { total = total.plus(it) }
                        "xii" -> item_sub_details.xii?.let { total = total.plus(it) }
                        "total" -> item_sub_details.total?.let { total = total.plus(it) }
                    }
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

        val totalNilaiPO = getTotalPO(preventiveDetailDataList)
        val totalNilaiInvoice = getTotalInvoice(preventiveDetailDataList)
        val totalNilaiBudget = getTotalNilaiBudget(preventiveDetailDataList)
        val totalNilaiRealisasiBudget = getTotalNilaiRealisasiBudget(preventiveDetailDataList)
        val totalLabaRugi = getDifferenceValue(totalNilaiInvoice, totalNilaiBudget)
        val totalRealisasiVsBudget = getDivisionPrecent(totalNilaiRealisasiBudget, totalNilaiBudget)
        val totalInvoiceVsNilaiPO  = getDivisionPrecent(totalNilaiPO, totalNilaiInvoice)
        val totalRealisasiBudgetVsNilaiPO = getDivisionPrecent(totalNilaiPO, totalNilaiRealisasiBudget)
        val totalBudgetVsNilaiPO = getDivisionPrecent(totalNilaiPO, totalNilaiBudget)

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

    fun getTotalPO(data:List<PreventiveCustomerDetailHeader>) = longArrayOf(
                                                                getTotalPO(data, "i"),
                                                                getTotalPO(data, "ii"),
                                                                getTotalPO(data, "iii"),
                                                                getTotalPO(data, "iv"),
                                                                getTotalPO(data, "v"),
                                                                getTotalPO(data, "vi"),
                                                                getTotalPO(data, "vii"),
                                                                getTotalPO(data, "viii"),
                                                                getTotalPO(data, "ix"),
                                                                getTotalPO(data, "x"),
                                                                getTotalPO(data, "xi"),
                                                                getTotalPO(data, "xii"),
                                                                getTotalPO(data, "total"))

    fun getTotalInvoice(data:List<PreventiveCustomerDetailHeader>) = longArrayOf(
            getTotalInvoice(data, "i"),
            getTotalInvoice(data, "ii"),
            getTotalInvoice(data, "iii"),
            getTotalInvoice(data, "iv"),
            getTotalInvoice(data, "v"),
            getTotalInvoice(data, "vi"),
            getTotalInvoice(data, "vii"),
            getTotalInvoice(data, "viii"),
            getTotalInvoice(data, "ix"),
            getTotalInvoice(data, "x"),
            getTotalInvoice(data, "xi"),
            getTotalInvoice(data, "xii"),
            getTotalInvoice(data, "total"))

    fun getTotalNilaiBudget(data:List<PreventiveCustomerDetailHeader>) = longArrayOf(
            getTotalBudget(data, "i"),
            getTotalBudget(data, "ii"),
            getTotalBudget(data, "iii"),
            getTotalBudget(data, "iv"),
            getTotalBudget(data, "v"),
            getTotalBudget(data, "vi"),
            getTotalBudget(data, "vii"),
            getTotalBudget(data, "viii"),
            getTotalBudget(data, "ix"),
            getTotalBudget(data, "x"),
            getTotalBudget(data, "xi"),
            getTotalBudget(data, "xii"),
            getTotalBudget(data, "total"))

    fun getTotalNilaiRealisasiBudget(data:List<PreventiveCustomerDetailHeader>) = longArrayOf(
            getTotalRealisasiBudget(data, "i"),
            getTotalRealisasiBudget(data, "ii"),
            getTotalRealisasiBudget(data, "iii"),
            getTotalRealisasiBudget(data, "iv"),
            getTotalRealisasiBudget(data, "v"),
            getTotalRealisasiBudget(data, "vi"),
            getTotalRealisasiBudget(data, "vii"),
            getTotalRealisasiBudget(data, "viii"),
            getTotalRealisasiBudget(data, "ix"),
            getTotalRealisasiBudget(data, "x"),
            getTotalRealisasiBudget(data, "xi"),
            getTotalRealisasiBudget(data, "xii"),
            getTotalRealisasiBudget(data, "total"))

    fun getDifferenceValue(data:LongArray, data1:LongArray) = longArrayOf(
            data[0] - data1[0],
            data[1] - data1[1],
            data[2] - data1[2],
            data[3] - data1[3],
            data[4] - data1[4],
            data[5] - data1[5],
            data[6] - data1[6],
            data[7] - data1[7],
            data[8] - data1[8],
            data[9] - data1[9],
            data[10] - data1[10],
            data[11] - data1[11],
            data[12] - data1[12]
    )

    fun getDivisionPrecent(data:LongArray, data1: LongArray) = floatArrayOf(
            if (data1[0] > 0) data[0].toFloat() * 100 / data1[0] else (0).toFloat(),
            if (data1[1] > 0) data[1].toFloat() * 100 / data1[1] else (0).toFloat(),
            if (data1[2] > 0) data[2].toFloat() * 100 / data1[2] else (0).toFloat(),
            if (data1[3] > 0) data[3].toFloat() * 100 / data1[3] else (0).toFloat(),
            if (data1[4] > 0) data[4].toFloat() * 100 / data1[4] else (0).toFloat(),
            if (data1[5] > 0) data[5].toFloat() * 100 / data1[5] else (0).toFloat(),
            if (data1[6] > 0) data[6].toFloat() * 100 / data1[6] else (0).toFloat(),
            if (data1[7] > 0) data[7].toFloat() * 100 / data1[7] else (0).toFloat(),
            if (data1[8] > 0) data[8].toFloat() * 100 / data1[8] else (0).toFloat(),
            if (data1[9] > 0) data[9].toFloat() * 100 / data1[9] else (0).toFloat(),
            if (data1[10] > 0) data[10].toFloat() * 100 / data1[10] else (0).toFloat(),
            if (data1[11] > 0) data[11].toFloat() * 100 / data1[11] else (0).toFloat(),
            if (data1[12] > 0) data[12].toFloat() * 100 / data1[12] else (0).toFloat()
    )
}
