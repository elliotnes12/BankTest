package com.testbankTest.BankVwTest.Service

import com.testbankTest.BankVwTest.entity.Cliente
import com.testbankTest.BankVwTest.repository.ClienteJpaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("ServiceCliente")
class ServiceClienteImp : ServiceCliente {


    @Autowired
    lateinit var clientejpa: ClienteJpaRepository

    override fun listAllClientes(): List<Cliente> {
       return clientejpa.findAll()
    }

    override fun save(cliente: Cliente): Cliente {
        return clientejpa.save(cliente)
    }

    override fun validateCliente(greencard:String): Cliente? = clientejpa.findBygrindcard(greencard)

}