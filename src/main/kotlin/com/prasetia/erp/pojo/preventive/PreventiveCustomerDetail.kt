package com.prasetia.erp.pojo.preventive

import javax.persistence.Id

class PreventiveCustomerDetailHeader(
        @Id
        val id:Long,
        val customer_id:Long?,
        val customer_name:String?,
        val area:String?,
        val area_id:Long?,
        val tahun:String,
        var sale_order: MutableList<PreventiveClientOrderRef>?
){
    constructor(): this(0, 0, "", "", 0, "", null)
}

class PreventiveClientOrderRef(
        @Id
        val id:Long,
        val client_order_ref: String,
        var sale_order_line: MutableList<PreventiveSaleOrder>?
){
    constructor(): this(0, "", null)
}

class PreventiveSaleOrder(
        @Id
        val bulan:Long,
        val nilai_po: Long?
){
    constructor(): this(0, 0)
}