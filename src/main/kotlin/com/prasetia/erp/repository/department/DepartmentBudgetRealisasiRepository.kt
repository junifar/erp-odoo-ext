package com.prasetia.erp.repository.department

import com.prasetia.erp.model.department.DepartmentBudgetRealisasi
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.scheduling.annotation.Async
import javax.websocket.server.PathParam

interface DepartmentBudgetRealisasiRepository:CrudRepository<DepartmentBudgetRealisasi, Long>{
    companion object {
        const val QUERY = """
                            SELECT
                                    ROW_NUMBER() OVER (ORDER BY "public".budget_plan_line.parent_id) AS id,
                                    AA.budget_plan_line_id AS budget_id,
                                    "public".budget_plan_line.parent_id,
                                    AA.narration,
                                    AA.ref,
                                    AA.date,
                                    sum(AA.realisasi_debit-AA.realisasi_credit) AS budget_realisasi
                            FROM
                            (
                                    SELECT
                                    "public".budget_plan_line."id" AS budget_plan_line_id,
                                    "public".account_move."date" AS date,
                                    sum("public".account_move_line.debit) AS realisasi_debit,
                                    sum("public".account_move_line.credit) AS realisasi_credit,
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
                                    GROUP BY
                                    "public".budget_plan_line."id",
                                    "public".account_move."date",
                                    "public".account_move.narration,
                                    "public".account_move."ref"
                                    UNION
                                    SELECT
                                    "public".cash_advance_line.budget_item_id AS budget_plan_line_id,
                                    "public".cash_advance."date" as date,
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
                                    "public".cash_advance."number",
                                    "public".cash_advance."date"
                                    UNION
                                    SELECT
                                    "public".cash_settlement_line.budget_item_id AS budget_plan_line_id,
                                    "public".cash_advance."date" as date,
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
                                    "public".cash_advance."number",
                                    "public".cash_advance."date"
                            ) AS AA
                            LEFT JOIN "public".budget_plan_line ON "public".budget_plan_line."id" = AA.budget_plan_line_id
                            LEFT JOIN "public".budget_plan ON "public".budget_plan."id" = "public".budget_plan_line.budget_id
                            WHERE
                            EXTRACT(YEAR from "public".budget_plan.periode_start) IS NOT NULL AND
                            EXTRACT(YEAR from "public".budget_plan.periode_start) = :tahun AND
                            "public".budget_plan.department_id = :department_id
                            GROUP BY
                                AA.budget_plan_line_id,
                                AA.narration,
                                "public".budget_plan_line.parent_id,
                                AA.ref,
                                AA."date"
                            """
    }

    @Async
    @Query(QUERY, nativeQuery = true)
    fun getDepartmentBudgetRealisasi(@PathParam("tahun") tahun:Long, @PathParam("department_id") department_id:Long): Iterable<DepartmentBudgetRealisasi>
}