package com.testbankTest.BankVwTest.repository

import com.testbankTest.BankVwTest.entity.CuentaCliente
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.io.Serializable

@Repository("RepositoryCuentaCliente")
interface CuentaClienteJpaRepository : JpaRepository<CuentaCliente,Serializable> {

}