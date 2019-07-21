package com.testbankTest.BankVwTest.Service

import com.testbankTest.BankVwTest.entity.CuentaCliente

interface ServiceCuentaCliente {
    abstract  fun createRelationCuenta(relation: CuentaCliente):CuentaCliente
}