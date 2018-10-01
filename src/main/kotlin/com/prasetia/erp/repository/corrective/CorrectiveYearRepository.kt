package com.prasetia.erp.repository.corrective

import com.prasetia.erp.model.corrective.CorrectiveYear
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Repository
import javax.websocket.server.PathParam

@Repository
interface CorrectiveYearRepository:CrudRepository<CorrectiveYear, Long>{
    companion object {
        const val QUERY = """
                            SELECT
                                ROW_NUMBER() OVER (ORDER BY "public".res_partner."id") AS id,
                                "public".res_partner."id" AS customer_id,
                                "public".res_partner.code,
                                Count("public".project_project."id") AS jumlah_site,
                                --"public".project_project.year_project,
                                EXTRACT(YEAR FROM "public".project_project.tanggal_surat_tugas) as year_project,
                                Sum(A.nilai_po) AS nilai_po,
                                Sum(B.nilai_inv) AS nilai_inv,
                                round(Sum(B.nilai_inv)/Sum(A.nilai_po),2) AS percentage,
                                Sum(C.realisasi_budget) AS realisasi_budget,
                                Sum(B.nilai_inv) - Sum(C.realisasi_budget) AS profit,
                                CASE WHEN Sum(C.realisasi_budget) = NULL THEN 0 ELSE (Sum(B.nilai_inv) - Sum(C.realisasi_budget))/Sum(C.realisasi_budget) END AS profit_percentage
                            FROM
                                "public".project_project
                                LEFT JOIN "public".project_site ON "public".project_project.site_id = "public".project_site."id"
                                LEFT JOIN "public".res_partner ON "public".project_site.customer_id = "public".res_partner."id"
                                LEFT JOIN (
                                        SELECT
                                        "public".sale_order_line.project_id,
                                        Sum("public".sale_order_line.price_unit * "public".sale_order_line.product_uom_qty) AS nilai_po
                                        FROM
                                        "public".sale_order_line
                                        LEFT JOIN "public".sale_order ON "public".sale_order_line.order_id = "public".sale_order."id"
                                        LEFT JOIN "public".project_project ON "public".sale_order_line.project_id = "public".project_project."id"
                                        WHERE
                                        "public".sale_order."state" NOT IN ('draft', 'cancel') AND
                                        "public".project_project.site_type_id = 8
                                        GROUP BY
                                        "public".sale_order_line.project_id
                                    ) AS A ON "public".project_project."id" = A.project_id
                                LEFT JOIN (
                                    SELECT
                                        "public".sale_order_line.project_id,
                                        Sum("public".account_invoice_line.price_subtotal) AS nilai_inv
                                        FROM
                                        "public".sale_order_line
                                        LEFT JOIN "public".sale_order ON "public".sale_order_line.order_id = "public".sale_order."id"
                                        LEFT JOIN "public".project_project ON "public".sale_order_line.project_id = "public".project_project."id"
                                        LEFT JOIN "public".sale_order_line_invoice_rel ON "public".sale_order_line_invoice_rel.order_line_id = "public".sale_order_line."id"
                                        LEFT JOIN "public".account_invoice_line ON "public".sale_order_line_invoice_rel.invoice_id = "public".account_invoice_line."id"
                                        LEFT JOIN "public".account_invoice ON "public".account_invoice_line.invoice_id = "public".account_invoice."id"
                                        WHERE
                                        "public".sale_order."state" NOT IN ('draft', 'cancel') AND
                                        "public".project_project.site_type_id = 8 AND
                                        "public".account_invoice."state" NOT IN ('draft', 'cancel')
                                        GROUP BY
                                        "public".sale_order_line.project_id
                                    ) AS B ON "public".project_project."id" = B.project_id
                                LEFT JOIN (
                                            SELECT
                                            "public".project_project."id",
                                            Max(AB.budget_realisasi) AS realisasi_budget
                                            FROM
                                            "public".project_project
                                            LEFT JOIN "public".budget_plan ON "public".budget_plan.project_id = "public".project_project."id"
                                            LEFT JOIN "public".budget_plan_line ON "public".budget_plan_line.budget_id = "public".budget_plan."id"
                                            LEFT JOIN (
                                                SELECT
                                                        AA.budget_plan_line_id AS budget_plan_line_id,
                                                        sum(AA.realisasi_debit-AA.realisasi_credit) AS budget_realisasi,
                                                        AA.ref
                                                FROM
                                                        (
                                                        SELECT
                                                "public".budget_plan_line."id" AS budget_plan_line_id,
                                                "public".account_move_line.debit AS realisasi_debit,
                                                "public".account_move_line.credit AS realisasi_credit,
                                                "public".account_move.narration,
                                                "public".account_move."ref"
                                                FROM
                                                "public".budget_plan_line
                                                LEFT JOIN "public".budget_used ON "public".budget_used.budget_item_id = "public".budget_plan_line."id"
                                                LEFT JOIN "public".account_move_line ON "public".budget_used.move_line_id = "public".account_move_line."id"
                                                LEFT JOIN "public".account_move ON "public".account_move_line.move_id = "public".account_move."id"
                                                LEFT JOIN "public".advance_move_rel ON "public".advance_move_rel.move_id = "public".account_move."id"
                                                LEFT JOIN "public".settlement_move_rel ON "public".settlement_move_rel.move_id = "public".account_move."id"
                                                WHERE
                                                "public".advance_move_rel.advance_id IS NULL AND
                                                "public".settlement_move_rel.settlement_id IS NULL AND
                                                "public".account_move_line.debit IS NOT NULL AND
                                                "public".account_move_line.credit IS NOT NULL
                                                UNION
                                                SELECT
                                                "public".cash_advance_line.budget_item_id AS budget_plan_line_id,
                                                Sum("public".cash_advance_line.price_unit * "public".cash_advance_line.quantity) AS realisasi_debit,
                                                Sum(0) AS realisasi_credit,
                                                string_agg("public".cash_advance_line."name", '; ') AS narration,
                                                "public".cash_advance."number" as ref
                                                FROM
                                                "public".cash_advance_line
                                                LEFT JOIN "public".cash_advance ON "public".cash_advance_line.voucher_id = "public".cash_advance."id"
                                                WHERE
                                                "public".cash_advance."state" = 'close'
                                                GROUP BY
                                                "public".cash_advance_line.budget_item_id,
                                                "public".cash_advance."number"
                                                UNION
                                                SELECT
                                                "public".cash_settlement_line.budget_item_id AS budget_plan_line_id,
                                                Sum("public".cash_settlement_line.price_unit * "public".cash_settlement_line.quantity) AS realisasi_debit,
                                                Sum(0) AS realisasi_credit,
                                                string_agg("public".cash_settlement_line."name", '; ') AS narration,
                                                "public".cash_advance."number" as ref
                                                FROM
                                                "public".cash_settlement_line
                                                LEFT JOIN "public".cash_settlement ON "public".cash_settlement_line.voucher_id = "public".cash_settlement."id"
                                                LEFT JOIN "public".cash_advance ON "public".cash_settlement.advance_id = "public".cash_advance."id"
                                                WHERE
                                                "public".cash_advance."state" = 'lunas'
                                                GROUP BY
                                                "public".cash_settlement_line.budget_item_id,
                                                "public".cash_advance."number"
                                                ) AS AA
                                                GROUP BY
                                                AA.budget_plan_line_id,
                                                AA.ref
                                            ) AS AB ON AB.budget_plan_line_id = "public".budget_plan_line."id"
                                            WHERE
                                            "public".project_project.site_type_id = 8
                                            GROUP BY
                                            "public".project_project."id"
                                    ) AS C ON "public".project_project."id" = C.id
                            WHERE
                                "public".project_project.site_type_id = 8 AND
                                "public".project_site.customer_id IS NOT NULL AND
                                EXTRACT(YEAR FROM "public".project_project.tanggal_surat_tugas) = :tahun
                            GROUP BY
                                "public".res_partner."id",
                                "public".res_partner.code,
                                EXTRACT(YEAR FROM "public".project_project.tanggal_surat_tugas)
                            ORDER BY
                                "public".res_partner."id" ASC
                            """
    }

    @Async
    @Query(QUERY, nativeQuery = true)
    fun getCorrectiveYear(@PathParam("tahun") tahun: Long): Iterable<CorrectiveYear>
}