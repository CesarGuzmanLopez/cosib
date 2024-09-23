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
 * @param base64 Archivo en formato de bytes codificado en Base64
 * @param format Tipo de archivo
 */
data class ArchivoBase64Dto(

    @Schema(example = "null", description = "Archivo en formato de bytes codificado en Base64")
    @get:JsonProperty("base64") val base64: kotlin.String? = null,

    @Schema(example = "null", description = "Tipo de archivo")
    @get:JsonProperty("format") val format: kotlin.String? = null
    ) {

}

