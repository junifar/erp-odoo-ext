package com.prasetia.erp.controller.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.prasetia.erp.constant.GlobalConstant.Companion.BASE_URL
import com.prasetia.erp.pojo.PreventiveCustomerYear
import com.prasetia.erp.pojo.preventive.PreventiveCustomerDetailHeader
import org.apache.poi.hssf.usermodel.*
import org.apache.poi.hssf.util.HSSFColor
import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.CellRangeAddress
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import java.net.URL
import javax.servlet.http.HttpServletResponse

@Controller("Preventive Web Controller")
class PreventiveController{

    var numRow:Int = 8
    var numRowTotalNilaiPO = 0
    var numRowTotalNilaiInvoice = 0
    var numRowTotalBudget = 0
    var numRowTotalRealisasi = 0

    fun setColWidth(sheet:HSSFSheet):HSSFSheet{
        // 1:260
        sheet.setColumnWidth(0, 780)
        sheet.setColumnWidth(1, 6240)
        sheet.setColumnWidth(2, 8580)
        sheet.setColumnWidth(3, 3640)
        sheet.setColumnWidth(4, 2080)
        sheet.setColumnWidth(5, 3640)
        sheet.setColumnWidth(6, 2080)
        sheet.setColumnWidth(7, 3640)
        sheet.setColumnWidth(8, 2080)
        sheet.setColumnWidth(9, 3640)
        sheet.setColumnWidth(10, 2080)
        sheet.setColumnWidth(11, 3640)
        sheet.setColumnWidth(12, 2080)
        sheet.setColumnWidth(13, 3640)
        sheet.setColumnWidth(14, 2080)
        sheet.setColumnWidth(15, 3640)
        sheet.setColumnWidth(16, 2080)
        sheet.setColumnWidth(17, 3640)
        sheet.setColumnWidth(18, 2080)
        sheet.setColumnWidth(19, 3640)
        sheet.setColumnWidth(20, 2080)
        sheet.setColumnWidth(21, 3640)
        sheet.setColumnWidth(22, 2080)
        sheet.setColumnWidth(23, 3640)
        sheet.setColumnWidth(24, 2080)
        sheet.setColumnWidth(25, 3640)
        sheet.setColumnWidth(26, 2080)
        sheet.setColumnWidth(27, 4420)
        sheet.setColumnWidth(28, 2040)
        return  sheet
    }

    fun styleHeader(workbook: HSSFWorkbook):HSSFCellStyle{
        val styleHeader = workbook.createCellStyle()
        val fontCalibri = workbook.createFont()
        fontCalibri.fontName = "Calibri"
        fontCalibri.bold = true
        fontCalibri.fontHeightInPoints = 11
        styleHeader.setFont(fontCalibri)
        return styleHeader
    }

