/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.8.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
*/
package documents.api.generated.api

import documents.api.generated.dto.DocumentoResponseDto
import documents.api.generated.dto.ErrorResponseDto
import documents.api.generated.dto.EstadosDocumentosResponseDto
import documents.api.generated.dto.TipoDocumentoDto
import documents.api.generated.dto.VerificarCSF200ResponseDto
import documents.api.generated.dto.VerificarDocumentoBancario200ResponseDto
import documents.api.generated.dto.VerificarINE200ResponseDto
import io.swagger.v3.oas.annotations.*
import io.swagger.v3.oas.annotations.enums.*
import io.swagger.v3.oas.annotations.media.*
import io.swagger.v3.oas.annotations.responses.*
import io.swagger.v3.oas.annotations.security.*
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.*
import org.springframework.validation.annotation.Validated
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.beans.factory.annotation.Autowired

import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Email
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size
import javax.validation.Valid

import kotlin.collections.List
import kotlin.collections.Map

@RestController
@Validated
interface DocumentosApi {

    @Operation(
        tags = ["Documentos",],
        summary = "Obtener documento por tipo",
        operationId = "obtenerDocumentoPorTipo",
        description = """Regresa el documento asociado al usuario y tipo de documento especificado.""",
        responses = [
            ApiResponse(responseCode = "200", description = "Documento encontrado", content = [Content(schema = Schema(implementation = DocumentoResponseDto::class))]),
            ApiResponse(responseCode = "401", description = "Token expirado o no válido", content = [Content(schema = Schema(implementation = ErrorResponseDto::class))]),
            ApiResponse(responseCode = "404", description = "Documento no encontrado", content = [Content(schema = Schema(implementation = ErrorResponseDto::class))]),
            ApiResponse(responseCode = "500", description = "Error interno del servidor", content = [Content(schema = Schema(implementation = ErrorResponseDto::class))])
        ],
        security = [ SecurityRequirement(name = "Bearer") ]
    )
    @RequestMapping(
            method = [RequestMethod.GET],
            value = ["/documentos/{usuarioId}/{tipoDocumento}"],
            produces = ["application/json"]
    )
    fun obtenerDocumentoPorTipo(@Parameter(description = "ID del usuario para el cual se desea obtener el documento", required = true) @PathVariable("usuarioId") usuarioId: kotlin.String,@Parameter(description = "Tipo de documento que se desea obtener", required = true, schema = Schema(allowableValues = ["\"ine\"", "\"csf\"", "\"documento_bancario\"", "\"ine_frente\"", "\"ine_reverso\""])) @PathVariable("tipoDocumento") tipoDocumento: TipoDocumentoDto): ResponseEntity<DocumentoResponseDto>

