new Vue({
        el: "#operationAccount",
        data:{
        typeOperation:"",
        account:"",
        amount:"",
        provAccount:"",
        log:""
  },
  methods: {
     changeItem: function changeItem(event) {

           var divCuentaDestino = document.getElementById("cuentaDestino");

           if(event.target.value == "0002"){

              divCuentaDestino.style = "display:block";
              this.provAccount = "";
           }
           else{
              divCuentaDestino.style = "display:none";
           }
     },
     createOperation: function createOperation(event){

           event.preventDefault();

           var object = this;

           if(this.account == ""){

              this.log = "Ingrese una cuenta correcta";

              return;
           }
           if(this.amount == ""){

              this.log = "Ingrese un monto";

              return;
           }
           if(this.typeOperation  == ""){

              this.log = "Seleccione un tipo de operacion";

              return;
           }

           if(this.typeOperation == "0002" && this.provAccount == "" ){

              this.log = "Ingrese una cuenta destino para esta operacion";

              return;
           }

           if(this.typeOperation == "0001" || this.typeOperation == "0003"){

               this.provAccount = this.account;
           }
           this.log = "";


            this.$http.get('/Account/operationAccount/'+this.account+"/"+this.typeOperation+"/"+this.amount+"/"+this.provAccount).then(function(response){

                 object.log = response.body.message;
                 object.account = "";
                 object.typeOperation = "";
                 object.amount = "";

            }, function(){
               //Método que se dispara si hubo algún error.
            });

     }


  }

});