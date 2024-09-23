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
 * @param nombre Nombre
 * @param apellido1 Primer apellido
 * @param apellido2 Segundo apellido
 * @param fechaNacimiento Fecha de nacimiento
 * @param sexo Sexo
 * @param direccion Dirección
 */
data class RegisterRequestDto(

    @Schema(example = "null", required = true, description = "Nombre de usuario")
    @get:JsonProperty("username", required = true) val username: kotlin.String,

    @Schema(example = "null", required = true, description = "Contraseña")
    @get:JsonProperty("password", required = true) val password: kotlin.String,

    @Schema(example = "null", required = true, description = "Nombre")
    @get:JsonProperty("nombre", required = true) val nombre: kotlin.String,

    @Schema(example = "null", required = true, description = "Primer apellido")
    @get:JsonProperty("apellido1", required = true) val apellido1: kotlin.String,

    @Schema(example = "null", required = true, description = "Segundo apellido")
    @get:JsonProperty("apellido2", required = true) val apellido2: kotlin.String,

    @field:Valid
    @Schema(example = "null", description = "Fecha de nacimiento")
    @get:JsonProperty("fecha_nacimiento") val fechaNacimiento: java.time.LocalDate? = null,

    @Schema(example = "null", description = "Sexo")
    @get:JsonProperty("sexo") val sexo: kotlin.String? = null,

    @Schema(example = "null", description = "Dirección")
    @get:JsonProperty("direccion") val direccion: kotlin.String? = null
    ) {

}

