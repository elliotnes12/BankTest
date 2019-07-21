package com.testbankTest.BankVwTest.Service

import com.testbankTest.BankVwTest.entity.CuentaOperacion
import com.testbankTest.BankVwTest.repository.CuentaOperacionJpaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("ServiceCuentaOperacion")
class ServiceCuentaOperacionImpl : ServiceCuentaOperacion {

    @Autowired
    lateinit var cuentajpa: CuentaOperacionJpaRepository

    override fun createOperation(cuenta: CuentaOperacion): CuentaOperacion {
        return cuentajpa.save(cuenta)
    }
}