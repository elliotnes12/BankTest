package com.testbankTest.BankVwTest.repository

import com.testbankTest.BankVwTest.entity.CuentaOperacion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.io.Serializable

@Repository("CuentaOperacionRepository")
interface CuentaOperacionJpaRepository : JpaRepository<CuentaOperacion,Serializable> {

}