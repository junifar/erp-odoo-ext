package com.prasetia.erp.controller.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.prasetia.erp.constant.GlobalConstant.Companion.BASE_URL
import com.prasetia.erp.model.preventive.PreventiveCustomer
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
        val preventiveDataList: List<PreventiveCustomer> = objectMapper.readValue(url)
        model.addAttribute("preventiveDataList", preventiveDataList)
        return "preventive/index"
    }

    @RequestMapping("/preventive/detail/{id}")
    fun detailPreventive(model: Model, @PathVariable("id") id: Int): String{
        return "preventive/detail"
    }

}