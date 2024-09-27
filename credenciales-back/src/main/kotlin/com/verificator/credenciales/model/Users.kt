// src/main/kotlin/com/verificator/credenciales/model/User.kt
package com.verificator.credenciales.model

import java.time.LocalDate
import java.util.*
import jakarta.persistence.*

@Entity
@Table(name = "users")
data class Users(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,

    @Column(unique = true, nullable = false)
    var username: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = true)
    var nombre: String? = null,
    @Column (nullable = true)
    var apellido1: String? = null,
    @Column (nullable = true)
    var apellido2: String? = null,
    @Column(nullable = true)
    var fechaNacimiento: LocalDate? = null,

    @Column(nullable = true)
    var sexo: String? = null,

    @Column(nullable = true)
    var direccion: String? = null,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var documents: List<Documents> = listOf()
)