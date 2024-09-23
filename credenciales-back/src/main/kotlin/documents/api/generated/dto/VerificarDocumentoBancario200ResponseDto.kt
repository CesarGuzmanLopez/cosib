package documents.api.generated.dto

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
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
 * @param nombre 
 * @param banco 
 * @param cuenta 
 * @param datosAdicionales 
 */
data class VerificarDocumentoBancario200ResponseDto(

    @Schema(example = "null", description = "")
    @get:JsonProperty("nombre") val nombre: kotlin.String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("banco") val banco: kotlin.String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("cuenta") val cuenta: kotlin.String? = null,

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("datos_adicionales") val datosAdicionales: kotlin.Any? = null
) {

}

