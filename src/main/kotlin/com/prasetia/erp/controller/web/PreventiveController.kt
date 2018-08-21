package com.prasetia.erp.controller.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.prasetia.erp.constant.GlobalConstant.Companion.BASE_URL
import com.prasetia.erp.pojo.PreventiveCustomerYear
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
        preventiveDataList.forEach{
           println(it.tahun)
        }
        model.addAttribute("preventiveDataList", preventiveDataList)
        return "preventive/index"
    }

    @RequestMapping("/preventive/detail/{customer_id}/{tahun}/{area_id}")
    fun detailPreventive(model: Model, @PathVariable("customer_id") customer_id: Int, @PathVariable("tahun") tahun: Int,
                         @PathVariable("area_id") area_id: String): String{
        println(tahun)
        return "preventive/detail"
    }

}