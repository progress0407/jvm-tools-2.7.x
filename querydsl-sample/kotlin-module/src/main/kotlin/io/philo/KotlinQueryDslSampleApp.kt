package io.philo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication

class KotlinQueryDslSampleApp

fun main(args: Array<String>) {
    runApplication<KotlinQueryDslSampleApp>(*args)
}
