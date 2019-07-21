package com.testbankTest.BankVwTest.repository

import com.testbankTest.BankVwTest.entity.CuentaBancaria
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.io.Serializable

@Repository("CuentaBancariaJpaRepository")
interface CuentaBancariaJpaRepository : JpaRepository<CuentaBancaria, Serializable> {
    abstract fun findBynumberAccount(numberAccount:String):CuentaBancaria?
}