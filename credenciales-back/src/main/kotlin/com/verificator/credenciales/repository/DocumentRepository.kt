package com.verificator.credenciales.repository

import com.verificator.credenciales.model.Document
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DocumentRepository : JpaRepository<Document, UUID> {
    fun findByUserId(userId: UUID): List<Document>
}