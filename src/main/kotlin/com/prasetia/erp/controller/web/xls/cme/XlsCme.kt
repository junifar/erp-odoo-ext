package com.prasetia.erp.controller.web.xls.cme

import com.prasetia.erp.controller.web.xls.cme.sheet.SheetSummary
import com.prasetia.erp.pojo.cme.CmeSummaryYearProjectTypeCustData
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import javax.servlet.http.HttpServletResponse

class XlsCme(response:HttpServletResponse, tahun: String, type_id: Int, data:List<CmeSummaryYearProjectTypeCustData>){
    var workbook:HSSFWorkbook = HSSFWorkbook()
    init {
        SheetSummary(workbook, data)
        val out = response.outputStream
        workbook.write(out)
        out.flush()
        out.close()
        workbook.close()
    }
}