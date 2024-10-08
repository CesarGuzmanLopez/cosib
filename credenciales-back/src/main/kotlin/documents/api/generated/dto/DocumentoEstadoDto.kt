package documents.api.generated.dto

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import documents.api.generated.dto.EstadoDocumentoDto
import documents.api.generated.dto.TipoDocumentoDto
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Email
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size
import javax.validation.Valid
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 
 * @param documentoId ID del documento
 * @param tipoDocumento 
 * @param estado 
 */
data class DocumentoEstadoDto(

    @Schema(example = "null", description = "ID del documento")
    @get:JsonProperty("documentoId") val documentoId: kotlin.String? = null,

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("tipo_documento") val tipoDocumento: TipoDocumentoDto? = null,

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("estado") val estado: EstadoDocumentoDto? = null
    ) {

}

