package com.prasetia.erp.controller.web.xls.corrective.sheet

import com.prasetia.erp.pojo.corrective.CorrectiveYearData
import org.apache.poi.hssf.usermodel.HSSFCellStyle
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.hssf.util.HSSFColor
import org.apache.poi.ss.usermodel.BorderStyle
import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.ss.usermodel.HorizontalAlignment
import org.apache.poi.ss.util.CellRangeAddress

class SheetCorrectiveYear(workbook: HSSFWorkbook, tahun: String, data:List<CorrectiveYearData>){
    var sheet: HSSFSheet = workbook.createSheet("Summary $tahun")
    var numRow:Int = 4

    fun setColWidth(sheet: HSSFSheet):HSSFSheet{
        sheet.setColumnWidth(0, 1560)
        sheet.setColumnWidth(1, 13520)
        sheet.setColumnWidth(2, 4940)
        sheet.setColumnWidth(3, 8580)
        sheet.setColumnWidth(4, 2860)
        sheet.setColumnWidth(5, 4420)
        sheet.setColumnWidth(6, 3120)
        sheet.setColumnWidth(7, 3640)
        sheet.setColumnWidth(8, 260)
        sheet.setColumnWidth(9, 260)
        sheet.setColumnWidth(10, 4420)
        sheet.setColumnWidth(11, 5720)
        sheet.setColumnWidth(12, 2080)
        sheet.setColumnWidth(13, 4420)
        sheet.setColumnWidth(14, 2860)
        sheet.setColumnWidth(15, 2340)
        sheet.setColumnWidth(16, 10140)
        sheet.setColumnWidth(17, 4160)
        sheet.setColumnWidth(18, 6240)
        sheet.setColumnWidth(19, 4420)
        sheet.setColumnWidth(20, 4680)
        sheet.setColumnWidth(21, 2600)
        sheet.setColumnWidth(22, 3120)
        return sheet
    }

    fun styleHeader(workbook: HSSFWorkbook): HSSFCellStyle {
        val styleHeader = workbook.createCellStyle()
        val fontCalibri = workbook.createFont()
        fontCalibri.fontName = "Calibri"
        fontCalibri.bold = true
        fontCalibri.fontHeightInPoints = 11
        styleHeader.setFont(fontCalibri)
        return styleHeader
    }

    fun styleTableHeader(workbook: HSSFWorkbook): HSSFCellStyle {
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

    fun createHeaderXls(workbook: HSSFWorkbook, sheet:HSSFSheet){
        val styleTableHeader = styleTableHeader(workbook)

        var header = sheet.createRow(4)
        header.createCell(0).setCellValue("No")
        header.getCell(0).setCellStyle(styleTableHeader)
        header.createCell(1).setCellValue("Pembayaran")
        header.getCell(1).setCellStyle(styleTableHeader)
        header.createCell(2).setCellValue("PIC")
        header.getCell(2).setCellStyle(styleTableHeader)
        header.createCell(3).setCellValue("Penerima Dana")
        header.getCell(3).setCellStyle(styleTableHeader)
        header.createCell(4).setCellValue("TGL")
        header.getCell(4).setCellStyle(styleTableHeader)
        header.createCell(5).setCellValue("Start No CA/GPR/RMB")
        header.getCell(5).setCellStyle(styleTableHeader)
        header.createCell(6).setCellValue("Status")
        header.getCell(6).setCellStyle(styleTableHeader)
        header.createCell(7).setCellValue("Qty")
        header.getCell(7).setCellStyle(styleTableHeader)
        header.createCell(8).setCellValue("Unit")
        header.getCell(8).setCellStyle(styleTableHeader)
        header.createCell(9).setCellValue("Price")
        header.getCell(9).setCellStyle(styleTableHeader)
        header.createCell(10).setCellValue("Start Nominal")
        header.getCell(10).setCellStyle(styleTableHeader)
        header.createCell(11).setCellValue("Site / Dept")
        header.getCell(11).setCellStyle(styleTableHeader)
        header.createCell(12).setCellValue("No Memo")
        header.getCell(12).setCellStyle(styleTableHeader)
        header.createCell(13).setCellValue("Realisasi Payment")
        header.getCell(13).setCellStyle(styleTableHeader)
        header.createCell(14).setCellValue("Start Payment")
        header.getCell(14).setCellStyle(styleTableHeader)
        header.createCell(15).setCellValue("Cust")
        header.getCell(15).setCellStyle(styleTableHeader)
        header.createCell(16).setCellValue("Nomor PO/SPK/WO/BAST")
        header.getCell(16).setCellStyle(styleTableHeader)
        header.createCell(17).setCellValue("Nilai PO")
        header.getCell(17).setCellStyle(styleTableHeader)
        header.createCell(18).setCellValue("Nilai INV")
        header.getCell(18).setCellStyle(styleTableHeader)
        header.createCell(19).setCellValue("Nilai Penagihan INV")
        header.getCell(19).setCellStyle(styleTableHeader)
        header.createCell(20).setCellValue("Laba / Rugi")
        header.getCell(20).setCellStyle(styleTableHeader)
        header.createCell(21).setCellValue("% Laba / Rugi")
        header.getCell(21).setCellStyle(styleTableHeader)
        header.createCell(22).setCellValue("Status INV")
        header.getCell(22).setCellStyle(styleTableHeader)
    }

    init {
        setColWidth(sheet)
        createHeaderXls(workbook, sheet)
    }
}