    fun styleTableHeader(workbook: HSSFWorkbook):HSSFCellStyle{
        val styleTableHeader = workbook.createCellStyle()
        val fontCalibriTableHeader = workbook.createFont()
        fontCalibriTableHeader.fontName = "Calibri"
        fontCalibriTableHeader.fontHeightInPoints = 11
        fontCalibriTableHeader.bold = true
        styleTableHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND)
        styleTableHeader.fillForegroundColor = HSSFColor.GREY_25_PERCENT.index
        styleTableHeader.setAlignment(HorizontalAlignment.CENTER)
        styleTableHeader.setBorderBottom(BorderStyle.THIN)
        styleTableHeader.setBorderTop(BorderStyle.THIN)
        styleTableHeader.setBorderLeft(BorderStyle.THIN)
        styleTableHeader.setBorderRight(BorderStyle.THIN)
        styleTableHeader.setFont(fontCalibriTableHeader)
        return styleTableHeader
    }

    fun styleTableHeaderNumber(workbook: HSSFWorkbook):HSSFCellStyle{
        val styleTableHeader = workbook.createCellStyle()
        val fontCalibriTableHeader = workbook.createFont()
        fontCalibriTableHeader.fontName = "Calibri"
        fontCalibriTableHeader.fontHeightInPoints = 11
        fontCalibriTableHeader.bold = true
        styleTableHeader.dataFormat = HSSFDataFormat.getBuiltinFormat("#,##0.00")
        styleTableHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND)
        styleTableHeader.fillForegroundColor = HSSFColor.GREY_25_PERCENT.index
        styleTableHeader.setBorderBottom(BorderStyle.THIN)
        styleTableHeader.setBorderTop(BorderStyle.THIN)
        styleTableHeader.setBorderLeft(BorderStyle.THIN)
        styleTableHeader.setBorderRight(BorderStyle.THIN)
        styleTableHeader.setFont(fontCalibriTableHeader)
        return styleTableHeader
    }

    fun styleTableHeaderPercent(workbook: HSSFWorkbook):HSSFCellStyle{
        val styleTableHeader = workbook.createCellStyle()
        val fontCalibriTableHeader = workbook.createFont()
        fontCalibriTableHeader.fontName = "Calibri"
        fontCalibriTableHeader.fontHeightInPoints = 11
        fontCalibriTableHeader.bold = true
        styleTableHeader.dataFormat = HSSFDataFormat.getBuiltinFormat("0.00%")
        styleTableHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND)
        styleTableHeader.fillForegroundColor = HSSFColor.GREY_25_PERCENT.index
        styleTableHeader.setBorderBottom(BorderStyle.THIN)
        styleTableHeader.setBorderTop(BorderStyle.THIN)
        styleTableHeader.setBorderLeft(BorderStyle.THIN)
        styleTableHeader.setBorderRight(BorderStyle.THIN)
        styleTableHeader.setFont(fontCalibriTableHeader)
        return styleTableHeader
    }

    fun fontCalibriTableContent(workbook: HSSFWorkbook):HSSFFont{
        val fontCalibriTableContent = workbook.createFont()
        fontCalibriTableContent.fontName = "Calibri"
        fontCalibriTableContent.fontHeightInPoints = 11
        fontCalibriTableContent.bold = false
        return fontCalibriTableContent
    }

    fun styleTableContent(workbook: HSSFWorkbook):HSSFCellStyle{
        val styleTableContent = workbook.createCellStyle()
        val fontCalibriTableContent = fontCalibriTableContent(workbook)
        styleTableContent.setBorderBottom(BorderStyle.THIN)
        styleTableContent.setBorderTop(BorderStyle.THIN)
        styleTableContent.setBorderLeft(BorderStyle.THIN)
        styleTableContent.setBorderRight(BorderStyle.THIN)
        styleTableContent.setFont(fontCalibriTableContent)
        return styleTableContent
    }

    fun styleTableContentNumber(workbook: HSSFWorkbook):HSSFCellStyle{
        val styleTableContentNumber = workbook.createCellStyle()
        styleTableContentNumber.dataFormat = HSSFDataFormat.getBuiltinFormat("#,##0.00")
        styleTableContentNumber.setBorderBottom(BorderStyle.THIN)
        styleTableContentNumber.setBorderTop(BorderStyle.THIN)
        styleTableContentNumber.setBorderLeft(BorderStyle.THIN)
        styleTableContentNumber.setBorderRight(BorderStyle.THIN)
        styleTableContentNumber.setFont(fontCalibriTableContent(workbook))
        return styleTableContentNumber
    }

    fun createHeaderPreventiveXls(workbook: HSSFWorkbook, sheet: HSSFSheet, preventiveDetailDataList: List<PreventiveCustomerDetailHeader>){
        val styleHeader = styleHeader(workbook)
        val styleTableHeader = styleTableHeader(workbook)

        lateinit var customer: String
        lateinit var tahun: String
        lateinit var area: String

        preventiveDetailDataList.forEach {
            customer = it.customer_name.toString()
            tahun = it.tahun
            area = it.area.toString()
        }

        var header = sheet.createRow(1)
        header.createCell(1).setCellValue("Tipe Proyek")
        header.getCell(1).setCellStyle(styleHeader)
        header.createCell(2).setCellValue("FLM (First Line Maintenance)")
        header.getCell(2).setCellStyle(styleHeader)

        header = sheet.createRow(2)
        header.createCell(1).setCellValue("Customer")
        header.getCell(1).setCellStyle(styleHeader)
        header.createCell(2).setCellValue("$customer")
        header.getCell(2).setCellStyle(styleHeader)

        header = sheet.createRow(3)
        header.createCell(1).setCellValue("Periode")
        header.getCell(1).setCellStyle(styleHeader)
        header.createCell(2).setCellValue("Januari - Desember $tahun")
        header.getCell(2).setCellStyle(styleHeader)

        header = sheet.createRow(4)
        header.createCell(1).setCellValue("Area")
        header.getCell(1).setCellStyle(styleHeader)
        header.createCell(2).setCellValue("$area")
        header.getCell(2).setCellStyle(styleHeader)

        sheet.addMergedRegion(CellRangeAddress(7, 7, 3,4))
        sheet.addMergedRegion(CellRangeAddress(7, 7, 5,6))
        sheet.addMergedRegion(CellRangeAddress(7, 7, 7,8))
        sheet.addMergedRegion(CellRangeAddress(7, 7, 9,10))
        sheet.addMergedRegion(CellRangeAddress(7, 7, 11,12))
        sheet.addMergedRegion(CellRangeAddress(7, 7, 13,14))
        sheet.addMergedRegion(CellRangeAddress(7, 7, 15,16))
        sheet.addMergedRegion(CellRangeAddress(7, 7, 17,18))
        sheet.addMergedRegion(CellRangeAddress(7, 7, 19,20))
        sheet.addMergedRegion(CellRangeAddress(7, 7, 21,22))
        sheet.addMergedRegion(CellRangeAddress(7, 7, 23,24))
        sheet.addMergedRegion(CellRangeAddress(7, 7, 25,26))
        sheet.addMergedRegion(CellRangeAddress(7, 7, 27,28))
        sheet.createFreezePane(3, 8)

        header = sheet.createRow(7)
        header.createCell(1).setCellValue("Keterangan")
        header.getCell(1).setCellStyle(styleTableHeader)
        header.createCell(2).setCellValue("Nomor PO")
        header.getCell(2).setCellStyle(styleTableHeader)
        header.createCell(3).setCellValue("Januari")
        header.getCell(3).setCellStyle(styleTableHeader)
        header.createCell(4)
        header.getCell(4).setCellStyle(styleTableHeader)
        header.createCell(5).setCellValue("Februari")
        header.getCell(5).setCellStyle(styleTableHeader)
        header.createCell(6)
        header.getCell(6).setCellStyle(styleTableHeader)
        header.createCell(7).setCellValue("Maret")
        header.getCell(7).setCellStyle(styleTableHeader)
        header.createCell(8)
        header.getCell(8).setCellStyle(styleTableHeader)
        header.createCell(9).setCellValue("April")
        header.getCell(9).setCellStyle(styleTableHeader)
        header.createCell(10)
        header.getCell(10).setCellStyle(styleTableHeader)
        header.createCell(11).setCellValue("Mei")
        header.getCell(11).setCellStyle(styleTableHeader)
        header.createCell(12)
        header.getCell(12).setCellStyle(styleTableHeader)
        header.createCell(13).setCellValue("Juni")
        header.getCell(13).setCellStyle(styleTableHeader)
        header.createCell(14)
        header.getCell(14).setCellStyle(styleTableHeader)
        header.createCell(15).setCellValue("Juli")
        header.getCell(15).setCellStyle(styleTableHeader)
        header.createCell(16)
        header.getCell(16).setCellStyle(styleTableHeader)
        header.createCell(17).setCellValue("Agustus")
        header.getCell(17).setCellStyle(styleTableHeader)
        header.createCell(18)
        header.getCell(18).setCellStyle(styleTableHeader)
        header.createCell(19).setCellValue("September")
        header.getCell(19).setCellStyle(styleTableHeader)
        header.createCell(20)
        header.getCell(20).setCellStyle(styleTableHeader)
        header.createCell(21).setCellValue("Oktober")
        header.getCell(21).setCellStyle(styleTableHeader)
        header.createCell(22)
        header.getCell(22).setCellStyle(styleTableHeader)
        header.createCell(23).setCellValue("November")
        header.getCell(23).setCellStyle(styleTableHeader)
        header.createCell(24)
        header.getCell(24).setCellStyle(styleTableHeader)
        header.createCell(25).setCellValue("Desember")
        header.getCell(25).setCellStyle(styleTableHeader)
        header.createCell(26)
        header.getCell(26).setCellStyle(styleTableHeader)
        header.createCell(27).setCellValue("Total")
        header.getCell(27).setCellStyle(styleTableHeader)
        header.createCell(28)
        header.getCell(28).setCellStyle(styleTableHeader)
    }

    fun createPOPreventiveXls(workbook: HSSFWorkbook, sheet: HSSFSheet, preventiveDetailDataList: List<PreventiveCustomerDetailHeader>){
        val styleTableContent = styleTableContent(workbook)
        val styleTableContentNumber = styleTableContentNumber(workbook)
        val styleTableHeader = styleTableHeader(workbook)
        val styleTableHeaderNumber = styleTableHeaderNumber(workbook)
        var numRow = this.numRow
        var content:HSSFRow
        var firstRowNum = true

        preventiveDetailDataList.forEach {
            items->
            items.sale_order?.forEach {
                sale_order_items->
                content = sheet.createRow(numRow++)
                val cell3 = content.createCell(3)
                val cell5 = content.createCell(5)
                val cell7 = content.createCell(7)
                val cell9 = content.createCell(9)
                val cell11 = content.createCell(11)
                val cell13 = content.createCell(13)
                val cell15 = content.createCell(15)
                val cell17 = content.createCell(17)
                val cell19 = content.createCell(19)
                val cell21 = content.createCell(21)
                val cell23 = content.createCell(23)
                val cell25 = content.createCell(25)
                val cell27 = content.createCell(27)
                cell27.setCellType(CellType.FORMULA)

                if(firstRowNum){
                    content.createCell(1).setCellValue("Nilai PO / Bln")
                    firstRowNum = false
                }else{
                    content.createCell(1).setCellValue("")
                }
                content.getCell(1).setCellStyle(styleTableContent)
                content.createCell(2).setCellValue(sale_order_items.client_order_ref)
                content.getCell(2).setCellStyle(styleTableContent)
                sale_order_items.i?.toDouble()?.let { cell3.setCellValue(it) }
                content.getCell(3).setCellStyle(styleTableContentNumber)
                content.createCell(4)
                content.getCell(4).setCellStyle(styleTableContent)
                sale_order_items.ii?.toDouble()?.let { cell5.setCellValue(it) }
                content.getCell(5).setCellStyle(styleTableContentNumber)
                content.createCell(6)
                content.getCell(6).setCellStyle(styleTableContent)
                sale_order_items.iii?.toDouble()?.let { cell7.setCellValue(it) }
                content.getCell(7).setCellStyle(styleTableContentNumber)
                content.createCell(8)
                content.getCell(8).setCellStyle(styleTableContent)
                sale_order_items.iv?.toDouble()?.let { cell9.setCellValue(it) }
                content.getCell(9).setCellStyle(styleTableContentNumber)
                content.createCell(10)
                content.getCell(10).setCellStyle(styleTableContent)
                sale_order_items.v?.toDouble()?.let { cell11.setCellValue(it) }
                content.getCell(11).setCellStyle(styleTableContentNumber)
                content.createCell(12)
                content.getCell(12).setCellStyle(styleTableContent)
                sale_order_items.vi?.toDouble()?.let { cell13.setCellValue(it) }
                content.getCell(13).setCellStyle(styleTableContentNumber)
                content.createCell(14)
                content.getCell(14).setCellStyle(styleTableContent)
                sale_order_items.vii?.toDouble()?.let { cell15.setCellValue(it) }
                content.getCell(15).setCellStyle(styleTableContentNumber)
                content.createCell(16)
                content.getCell(16).setCellStyle(styleTableContent)
                sale_order_items.viii?.toDouble()?.let { cell17.setCellValue(it) }
                content.getCell(17).setCellStyle(styleTableContentNumber)
                content.createCell(18)
                content.getCell(18).setCellStyle(styleTableContent)
                sale_order_items.ix?.toDouble()?.let { cell19.setCellValue(it) }
                content.getCell(19).setCellStyle(styleTableContentNumber)
                content.createCell(20)
                content.getCell(20).setCellStyle(styleTableContent)
                sale_order_items.x?.toDouble()?.let { cell21.setCellValue(it) }
                content.getCell(21).setCellStyle(styleTableContentNumber)
                content.createCell(22)
                content.getCell(22).setCellStyle(styleTableContent)
                sale_order_items.xi?.toDouble()?.let { cell23.setCellValue(it) }
                content.getCell(23).setCellStyle(styleTableContentNumber)
                content.createCell(24)
                content.getCell(24).setCellStyle(styleTableContent)
                sale_order_items.xii?.toDouble()?.let { cell25.setCellValue(it) }
                content.getCell(25).setCellStyle(styleTableContentNumber)
                content.createCell(26)
                content.getCell(26).setCellStyle(styleTableContent)
//                sale_order_items.total?.toDouble()?.let { cell27.setCellValue(it) }
                cell27.cellFormula = "SUM(D$numRow+F$numRow+H$numRow+J$numRow+L$numRow+N$numRow+P$numRow+R$numRow+T$numRow+V$numRow+X$numRow+Z$numRow)"
                content.getCell(27).setCellStyle(styleTableContentNumber)
                content.createCell(28)
                content.getCell(28).setCellStyle(styleTableContent)
            }
        }

        content = sheet.createRow(numRow)
        val cell3 = content.createCell(3)
        val cell5 = content.createCell(5)
        val cell7 = content.createCell(7)
        val cell9 = content.createCell(9)
        val cell11 = content.createCell(11)
        val cell13 = content.createCell(13)
        val cell15 = content.createCell(15)
        val cell17 = content.createCell(17)
        val cell19 = content.createCell(19)
        val cell21 = content.createCell(21)
        val cell23 = content.createCell(23)
        val cell25 = content.createCell(25)
        val cell27 = content.createCell(27)
        cell27.setCellType(CellType.FORMULA)

        content.createCell(1).setCellValue("")
        content.getCell(1).setCellStyle(styleTableHeader)
        content.createCell(2).setCellValue("Total PO / Bulan")
        content.getCell(2).setCellStyle(styleTableHeader)
        cell3.cellFormula = "SUM(D9:D$numRow)"
        content.getCell(3).setCellStyle(styleTableHeaderNumber)
        content.createCell(4)
        content.getCell(4).setCellStyle(styleTableHeaderNumber)
        cell5.cellFormula = "SUM(F9:F$numRow)"
        content.getCell(5).setCellStyle(styleTableHeaderNumber)
        content.createCell(6)
        content.getCell(6).setCellStyle(styleTableHeaderNumber)
        cell7.cellFormula = "SUM(H9:H$numRow)"
        content.getCell(7).setCellStyle(styleTableHeaderNumber)
        content.createCell(8)
        content.getCell(8).setCellStyle(styleTableHeaderNumber)
        cell9.cellFormula = "SUM(J9:J$numRow)"
        content.getCell(9).setCellStyle(styleTableHeaderNumber)
        content.createCell(10)
        content.getCell(10).setCellStyle(styleTableHeaderNumber)
        cell11.cellFormula = "SUM(L9:L$numRow)"
        content.getCell(11).setCellStyle(styleTableHeaderNumber)
        content.createCell(12)
        content.getCell(12).setCellStyle(styleTableHeaderNumber)
        cell13.cellFormula = "SUM(N9:N$numRow)"
        content.getCell(13).setCellStyle(styleTableHeaderNumber)
        content.createCell(14)
        content.getCell(14).setCellStyle(styleTableHeaderNumber)
        cell15.cellFormula = "SUM(P9:P$numRow)"
        content.getCell(15).setCellStyle(styleTableHeaderNumber)
        content.createCell(16)
        content.getCell(16).setCellStyle(styleTableHeaderNumber)
        cell17.cellFormula = "SUM(R9:R$numRow)"
        content.getCell(17).setCellStyle(styleTableHeaderNumber)
        content.createCell(18)
        content.getCell(18).setCellStyle(styleTableHeaderNumber)
        cell19.cellFormula = "SUM(T9:T$numRow)"
        content.getCell(19).setCellStyle(styleTableHeaderNumber)
        content.createCell(20)
        content.getCell(20).setCellStyle(styleTableHeaderNumber)
        cell21.cellFormula = "SUM(V9:V$numRow)"
        content.getCell(21).setCellStyle(styleTableHeaderNumber)
        content.createCell(22)
        content.getCell(22).setCellStyle(styleTableHeaderNumber)
        cell23.cellFormula = "SUM(X9:X$numRow)"
        content.getCell(23).setCellStyle(styleTableHeaderNumber)
        content.createCell(24)
        content.getCell(24).setCellStyle(styleTableHeaderNumber)
        cell25.cellFormula = "SUM(Z9:Z$numRow)"
        content.getCell(25).setCellStyle(styleTableHeaderNumber)
        content.createCell(26)
        content.getCell(26).setCellStyle(styleTableHeaderNumber)
        cell27.cellFormula = "SUM(AB9:AB$numRow)"
        content.getCell(27).setCellStyle(styleTableHeaderNumber)
        content.createCell(28)
        content.getCell(28).setCellStyle(styleTableHeaderNumber)
        this.numRowTotalNilaiPO = numRow + 1
        this.numRow = ++numRow
    }

    fun createInvoicePreventiveXls(workbook: HSSFWorkbook, sheet: HSSFSheet, preventiveDetailDataList: List<PreventiveCustomerDetailHeader>){
        val styleTableContent = styleTableContent(workbook)
        val styleTableContentNumber = styleTableContentNumber(workbook)
        val styleTableHeader = styleTableHeader(workbook)
        val styleTableHeaderNumber = styleTableHeaderNumber(workbook)

        var numRowLocal = this.numRow
        val firstRow = this.numRow+1
        var content:HSSFRow
        var firstRowNum = true

        preventiveDetailDataList.forEach {
            items->
            items.invoice?.forEach {
                sale_order_items->
                content = sheet.createRow(numRowLocal++)
                val cell3 = content.createCell(3)
                val cell5 = content.createCell(5)
                val cell7 = content.createCell(7)
                val cell9 = content.createCell(9)
                val cell11 = content.createCell(11)
                val cell13 = content.createCell(13)
                val cell15 = content.createCell(15)
                val cell17 = content.createCell(17)
                val cell19 = content.createCell(19)
                val cell21 = content.createCell(21)
                val cell23 = content.createCell(23)
                val cell25 = content.createCell(25)
                val cell27 = content.createCell(27)
                cell27.setCellType(CellType.FORMULA)

                if(firstRowNum){
                    content.createCell(1).setCellValue("Nilai Penagihan DPP/Bln")
                    firstRowNum = false
                }else{
                    content.createCell(1).setCellValue("")
                }
                content.getCell(1).setCellStyle(styleTableContent)
                content.createCell(2).setCellValue(sale_order_items.client_order_ref)
                content.getCell(2).setCellStyle(styleTableContent)
                sale_order_items.i?.toDouble()?.let { cell3.setCellValue(it) }
                content.getCell(3).setCellStyle(styleTableContentNumber)
                content.createCell(4)
                content.getCell(4).setCellStyle(styleTableContent)
                sale_order_items.ii?.toDouble()?.let { cell5.setCellValue(it) }
                content.getCell(5).setCellStyle(styleTableContentNumber)
                content.createCell(6)
                content.getCell(6).setCellStyle(styleTableContent)
                sale_order_items.iii?.toDouble()?.let { cell7.setCellValue(it) }
                content.getCell(7).setCellStyle(styleTableContentNumber)
                content.createCell(8)
                content.getCell(8).setCellStyle(styleTableContent)
                sale_order_items.iv?.toDouble()?.let { cell9.setCellValue(it) }
                content.getCell(9).setCellStyle(styleTableContentNumber)
                content.createCell(10)
                content.getCell(10).setCellStyle(styleTableContent)
                sale_order_items.v?.toDouble()?.let { cell11.setCellValue(it) }
                content.getCell(11).setCellStyle(styleTableContentNumber)
                content.createCell(12)
                content.getCell(12).setCellStyle(styleTableContent)
                sale_order_items.vi?.toDouble()?.let { cell13.setCellValue(it) }
                content.getCell(13).setCellStyle(styleTableContentNumber)
                content.createCell(14)
                content.getCell(14).setCellStyle(styleTableContent)
                sale_order_items.vii?.toDouble()?.let { cell15.setCellValue(it) }
                content.getCell(15).setCellStyle(styleTableContentNumber)
                content.createCell(16)
                content.getCell(16).setCellStyle(styleTableContent)
                sale_order_items.viii?.toDouble()?.let { cell17.setCellValue(it) }
                content.getCell(17).setCellStyle(styleTableContentNumber)
                content.createCell(18)
                content.getCell(18).setCellStyle(styleTableContent)
                sale_order_items.ix?.toDouble()?.let { cell19.setCellValue(it) }
                content.getCell(19).setCellStyle(styleTableContentNumber)
                content.createCell(20)
                content.getCell(20).setCellStyle(styleTableContent)
                sale_order_items.x?.toDouble()?.let { cell21.setCellValue(it) }
                content.getCell(21).setCellStyle(styleTableContentNumber)
                content.createCell(22)
                content.getCell(22).setCellStyle(styleTableContent)
                sale_order_items.xi?.toDouble()?.let { cell23.setCellValue(it) }
                content.getCell(23).setCellStyle(styleTableContentNumber)
                content.createCell(24)
                content.getCell(24).setCellStyle(styleTableContent)
                sale_order_items.xii?.toDouble()?.let { cell25.setCellValue(it) }
                content.getCell(25).setCellStyle(styleTableContentNumber)
                content.createCell(26)
                content.getCell(26).setCellStyle(styleTableContent)
//                sale_order_items.total?.toDouble()?.let { cell27.setCellValue(it) }
                cell27.cellFormula = "SUM(D$numRowLocal+F$numRowLocal+H$numRowLocal+J$numRowLocal+L$numRowLocal+N$numRowLocal+P$numRowLocal+R$numRowLocal+T$numRowLocal+V$numRowLocal+X$numRowLocal+Z$numRowLocal)"
                content.getCell(27).setCellStyle(styleTableContentNumber)
                content.createCell(28)
                content.getCell(28).setCellStyle(styleTableContent)
            }
        }

        content = sheet.createRow(numRowLocal)
        val cell3 = content.createCell(3)
        val cell5 = content.createCell(5)
        val cell7 = content.createCell(7)
        val cell9 = content.createCell(9)
        val cell11 = content.createCell(11)
        val cell13 = content.createCell(13)
        val cell15 = content.createCell(15)
        val cell17 = content.createCell(17)
        val cell19 = content.createCell(19)
        val cell21 = content.createCell(21)
        val cell23 = content.createCell(23)
        val cell25 = content.createCell(25)
        val cell27 = content.createCell(27)
        cell27.setCellType(CellType.FORMULA)

        content.createCell(1).setCellValue("")
        content.getCell(1).setCellStyle(styleTableHeader)
        content.createCell(2).setCellValue("Total Penagihan (DPP)")
        content.getCell(2).setCellStyle(styleTableHeader)
        cell3.cellFormula = "SUM(D$firstRow:D$numRowLocal)"
        content.getCell(3).setCellStyle(styleTableHeaderNumber)
        content.createCell(4)
        content.getCell(4).setCellStyle(styleTableHeaderNumber)
        cell5.cellFormula = "SUM(F$firstRow:F$numRowLocal)"
        content.getCell(5).setCellStyle(styleTableHeaderNumber)
        content.createCell(6)
        content.getCell(6).setCellStyle(styleTableHeaderNumber)
        cell7.cellFormula = "SUM(H$firstRow:H$numRowLocal)"
        content.getCell(7).setCellStyle(styleTableHeaderNumber)
        content.createCell(8)
        content.getCell(8).setCellStyle(styleTableHeaderNumber)
        cell9.cellFormula = "SUM(J$firstRow:J$numRowLocal)"
        content.getCell(9).setCellStyle(styleTableHeaderNumber)
        content.createCell(10)
        content.getCell(10).setCellStyle(styleTableHeaderNumber)
        cell11.cellFormula = "SUM(L$firstRow:L$numRowLocal)"
        content.getCell(11).setCellStyle(styleTableHeaderNumber)
        content.createCell(12)
        content.getCell(12).setCellStyle(styleTableHeaderNumber)
        cell13.cellFormula = "SUM(N$firstRow:N$numRowLocal)"
        content.getCell(13).setCellStyle(styleTableHeaderNumber)
        content.createCell(14)
        content.getCell(14).setCellStyle(styleTableHeaderNumber)
        cell15.cellFormula = "SUM(P$firstRow:P$numRowLocal)"
        content.getCell(15).setCellStyle(styleTableHeaderNumber)
        content.createCell(16)
        content.getCell(16).setCellStyle(styleTableHeaderNumber)
        cell17.cellFormula = "SUM(R$firstRow:R$numRowLocal)"
        content.getCell(17).setCellStyle(styleTableHeaderNumber)
        content.createCell(18)
        content.getCell(18).setCellStyle(styleTableHeaderNumber)
        cell19.cellFormula = "SUM(T$firstRow:T$numRowLocal)"
        content.getCell(19).setCellStyle(styleTableHeaderNumber)
        content.createCell(20)
        content.getCell(20).setCellStyle(styleTableHeaderNumber)
        cell21.cellFormula = "SUM(V$firstRow:V$numRowLocal)"
        content.getCell(21).setCellStyle(styleTableHeaderNumber)
        content.createCell(22)
        content.getCell(22).setCellStyle(styleTableHeaderNumber)
        cell23.cellFormula = "SUM(X$firstRow:X$numRowLocal)"
        content.getCell(23).setCellStyle(styleTableHeaderNumber)
        content.createCell(24)
        content.getCell(24).setCellStyle(styleTableHeaderNumber)
        cell25.cellFormula = "SUM(Z$firstRow:Z$numRowLocal)"
        content.getCell(25).setCellStyle(styleTableHeaderNumber)
        content.createCell(26)
        content.getCell(26).setCellStyle(styleTableHeaderNumber)
        cell27.cellFormula = "SUM(AB$firstRow:AB$numRowLocal)"
        content.getCell(27).setCellStyle(styleTableHeaderNumber)
        content.createCell(28)
        content.getCell(28).setCellStyle(styleTableHeaderNumber)
        this.numRowTotalNilaiInvoice = numRowLocal+1
        this.numRow = ++numRowLocal
    }

    fun createBudgetPreventiveXls(workbook: HSSFWorkbook, sheet: HSSFSheet, preventiveDetailDataList: List<PreventiveCustomerDetailHeader>){
        val styleTableContent = styleTableContent(workbook)
        val styleTableContentNumber = styleTableContentNumber(workbook)
        val styleTableHeader = styleTableHeader(workbook)
        val styleTableHeaderNumber = styleTableHeaderNumber(workbook)

        var numRowLocal = this.numRow
        val firstRow = this.numRow+1
        var content:HSSFRow
//        var firstRowNum = true

        preventiveDetailDataList.forEach {
            items->
            items.budget_area?.forEach {
                budget_area_items->
                var firstRowNum = true
                budget_area_items.budget?.forEach {
                    budget_items->
                    content = sheet.createRow(numRowLocal++)
                    val cell3 = content.createCell(3)
                    val cell5 = content.createCell(5)
                    val cell7 = content.createCell(7)
                    val cell9 = content.createCell(9)
                    val cell11 = content.createCell(11)
                    val cell13 = content.createCell(13)
                    val cell15 = content.createCell(15)
                    val cell17 = content.createCell(17)
                    val cell19 = content.createCell(19)
                    val cell21 = content.createCell(21)
                    val cell23 = content.createCell(23)
                    val cell25 = content.createCell(25)
                    val cell27 = content.createCell(27)
                    cell27.setCellType(CellType.FORMULA)

                    if(firstRowNum){
                        content.createCell(1).setCellValue("Budget ${budget_area_items.area_detail}")
                        firstRowNum = false
                    }else{
                        content.createCell(1).setCellValue("")
                    }
                    content.getCell(1).setCellStyle(styleTableContent)
                    content.createCell(2).setCellValue(budget_items.name)
                    content.getCell(2).setCellStyle(styleTableContent)
                    budget_items.i?.toDouble()?.let { cell3.setCellValue(it) }
                    content.getCell(3).setCellStyle(styleTableContentNumber)
                    content.createCell(4)
                    content.getCell(4).setCellStyle(styleTableContent)
                    budget_items.ii?.toDouble()?.let { cell5.setCellValue(it) }
                    content.getCell(5).setCellStyle(styleTableContentNumber)
                    content.createCell(6)
                    content.getCell(6).setCellStyle(styleTableContent)
                    budget_items.iii?.toDouble()?.let { cell7.setCellValue(it) }
                    content.getCell(7).setCellStyle(styleTableContentNumber)
                    content.createCell(8)
                    content.getCell(8).setCellStyle(styleTableContent)
                    budget_items.iv?.toDouble()?.let { cell9.setCellValue(it) }
                    content.getCell(9).setCellStyle(styleTableContentNumber)
                    content.createCell(10)
                    content.getCell(10).setCellStyle(styleTableContent)
                    budget_items.v?.toDouble()?.let { cell11.setCellValue(it) }
                    content.getCell(11).setCellStyle(styleTableContentNumber)
                    content.createCell(12)
                    content.getCell(12).setCellStyle(styleTableContent)
                    budget_items.vi?.toDouble()?.let { cell13.setCellValue(it) }
                    content.getCell(13).setCellStyle(styleTableContentNumber)
                    content.createCell(14)
                    content.getCell(14).setCellStyle(styleTableContent)
                    budget_items.vii?.toDouble()?.let { cell15.setCellValue(it) }
                    content.getCell(15).setCellStyle(styleTableContentNumber)
                    content.createCell(16)
                    content.getCell(16).setCellStyle(styleTableContent)
                    budget_items.viii?.toDouble()?.let { cell17.setCellValue(it) }
                    content.getCell(17).setCellStyle(styleTableContentNumber)
                    content.createCell(18)
                    content.getCell(18).setCellStyle(styleTableContent)
                    budget_items.ix?.toDouble()?.let { cell19.setCellValue(it) }
                    content.getCell(19).setCellStyle(styleTableContentNumber)
                    content.createCell(20)
                    content.getCell(20).setCellStyle(styleTableContent)
                    budget_items.x?.toDouble()?.let { cell21.setCellValue(it) }
                    content.getCell(21).setCellStyle(styleTableContentNumber)
                    content.createCell(22)
                    content.getCell(22).setCellStyle(styleTableContent)
                    budget_items.xi?.toDouble()?.let { cell23.setCellValue(it) }
                    content.getCell(23).setCellStyle(styleTableContentNumber)
                    content.createCell(24)
                    content.getCell(24).setCellStyle(styleTableContent)
                    budget_items.xii?.toDouble()?.let { cell25.setCellValue(it) }
                    content.getCell(25).setCellStyle(styleTableContentNumber)
                    content.createCell(26)
                    content.getCell(26).setCellStyle(styleTableContent)
                    cell27.cellFormula = "SUM(D$numRowLocal+F$numRowLocal+H$numRowLocal+J$numRowLocal+L$numRowLocal+N$numRowLocal+P$numRowLocal+R$numRowLocal+T$numRowLocal+V$numRowLocal+X$numRowLocal+Z$numRowLocal)"
                    content.getCell(27).setCellStyle(styleTableContentNumber)
                    content.createCell(28)
                    content.getCell(28).setCellStyle(styleTableContent)
                }
            }
        }

        content = sheet.createRow(numRowLocal)
        val cell3 = content.createCell(3)
        val cell5 = content.createCell(5)
        val cell7 = content.createCell(7)
        val cell9 = content.createCell(9)
        val cell11 = content.createCell(11)
        val cell13 = content.createCell(13)
        val cell15 = content.createCell(15)
        val cell17 = content.createCell(17)
        val cell19 = content.createCell(19)
        val cell21 = content.createCell(21)
        val cell23 = content.createCell(23)
        val cell25 = content.createCell(25)
        val cell27 = content.createCell(27)
        cell27.setCellType(CellType.FORMULA)

        content.createCell(1).setCellValue("")
        content.getCell(1).setCellStyle(styleTableHeader)
        content.createCell(2).setCellValue("Total Budget")
        content.getCell(2).setCellStyle(styleTableHeader)
        cell3.cellFormula = "SUM(D$firstRow:D$numRowLocal)"
        content.getCell(3).setCellStyle(styleTableHeaderNumber)
        content.createCell(4)
        content.getCell(4).setCellStyle(styleTableHeaderNumber)
        cell5.cellFormula = "SUM(F$firstRow:F$numRowLocal)"
        content.getCell(5).setCellStyle(styleTableHeaderNumber)
        content.createCell(6)
        content.getCell(6).setCellStyle(styleTableHeaderNumber)
        cell7.cellFormula = "SUM(H$firstRow:H$numRowLocal)"
        content.getCell(7).setCellStyle(styleTableHeaderNumber)
        content.createCell(8)
        content.getCell(8).setCellStyle(styleTableHeaderNumber)
        cell9.cellFormula = "SUM(J$firstRow:J$numRowLocal)"
        content.getCell(9).setCellStyle(styleTableHeaderNumber)
        content.createCell(10)
        content.getCell(10).setCellStyle(styleTableHeaderNumber)
        cell11.cellFormula = "SUM(L$firstRow:L$numRowLocal)"
        content.getCell(11).setCellStyle(styleTableHeaderNumber)
        content.createCell(12)
        content.getCell(12).setCellStyle(styleTableHeaderNumber)
        cell13.cellFormula = "SUM(N$firstRow:N$numRowLocal)"
        content.getCell(13).setCellStyle(styleTableHeaderNumber)
        content.createCell(14)
        content.getCell(14).setCellStyle(styleTableHeaderNumber)
        cell15.cellFormula = "SUM(P$firstRow:P$numRowLocal)"
        content.getCell(15).setCellStyle(styleTableHeaderNumber)
        content.createCell(16)
        content.getCell(16).setCellStyle(styleTableHeaderNumber)
        cell17.cellFormula = "SUM(R$firstRow:R$numRowLocal)"
        content.getCell(17).setCellStyle(styleTableHeaderNumber)
        content.createCell(18)
        content.getCell(18).setCellStyle(styleTableHeaderNumber)
        cell19.cellFormula = "SUM(T$firstRow:T$numRowLocal)"
        content.getCell(19).setCellStyle(styleTableHeaderNumber)
        content.createCell(20)
        content.getCell(20).setCellStyle(styleTableHeaderNumber)
        cell21.cellFormula = "SUM(V$firstRow:V$numRowLocal)"
        content.getCell(21).setCellStyle(styleTableHeaderNumber)
        content.createCell(22)
        content.getCell(22).setCellStyle(styleTableHeaderNumber)
        cell23.cellFormula = "SUM(X$firstRow:X$numRowLocal)"
        content.getCell(23).setCellStyle(styleTableHeaderNumber)
        content.createCell(24)
        content.getCell(24).setCellStyle(styleTableHeaderNumber)
        cell25.cellFormula = "SUM(Z$firstRow:Z$numRowLocal)"
        content.getCell(25).setCellStyle(styleTableHeaderNumber)
        content.createCell(26)
        content.getCell(26).setCellStyle(styleTableHeaderNumber)
        cell27.cellFormula = "SUM(AB$firstRow:AB$numRowLocal)"
        content.getCell(27).setCellStyle(styleTableHeaderNumber)
        content.createCell(28)
        content.getCell(28).setCellStyle(styleTableHeaderNumber)
        this.numRowTotalBudget = numRowLocal + 1
        this.numRow = ++numRowLocal
    }

    fun createRealisasiPreventiveXls(workbook: HSSFWorkbook, sheet: HSSFSheet,preventiveDetailDataList: List<PreventiveCustomerDetailHeader>){
        val styleTableContent = styleTableContent(workbook)
        val styleTableContentNumber = styleTableContentNumber(workbook)
        val styleTableHeader = styleTableHeader(workbook)
        val styleTableHeaderNumber = styleTableHeaderNumber(workbook)

        var numRowLocal = this.numRow
        val firstRow = this.numRow+1
        var content:HSSFRow
//        var firstRowNum = true

        preventiveDetailDataList.forEach {
            items->
            items.realisasi_budget_area?.forEach {
                realisasi_budget_area_items->
                var firstRowNum = true
                realisasi_budget_area_items.realisasi_budget?.forEach {
                    realisasi_budget_items->
                    content = sheet.createRow(numRowLocal++)
                    val cell3 = content.createCell(3)
                    val cell5 = content.createCell(5)
                    val cell7 = content.createCell(7)
                    val cell9 = content.createCell(9)
                    val cell11 = content.createCell(11)
                    val cell13 = content.createCell(13)
                    val cell15 = content.createCell(15)
                    val cell17 = content.createCell(17)
                    val cell19 = content.createCell(19)
                    val cell21 = content.createCell(21)
                    val cell23 = content.createCell(23)
                    val cell25 = content.createCell(25)
                    val cell27 = content.createCell(27)
                    cell27.setCellType(CellType.FORMULA)

                    if(firstRowNum){
                        content.createCell(1).setCellValue("Realisasi Budget ${realisasi_budget_area_items.area_detail}")
                        firstRowNum = false
                    }else{
                        content.createCell(1).setCellValue("")
                    }
                    content.getCell(1).setCellStyle(styleTableContent)
                    content.createCell(2).setCellValue(realisasi_budget_items.name)
                    content.getCell(2).setCellStyle(styleTableContent)
                    realisasi_budget_items.i?.toDouble()?.let { cell3.setCellValue(it) }
                    content.getCell(3).setCellStyle(styleTableContentNumber)
                    content.createCell(4)
                    content.getCell(4).setCellStyle(styleTableContent)
                    realisasi_budget_items.ii?.toDouble()?.let { cell5.setCellValue(it) }
                    content.getCell(5).setCellStyle(styleTableContentNumber)
                    content.createCell(6)
                    content.getCell(6).setCellStyle(styleTableContent)
                    realisasi_budget_items.iii?.toDouble()?.let { cell7.setCellValue(it) }
                    content.getCell(7).setCellStyle(styleTableContentNumber)
                    content.createCell(8)
                    content.getCell(8).setCellStyle(styleTableContent)
                    realisasi_budget_items.iv?.toDouble()?.let { cell9.setCellValue(it) }
                    content.getCell(9).setCellStyle(styleTableContentNumber)
                    content.createCell(10)
                    content.getCell(10).setCellStyle(styleTableContent)
                    realisasi_budget_items.v?.toDouble()?.let { cell11.setCellValue(it) }
                    content.getCell(11).setCellStyle(styleTableContentNumber)
                    content.createCell(12)
                    content.getCell(12).setCellStyle(styleTableContent)
                    realisasi_budget_items.vi?.toDouble()?.let { cell13.setCellValue(it) }
                    content.getCell(13).setCellStyle(styleTableContentNumber)
                    content.createCell(14)
                    content.getCell(14).setCellStyle(styleTableContent)
                    realisasi_budget_items.vii?.toDouble()?.let { cell15.setCellValue(it) }
                    content.getCell(15).setCellStyle(styleTableContentNumber)
                    content.createCell(16)
                    content.getCell(16).setCellStyle(styleTableContent)
                    realisasi_budget_items.viii?.toDouble()?.let { cell17.setCellValue(it) }
                    content.getCell(17).setCellStyle(styleTableContentNumber)
                    content.createCell(18)
                    content.getCell(18).setCellStyle(styleTableContent)
                    realisasi_budget_items.ix?.toDouble()?.let { cell19.setCellValue(it) }
                    content.getCell(19).setCellStyle(styleTableContentNumber)
                    content.createCell(20)
                    content.getCell(20).setCellStyle(styleTableContent)
                    realisasi_budget_items.x?.toDouble()?.let { cell21.setCellValue(it) }
                    content.getCell(21).setCellStyle(styleTableContentNumber)
                    content.createCell(22)
                    content.getCell(22).setCellStyle(styleTableContent)
                    realisasi_budget_items.xi?.toDouble()?.let { cell23.setCellValue(it) }
                    content.getCell(23).setCellStyle(styleTableContentNumber)
                    content.createCell(24)
                    content.getCell(24).setCellStyle(styleTableContent)
                    realisasi_budget_items.xii?.toDouble()?.let { cell25.setCellValue(it) }
                    content.getCell(25).setCellStyle(styleTableContentNumber)
                    content.createCell(26)
                    content.getCell(26).setCellStyle(styleTableContent)
                    cell27.cellFormula = "SUM(D$numRowLocal+F$numRowLocal+H$numRowLocal+J$numRowLocal+L$numRowLocal+N$numRowLocal+P$numRowLocal+R$numRowLocal+T$numRowLocal+V$numRowLocal+X$numRowLocal+Z$numRowLocal)"
                    content.getCell(27).setCellStyle(styleTableContentNumber)
                    content.createCell(28)
                    content.getCell(28).setCellStyle(styleTableContent)
                }
            }
        }

        content = sheet.createRow(numRowLocal)
        val cell3 = content.createCell(3)
        val cell5 = content.createCell(5)
        val cell7 = content.createCell(7)
        val cell9 = content.createCell(9)
        val cell11 = content.createCell(11)
        val cell13 = content.createCell(13)
        val cell15 = content.createCell(15)
        val cell17 = content.createCell(17)
        val cell19 = content.createCell(19)
        val cell21 = content.createCell(21)
        val cell23 = content.createCell(23)
        val cell25 = content.createCell(25)
        val cell27 = content.createCell(27)
        cell27.setCellType(CellType.FORMULA)

        content.createCell(1).setCellValue("")
        content.getCell(1).setCellStyle(styleTableHeader)
        content.createCell(2).setCellValue("Total Budget")
        content.getCell(2).setCellStyle(styleTableHeader)
        cell3.cellFormula = "SUM(D$firstRow:D$numRowLocal)"
        content.getCell(3).setCellStyle(styleTableHeaderNumber)
        content.createCell(4)
        content.getCell(4).setCellStyle(styleTableHeaderNumber)
        cell5.cellFormula = "SUM(F$firstRow:F$numRowLocal)"
        content.getCell(5).setCellStyle(styleTableHeaderNumber)
        content.createCell(6)
        content.getCell(6).setCellStyle(styleTableHeaderNumber)
        cell7.cellFormula = "SUM(H$firstRow:H$numRowLocal)"
        content.getCell(7).setCellStyle(styleTableHeaderNumber)
        content.createCell(8)
        content.getCell(8).setCellStyle(styleTableHeaderNumber)
        cell9.cellFormula = "SUM(J$firstRow:J$numRowLocal)"
        content.getCell(9).setCellStyle(styleTableHeaderNumber)
        content.createCell(10)
        content.getCell(10).setCellStyle(styleTableHeaderNumber)
        cell11.cellFormula = "SUM(L$firstRow:L$numRowLocal)"
        content.getCell(11).setCellStyle(styleTableHeaderNumber)
        content.createCell(12)
        content.getCell(12).setCellStyle(styleTableHeaderNumber)
        cell13.cellFormula = "SUM(N$firstRow:N$numRowLocal)"
        content.getCell(13).setCellStyle(styleTableHeaderNumber)
        content.createCell(14)
        content.getCell(14).setCellStyle(styleTableHeaderNumber)
        cell15.cellFormula = "SUM(P$firstRow:P$numRowLocal)"
        content.getCell(15).setCellStyle(styleTableHeaderNumber)
        content.createCell(16)
        content.getCell(16).setCellStyle(styleTableHeaderNumber)
        cell17.cellFormula = "SUM(R$firstRow:R$numRowLocal)"
        content.getCell(17).setCellStyle(styleTableHeaderNumber)
        content.createCell(18)
        content.getCell(18).setCellStyle(styleTableHeaderNumber)
        cell19.cellFormula = "SUM(T$firstRow:T$numRowLocal)"
        content.getCell(19).setCellStyle(styleTableHeaderNumber)
        content.createCell(20)
        content.getCell(20).setCellStyle(styleTableHeaderNumber)
        cell21.cellFormula = "SUM(V$firstRow:V$numRowLocal)"
        content.getCell(21).setCellStyle(styleTableHeaderNumber)
        content.createCell(22)
        content.getCell(22).setCellStyle(styleTableHeaderNumber)
        cell23.cellFormula = "SUM(X$firstRow:X$numRowLocal)"
        content.getCell(23).setCellStyle(styleTableHeaderNumber)
        content.createCell(24)
        content.getCell(24).setCellStyle(styleTableHeaderNumber)
        cell25.cellFormula = "SUM(Z$firstRow:Z$numRowLocal)"
        content.getCell(25).setCellStyle(styleTableHeaderNumber)
        content.createCell(26)
        content.getCell(26).setCellStyle(styleTableHeaderNumber)
        cell27.cellFormula = "SUM(AB$firstRow:AB$numRowLocal)"
        content.getCell(27).setCellStyle(styleTableHeaderNumber)
        content.createCell(28)
        content.getCell(28).setCellStyle(styleTableHeaderNumber)
        this.numRowTotalRealisasi = numRowLocal+1
        this.numRow = ++numRowLocal
    }

    fun createSummaryPreventiveXls(workbook: HSSFWorkbook, sheet: HSSFSheet, preventiveDetailDataList: List<PreventiveCustomerDetailHeader>){
        val styleTableContent = styleTableContent(workbook)
        val styleTableContentNumber = styleTableContentNumber(workbook)
        val styleTableHeader = styleTableHeader(workbook)
        val styleTableHeaderNumber = styleTableHeaderNumber(workbook)
        val styleTableHeaderPercent = styleTableHeaderPercent(workbook)

        var numRowLocal = this.numRow
        val firstRow = this.numRow+1
        var content:HSSFRow

        var cell3:HSSFCell
        var cell5:HSSFCell
        var cell7:HSSFCell
        var cell9:HSSFCell
        var cell11:HSSFCell
        var cell13:HSSFCell
        var cell15:HSSFCell
        var cell17:HSSFCell
        var cell19:HSSFCell
        var cell21:HSSFCell
        var cell23:HSSFCell
        var cell25:HSSFCell
        var cell27:HSSFCell

        content = sheet.createRow(numRowLocal++)
        cell3 = content.createCell(3)
        cell5 = content.createCell(5)
        cell7 = content.createCell(7)
        cell9 = content.createCell(9)
        cell11 = content.createCell(11)
        cell13 = content.createCell(13)
        cell15 = content.createCell(15)
        cell17 = content.createCell(17)
        cell19 = content.createCell(19)
        cell21 = content.createCell(21)
        cell23 = content.createCell(23)
        cell25 = content.createCell(25)
        cell27 = content.createCell(27)

        content.createCell(1).setCellValue("Laba/(Rugi) gross")
        content.getCell(1).setCellStyle(styleTableHeader)
        content.createCell(2).setCellValue("")
        content.getCell(2).setCellStyle(styleTableHeader)
        cell3.cellFormula = "D${this.numRowTotalNilaiInvoice}+D${this.numRowTotalRealisasi}"
        content.getCell(3).setCellStyle(styleTableHeaderNumber)
        content.createCell(4)
        content.getCell(4).setCellStyle(styleTableHeaderNumber)
        cell5.cellFormula = "F${this.numRowTotalNilaiInvoice}+F${this.numRowTotalRealisasi}"
        content.getCell(5).setCellStyle(styleTableHeaderNumber)
        content.createCell(6)
        content.getCell(6).setCellStyle(styleTableHeaderNumber)
        cell7.cellFormula = "H${this.numRowTotalNilaiInvoice}+H${this.numRowTotalRealisasi}"
        content.getCell(7).setCellStyle(styleTableHeaderNumber)
        content.createCell(8)
        content.getCell(8).setCellStyle(styleTableHeaderNumber)
        cell9.cellFormula = "J${this.numRowTotalNilaiInvoice}+J${this.numRowTotalRealisasi}"
        content.getCell(9).setCellStyle(styleTableHeaderNumber)
        content.createCell(10)
        content.getCell(10).setCellStyle(styleTableHeaderNumber)
        cell11.cellFormula = "L${this.numRowTotalNilaiInvoice}+L${this.numRowTotalRealisasi}"
        content.getCell(11).setCellStyle(styleTableHeaderNumber)
        content.createCell(12)
        content.getCell(12).setCellStyle(styleTableHeaderNumber)
        cell13.cellFormula = "N${this.numRowTotalNilaiInvoice}+N${this.numRowTotalRealisasi}"
        content.getCell(13).setCellStyle(styleTableHeaderNumber)
        content.createCell(14)
        content.getCell(14).setCellStyle(styleTableHeaderNumber)
        cell15.cellFormula = "P${this.numRowTotalNilaiInvoice}+P${this.numRowTotalRealisasi}"
        content.getCell(15).setCellStyle(styleTableHeaderNumber)
        content.createCell(16)
        content.getCell(16).setCellStyle(styleTableHeaderNumber)
        cell17.cellFormula = "R${this.numRowTotalNilaiInvoice}+R${this.numRowTotalRealisasi}"
        content.getCell(17).setCellStyle(styleTableHeaderNumber)
        content.createCell(18)
        content.getCell(18).setCellStyle(styleTableHeaderNumber)
        cell19.cellFormula = "T${this.numRowTotalNilaiInvoice}+T${this.numRowTotalRealisasi}"
        content.getCell(19).setCellStyle(styleTableHeaderNumber)
        content.createCell(20)
        content.getCell(20).setCellStyle(styleTableHeaderNumber)
        cell21.cellFormula = "V${this.numRowTotalNilaiInvoice}+V${this.numRowTotalRealisasi}"
        content.getCell(21).setCellStyle(styleTableHeaderNumber)
        content.createCell(22)
        content.getCell(22).setCellStyle(styleTableHeaderNumber)
        cell23.cellFormula = "X${this.numRowTotalNilaiInvoice}+X${this.numRowTotalRealisasi}"
        content.getCell(23).setCellStyle(styleTableHeaderNumber)
        content.createCell(24)
        content.getCell(24).setCellStyle(styleTableHeaderNumber)
        cell25.cellFormula = "Z${this.numRowTotalNilaiInvoice}+Z${this.numRowTotalRealisasi}"
        content.getCell(25).setCellStyle(styleTableHeaderNumber)
        content.createCell(26)
        content.getCell(26).setCellStyle(styleTableHeaderNumber)
        cell27.cellFormula = "AB${this.numRowTotalNilaiInvoice}+AB${this.numRowTotalRealisasi}"
        content.getCell(27).setCellStyle(styleTableHeaderNumber)
        content.createCell(28)
        content.getCell(28).setCellStyle(styleTableHeaderNumber)
        sheet.addMergedRegion(CellRangeAddress(numRowLocal-1, numRowLocal-1, 1,2))

        content = sheet.createRow(numRowLocal++)
        cell3 = content.createCell(3)
        cell5 = content.createCell(5)
        cell7 = content.createCell(7)
        cell9 = content.createCell(9)
        cell11 = content.createCell(11)
        cell13 = content.createCell(13)
        cell15 = content.createCell(15)
        cell17 = content.createCell(17)
        cell19 = content.createCell(19)
        cell21 = content.createCell(21)
        cell23 = content.createCell(23)
        cell25 = content.createCell(25)
        cell27 = content.createCell(27)

        content.createCell(1).setCellValue("Realisasi Budget vs nilai budget")
        content.getCell(1).setCellStyle(styleTableHeader)
        content.createCell(2).setCellValue("")
        content.getCell(2).setCellStyle(styleTableHeader)
        cell3.cellFormula = "D${this.numRowTotalRealisasi}/D${this.numRowTotalBudget}"
        content.getCell(3).setCellStyle(styleTableHeaderPercent)
        content.createCell(4)
        content.getCell(4).setCellStyle(styleTableHeaderPercent)
        cell5.cellFormula = "F${this.numRowTotalRealisasi}/F${this.numRowTotalBudget}"
        content.getCell(5).setCellStyle(styleTableHeaderPercent)
        content.createCell(6)
        content.getCell(6).setCellStyle(styleTableHeaderPercent)
        cell7.cellFormula = "H${this.numRowTotalRealisasi}/H${this.numRowTotalBudget}"
        content.getCell(7).setCellStyle(styleTableHeaderPercent)
        content.createCell(8)
        content.getCell(8).setCellStyle(styleTableHeaderPercent)
        cell9.cellFormula = "J${this.numRowTotalRealisasi}/J${this.numRowTotalBudget}"
        content.getCell(9).setCellStyle(styleTableHeaderPercent)
        content.createCell(10)
        content.getCell(10).setCellStyle(styleTableHeaderPercent)
        cell11.cellFormula = "L${this.numRowTotalRealisasi}/L${this.numRowTotalBudget}"
        content.getCell(11).setCellStyle(styleTableHeaderPercent)
        content.createCell(12)
        content.getCell(12).setCellStyle(styleTableHeaderPercent)
        cell13.cellFormula = "N${this.numRowTotalRealisasi}/N${this.numRowTotalBudget}"
        content.getCell(13).setCellStyle(styleTableHeaderPercent)
        content.createCell(14)
        content.getCell(14).setCellStyle(styleTableHeaderPercent)
        cell15.cellFormula = "P${this.numRowTotalRealisasi}/P${this.numRowTotalBudget}"
        content.getCell(15).setCellStyle(styleTableHeaderPercent)
        content.createCell(16)
        content.getCell(16).setCellStyle(styleTableHeaderPercent)
        cell17.cellFormula = "R${this.numRowTotalRealisasi}/R${this.numRowTotalBudget}"
        content.getCell(17).setCellStyle(styleTableHeaderPercent)
        content.createCell(18)
        content.getCell(18).setCellStyle(styleTableHeaderPercent)
        cell19.cellFormula = "T${this.numRowTotalRealisasi}/T${this.numRowTotalBudget}"
        content.getCell(19).setCellStyle(styleTableHeaderPercent)
        content.createCell(20)
        content.getCell(20).setCellStyle(styleTableHeaderPercent)
        cell21.cellFormula = "V${this.numRowTotalRealisasi}/V${this.numRowTotalBudget}"
        content.getCell(21).setCellStyle(styleTableHeaderPercent)
        content.createCell(22)
        content.getCell(22).setCellStyle(styleTableHeaderPercent)
        cell23.cellFormula = "X${this.numRowTotalRealisasi}/X${this.numRowTotalBudget}"
        content.getCell(23).setCellStyle(styleTableHeaderPercent)
        content.createCell(24)
        content.getCell(24).setCellStyle(styleTableHeaderPercent)
        cell25.cellFormula = "Z${this.numRowTotalRealisasi}/Z${this.numRowTotalBudget}"
        content.getCell(25).setCellStyle(styleTableHeaderPercent)
        content.createCell(26)
        content.getCell(26).setCellStyle(styleTableHeaderPercent)
        cell27.cellFormula = "AB${this.numRowTotalRealisasi}/AB${this.numRowTotalBudget}"
        content.getCell(27).setCellStyle(styleTableHeaderPercent)
        content.createCell(28)
        content.getCell(28).setCellStyle(styleTableHeaderPercent)
        sheet.addMergedRegion(CellRangeAddress(numRowLocal-1, numRowLocal-1, 1,2))

        content = sheet.createRow(numRowLocal++)
        cell3 = content.createCell(3)
        cell5 = content.createCell(5)
        cell7 = content.createCell(7)
        cell9 = content.createCell(9)
        cell11 = content.createCell(11)
        cell13 = content.createCell(13)
        cell15 = content.createCell(15)
        cell17 = content.createCell(17)
        cell19 = content.createCell(19)
        cell21 = content.createCell(21)
        cell23 = content.createCell(23)
        cell25 = content.createCell(25)
        cell27 = content.createCell(27)

        content.createCell(1).setCellValue("Realisasi Penagihan vs nilai PO")
        content.getCell(1).setCellStyle(styleTableHeader)
        content.createCell(2).setCellValue("")
        content.getCell(2).setCellStyle(styleTableHeader)
        cell3.cellFormula = "D${this.numRowTotalNilaiInvoice}/D${this.numRowTotalNilaiPO}"
        content.getCell(3).setCellStyle(styleTableHeaderPercent)
        content.createCell(4)
        content.getCell(4).setCellStyle(styleTableHeaderPercent)
        cell5.cellFormula = "F${this.numRowTotalNilaiInvoice}/F${this.numRowTotalNilaiPO}"
        content.getCell(5).setCellStyle(styleTableHeaderPercent)
        content.createCell(6)
        content.getCell(6).setCellStyle(styleTableHeaderPercent)
        cell7.cellFormula = "H${this.numRowTotalNilaiInvoice}/H${this.numRowTotalNilaiPO}"
        content.getCell(7).setCellStyle(styleTableHeaderPercent)
        content.createCell(8)
        content.getCell(8).setCellStyle(styleTableHeaderPercent)
        cell9.cellFormula = "J${this.numRowTotalNilaiInvoice}/J${this.numRowTotalNilaiPO}"
        content.getCell(9).setCellStyle(styleTableHeaderPercent)
        content.createCell(10)
        content.getCell(10).setCellStyle(styleTableHeaderPercent)
        cell11.cellFormula = "L${this.numRowTotalNilaiInvoice}/L${this.numRowTotalNilaiPO}"
        content.getCell(11).setCellStyle(styleTableHeaderPercent)
        content.createCell(12)
        content.getCell(12).setCellStyle(styleTableHeaderPercent)
        cell13.cellFormula = "N${this.numRowTotalNilaiInvoice}/N${this.numRowTotalNilaiPO}"
        content.getCell(13).setCellStyle(styleTableHeaderPercent)
        content.createCell(14)
        content.getCell(14).setCellStyle(styleTableHeaderPercent)
        cell15.cellFormula = "P${this.numRowTotalNilaiInvoice}/P${this.numRowTotalNilaiPO}"
        content.getCell(15).setCellStyle(styleTableHeaderPercent)
        content.createCell(16)
        content.getCell(16).setCellStyle(styleTableHeaderPercent)
        cell17.cellFormula = "R${this.numRowTotalNilaiInvoice}/R${this.numRowTotalNilaiPO}"
        content.getCell(17).setCellStyle(styleTableHeaderPercent)
        content.createCell(18)
        content.getCell(18).setCellStyle(styleTableHeaderPercent)
        cell19.cellFormula = "T${this.numRowTotalNilaiInvoice}/T${this.numRowTotalNilaiPO}"
        content.getCell(19).setCellStyle(styleTableHeaderPercent)
        content.createCell(20)
        content.getCell(20).setCellStyle(styleTableHeaderPercent)
        cell21.cellFormula = "V${this.numRowTotalNilaiInvoice}/V${this.numRowTotalNilaiPO}"
        content.getCell(21).setCellStyle(styleTableHeaderPercent)
        content.createCell(22)
        content.getCell(22).setCellStyle(styleTableHeaderPercent)
        cell23.cellFormula = "X${this.numRowTotalNilaiInvoice}/X${this.numRowTotalNilaiPO}"
        content.getCell(23).setCellStyle(styleTableHeaderPercent)
        content.createCell(24)
        content.getCell(24).setCellStyle(styleTableHeaderPercent)
        cell25.cellFormula = "Z${this.numRowTotalNilaiInvoice}/Z${this.numRowTotalNilaiPO}"
        content.getCell(25).setCellStyle(styleTableHeaderPercent)
        content.createCell(26)
        content.getCell(26).setCellStyle(styleTableHeaderPercent)
        cell27.cellFormula = "AB${this.numRowTotalNilaiInvoice}/AB${this.numRowTotalNilaiPO}"
        content.getCell(27).setCellStyle(styleTableHeaderPercent)
        content.createCell(28)
        content.getCell(28).setCellStyle(styleTableHeaderPercent)
        sheet.addMergedRegion(CellRangeAddress(numRowLocal-1, numRowLocal-1, 1,2))

        content = sheet.createRow(numRowLocal++)
        cell3 = content.createCell(3)
        cell5 = content.createCell(5)
        cell7 = content.createCell(7)
        cell9 = content.createCell(9)
        cell11 = content.createCell(11)
        cell13 = content.createCell(13)
        cell15 = content.createCell(15)
        cell17 = content.createCell(17)
        cell19 = content.createCell(19)
        cell21 = content.createCell(21)
        cell23 = content.createCell(23)
        cell25 = content.createCell(25)
        cell27 = content.createCell(27)

        content.createCell(1).setCellValue("Realisasi Budget vs Nilai PO")
        content.getCell(1).setCellStyle(styleTableHeader)
        content.createCell(2).setCellValue("")
        content.getCell(2).setCellStyle(styleTableHeader)
        cell3.cellFormula = "D${this.numRowTotalRealisasi}/D${this.numRowTotalNilaiPO}"
        content.getCell(3).setCellStyle(styleTableHeaderPercent)
        content.createCell(4)
        content.getCell(4).setCellStyle(styleTableHeaderPercent)
        cell5.cellFormula = "F${this.numRowTotalRealisasi}/F${this.numRowTotalNilaiPO}"
        content.getCell(5).setCellStyle(styleTableHeaderPercent)
        content.createCell(6)
        content.getCell(6).setCellStyle(styleTableHeaderPercent)
        cell7.cellFormula = "H${this.numRowTotalRealisasi}/H${this.numRowTotalNilaiPO}"
        content.getCell(7).setCellStyle(styleTableHeaderPercent)
        content.createCell(8)
        content.getCell(8).setCellStyle(styleTableHeaderPercent)
        cell9.cellFormula = "J${this.numRowTotalRealisasi}/J${this.numRowTotalNilaiPO}"
        content.getCell(9).setCellStyle(styleTableHeaderPercent)
        content.createCell(10)
        content.getCell(10).setCellStyle(styleTableHeaderPercent)
        cell11.cellFormula = "L${this.numRowTotalRealisasi}/L${this.numRowTotalNilaiPO}"
        content.getCell(11).setCellStyle(styleTableHeaderPercent)
        content.createCell(12)
        content.getCell(12).setCellStyle(styleTableHeaderPercent)
        cell13.cellFormula = "N${this.numRowTotalRealisasi}/N${this.numRowTotalNilaiPO}"
        content.getCell(13).setCellStyle(styleTableHeaderPercent)
        content.createCell(14)
        content.getCell(14).setCellStyle(styleTableHeaderPercent)
        cell15.cellFormula = "P${this.numRowTotalRealisasi}/P${this.numRowTotalNilaiPO}"
        content.getCell(15).setCellStyle(styleTableHeaderPercent)
        content.createCell(16)
        content.getCell(16).setCellStyle(styleTableHeaderPercent)
        cell17.cellFormula = "R${this.numRowTotalRealisasi}/R${this.numRowTotalNilaiPO}"
        content.getCell(17).setCellStyle(styleTableHeaderPercent)
        content.createCell(18)
        content.getCell(18).setCellStyle(styleTableHeaderPercent)
        cell19.cellFormula = "T${this.numRowTotalRealisasi}/T${this.numRowTotalNilaiPO}"
        content.getCell(19).setCellStyle(styleTableHeaderPercent)
        content.createCell(20)
        content.getCell(20).setCellStyle(styleTableHeaderPercent)
        cell21.cellFormula = "V${this.numRowTotalRealisasi}/V${this.numRowTotalNilaiPO}"
        content.getCell(21).setCellStyle(styleTableHeaderPercent)
        content.createCell(22)
        content.getCell(22).setCellStyle(styleTableHeaderPercent)
        cell23.cellFormula = "X${this.numRowTotalRealisasi}/X${this.numRowTotalNilaiPO}"
        content.getCell(23).setCellStyle(styleTableHeaderPercent)
        content.createCell(24)
        content.getCell(24).setCellStyle(styleTableHeaderPercent)
        cell25.cellFormula = "Z${this.numRowTotalRealisasi}/Z${this.numRowTotalNilaiPO}"
        content.getCell(25).setCellStyle(styleTableHeaderPercent)
        content.createCell(26)
        content.getCell(26).setCellStyle(styleTableHeaderPercent)
        cell27.cellFormula = "AB${this.numRowTotalRealisasi}/AB${this.numRowTotalNilaiPO}"
        content.getCell(27).setCellStyle(styleTableHeaderPercent)
        content.createCell(28)
        content.getCell(28).setCellStyle(styleTableHeaderPercent)
        sheet.addMergedRegion(CellRangeAddress(numRowLocal-1, numRowLocal-1, 1,2))

        content = sheet.createRow(numRowLocal++)
        cell3 = content.createCell(3)
        cell5 = content.createCell(5)
        cell7 = content.createCell(7)
        cell9 = content.createCell(9)
        cell11 = content.createCell(11)
        cell13 = content.createCell(13)
        cell15 = content.createCell(15)
        cell17 = content.createCell(17)
        cell19 = content.createCell(19)
        cell21 = content.createCell(21)
        cell23 = content.createCell(23)
        cell25 = content.createCell(25)
        cell27 = content.createCell(27)

        content.createCell(1).setCellValue("Budget vs PO")
        content.getCell(1).setCellStyle(styleTableHeader)
        content.createCell(2).setCellValue("")
        content.getCell(2).setCellStyle(styleTableHeader)
        cell3.cellFormula = "D${this.numRowTotalBudget}/D${this.numRowTotalNilaiPO}"
        content.getCell(3).setCellStyle(styleTableHeaderPercent)
        content.createCell(4)
        content.getCell(4).setCellStyle(styleTableHeaderPercent)
        cell5.cellFormula = "F${this.numRowTotalBudget}/F${this.numRowTotalNilaiPO}"
        content.getCell(5).setCellStyle(styleTableHeaderPercent)
        content.createCell(6)
        content.getCell(6).setCellStyle(styleTableHeaderPercent)
        cell7.cellFormula = "H${this.numRowTotalBudget}/H${this.numRowTotalNilaiPO}"
        content.getCell(7).setCellStyle(styleTableHeaderPercent)
        content.createCell(8)
        content.getCell(8).setCellStyle(styleTableHeaderPercent)
        cell9.cellFormula = "J${this.numRowTotalBudget}/J${this.numRowTotalNilaiPO}"
        content.getCell(9).setCellStyle(styleTableHeaderPercent)
        content.createCell(10)
        content.getCell(10).setCellStyle(styleTableHeaderPercent)
        cell11.cellFormula = "L${this.numRowTotalBudget}/L${this.numRowTotalNilaiPO}"
        content.getCell(11).setCellStyle(styleTableHeaderPercent)
        content.createCell(12)
        content.getCell(12).setCellStyle(styleTableHeaderPercent)
        cell13.cellFormula = "N${this.numRowTotalBudget}/N${this.numRowTotalNilaiPO}"
        content.getCell(13).setCellStyle(styleTableHeaderPercent)
        content.createCell(14)
        content.getCell(14).setCellStyle(styleTableHeaderPercent)
        cell15.cellFormula = "P${this.numRowTotalBudget}/P${this.numRowTotalNilaiPO}"
        content.getCell(15).setCellStyle(styleTableHeaderPercent)
        content.createCell(16)
        content.getCell(16).setCellStyle(styleTableHeaderPercent)
        cell17.cellFormula = "R${this.numRowTotalBudget}/R${this.numRowTotalNilaiPO}"
        content.getCell(17).setCellStyle(styleTableHeaderPercent)
        content.createCell(18)
        content.getCell(18).setCellStyle(styleTableHeaderPercent)
        cell19.cellFormula = "T${this.numRowTotalBudget}/T${this.numRowTotalNilaiPO}"
        content.getCell(19).setCellStyle(styleTableHeaderPercent)
        content.createCell(20)
        content.getCell(20).setCellStyle(styleTableHeaderPercent)
        cell21.cellFormula = "V${this.numRowTotalBudget}/V${this.numRowTotalNilaiPO}"
        content.getCell(21).setCellStyle(styleTableHeaderPercent)
        content.createCell(22)
        content.getCell(22).setCellStyle(styleTableHeaderPercent)
        cell23.cellFormula = "X${this.numRowTotalBudget}/X${this.numRowTotalNilaiPO}"
        content.getCell(23).setCellStyle(styleTableHeaderPercent)
        content.createCell(24)
        content.getCell(24).setCellStyle(styleTableHeaderPercent)
        cell25.cellFormula = "Z${this.numRowTotalBudget}/Z${this.numRowTotalNilaiPO}"
        content.getCell(25).setCellStyle(styleTableHeaderPercent)
        content.createCell(26)
        content.getCell(26).setCellStyle(styleTableHeaderPercent)
        cell27.cellFormula = "AB${this.numRowTotalBudget}/AB${this.numRowTotalNilaiPO}"
        content.getCell(27).setCellStyle(styleTableHeaderPercent)
        content.createCell(28)
        content.getCell(28).setCellStyle(styleTableHeaderPercent)
        sheet.addMergedRegion(CellRangeAddress(numRowLocal-1, numRowLocal-1, 1,2))

//        this.numRowTotalRealisasi = numRowLocal
//        this.numRow = ++numRowLocal

    }

    @RequestMapping("/preventive/download/xls/{customer_id}/{tahun}/{area_id}")
    fun downloadExcel(model:Model, response: HttpServletResponse,@PathVariable("customer_id") customer_id: Int,
                      @PathVariable("tahun") tahun: Int,
                      @PathVariable("area_id") area_id: String){

        val objectMapper = ObjectMapper()
        val url = URL(BASE_URL + "api/preventive_by_customer_year_area/%d/%d/%s".format(customer_id,tahun,area_id))
        val preventiveDetailDataList:List<PreventiveCustomerDetailHeader> = objectMapper.readValue(url)

        response.contentType = "application/vnd.ms-excel"
        response.setHeader("Content-Disposition", "attachment; filename=\"budget-preventive-file-$customer_id-$tahun-$area_id.xls\"")

        val workbook = HSSFWorkbook()
        val sheet = workbook.createSheet("Preventive")
        val sheet2 = workbook.createSheet("PO & INV")

        this.numRow = 8

        setColWidth(sheet)
        createHeaderPreventiveXls(workbook, sheet, preventiveDetailDataList)
        createPOPreventiveXls(workbook, sheet, preventiveDetailDataList)
        createInvoicePreventiveXls(workbook, sheet, preventiveDetailDataList)
        createBudgetPreventiveXls(workbook, sheet, preventiveDetailDataList)
        createRealisasiPreventiveXls(workbook, sheet, preventiveDetailDataList)
        createSummaryPreventiveXls(workbook, sheet, preventiveDetailDataList)

        val content = sheet2.createRow(1)
        content.createCell(1).setCellValue("Test")

        val out = response.outputStream
        workbook.write(out)
        out.flush()
        out.close()
        workbook.close()
    }

    //type : total_po, total_penagihan, total_budget, total_realisasi, total_laba_rugi
    fun getTotalPreventiveCustomer(data:List<PreventiveCustomerYear>, type:String): Long{
        var total: Long = 0
        data.forEach {
            items->
            items.detail?.forEach {
                item_details->
                item_details.detail?.forEach {
                    when (type){
                        "total_po" -> it.nilai_po?.let { total = total.plus(it) }
                        "total_penagihan" -> it.nilai_penagihan?.let { total = total.plus(it) }
                        "total_budget" -> it.nilai_budget?.let { total= total.plus(it) }
                        "total_realisasi" -> it.realisasi_budget?.let { total = total.plus(it) }
                        "total_laba_rugi" -> it.laba_rugi?.let { total = total.plus(it) }
                    }
                }
            }
        }
        return total
    }

    fun getTotalPreventiveCustomer(data:List<PreventiveCustomerYear>) = longArrayOf(
            getTotalPreventiveCustomer(data, "total_po"),
            getTotalPreventiveCustomer(data, "total_penagihan"),
            getTotalPreventiveCustomer(data, "total_budget"),
            getTotalPreventiveCustomer(data, "total_realisasi"),
            getTotalPreventiveCustomer(data, "total_laba_rugi")
    )

    @RequestMapping("/preventive")
    fun indexPreventive(model:Model): String{
        val objectMapper = ObjectMapper()
        val url = URL(BASE_URL + "api/preventive_customer")
        val preventiveDataList: List<PreventiveCustomerYear> = objectMapper.readValue(url)
        model.addAttribute("total", getTotalPreventiveCustomer(preventiveDataList))
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
