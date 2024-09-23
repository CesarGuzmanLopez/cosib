package com.verificator.credenciales.repository

import com.verificator.credenciales.model.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<Users, UUID> {
    fun findByUsername(username: String): Users?
                
}