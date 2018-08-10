package com.prasetia.erp.pojo


class PreventiveCustomerYear(
        var tahun: String,
        var detail: MutableList<PreventiveCustomerMonth>?
)

class PreventiveCustomerMonth(
        var month: Int?,
        var detail: MutableList<PreventiveCustomerYearDetail>?
)

class PreventiveCustomerYearDetail(
        var customer_name: String,
        var customer_id: Long,
        val nilai_po: Long
)