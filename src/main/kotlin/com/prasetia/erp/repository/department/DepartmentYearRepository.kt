package com.prasetia.erp.repository.department

import com.prasetia.erp.model.department.DepartmentYear
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Repository
import javax.websocket.server.PathParam

@Repository
interface DepartmentYearRepository:CrudRepository<DepartmentYear, Long>{
    companion object {
        const val QUERY = """
                            SELECT
                            ROW_NUMBER() OVER (ORDER BY "public".hr_department."name") AS id,
                            "public".hr_department."name" as department_name,
                            "public".budget_plan.department_id as department_id,
                            Sum(d.nilai_budget) AS nilai_budget,
                            COALESCE(sum(C.realisasi_budget), 0) AS realisasi_budget,
                            COALESCE(round(cast(sum(C.realisasi_budget)/sum(D.nilai_budget) as numeric) * 100,2),0) AS persent_budget
                            FROM
                            "public".budget_plan
                            LEFT JOIN "public".hr_department ON "public".budget_plan.department_id = "public".hr_department."id"
                            LEFT JOIN (
                                                SELECT
                                                "public".budget_plan.id,
                                                Sum("public".budget_plan_line.amount) AS nilai_budget
                                                FROM
                                                "public".budget_plan
                                                LEFT JOIN "public".budget_plan_line ON "public".budget_plan_line.budget_id = "public".budget_plan."id"
                                                WHERE
                                                "public".budget_plan."state" <> 'draft' AND
                                                "public".budget_plan.project_id IS NOT NULL
                                                GROUP BY
                                                "public".budget_plan.id
                                ) AS d ON D.id= "public".budget_plan."id"
                            LEFT JOIN (
                                                SELECT
                                                "public".budget_plan."id",
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
                                                                "public".account_move.narration,
                                                                "public".account_move."ref"
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
                                                "public".project_project.site_type_id IN (36, 37, 38, 40, 41, 42, 43, 45, 46, 47, 48, 61, 64, 65, 68, 70, 74)
                                                GROUP BY
                                                "public".budget_plan."id"
                                                ) AS "c" ON "public".budget_plan."id" = C.id
                            WHERE
                            "public".budget_plan.site_type_id IN (36, 37, 38, 40, 41, 42, 43, 45, 46, 47, 48, 61, 64, 65, 68, 70, 74) AND
                            EXTRACT(YEAR from "public".budget_plan.periode_start) IS NOT NULL AND
                            EXTRACT(YEAR from "public".budget_plan.periode_start) = :tahun
                            GROUP BY
                            "public".hr_department."name",
                            "public".budget_plan.department_id
                        """
        const val QUERY_BY_DEPT_ID = """
                            SELECT
                            ROW_NUMBER() OVER (ORDER BY "public".hr_department."name") AS id,
                            "public".hr_department."name" as department_name,
                            "public".budget_plan.department_id as department_id,
                            Sum(d.nilai_budget) AS nilai_budget,
                            COALESCE(sum(C.realisasi_budget), 0) AS realisasi_budget,
                            COALESCE(round(cast(sum(C.realisasi_budget)/sum(D.nilai_budget) as numeric) * 100,2),0) AS persent_budget
                            FROM
                            "public".budget_plan
                            LEFT JOIN "public".hr_department ON "public".budget_plan.department_id = "public".hr_department."id"
                            LEFT JOIN (
                                                                    SELECT
                                                                    "public".budget_plan.id,
                                                                    Sum("public".budget_plan_line.amount) AS nilai_budget
                                                                    FROM
                                                                    "public".budget_plan
                                                                    LEFT JOIN "public".budget_plan_line ON "public".budget_plan_line.budget_id = "public".budget_plan."id"
                                                                    WHERE
                                                                    "public".budget_plan."state" <> 'draft' AND
                                                                    "public".budget_plan.project_id IS NOT NULL
                                                                    GROUP BY
                                                                    "public".budget_plan.id
                                    ) AS d ON D.id= "public".budget_plan."id"
                            LEFT JOIN (
                                                                    SELECT
                                                                    "public".budget_plan."id",
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
                                                                                                    "public".account_move.narration,
                                                                                                    "public".account_move."ref"
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
                                                                    "public".project_project.site_type_id IN (36, 37, 38, 40, 41, 42, 43, 45, 46, 47, 48, 61, 64, 65, 68, 70, 74)
                                                                    GROUP BY
                                                                    "public".budget_plan."id"
                                                                    ) AS "c" ON "public".budget_plan."id" = C.id
                            WHERE
                            "public".budget_plan.site_type_id IN (36, 37, 38, 40, 41, 42, 43, 45, 46, 47, 48, 61, 64, 65, 68, 70, 74) AND
                            EXTRACT(YEAR from "public".budget_plan.periode_start) IS NOT NULL AND
                            EXTRACT(YEAR from "public".budget_plan.periode_start) = :tahun AND
                            "public".budget_plan.department_id = :department_id
                            GROUP BY
                            "public".hr_department."name",
                            "public".budget_plan.department_id
                        """
    }

    @Async
    @Query(QUERY, nativeQuery = true)
    fun getDepartmentYear(@PathParam("tahun") tahun: Long): Iterable<DepartmentYear>

    @Async
    @Query(QUERY_BY_DEPT_ID, nativeQuery = true)
    fun getDepartmentYearDeptID(@PathParam("tahun") tahun: Long, @PathParam("department_id") department_id:Long): Iterable<DepartmentYear>
}