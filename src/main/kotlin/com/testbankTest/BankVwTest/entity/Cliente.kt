package com.testbankTest.BankVwTest.entity

import javax.persistence.*

@Entity
@Table(name = "Cliente")
class Cliente{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idCliente: Int = 0

    @Column(nullable = false)
    var firstName: String = ""

    @Column(nullable = false)
    var lastName: String = ""

    @Column(nullable = false)
    var grindcard: String = ""

    @Column(nullable = false)
    var gender: String = ""

    @Column(nullable = false)
    @OneToMany(mappedBy = "cliente")
    var cuentas:List<CuentaCliente>? = null

}













