package com.prasetia.erp.model.cme

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class CmeSummaryYearProjectType(
        @Id
        val id: Long,
        val year_project: Long,
        val jumlah_site: Long,
        val project_type: String,
        val site_cancel: Long,
        val nilai_po: Long,
        val nilai_invoice: Long,
        val nilai_budget: Long,
        val realisasi_budget: Long,
        val estimate_po: Long,
        val site_type_id: Long?
){
    constructor(): this(0,0,0,"",0,0,0,0,0,0, 0)
}