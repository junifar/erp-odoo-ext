package com.prasetia.erp.model.corrective

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class CorrectiveAdvance(
        @Id
        val id:Long,
        val year_project:String,
        val project_id:Long,
        val amount:Long,
        val narration:String,
        val ref:String,
        val pic:String,
        val penerima_dana:String,
        val tanggal: Date?,
        val ca_id: Long
){
    constructor(): this(0, "", 0, 0, "","", "", "", null, 0)
}