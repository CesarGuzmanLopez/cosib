package documents.api.generated.dto

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonValue
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
* Values: no_subido,subido_invalido,subido_valido,subido_verificado
*/
enum class EstadoDocumentoDto(val value: kotlin.String) {

    @JsonProperty("no_subido") no_subido("no_subido"),
    @JsonProperty("subido_invalido") subido_invalido("subido_invalido"),
    @JsonProperty("subido_valido") subido_valido("subido_valido"),
    @JsonProperty("subido_verificado") subido_verificado("subido_verificado")
}

