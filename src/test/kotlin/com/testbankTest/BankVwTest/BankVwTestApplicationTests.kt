package com.testbankTest.BankVwTest

import com.fasterxml.jackson.databind.ObjectMapper
import com.testbankTest.BankVwTest.Service.ServiceCuentaBancaria
import com.testbankTest.BankVwTest.pojos.ClienteCuentaTest
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringRunner::class)
@SpringBootTest
class BankVwTestApplicationTests {

	@Autowired
	private lateinit var WebApplicationContext:WebApplicationContext

	/*Configuramos el Objeto Mock para imprimir las cabeceras*/
	private val mockMvc: MockMvc by lazy {
		MockMvcBuilders.webAppContextSetup(WebApplicationContext).alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print()).build()
	}


	@Autowired
	private lateinit var mapper:ObjectMapper

	@Autowired
	private lateinit var cuentabancaria: ServiceCuentaBancaria
	@Test
	fun contextLoads() {
	}

	@Test
	fun findAllAccount(){
		mockMvc.perform(MockMvcRequestBuilders.get("/Account/findAll/123456789")).andExpect(status().isOk).andReturn()
	}
	@Test
    fun saveSuccessFully(){

		val cliente: ClienteCuentaTest = ClienteCuentaTest()
		cliente.firstName = "Elliot"
		cliente.lastName = "Garcia"
		cliente.amount = "100"
		cliente.gender = "M"
		cliente.numberAccount = "10001"

		mockMvc.perform(MockMvcRequestBuilders.post("/Account/add").content(mapper.writeValueAsBytes(cliente)).contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk)
	}
}
