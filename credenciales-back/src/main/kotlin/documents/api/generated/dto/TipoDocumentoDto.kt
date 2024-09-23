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
* Values: ine,csf,documento_bancario,ine_frente,ine_reverso
*/
enum class TipoDocumentoDto(val value: kotlin.String) {

    @JsonProperty("ine") ine("ine"),
    @JsonProperty("csf") csf("csf"),
    @JsonProperty("documento_bancario") documento_bancario("documento_bancario"),
    @JsonProperty("ine_frente") ine_frente("ine_frente"),
    @JsonProperty("ine_reverso") ine_reverso("ine_reverso")
}

