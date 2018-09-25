package com.prasetia.erp.model.corrective

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class CorrectiveAdvanceInvoice(
        @Id
        val id:Long,
        val nilai_invoice:Long,
        val invoice_state:String,
        val no_inv:String,
        val year_project:String
){
    constructor(): this(0, 0, "", "", "")
}