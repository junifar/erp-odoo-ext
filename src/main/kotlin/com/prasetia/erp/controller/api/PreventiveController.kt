package com.prasetia.erp.controller.api

import com.prasetia.erp.model.preventive.PreventiveCustomer
import com.prasetia.erp.pojo.PreventiveCustomerMonth
import com.prasetia.erp.pojo.PreventiveCustomerYear
import com.prasetia.erp.pojo.PreventiveCustomerYearDetail
import com.prasetia.erp.repository.PreventiveCustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PreventiveController{

    @Autowired
    lateinit var repository: PreventiveCustomerRepository

    @RequestMapping("/api/preventive_customer")
    fun getAllData()= repository.getAllData()
}