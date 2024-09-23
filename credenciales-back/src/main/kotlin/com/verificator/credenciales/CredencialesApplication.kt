// src/main/kotlin/com/verificator/credenciales/CredencialesApplication.kt
package com.verificator.credenciales

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CredencialesApplication

fun main(args: Array<String>) {
    runApplication<CredencialesApplication>(*args)
}
