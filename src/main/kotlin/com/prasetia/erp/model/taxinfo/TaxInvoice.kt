package com.prasetia.erp.model.taxinfo

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class TaxInvoice(
        @Id
        val id:Long,
        val invoice_id:Long?,
        val nomor_faktur:String?,
        val invoice_no:String?,
        val customer_name:String?,
        val state:String?,
        val tax_amount:Float?,
        val name:String?,
        val total:Float?,
        val subtotal_original:Float?,
        val subtotal:Float?,
        val tanggal_pembayaran: Date?,
        val bank:String?,
        val ref:String?,
        val voucher_id:Long?
){
    constructor(): this(0, 0, "", "","","",0f,
            "", 0f, 0f, 0f, null, "", "", 0)
}
