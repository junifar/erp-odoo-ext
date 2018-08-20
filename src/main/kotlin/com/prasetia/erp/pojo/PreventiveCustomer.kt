package com.prasetia.erp.pojo


class PreventiveCustomerYear(
        var tahun: String,
        var detail: MutableList<PreventiveCustomerGroup>?
)

class PreventiveCustomerGroup(
        var customer: String,
        var customer_id: Long?,
        var detail: MutableList<PreventiveCustomerGroupDetail>?
)

class PreventiveCustomerGroupDetail(
        val id: Long,
        val area: String?,
        val nilai_po: Long?,
        val nilai_penagihan: Long?,
        val nilai_budget: Long?,
        val realisasi_budget: Long?,
        val laba_rugi: Long?
)