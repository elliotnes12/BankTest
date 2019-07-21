package com.testbankTest.BankVwTest.Controller

import com.testbankTest.BankVwTest.utilidades.ResponseRest
import org.springframework.web.bind.annotation.*
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.testbankTest.BankVwTest.Service.ServiceCliente
import com.testbankTest.BankVwTest.Service.ServiceCuentaBancaria
import com.testbankTest.BankVwTest.Service.ServiceCuentaCliente
import com.testbankTest.BankVwTest.Service.ServiceCuentaOperacion
import com.testbankTest.BankVwTest.entity.Cliente
import com.testbankTest.BankVwTest.entity.CuentaBancaria
import com.testbankTest.BankVwTest.entity.CuentaCliente
import com.testbankTest.BankVwTest.entity.CuentaOperacion
import jdk.nashorn.internal.runtime.JSType.toDouble
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import java.util.*


data class AccountObject(
        @SerializedName("numberAccount") val numberAccount:String,
        @SerializedName("typeAccount")  val typeAccount:String,
        @SerializedName("amount")  val amount:String,
        @SerializedName("firstName")  val firstName:String,
        @SerializedName("lastName")  val lastName:String,
        @SerializedName("gender")  val gender:String,
        @SerializedName("greenCard")  val greenCard:String
)

@RestController
@CrossOrigin
@RequestMapping("/Account")
class AccountControllerRest{


    @Autowired
    @Qualifier("ServiceCliente")
    lateinit var serviceCliente: ServiceCliente

    @Autowired
    @Qualifier("ServiceCuentaBancaria")
    lateinit var serviceCuentaBancaria: ServiceCuentaBancaria

    @Autowired
    @Qualifier("ServiceCuentaClienteImpl")
    lateinit var serviceCuentaCliente: ServiceCuentaCliente

    @Autowired
    @Qualifier("ServiceCuentaOperacion")
    lateinit var serviceCuentaOperacionObject: ServiceCuentaOperacion




    @GetMapping("status")
    fun checkStatus():String{
        return "ok"
    }
    @GetMapping("/findAll/{account}")
    @ResponseBody
    fun getAllOperation(@PathVariable("account")account:String):Set<CuentaOperacion>?{


        var cuentab:CuentaBancaria? = serviceCuentaBancaria.findByAccount(account)

        if(cuentab != null){

            return cuentab.operations
        }

        return null
    }

