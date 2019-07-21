package com.testbankTest.BankVwTest.Service

import com.testbankTest.BankVwTest.entity.CuentaBancaria
import com.testbankTest.BankVwTest.repository.CuentaBancariaJpaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service("ServiceCuentaBancaria")
class ServiceCuentaBancariaImp : ServiceCuentaBancaria {

    @Autowired
    @Qualifier("CuentaBancariaJpaRepository")
    lateinit var cuentajpa: CuentaBancariaJpaRepository

    override fun findByAccount(numberAccount: String): CuentaBancaria? {
        return  cuentajpa.findBynumberAccount(numberAccount)
    }
    override fun createAccount(cuenta: CuentaBancaria): CuentaBancaria {
       return cuentajpa.save(cuenta)
    }
    override fun updateAccount(cuenta: CuentaBancaria): CuentaBancaria {
        return cuentajpa.save(cuenta)
    }

}