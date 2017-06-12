$(document).ready( function() {


$("#nome").bind('keyup', function (e) {
    if (e.which >= 97 && e.which <= 122) {
        var newKey = e.which - 32;
        e.keyCode = newKey;
        e.charCode = newKey;
    }
    $("#nome").val(($("#nome").val()).toUpperCase());
});  
    
    

jQuery.validator.addMethod( "fullname", function(value, element) {
    
    if (/\w+\s+\w+/.test(value)) {
        return true;
    } else {
        return false;
    }
}, "O nome deve ser completo." );       
    
$(".form-registrar").validate({
 
    rules:{
      matricula:{
        required: true, minlength: 5, number: true
      },
      nome:{
        required: true,
        fullname: true
      },
      email:{
        required: true, email: true
      },
      senha:{
        required: true, minlength: 6  
      }
    },

    messages:{
      matricula:{
        required: "A matricula e obrigatoria.",
        number: "A matricula deve conter somente numeros inteiros.",
        minlength: "A matricula deve conter, no minimo, 5 digitos."
      },
      nome:{
        required: "O nome e obrigatorio",
        fullname: "O nome deve ser completo. Exemplo: Samantha Silva" 
      },
      email:{
        required: "O email e obrigatorio",
        email: "Insira um email valido"
      },
      senha:{
        required: "A senha e obrigatoria",
        minlength: "A senha deve conter, no minimo 6 caracteres"
      }
    }
  });

$(".form-login").validate({

    rules:{
      j_username:{
        required: true, minlength: 5, number: true
      },
      j_password:{
        required: true
      }
    },
    messages:{
      j_username:{
        required: "O campo matricula deve ser preenchido. Por favor insira um registro.",
        number: "A matricula deve conter somente numeros.",
        minlength: "A matricula deve conter, no minimo, 5 digitos."
      },
      j_password:{
        required: "O campo senha deve ser preenchido. Por favor insira um registro."
      }
    }
  });

});