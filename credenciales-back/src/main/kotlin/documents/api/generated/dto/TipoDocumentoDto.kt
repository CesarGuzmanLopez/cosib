package documents.api.generated.dto

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.annotation.JsonCreator
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
* Values: ine,csf,documento_bancario,ine_frente,ine_reverso
*/
enum class TipoDocumentoDto(@get:JsonValue val value: kotlin.String) {

    ine("ine"),
    csf("csf"),
    documento_bancario("documento_bancario"),
    ine_frente("ine_frente"),
    ine_reverso("ine_reverso");

    companion object {
        @JvmStatic
        @JsonCreator
        fun forValue(value: kotlin.String): TipoDocumentoDto {
                return values().first{it -> it.value == value}
        }
    }
}

