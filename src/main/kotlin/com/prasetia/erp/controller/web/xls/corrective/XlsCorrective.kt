package com.prasetia.erp.controller.web.xls.corrective

import com.prasetia.erp.controller.web.xls.corrective.sheet.SheetSummary
import com.prasetia.erp.pojo.corrective.CorrectiveYearData
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import javax.servlet.http.HttpServletResponse

class XlsCorrective(response: HttpServletResponse, data:List<CorrectiveYearData>){
    var workbook: HSSFWorkbook = HSSFWorkbook()
    init {
        SheetSummary(workbook, data)

        val out = response.outputStream
        workbook.write(out)
        out.flush()
        out.close()
        workbook.close()
    }
}