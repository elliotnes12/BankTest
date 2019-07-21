 new Vue({
        el: "#formAccount",
        data:{
        numberAccount: "",
        typeAccount: "",
        amount:"",
        firstName:"",
        lastName:"",
        gender:"",
        greenCard:"",
        log:""
  },
  methods: {

      sub: function(event){

           event.preventDefault();
           var object = this;

           var data = {
                "numberAccount": object.numberAccount,
                "typeAccount": object.typeAccount,
                "amount": object.amount,
                "firstName": object.firstName,
                "lastName": object.lastName,
                "gender": object.gender,
                "greenCard":object.greenCard
           }


            if(this.numberAccount == "" ){

             this.log = "Ingrese una cuenta 10 digitos";

             return;
            }
            if(this.typeAccount == ""){

              this.log = "Ingrese el tipo de cuenta";

              return;
            }
            if(this.amount == ""){

               this.log = "Ingrese un monto";
               return;
            }
            if(this.firstName == ""){
               this.log = "Ingrese un nombre";
               return;
            }
            if(this.lastName == ""){
                this.log = "Ingrese un apellido";
                return;
            }
            if(this.gender == ""){
                this.log = "Ingrese el sexo";
                return;
            }
            if(this.greenCard == ""){
               this.log = "Ingrese la curp";
               return;
            }

             this.$http.post('/Account/add',data).then(function(response){


               object.log = response.body.message;

             }, function(){

             });

      }
  }

});



