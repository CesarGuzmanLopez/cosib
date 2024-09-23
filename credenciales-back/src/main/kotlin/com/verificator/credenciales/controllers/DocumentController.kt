        package com.verificator.credenciales.controllers

        import com.verificator.credenciales.model.Documents
        import com.verificator.credenciales.model.EstadoDocumento
        import com.verificator.credenciales.model.TipoDocumento
        import com.verificator.credenciales.repository.DocumentRepository
        import com.verificator.credenciales.repository.UserRepository
        import documents.api.generated.api.DocumentosApi
        import documents.api.generated.dto.*
        import org.springframework.core.io.Resource
        import org.springframework.http.ResponseEntity
        import org.springframework.web.bind.annotation.RequestMapping
        import org.springframework.web.bind.annotation.RestController
        import java.util.*
        import java.time.LocalDate
        import javax.imageio.ImageIO

        @RestController
        @RequestMapping("/api")
        class DocumentController(
            private val documentRepository: DocumentRepository,
            private val userRepository: UserRepository
        ) : DocumentosApi {

            override fun obtenerEstadoDocumentos(usuarioId: String): ResponseEntity<EstadosDocumentosResponseDto> {
                val userId = UUID.fromString(usuarioId)
                userRepository.findById(userId).orElseThrow { RuntimeException("User not found") }
                val documents = documentRepository.findByUserId(userId)

                val documentoEstadoDtos = documents.map { document ->
                    DocumentoEstadoDto(
                        documentoId = document.id.toString(),
                        tipoDocumento = TipoDocumentoDto.valueOf(document.tipoDocumento.name),
                        estado = EstadoDocumentoDto.valueOf(document.estado.name.lowercase())
                    )
                }

                val response = EstadosDocumentosResponseDto(
                    documentos = documentoEstadoDtos,
                    mensaje = "Consulta realizada con éxito"
                )

                return ResponseEntity.ok(response)
            }

            override fun verificarCSF(usuarioId: String, archivo: Resource): ResponseEntity<VerificarCSF200ResponseDto> {
                if (archivo == null) {
                    throw RuntimeException("Document is required")
                }

                // Generate fake data for testing
                val fakeNombre = "Juan Perez"
                val fakeSituacion = "Activo"
                val fakeRfc = "RFC123456"
                val fakeDatosAdicionales = mapOf("tipoContribuyente" to "Persona Física", "regimenFiscal" to "General")

                // Verify document format
                val formatFile = archivo.inputStream.readBytes()
                val format = determineFileFormat(formatFile)

                // Fetch the user entity
                val user = userRepository.findById(UUID.fromString(usuarioId)).orElseThrow {
                    RuntimeException("User not found")
                }

                // Check for existing documents and delete them
                val existingDocuments = documentRepository.findByUserId(user.id!!)
                existingDocuments.filter { it.tipoDocumento == TipoDocumento.csf }
                    .forEach { documentRepository.delete(it) }

                // Save the new document in the database
                val documentEntity = Documents(
                    tipoDocumento = TipoDocumento.csf,
                    estado = EstadoDocumento.SUBIDO_VERIFICADO,//subido_verificado
                    ruta = formatFile, // Save the document
                    user = user,
                    nombre = fakeNombre,
                    situacion = fakeSituacion,
                    rfc = fakeRfc,
                    formatFile = format
                )
                documentRepository.save(documentEntity)

                val response = VerificarCSF200ResponseDto(
                    nombre = fakeNombre,
                    situacion = fakeSituacion,
                    rfc = fakeRfc,
                    datosAdicionales = fakeDatosAdicionales
                )

                return ResponseEntity.ok(response)    }

            override fun verificarDocumentoBancario(
                usuarioId: String,
                documento: Resource?
            ): ResponseEntity<VerificarDocumentoBancario200ResponseDto> {
                if (documento == null) {
                    throw RuntimeException("Document is required")
                }

                // Generate fake data for testing
                val fakeNombre = "Juan Perez"
                val fakeBanco = "Banco Ejemplo"
                val fakeCuenta = "1234567890"
                val fakeDatosAdicionales = mapOf("tipoCuenta" to "Ahorros", "moneda" to "MXN")

                // Verify document format
                val formatFile = documento.inputStream.readBytes()
                val format = determineFileFormat(formatFile)

                // Fetch the user entity
                val user = userRepository.findById(UUID.fromString(usuarioId)).orElseThrow {
                    RuntimeException("User not found")
                }

                // Check for existing documents and delete them
                val existingDocuments = documentRepository.findByUserId(user.id!!)
                existingDocuments.filter { it.tipoDocumento == TipoDocumento.documento_bancario }
                    .forEach { documentRepository.delete(it) }

                // Save the new document in the database
                val documentEntity = Documents(
                    tipoDocumento = TipoDocumento.documento_bancario,
                    estado = EstadoDocumento.SUBIDO_VERIFICADO ,
                    ruta = formatFile, // Save the document
                    user = user,
                    nombre = fakeNombre,
                    banco = fakeBanco,
                    cuenta = fakeCuenta,
                    formatFile = format
                )
                documentRepository.save(documentEntity)

                val response = VerificarDocumentoBancario200ResponseDto(
                    nombre = fakeNombre,
                    banco = fakeBanco,
                    cuenta = fakeCuenta,
                    datosAdicionales = fakeDatosAdicionales
                )

                return ResponseEntity.ok(response)
            }

            override fun verificarINE(
                usuarioId: String,
                ine1: Resource,
                ine2: Resource
            ): ResponseEntity<VerificarINE200ResponseDto> {
                if (ine1 == null || ine2 == null) {
                    throw RuntimeException("Both INE front and back images are required")
                }

                // Generate fake data for testing
                val fakeNombre = "Juan Perez"
                val fakeSexo = "M"
                val fakeDireccion = "123 Fake Street"
                val fakeFechaNacimiento = LocalDate.of(1990, 1, 1)

                // Fetch the user entity
                val user = userRepository.findById(UUID.fromString(usuarioId)).orElseThrow {
                    RuntimeException("User not found")
                }

                // Check for existing documents and delete them
                val existingDocuments = documentRepository.findByUserId(user.id!!)
                existingDocuments.filter { it.tipoDocumento == TipoDocumento.ine_frente || it.tipoDocumento == TipoDocumento.ine_reverso }
                    .forEach { documentRepository.delete(it) }

                // Verify document formats
                val formatoine1 = ine1.inputStream.readBytes()
                val formatoine2 = ine2.inputStream.readBytes()
                val format1 = determineFileFormat(formatoine1)
                val format2 = determineFileFormat(formatoine2)

                // Save the new front document in the database
                val documentFront = Documents(
                    tipoDocumento = TipoDocumento.ine_frente,
                    estado = EstadoDocumento.SUBIDO_VERIFICADO,
                    ruta = formatoine1, // Save the front image
                    user = user,
                    nombre = fakeNombre,
                    apellidos = "Perez",
                    fechaNacimiento = fakeFechaNacimiento,
                    curp = "CURP123456",
                    direccion = fakeDireccion,
                    formatFile = format1
                )
                documentRepository.save(documentFront)

                // Save the new back document in the database
                val documentBack = Documents(
                    tipoDocumento = TipoDocumento.ine_reverso,
                    estado = EstadoDocumento.SUBIDO_VERIFICADO,
                    ruta = formatoine2, // Save the back image
                    user = user,
                    nombre = fakeNombre,
                    formatFile = format2
                )
                documentRepository.save(documentBack)

                val response = VerificarINE200ResponseDto(
                    nombre = fakeNombre,
                    sexo = fakeSexo,
                    direccion = fakeDireccion,
                    fechaNacimiento = fakeFechaNacimiento
                )

                return ResponseEntity.ok(response)
            }



            override fun obtenerDocumentoPorTipo(
                usuarioId: String,
                tipoDocumento: TipoDocumentoDto
            ): ResponseEntity<DocumentoResponseDto> {
                val userId = UUID.fromString(usuarioId)
                userRepository.findById(userId).orElseThrow { RuntimeException("User not found") }
                val document = documentRepository.findByUserId(userId)
                    .find { it.tipoDocumento == TipoDocumento.valueOf(tipoDocumento.name) }

                if (document == null) {
                    throw RuntimeException("Document not found")
                }
                val base64archivo = Base64.getEncoder().encodeToString(document.ruta)
                val tipoDocumento = document.formatFile
                val respuestabase64 = ArchivoBase64Dto(
                    base64 = base64archivo,
                    format = tipoDocumento

                )
                val response = DocumentoResponseDto(
                    documentoId = document.id.toString(),
                    tipoDocumento = TipoDocumentoDto.valueOf(document.tipoDocumento.name),
                    estado = EstadoDocumentoDto.valueOf(document.estado.name.lowercase()),
                    archivo = respuestabase64
                )

                return ResponseEntity.ok(response)
            }

            private fun determineFileFormat(bytes: ByteArray): String {
                val magic = bytes.copyOfRange(0, 4)
                val magicString = magic.joinToString(" ") { it.toString(16) }
                return when (magicString) {
                    "25 50 44 46" -> "pdf"
                    "89 50 4e 47" -> "png"
                    "ff d8 ff e0" -> "jpg"
                    else -> "unknown"
                }
            }
        }