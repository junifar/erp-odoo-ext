package com.prasetia.erp.direksi.direksi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
class DireksiApplication:SpringBootServletInitializer()


fun main(args: Array<String>) {
    runApplication<DireksiApplication>(*args)
}
