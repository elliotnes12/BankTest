package com.testbankTest.BankVwTest.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import java.util.*
import javax.persistence.*


@Entity
@Table(name="CuentaOperacion")
class CuentaOperacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idOperation: Int = 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "cuenta_id")
    var cuentabancaria:CuentaBancaria? = null

    @Column(name="dateCreated")
    @Temporal(TemporalType.DATE)
    var dateCreated: Date = Date()

    @Column(name="provAccount")
    var cuentaproveedora:String = ""

    @Column(name="amount")
    var amount:String = ""

    @Column(name="typeOperation")
    var typeOperation:String = ""

}