package documents.api.generated.dto

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import documents.api.generated.dto.DocumentoEstadoDto
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
 * @param documentos 
 * @param mensaje 
 */
data class EstadosDocumentosResponseDto(

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("documentos") val documentos: kotlin.collections.List<DocumentoEstadoDto>? = null,

    @Schema(example = "Consulta realizada con éxito", description = "")
    @get:JsonProperty("mensaje") val mensaje: kotlin.String? = null
    ) {

}

