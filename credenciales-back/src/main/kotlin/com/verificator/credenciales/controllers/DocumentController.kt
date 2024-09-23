package com.verificator.credenciales.controllers

import documents.api.generated.api.DocumentosApi
import documents.api.generated.dto.EstadosDocumentosResponseDto
import documents.api.generated.dto.VerificarCSF200ResponseDto
import documents.api.generated.dto.VerificarDocumentoBancario200ResponseDto
import documents.api.generated.dto.VerificarINE200ResponseDto
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class DocumentController  : DocumentosApi{
    override fun obtenerEstadoDocumentos(usuarioId: String): ResponseEntity<EstadosDocumentosResponseDto> {
        TODO("Not yet implemented")

    }

    override fun verificarCSF(archivo: Resource?): ResponseEntity<VerificarCSF200ResponseDto> {
        TODO("Not yet implemented")
    }

    override fun verificarDocumentoBancario(documento: Resource?): ResponseEntity<VerificarDocumentoBancario200ResponseDto> {
        TODO("Not yet implemented")
    }

    override fun verificarINE(ine1: Resource?, ine2: Resource?): ResponseEntity<VerificarINE200ResponseDto> {
        TODO("Not yet implemented")
            }
}