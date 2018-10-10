package com.prasetia.erp.pojo.cme

import javax.persistence.Id

class CmeSummaryYearProjectTypeCustData(
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
        val site_type_id: Long?,
        val customer:String?,
        val customer_id:Long?,
        val percentage: Float,
        val remaining_invoice: Long,
        val percentage_realization: Float,
        val profit_loss: Long,
        val percentage_profit_realization: Float,
        val percentage_profit_po: Float,
        var project_list: List<CmeYearProjectTypeCustProjectDetailData>?
){
    constructor(): this(0,0,0,"",0,0,0,0,
            0,0, 0, "", 0, 0f, 0,
            0f, 0, 0f, 0f, null)
}
class CmeYearProjectTypeCustProjectDetailData(@Id
                                              val id: Long,
                                              val name:String,
                                              val year_project: Long,
                                              val project_type: String,
                                              val project_id: String,
                                              val nilai_po: Long,
                                              val no_po: String?,
                                              val nilai_invoice: Long,
                                              val nilai_budget: Long,
                                              val realisasi_budget: Long,
                                              val estimate_po: Long,
                                              val customer:String?,
                                              val customer_id:Long?,
                                              val site_type_id: Long?,
                                              val area: String?
){
    constructor(): this(0,"",0,"","",0,"",
            0,0,0,0,"", 0, 0, "")
}