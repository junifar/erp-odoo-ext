package com.prasetia.erp.controller.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.prasetia.erp.constant.GlobalConstant
import com.prasetia.erp.pojo.cme.CmeSummaryYearData
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import java.net.URL

@Controller("CME Web Controller")
class CmeController{

    @RequestMapping("/project")
    fun indexCME(model: Model): String{
        val objectMapper = ObjectMapper()
        val url = URL(GlobalConstant.BASE_URL + "api/project_summary_year")
        val cmeSummaryYearDataList: List<CmeSummaryYearData> = objectMapper.readValue(url)
        model.addAttribute("cmeSummaryYearDataList", cmeSummaryYearDataList)
        return "project/index"
    }
}