package com.testbankTest.BankVwTest.repository

import com.testbankTest.BankVwTest.entity.Cliente
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.io.Serializable

@Repository("JpaRepositoryCliente")
interface ClienteJpaRepository : JpaRepository<Cliente,Serializable> {
     abstract fun findBygrindcard(grindcard:String):Cliente?
}