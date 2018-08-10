package com.prasetia.erp.repository

import com.prasetia.erp.model.preventive.PreventiveCustomer
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Repository

@Repository
interface PreventiveCustomerRepository: CrudRepository<PreventiveCustomer, Long>{
    companion object {
        const val QUERY = """
                        SELECT
                            ROW_NUMBER() OVER (ORDER BY "public".res_partner."id") AS id,
                            "public".res_partner."id" AS customer_id,
                            "public".res_partner."name" AS customer_name,
                            "public".project_site.tahun,
														"public".project_site.bulan,
                            COALESCE(SUM(A.nilai_po),0) AS nilai_po
                        FROM
                            "public".project_project
                        LEFT JOIN "public".project_site ON "public".project_project.site_id = "public".project_site."id"
                        LEFT JOIN "public".res_partner ON "public".project_site.customer_id = "public".res_partner."id"
                        LEFT JOIN (
                                SELECT
                                "public".sale_order_line.project_id,
                                "public".sale_order_line.price_unit * "public".sale_order_line.product_uom_qty nilai_po
                                FROM
                                "public".sale_order_line
                                LEFT JOIN "public".sale_order ON "public".sale_order_line.order_id = "public".sale_order."id"
                                WHERE
                                "public".sale_order."state" NOT IN ('draft', 'cancel')
                            ) AS A ON A.project_id = "public".project_project."id"
                        WHERE
                            "public".project_project.site_type_id = 7 AND
                            "public".project_site.tahun IS NOT NULL AND
                            "public".project_site.bulan IS NOT NULL AND
                            "public".res_partner."id" IS NOT NULL
                        GROUP BY
                            "public".res_partner."id",
                            "public".res_partner."name",
                            "public".project_site.tahun,
                            "public".project_site.bulan
                        """
    }

    @Async
    @Query(QUERY, nativeQuery = true)
    fun getAllData(): Iterable<PreventiveCustomer>
}