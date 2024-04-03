package me.bossm0n5t3r.loki

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootLoggingWithLokiApplication

fun main(args: Array<String>) {
    runApplication<SpringBootLoggingWithLokiApplication>(*args)
}