    @GetMapping("/operationAccount/{account}/{typeOperation}/{amount}/{provAccount}")
    @ResponseBody
    fun operationAccount(@PathVariable("account") account:String,@PathVariable("typeOperation") typeOperation:String,@PathVariable("amount") amount:String,@PathVariable("provAccount") provAccount:String ): ResponseRest {
        var response = ResponseRest()

        var cuentab:CuentaBancaria? = serviceCuentaBancaria.findByAccount(account)

        /*La cuenta no existe*/
        if(cuentab == null){
            response.message = "Error : Ingrese una cuenta existente ::"+account+" Fecha :"+Date()

        }
        else{

           /*tipo de operacion*/

            if(typeOperation == "0003"){

                 var amountAccount = java.lang.Double.parseDouble(cuentab.amount)
                 var amountLine = java.lang.Double.parseDouble(amount)

                 var total:Double = amountAccount + amountLine

                 cuentab.amount = total.toString()

                 serviceCuentaBancaria.updateAccount(cuentab)

                 var operaciones:CuentaOperacion = CuentaOperacion()
                 operaciones.cuentabancaria = cuentab
                 operaciones.dateCreated = Date()
                 operaciones.typeOperation = typeOperation
                 operaciones.cuentaproveedora = cuentab.numberAccount
                 operaciones.amount = amountLine.toString()

                 /*
                 * Registramos las operaciones
                 * */
                 serviceCuentaOperacionObject.createOperation(operaciones)

                 response.message = "Deposito Realizado Monto:"+total+" Fecha :"+Date()
                 response.bandera = true
                 response.fromAccount = cuentab.numberAccount
                 response.amount = amountLine.toString()
            }
            if(typeOperation == "0001"){

                var amountAccount = java.lang.Double.parseDouble(cuentab.amount)
                var amountLine = java.lang.Double.parseDouble(amount)

                if(amountAccount < amountLine){
                     /*Fondos insuficientes*/
                    response.message = "Fondos insuficientes - Cuenta:"+account+" Fecha :"+Date()
                }

                if(amountAccount >= amountLine ){
                    /*Descuento*/

                    var discount:Double = amountAccount - amountLine

                    cuentab.amount = discount.toString()

                    serviceCuentaBancaria.updateAccount(cuentab)
                    var operaciones:CuentaOperacion = CuentaOperacion()

                    operaciones.cuentabancaria = cuentab
                    operaciones.dateCreated = Date()
                    operaciones.typeOperation = typeOperation
                    operaciones.amount = amountLine.toString()
                    operaciones.cuentaproveedora = cuentab.numberAccount

                    serviceCuentaOperacionObject.createOperation(operaciones)

                    response.message = "Retiro Realizado Monto:"+amountLine+" Fecha :"+Date()
                    response.bandera = true
                    response.fromAccount = cuentab.numberAccount
                    response.amount = amountLine.toString()


                }
            }

            if(typeOperation == "0002"){

                var amountAccount = java.lang.Double.parseDouble(cuentab.amount)
                var amountLine = java.lang.Double.parseDouble(amount)

                if(amountAccount < amountLine){
                    /*Fondos insuficientes*/
                    response.message = "Fondos insuficientes - Cuenta:"+account+" Fecha :"+Date()
                }

                if(amountAccount >= amountLine){
                    /*Verificamos cuenta de destino*/

                    var provAccountObject:CuentaBancaria? = serviceCuentaBancaria.findByAccount(provAccount)

                    if(provAccountObject == null){
                        response.message = "Error : Ingrese una cuenta existente para la transaccion - Cuenta ::"+provAccount+" Fecha :"+Date()
                    }
                    else{

                        var discount:Double = amountAccount - amountLine
                        cuentab.amount = discount.toString()

                        /*Actualizamos el total de la cuenta*/
                        serviceCuentaBancaria.updateAccount(cuentab)


                        var provAccountSum:Double = java.lang.Double.parseDouble(provAccountObject.amount)

                        var TotalAmount:Double = provAccountSum + amountLine
                        provAccountObject.amount = TotalAmount.toString()


                        serviceCuentaBancaria.updateAccount(provAccountObject)


                        /*Registro de operacion*/
                        var operaciones:CuentaOperacion = CuentaOperacion()

                        operaciones.cuentabancaria = cuentab
                        operaciones.dateCreated = Date()
                        operaciones.typeOperation = typeOperation
                        operaciones.amount = amountLine.toString()
                        operaciones.cuentaproveedora = provAccountObject.numberAccount
                        serviceCuentaOperacionObject.createOperation(operaciones)


                        response.message = "Transaccion Realizada Monto:"+amountLine+" Fecha :"+Date()+"- Cuenta Destino :"+provAccountObject.numberAccount
                        response.bandera = true
                        response.fromAccount = cuentab.numberAccount
                        response.toAccount = provAccountObject.numberAccount
                        response.amount = amountLine.toString()
                    }
                }




            }


        }


        return response
    }

    @PostMapping( "/add")
    @ResponseBody
    fun addNewAccount(@RequestBody(required = true) account:String): ResponseRest {
        var response = ResponseRest()
        var gson = Gson()


        /*
         *  Converter json to Object
         * */

        var objectA:AccountObject = gson.fromJson(account, AccountObject::class.java)


        /*
        * Agregamos El cliente
        * */



        var clobject:Cliente? = serviceCliente.validateCliente(objectA.greenCard)
        if(clobject == null){

            var cliente:Cliente = Cliente()
            cliente.firstName = objectA.firstName
            cliente.lastName = objectA.lastName
            cliente.gender = objectA.gender
            cliente.grindcard = objectA.greenCard

            clobject = serviceCliente.save(cliente)

         }

            /*Ya tenemos al cliente en la cartera*/

            var cuentab:CuentaBancaria? = serviceCuentaBancaria.findByAccount(objectA.numberAccount)

            if(cuentab == null){

                cuentab = CuentaBancaria()
                cuentab.numberAccount = objectA.numberAccount
                cuentab.typeAccount = objectA.typeAccount
                cuentab.amount = objectA.amount
                cuentab.enabled = true
                cuentab.dateCreated = Date()
                cuentab = serviceCuentaBancaria.createAccount(cuentab)


                var cuentaCliente = CuentaCliente()
                cuentaCliente.cliente = clobject
                cuentaCliente.dateCreated = Date()
                cuentaCliente.idAccount = cuentab

                serviceCuentaCliente.createRelationCuenta(cuentaCliente)

                response.message = "Cuentra registrada con éxito : Cuenta:"+objectA.numberAccount
                response.bandera = true

            }
            else{
                response.message = "El Numero de cuenta ya se encuentra registrada: "+objectA.numberAccount
            }



        return response
    }

}

