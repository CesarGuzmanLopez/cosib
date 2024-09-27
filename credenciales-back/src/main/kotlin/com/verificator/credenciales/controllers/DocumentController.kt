        package com.verificator.credenciales.controllers

        import com.verificator.credenciales.model.Documents
        import com.verificator.credenciales.model.EstadoDocumento
        import com.verificator.credenciales.model.TipoDocumento
        import com.verificator.credenciales.repository.DocumentRepository
        import com.verificator.credenciales.repository.UserRepository
        import documents.api.generated.api.DocumentosApi
        import documents.api.generated.dto.*
        import org.springframework.core.io.Resource
        import org.springframework.http.HttpEntity
        import org.springframework.http.HttpHeaders
        import org.springframework.http.MediaType
        import org.springframework.http.ResponseEntity
        import org.springframework.util.LinkedMultiValueMap
        import org.springframework.web.bind.annotation.RequestMapping
        import org.springframework.web.bind.annotation.RestController
        import org.springframework.web.client.RestTemplate
         import java.util.*
        import java.time.LocalDate

        @RestController
        @RequestMapping("/api")
        class DocumentController(
            private val documentRepository: DocumentRepository,
            private val userRepository: UserRepository
        ) : DocumentosApi {

            override fun obtenerEstadoDocumentos(usuarioId: String): ResponseEntity<EstadosDocumentosResponseDto> {
                try{
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

                }catch (e: Exception){
                    throw RuntimeException("Error al obtener estado de documentos")
                }
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
                //el apellido se separ con %% en lugar de un espacio
                // Save the new document in the database
                val documentEntity = Documents(
                    tipoDocumento = TipoDocumento.csf,
                    estado = EstadoDocumento.SUBIDO_VERIFICADO,//subido_verificado
                    ruta = formatFile, // Save the document
                    user = user,
                    nombre = fakeNombre,
                    situacion = fakeSituacion,
                    rfc = fakeRfc,
                    formatFile = format,
                )
                documentRepository.save(documentEntity)

                val response = VerificarCSF200ResponseDto(
                    nombre = fakeNombre,
                    situacion = fakeSituacion,
                    rfc = fakeRfc,
                    datosAdicionales = fakeDatosAdicionales
                )

                return ResponseEntity.ok(response)
            }

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
                try{
                    if (ine1 == null || ine2 == null) {
                        throw RuntimeException("Both INE front and back images are required")
                    }

                    // Call external endpoint with ine1
                    val externalResponse = try {
                        callExternalEndpoint(ine1)
                    } catch (e: RuntimeException) {
                        return ResponseEntity.badRequest().body(
                            VerificarINE200ResponseDto(         //cambio de badRequest a ok
                                nombre = "INE no válida",
                                sexo = "",
                                direccion = "",
                                fechaNacimiento = LocalDate.now()
                            )
                        )
                    }

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
                        nombre = externalResponse.datos.nombres,
                        apellidos = "${externalResponse.datos.apellido_materno}%%${externalResponse.datos.apellido_paterno}",
                        fechaNacimiento = LocalDate.of(1990, 1, 1), // Placeholder date
                        curp = "CURP123456", // Placeholder CURP
                        direccion = "123 Fake Street", // Placeholder address
                        formatFile = format1
                    )
                    documentRepository.save(documentFront)

                    // Save the new back document in the database
                    val documentBack = Documents(
                        tipoDocumento = TipoDocumento.ine_reverso,
                        estado = EstadoDocumento.SUBIDO_VERIFICADO,
                        ruta = formatoine2, // Save the back image
                        user = user,
                        nombre = externalResponse.datos.nombres,
                        apellidos = "${externalResponse.datos.apellido_materno}%%${externalResponse.datos.apellido_paterno}",
                        formatFile = format2
                    )
                    documentRepository.save(documentBack)

                    val response = VerificarINE200ResponseDto(
                        nombre = externalResponse.datos.nombres,
                        apellido1 = externalResponse.datos.apellido_materno,
                        apellido2 = externalResponse.datos.apellido_paterno,
                        sexo = "M", // Placeholder sex
                        direccion = "123 Fake Street", // Placeholder address
                        fechaNacimiento = LocalDate.of(1990, 1, 1) // Placeholder date
                    )
                    return ResponseEntity.ok(response)

                }
                catch(e: Exception){
                    throw RuntimeException("Error al verificar INE")
                }
            }

            override fun obtenerDocumentoPorTipo(
                usuarioId: String,
                tipoDocumento: TipoDocumentoDto
            ): ResponseEntity<DocumentoResponseDto> {
                try{
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

                    val primerApellido = document.apellidos?.split("%%")?.get(0) ?: ""
                    val segundoApellido = document.apellidos?.split("%%")?.let {
                        if (it.size > 1) it[1] else ""
                    } ?: ""
                    val response = DocumentoResponseDto(
                        documentoId = document.id.toString(),
                        tipoDocumento = TipoDocumentoDto.valueOf(document.tipoDocumento.name),
                        estado = EstadoDocumentoDto.valueOf(document.estado.name.lowercase()),
                        archivo = respuestabase64,
                        apellido1 = primerApellido,
                        apellido2 = segundoApellido,
                        nombre = document.nombre
                    )

                    return ResponseEntity.ok(response)
                }catch (e: Exception){
                    throw RuntimeException("Error al obtener documento: ${e.message}")
                }
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

            private fun callExternalEndpoint(ine1: Resource): ExternalResponse {
                val restTemplate = RestTemplate()
                
                // Configurar headers para multipart/form-data
                val headers = HttpHeaders()
                headers.contentType = MediaType.MULTIPART_FORM_DATA
                
                // Crear el cuerpo de la solicitud como un MultiValueMap
                val body = LinkedMultiValueMap<String, Any>()
            
                // Adjuntar el archivo con el nombre "file" (como en el ejemplo curl)
                body.add("file", ine1)  // ine1 debería ser de tipo Resource (InputStreamResource/FileSystemResource)
            
                // Crear HttpEntity con el cuerpo y los headers
                val requestEntity = HttpEntity(body, headers)
            
                // Realizar la solicitud POST
                val response = restTemplate.postForEntity(
                    "http://jquiroz.net:5000/upload",
                    requestEntity,
                    ExternalResponse::class.java
                )
            
                // Validar el código de estado y manejar errores
                if (response.statusCodeValue == 400) {
                    throw RuntimeException("INE no válida")
                }
            
                // Retornar la respuesta del servidor si es exitosa
                return response.body ?: throw RuntimeException("Failed to get response from external endpoint")
            }
        }

        data class ExternalResponse(
            val codigo: Int,
            val datos: Datos,
            val mensaje: String
        )

        data class Datos(
            val apellido_paterno: String,
            val apellido_materno: String,
            val nombres: String
        )