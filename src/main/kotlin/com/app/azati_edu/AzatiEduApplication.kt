package com.app.azati_edu

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class AzatiEduApplication

fun main(args: Array<String>) {
    runApplication<AzatiEduApplication>(*args)
}

inline fun <reified T> getClassLogger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}