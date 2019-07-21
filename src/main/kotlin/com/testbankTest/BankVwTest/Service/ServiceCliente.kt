package com.testbankTest.BankVwTest.Service

import com.testbankTest.BankVwTest.entity.Cliente

interface ServiceCliente {
    fun save(cliente: Cliente):Cliente
    fun listAllClientes():List<Cliente>
    fun validateCliente(greedCard:String):Cliente?
}