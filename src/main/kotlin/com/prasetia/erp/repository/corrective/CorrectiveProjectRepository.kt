package com.prasetia.erp.repository.corrective

import com.prasetia.erp.model.corrective.CorrectiveProject
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Repository
import javax.websocket.server.PathParam

@Repository
interface CorrectiveProjectRepository:CrudRepository<CorrectiveProject, Long>{
    companion object {
        const val QUERY = """
                        SELECT
                        "public".project_project."id",
                        "public".project_project.year_project,
                        "public".project_site."name" AS site_name,
                        "public".res_partner.code AS customer
                        FROM
                        "public".project_project
                        LEFT JOIN "public".project_site ON "public".project_project.site_id = "public".project_site."id"
                        LEFT JOIN "public".res_partner ON "public".project_site.customer_id = "public".res_partner."id"
                        WHERE
                        "public".project_project.site_type_id = 8 AND
                        "public".project_project.year_project = :tahun
                        """
    }

    @Async
    @Query(QUERY, nativeQuery = true)
    fun getCorrectiveProject(@PathParam("tahun") tahun:String):Iterable<CorrectiveProject>
}