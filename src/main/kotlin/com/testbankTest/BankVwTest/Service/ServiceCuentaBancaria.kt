package com.testbankTest.BankVwTest.Service

import com.testbankTest.BankVwTest.entity.CuentaBancaria

interface ServiceCuentaBancaria {
    abstract fun findByAccount(numberAccount:String):CuentaBancaria?
    abstract fun createAccount(cuenta:CuentaBancaria):CuentaBancaria
    abstract fun updateAccount(cuenta:CuentaBancaria):CuentaBancaria
}