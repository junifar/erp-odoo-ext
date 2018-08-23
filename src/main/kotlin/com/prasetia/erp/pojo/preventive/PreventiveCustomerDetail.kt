package com.prasetia.erp.pojo.preventive

import javax.persistence.Id

class preventiveCustomerDetailHeader(
        @Id
        val id:Long,
        val customer_id:Long?,
        val customer_name:String?,
        val area:String?,
        val area_id:Long?,
        val tahun:String
){
    constructor(): this(0, 0, "", "", 0, "")
}