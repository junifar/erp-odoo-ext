package com.prasetia.erp.model.preventive

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class PreventiveRealisasiBudget(
       @Id
       val id:Long,
       val area_id:Long?,
       val bulan:Long,
       val tahun:Long,
       val customer_id: Long?,
       val name:String,
       val realisasi_budget: Long?
){
    constructor(): this(0, 0, 0, 0, 0, "", 0)
}