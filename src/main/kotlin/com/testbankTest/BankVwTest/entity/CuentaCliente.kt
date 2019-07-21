package com.testbankTest.BankVwTest.entity

import java.util.*
import javax.persistence.*


@Entity
@Table(name = "CuentaCliente")
class CuentaCliente{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var idCuentaCliente: Int = 0

        @Column(name="dateCreated")
        @Temporal(TemporalType.DATE)
        var dateCreated: Date = Date()

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "cliente_id")
        var cliente:Cliente? = null

        @OneToOne
        @JoinColumn(name="idAccount")
        var idAccount:CuentaBancaria? = null

}


