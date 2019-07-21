package com.testbankTest.BankVwTest.entity


import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "CuentaBancaria")
class CuentaBancaria
{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var idAccount: Int = 0

        @Column(nullable = false)
        var numberAccount: String = ""

        @Column(nullable = false)
        var typeAccount: String = ""

        @Column(nullable = false)
        var amount: String = ""

        @Column(nullable = false)
        @Type(type = "org.hibernate.type.NumericBooleanType")
        var enabled:Boolean = false

        @Column(name="dateCreated")
        @Temporal(TemporalType.DATE)
        var dateCreated: Date = Date()

        @Column(nullable = false)
        @JsonManagedReference
        @OneToMany(mappedBy = "cuentabancaria")
        var operations:Set<CuentaOperacion>? = null

}



