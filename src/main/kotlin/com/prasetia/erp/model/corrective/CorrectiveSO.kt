package com.prasetia.erp.model.corrective

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class CorrectiveSO(
        @Id
        val id:Long,
        val year_project:Long,
        val site_name:String,
        val project_id:String,
        val no_po:String,
        val nilai_po:Long,
        val nilai_invoice:Long,
        val persent_invoice:Float
){
    constructor(): this(0,0,"","","",0,0,0f)
}