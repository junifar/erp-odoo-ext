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
        var sale_order: MutableList<PreventiveSaleOrder>?,
        var invoice: MutableList<PreventiveInvoice>?,
        var budget: MutableList<PreventiveBudget>?
){
    constructor(): this(0, 0, "", "", 0, "", null, null, null)
}

class PreventiveSaleOrder(
        @Id
        val id:Long,
        val client_order_ref: String,
        val i:Long?,
        val ii:Long?,
        val iii:Long?,
        val iv:Long?,
        val v:Long?,
        val vi:Long?,
        val vii:Long?,
        val viii:Long?,
        val ix:Long?,
        val x:Long?,
        val xi:Long?,
        val xii:Long?,
        val total: Long?
){
    constructor(): this(0, "", 0, 0,0,0,0,0,0,0,0,0,0,0, 0)
}

class PreventiveInvoice(
        @Id
        val id:Long,
        val client_order_ref: String,
        val i:Long?,
        val ii:Long?,
        val iii:Long?,
        val iv:Long?,
        val v:Long?,
        val vi:Long?,
        val vii:Long?,
        val viii:Long?,
        val ix:Long?,
        val x:Long?,
        val xi:Long?,
        val xii:Long?,
        val total: Long?
){
    constructor(): this(0, "", 0, 0,0,0,0,0,0,0,0,0,0,0, 0)
}

class PreventiveBudget(
        @Id
        val id:Long,
        val name: String,
        val i:Long?,
        val ii:Long?,
        val iii:Long?,
        val iv:Long?,
        val v:Long?,
        val vi:Long?,
        val vii:Long?,
        val viii:Long?,
        val ix:Long?,
        val x:Long?,
        val xi:Long?,
        val xii:Long?,
        val total: Long?
){
    constructor(): this(0, "", 0, 0,0,0,0,0,0,0,0,0,0,0, 0)
}