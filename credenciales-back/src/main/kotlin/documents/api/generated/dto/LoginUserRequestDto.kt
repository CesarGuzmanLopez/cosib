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
 * @param username Nombre de usuario
 * @param password Contraseña
 */
data class LoginUserRequestDto(

    @Schema(example = "null", description = "Nombre de usuario")
    @get:JsonProperty("username") val username: kotlin.String? = null,

    @Schema(example = "null", description = "Contraseña")
    @get:JsonProperty("password") val password: kotlin.String? = null
) {

}

