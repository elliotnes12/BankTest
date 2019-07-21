package com.testbankTest.BankVwTest.Service

import com.testbankTest.BankVwTest.entity.CuentaOperacion

interface ServiceCuentaOperacion {
    abstract  fun  createOperation(cuenta: CuentaOperacion):CuentaOperacion
}