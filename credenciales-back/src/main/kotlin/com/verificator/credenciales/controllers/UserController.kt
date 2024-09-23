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
        //gemerop un jwt con el username
        val jwt: String = generatetoken(loginUserRequestDto.username)
        val respuesta = LoginResponseDto(
            token = jwt,
            id = user.id
        )
        return ResponseEntity.ok(respuesta)
    }

    override fun registerUser(registerRequestDto: RegisterRequestDto): ResponseEntity<LoginResponseDto> {
        val user = Users(
            username = registerRequestDto.username,
            password = registerRequestDto.password,
            nombre = registerRequestDto.nombre,
            fechaNacimiento = registerRequestDto.fechaNacimiento,
            sexo = registerRequestDto.sexo,
            direccion = registerRequestDto.direccion
        )
        val savedUser =  userRepository.save(user)

        val jwt: String = generatetoken(registerRequestDto.username)
        val respuesta = LoginResponseDto(
            token = jwt,
            id = savedUser.id
        )
        return ResponseEntity.ok(respuesta)
    }

    override fun obtenerDatosUsuario(usuarioId: String): ResponseEntity<UserDataResponseDto> {
        val user: Users = userRepository.findById(UUID.fromString(usuarioId)).orElseThrow {
            RuntimeException("User not found")
        }

        return ResponseEntity.ok(UserDataResponseDto(
            nombre = user.nombre,
            fechaNacimiento = user.fechaNacimiento,
            sexo = user.sexo,
            direccion = user.direccion
        ))
    }

    override fun modificarDatosUsuario(
        usuarioId: String,
        userUpdateRequestDto: UserUpdateRequestDto
    ): ResponseEntity<UserDataResponseDto> {
        var user: Users = userRepository.findById(UUID.fromString(usuarioId)).orElseThrow {
            RuntimeException("User not found")
        }

        user.nombre = userUpdateRequestDto.nombre
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