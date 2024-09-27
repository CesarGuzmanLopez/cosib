// src/main/kotlin/com/verificator/credenciales/model/User.kt
package com.verificator.credenciales.model

import java.time.LocalDate
import java.util.*
import jakarta.persistence.*

@Entity
@Table(name = "documents")
data class Documents( // Cambiado a singular para consistencia
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
    @Basic(fetch = FetchType.LAZY)
    @Column(nullable = false, columnDefinition = "LONGBLOB") // Especificar LONGBLOB
    val ruta: ByteArray, // Almacenamos el documento como un array de bytes

    @ManyToOne(fetch = FetchType.LAZY) // Añadir fetch type LAZY
    @JoinColumn(name = "user_id", nullable = false)
    val user: Users, // Asegúrate de que la clase se llama 'User'

    @Column(nullable = true)
    val nombre: String? = null,
    @Column (nullable = true)
    val situacion: String? = null,
    @Column(nullable = true)
    val apellidos: String? = null,

    @Column(nullable = true)
    val fechaNacimiento: LocalDate? = null,

    @Column(nullable = true)
    val curp: String? = null,

    @Column(nullable = true)
    val direccion: String? = null,

    @Column(nullable = true)
    val telefono: String? = null,

    @Column(nullable = true)
    val email: String? = null,

    @Column(nullable = true)
    val rfc: String? = null,

    @Column(nullable = true)
    val banco: String? = null,
    @Column (nullable = true)
    val cuenta: String? = null,
    @Column (nullable = true)
    val formatFile : String? = null,
)
