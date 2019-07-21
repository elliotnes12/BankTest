 new Vue({
        el: "#listOperations",
        data:{
        account:"",
        log:""
  },
  methods: {

      consulta: function(event){

                 event.preventDefault();
                 var object = this;

                 if(this.account == ""){

                  this.log = "Ingrese la cuenta";

                  return;
                 }

                this.$http.get('/Account/findAll/'+this.account).then(function(response){

                 if(response.bodyText != ""){

                    var json = JSON.parse(response.bodyText);

                    var cadena = "";
                    json.forEach(function(i,v){

                        cadena += '<div class="t-row"><div class="t-column">'+i.idOperation+'</div><div class="t-column">'+i.amount+'</div><div class="t-column">'+i.typeOperation+'</div><div class="t-column">'+i.dateCreated+'</div></div>';

                     });

                     var table = document.getElementById("table");
                     table.innerHTML = cadena;
                     object.log = "";
                 }
                 else{

                    object.log = "Sin Datos";
                 }


                 }, function(){
                             //Método que se dispara si hubo algún error.
                });
      }
  }

});

