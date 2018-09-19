package com.prasetia.erp.controller.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.prasetia.erp.constant.GlobalConstant.Companion.BASE_URL
import com.prasetia.erp.pojo.corrective.CorrectiveCustomerSummaryData
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import java.net.URL

@Controller("Corrective Web Controller")
class CorrectiveController{
    @RequestMapping("/corrective")
    fun indexCorrective(model: Model): String{
        val objectMapper = ObjectMapper()
        val url = URL(BASE_URL + "api/corrective_summary")
        val correctiveSummaryDataList: List<CorrectiveCustomerSummaryData> = objectMapper.readValue(url)
        model.addAttribute("correctiveSummaryDataList", correctiveSummaryDataList)
        return "corrective/index"
    }
}