package com.app.azati_edu

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class AzatiEduApplication

fun main(args: Array<String>) {
    runApplication<AzatiEduApplication>(*args)
}