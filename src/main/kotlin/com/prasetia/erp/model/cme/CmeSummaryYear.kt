package com.prasetia.erp.model.cme

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class CmeSummaryYear(
        @Id
        val id: Long,
        val year_project: Long,
        val jumlah_site: Long,
        val site_cancel: Long,
        val nilai_po: Long,
        val nilai_invoice: Long,
        val nilai_budget: Long,
        val realisasi_budget: Float,
        val estimate_po: Long
){
    constructor(): this(0,0,0,0,0,0,0, 0f, 0)
}