    @Operation(
        tags = ["Documentos",],
        summary = "Obtener estado de todos los documentos de un usuario",
        operationId = "obtenerEstadoDocumentos",
        description = """Regresa el estado de todos los documentos asociados al usuario autenticado.""",
        responses = [
            ApiResponse(responseCode = "200", description = "Estados de los documentos", content = [Content(schema = Schema(implementation = EstadosDocumentosResponseDto::class))]),
            ApiResponse(responseCode = "401", description = "Token expirado o no válido", content = [Content(schema = Schema(implementation = ErrorResponseDto::class))]),
            ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = [Content(schema = Schema(implementation = ErrorResponseDto::class))]),
            ApiResponse(responseCode = "500", description = "Error interno del servidor", content = [Content(schema = Schema(implementation = ErrorResponseDto::class))])
        ],
        security = [ SecurityRequirement(name = "Bearer") ]
    )
    @RequestMapping(
            method = [RequestMethod.GET],
            value = ["/documentos/estado"],
            produces = ["application/json"]
    )
    fun obtenerEstadoDocumentos(@NotNull @Parameter(description = "ID del usuario para el cual se desean obtener los estados de documentos", required = true) @Valid @RequestParam(value = "usuarioId", required = true) usuarioId: kotlin.String): ResponseEntity<EstadosDocumentosResponseDto>

    @Operation(
        tags = ["Documentos",],
        summary = "Verificación de CSF",
        operationId = "verificarCSF",
        description = """Envía un archivo en PDF o imagen y regresa datos del RFC.""",
        responses = [
            ApiResponse(responseCode = "200", description = "Documento CSF válido", content = [Content(schema = Schema(implementation = VerificarCSF200ResponseDto::class))]),
            ApiResponse(responseCode = "400", description = "Documento CSF no válido", content = [Content(schema = Schema(implementation = ErrorResponseDto::class))]),
            ApiResponse(responseCode = "401", description = "Token expirado o no válido", content = [Content(schema = Schema(implementation = ErrorResponseDto::class))]),
            ApiResponse(responseCode = "500", description = "Error interno del servidor", content = [Content(schema = Schema(implementation = ErrorResponseDto::class))])
        ],
        security = [ SecurityRequirement(name = "Bearer") ]
    )
    @RequestMapping(
            method = [RequestMethod.POST],
            value = ["/verificar-csf"],
            produces = ["application/json"],
            consumes = ["multipart/form-data"]
    )
    fun verificarCSF(@NotNull @Parameter(description = "ID del usuario para el cual se desea verificar el CSF", required = true) @Valid @RequestParam(value = "usuarioId", required = true) usuarioId: kotlin.String,@Parameter(description = "Archivo en PDF o imagen") @Valid @RequestPart("archivo", required = true) archivo: org.springframework.core.io.Resource): ResponseEntity<VerificarCSF200ResponseDto>

    @Operation(
        tags = ["Documentos",],
        summary = "Validación de Documento Bancario",
        operationId = "verificarDocumentoBancario",
        description = """Envía un documento bancario y regresa datos del propietario.""",
        responses = [
            ApiResponse(responseCode = "200", description = "Documento bancario válido", content = [Content(schema = Schema(implementation = VerificarDocumentoBancario200ResponseDto::class))]),
            ApiResponse(responseCode = "400", description = "Documento bancario no válido", content = [Content(schema = Schema(implementation = ErrorResponseDto::class))]),
            ApiResponse(responseCode = "401", description = "Token expirado o no válido", content = [Content(schema = Schema(implementation = ErrorResponseDto::class))]),
            ApiResponse(responseCode = "500", description = "Error interno del servidor", content = [Content(schema = Schema(implementation = ErrorResponseDto::class))])
        ],
        security = [ SecurityRequirement(name = "Bearer") ]
    )
    @RequestMapping(
            method = [RequestMethod.POST],
            value = ["/verificar-documento-bancario"],
            produces = ["application/json"],
            consumes = ["multipart/form-data"]
    )
    fun verificarDocumentoBancario(@NotNull @Parameter(description = "ID del usuario para el cual se desea verificar el documento bancario", required = true) @Valid @RequestParam(value = "usuarioId", required = true) usuarioId: kotlin.String,@Parameter(description = "Archivo del documento bancario") @Valid @RequestPart("documento", required = false) documento: org.springframework.core.io.Resource?): ResponseEntity<VerificarDocumentoBancario200ResponseDto>

    @Operation(
        tags = ["Documentos",],
        summary = "Verificar INE",
        operationId = "verificarINE",
        description = """Envía dos archivos de fotos de la INE y regresa los datos.""",
        responses = [
            ApiResponse(responseCode = "200", description = "Documento INE válido", content = [Content(schema = Schema(implementation = VerificarINE200ResponseDto::class))]),
            ApiResponse(responseCode = "400", description = "Documento INE no válido", content = [Content(schema = Schema(implementation = ErrorResponseDto::class))]),
            ApiResponse(responseCode = "401", description = "Token expirado o no válido", content = [Content(schema = Schema(implementation = ErrorResponseDto::class))]),
            ApiResponse(responseCode = "500", description = "Error interno del servidor", content = [Content(schema = Schema(implementation = ErrorResponseDto::class))])
        ],
        security = [ SecurityRequirement(name = "Bearer") ]
    )
    @RequestMapping(
            method = [RequestMethod.POST],
            value = ["/verificar-ine"],
            produces = ["application/json"],
            consumes = ["multipart/form-data"]
    )
    fun verificarINE(@NotNull @Parameter(description = "ID del usuario para el cual se desea verificar el INE", required = true) @Valid @RequestParam(value = "usuarioId", required = true) usuarioId: kotlin.String,@Parameter(description = "Primera imagen de la INE") @Valid @RequestPart("ine1", required = true) ine1: org.springframework.core.io.Resource,@Parameter(description = "Segunda imagen de la INE") @Valid @RequestPart("ine2", required = true) ine2: org.springframework.core.io.Resource): ResponseEntity<VerificarINE200ResponseDto>
}
