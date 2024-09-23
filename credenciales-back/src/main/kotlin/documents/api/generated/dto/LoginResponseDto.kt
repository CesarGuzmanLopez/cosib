package documents.api.generated.dto

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import documents.api.generated.dto.StringDto
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
 * @param id 
 */
data class LoginResponseDto(

    @Schema(example = "Bearer <token>", description = "")
    @get:JsonProperty("token") val token: kotlin.String? = null,

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("id") val id: StringDto? = null
) {

}

