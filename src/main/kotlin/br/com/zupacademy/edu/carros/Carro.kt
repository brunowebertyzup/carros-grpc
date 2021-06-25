package br.com.zupacademy.edu.carros

import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY
import javax.validation.constraints.NotBlank

@Entity
class Carro(
    @field:NotBlank
    @Column(nullable = false)
    val modelo: String,

    @field:NotBlank
    @Column(nullable = false, unique = true)
    val placa: String
) {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long? = null
}