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
 * @param error 
 * @param code 
 */
data class ErrorResponseDto(

    @Schema(example = "Mensaje de error", description = "")
    @get:JsonProperty("error") val error: kotlin.String? = null,

    @Schema(example = "400", description = "")
    @get:JsonProperty("code") val code: kotlin.Int? = null
) {

}

