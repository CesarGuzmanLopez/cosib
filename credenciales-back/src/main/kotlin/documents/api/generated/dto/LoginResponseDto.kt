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
 * @param token 
 * @param id ID del usuario
 */
data class LoginResponseDto(

    @Schema(example = "Bearer <token>", description = "")
    @get:JsonProperty("token") val token: kotlin.String? = null,

    @Schema(example = "null", description = "ID del usuario")
    @get:JsonProperty("id") val id: kotlin.String? = null
    ) {

}

