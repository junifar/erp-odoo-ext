package com.prasetia.erp.controller.web

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller("Preventive Web Controller")
class PreventiveController{

    @RequestMapping("/preventive")
    fun index(model:Model): String{
        return "preventive/index"
    }
}