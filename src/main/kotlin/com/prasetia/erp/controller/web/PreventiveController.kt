package com.prasetia.erp.controller.web

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller("Preventive Web Controller")
class PreventiveController{

    @RequestMapping("/preventive")
    fun indexPreventive(model:Model): String{
        return "preventive/index"
    }

    @RequestMapping("/preventive/detail/{id}")
    fun detailPreventive(model: Model, @PathVariable("id") id: Int): String{
        return "preventive/detail"
    }

}