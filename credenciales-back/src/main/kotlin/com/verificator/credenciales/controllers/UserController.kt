package com.verificator.credenciales.controllers

import com.verificator.credenciales.model.Users
import com.verificator.credenciales.repository.UserRepository
import documents.api.generated.api.UsuariosApi
import documents.api.generated.dto.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api")
class UserController(
    private val userRepository: UserRepository
) : UsuariosApi {
    fun generatetoken(username: String): String {
        return "token_$username"
    }

    override fun loginUser(loginUserRequestDto: LoginUserRequestDto): ResponseEntity<LoginResponseDto> {
        //busco el usuario verifico contraseña y genero token
        val user: Users = loginUserRequestDto.username?.let { userRepository.findByUsername(it) } ?: throw RuntimeException("User not found")
        if (user.password != loginUserRequestDto.password) {
            throw RuntimeException("Invalid password")
        }
        //genero un jwt con el username
        val jwt: String = generatetoken(loginUserRequestDto.username)
        val respuesta = LoginResponseDto(
            token = jwt,
            id = user.id.toString()
        )
        return ResponseEntity.ok(respuesta)
    }

    override fun registerUser(registerRequestDto: RegisterRequestDto): ResponseEntity<LoginResponseDto> {
        //primero verifico si el usuario ya existe
        val userExists: Users? = userRepository.findByUsername(registerRequestDto.username)
        if (userExists != null) {
            throw RuntimeException("User already exists")
        }
        val user = Users(
            username = registerRequestDto.username,
            password = registerRequestDto.password,
            nombre = registerRequestDto.nombre,
            apellido1 = registerRequestDto.apellido1,
            apellido2 = registerRequestDto.apellido2,
            fechaNacimiento = registerRequestDto.fechaNacimiento,
            sexo = registerRequestDto.sexo,
            direccion = registerRequestDto.direccion
        )
        val savedUser = userRepository.save(user)

        val jwt: String = generatetoken(registerRequestDto.username)
        val respuesta = LoginResponseDto(
            token = jwt,
            id = savedUser.id.toString()
        )
        return ResponseEntity.ok(respuesta)
    }

    override fun obtenerDatosUsuario(usuarioId: String): ResponseEntity<UserDataResponseDto> {
        val user: Users = userRepository.findById(UUID.fromString(usuarioId)).orElseThrow {
            RuntimeException("User not found")
        }

        return ResponseEntity.ok(UserDataResponseDto(
            nombre = user.nombre,
            apellido1 = user.apellido1,
            apellido2 = user.apellido2,
            fechaNacimiento = user.fechaNacimiento,
            sexo = user.sexo,
            direccion = user.direccion
        ))
    }

    override fun modificarDatosUsuario(
        usuarioId: String,
        userUpdateRequestDto: UserUpdateRequestDto
    ): ResponseEntity<UserDataResponseDto> {
        val user: Users = userRepository.findById(UUID.fromString(usuarioId)).orElseThrow {
            RuntimeException("User not found")
        }

        user.nombre = userUpdateRequestDto.nombre
        user.apellido1 = userUpdateRequestDto.apellido1
        user.apellido2 = userUpdateRequestDto.apellido2
        user.fechaNacimiento = userUpdateRequestDto.fechaNacimiento
        user.sexo = userUpdateRequestDto.sexo
        user.direccion = userUpdateRequestDto.direccion
        val updatedUser = userRepository.save(user)

        return ResponseEntity.ok(UserDataResponseDto(
            nombre = updatedUser.nombre,
            fechaNacimiento = updatedUser.fechaNacimiento,
            sexo = updatedUser.sexo,
            direccion = updatedUser.direccion
        ))
    }
}