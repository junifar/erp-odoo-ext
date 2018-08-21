package com.prasetia.erp.pojo

import javax.persistence.Id


class PreventiveCustomerYear(
        @Id
        val id: Long,
        var tahun: String,
        var detail: MutableList<PreventiveCustomerGroup>?
){
    constructor(): this(0,"", null)
}

class PreventiveCustomerGroup(
        @Id
        val id:Long,
        var customer: String,
        var customer_id: Long?,
        var detail: MutableList<PreventiveCustomerGroupDetail>?
){
    constructor(): this(0, "", 0, null)
}

class PreventiveCustomerGroupDetail(
        @Id
        val id: Long,
        val area: String?,
        val nilai_po: Long?,
        val nilai_penagihan: Long?,
        val nilai_budget: Long?,
        val realisasi_budget: Long?,
        val laba_rugi: Long?,
        val persent_penagihan:Long?,
        val persent_budget:Long?,
        val persent_laba_rugi:Long?
){
    constructor(): this(0, "", 0, 0, 0, 0, 0, 0, 0, 0)
}