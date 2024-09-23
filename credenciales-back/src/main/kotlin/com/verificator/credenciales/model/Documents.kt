// src/main/kotlin/com/verificator/credenciales/model/Document.kt
package com.verificator.credenciales.model

import java.time.LocalDate
import java.util.*
import jakarta.persistence.*

@Entity
@Table(name = "documents")
data class Documents(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val tipoDocumento: TipoDocumento,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val estado: EstadoDocumento,

    @Lob
    @Column(nullable = false)
    val ruta: ByteArray, // Almacenamos el documento como un array de bytes

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: Users,

    @Column(nullable = true)
    val nombre: String? = null,

    @Column(nullable = true)
    val apellidos: String? = null,

    @Column(nullable = true)
    val fechaNacimiento: LocalDate? = null,

    @Column(nullable = true)
    val curp: String? = null,

    @Column(nullable = true)
    val direccion: String? = null
)


