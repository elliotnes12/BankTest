package com.testbankTest.BankVwTest.Service

import com.testbankTest.BankVwTest.entity.CuentaCliente
import com.testbankTest.BankVwTest.repository.CuentaClienteJpaRepository
import org.mapstruct.Qualifier
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("ServiceCuentaClienteImpl")
class ServiceCuentaClienteImpl : ServiceCuentaCliente {


    @Autowired
    lateinit var cuentajpa: CuentaClienteJpaRepository

    override fun createRelationCuenta(relation: CuentaCliente): CuentaCliente {
        return cuentajpa.save(relation)
    }
}