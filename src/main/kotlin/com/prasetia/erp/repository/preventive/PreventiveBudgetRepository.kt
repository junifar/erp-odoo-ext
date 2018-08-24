package com.prasetia.erp.repository.preventive

import com.prasetia.erp.model.preventive.PreventiveBudget
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Repository
import javax.websocket.server.PathParam

@Repository
interface PreventiveBudgetRepository:CrudRepository<PreventiveBudget, Long>{
    companion object {
        const val QUERY = """
                            SELECT
                            "public".budget_plan_line."id",
                            "public".project_site.area_id,
                            "public".project_site.bulan,
                            "public".project_site.tahun,
                            "public".project_site.customer_id,
                            budget_plan_line_parent."name",
                            Sum("public".budget_plan_line.amount) AS nilai_budget
                            FROM
                            "public".budget_plan_line
                            LEFT JOIN "public".budget_plan ON "public".budget_plan_line.budget_id = "public".budget_plan."id"
                            LEFT JOIN "public".project_project ON "public".budget_plan.project_id = "public".project_project."id"
                            LEFT JOIN "public".project_site ON "public".project_project.site_id = "public".project_site."id"
                            LEFT JOIN "public".budget_plan_line AS budget_plan_line_parent ON "public".budget_plan_line.parent_id = budget_plan_line_parent."id"
                            WHERE
                            "public".project_project."state" NOT IN ('cancelled') AND
                            "public".project_project.site_type_id = 7 AND
                            "public".project_site.tahun IS NOT NULL AND
                            "public".budget_plan."state" NOT IN ('draft', 'cancel') AND
                            "public".budget_plan_line.parent_id IS NOT NULL AND
                            "public".project_site.customer_id = :partner_id AND
                            "public".project_site.area_id  = :area_id AND
                            "public".project_site.tahun = :tahun
                            GROUP BY
                            "public".budget_plan_line."id",
                            budget_plan_line_parent."name",
                            "public".project_site.area_id,
                            "public".project_site.bulan,
                            "public".project_site.tahun,
                            "public".project_site.customer_id
                        """

        const val QUERY_NULL = """
                            SELECT
                            "public".budget_plan_line."id",
                            "public".project_site.area_id,
                            "public".project_site.bulan,
                            "public".project_site.tahun,
                            "public".project_site.customer_id,
                            budget_plan_line_parent."name",
                            Sum("public".budget_plan_line.amount) AS nilai_budget
                            FROM
                            "public".budget_plan_line
                            LEFT JOIN "public".budget_plan ON "public".budget_plan_line.budget_id = "public".budget_plan."id"
                            LEFT JOIN "public".project_project ON "public".budget_plan.project_id = "public".project_project."id"
                            LEFT JOIN "public".project_site ON "public".project_project.site_id = "public".project_site."id"
                            LEFT JOIN "public".budget_plan_line AS budget_plan_line_parent ON "public".budget_plan_line.parent_id = budget_plan_line_parent."id"
                            WHERE
                            "public".project_project."state" NOT IN ('cancelled') AND
                            "public".project_project.site_type_id = 7 AND
                            "public".project_site.tahun IS NOT NULL AND
                            "public".budget_plan."state" NOT IN ('draft', 'cancel') AND
                            "public".budget_plan_line.parent_id IS NOT NULL AND
                            "public".project_site.customer_id = :partner_id AND
                            "public".project_site.area_id  IS NULL AND
                            "public".project_site.tahun = :tahun
                            GROUP BY
                            "public".budget_plan_line."id",
                            budget_plan_line_parent."name",
                            "public".project_site.area_id,
                            "public".project_site.bulan,
                            "public".project_site.tahun,
                            "public".project_site.customer_id
                        """
    }

    @Async
    @Query(QUERY, nativeQuery = true)
    fun getPreventiveBudget(@PathParam("partner_id") partner_id:Int, @PathParam("tahun") tahun: String,
                            @PathParam("area_id") area_id:Int):Iterable<PreventiveBudget>

    @Query(QUERY_NULL, nativeQuery = true)
    fun getPreventiveBudgetNullArea(@PathParam("partner_id") partner_id:Int,
                                    @PathParam("tahun") tahun: String):Iterable<PreventiveBudget>
}