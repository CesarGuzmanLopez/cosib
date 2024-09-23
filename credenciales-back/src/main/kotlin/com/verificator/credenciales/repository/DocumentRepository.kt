package com.verificator.credenciales.repository

 import com.verificator.credenciales.model.Documents
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DocumentRepository : JpaRepository<Documents, UUID> {
    fun findByUserId(userId: UUID): List<Documents>
}