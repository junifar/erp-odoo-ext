package com.prasetia.erp.pojo.cme

import javax.persistence.Id

class CmeSummaryYearData(
        @Id
        val id: Long,
        val year_project: Long,
        val jumlah_site: Long,
        val site_cancel: Long,
        val nilai_po: Long,
        val nilai_invoice: Long,
        val nilai_budget: Long,
        val realisasi_budget: Float,
        val estimate_po: Long,
        val percentage: Float,
        val remaining_invoice: Long,
        val percentage_realization: Float,
        val profit_loss: Float,
        val percentage_profit_realization: Float,
        val percentage_profit_po: Float
){
    constructor(): this(0,0,0,0,0,0,0,
            0F, 0, 0f, 0, 0f, 0F,
            0f, 0f)
}