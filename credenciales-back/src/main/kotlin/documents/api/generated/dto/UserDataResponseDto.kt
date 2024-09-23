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
 * @param apellido1 
 * @param apellido2 
 * @param fechaNacimiento 
 * @param sexo 
 * @param direccion 
 * @param email 
 */
data class UserDataResponseDto(

    @Schema(example = "null", description = "")
    @get:JsonProperty("nombre") val nombre: kotlin.String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("apellido1") val apellido1: kotlin.String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("apellido2") val apellido2: kotlin.String? = null,

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("fecha_nacimiento") val fechaNacimiento: java.time.LocalDate? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("sexo") val sexo: kotlin.String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("direccion") val direccion: kotlin.String? = null,

    @get:Email
    @Schema(example = "null", description = "")
    @get:JsonProperty("email") val email: kotlin.String? = null
    ) {

}

