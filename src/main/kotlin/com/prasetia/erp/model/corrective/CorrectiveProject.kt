package com.prasetia.erp.model.corrective

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class CorrectiveProject(
        @Id
        val id:Long,
        val year_project:String,
        val site_name:String,
        val customer:String
){
    constructor(): this(0,"","","")
}