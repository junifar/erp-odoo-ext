package com.prasetia.erp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication
class ErpApplication: SpringBootServletInitializer()

fun main(args: Array<String>) {
    runApplication<ErpApplication>(*args)
}
