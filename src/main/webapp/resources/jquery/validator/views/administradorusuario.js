$(document).ready( function() {
    

$("#add_nome,#edit_nome").bind('keyup', function (e) {
    if (e.which >= 97 && e.which <= 122) {
        var newKey = e.which - 32;
        e.keyCode = newKey;
        e.charCode = newKey;
    }
    $("#add_nome").val(($("#add_nome").val()).toUpperCase());
    $("#edit_nome").val(($("#edit_nome").val()).toUpperCase());
});  
    
    

 jQuery.validator.addMethod( "fullname", function(value, element) {
    
    if (/\w+\s+\w+/.test(value)) {
        return true;
    } else {
        return false;
    }
}, "O nome deve ser completo." );    
    
  $("#formusuario_adicionar").validate({

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
        required: "A matricula e obrigatoria",
        number: "A matricula deve conter somente numeros",
        minlength: "A matricula deve conter, no minimo, 5 numeros"
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
        minlength: "A senha deve conter, no minimo, 6 caracteres"
      }
    }
  });
    
 $("#formusuario_editar").validate({

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
        required: "A matricula e obrigatoria",
        number: "A matricula deve conter somente numeros",
        minlength: "A matricula deve conter, no minimo, 5 numeros"
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
        minlength: "A senha deve conter, no minimo, 6 caracteres"
      }
    }
  }); 
    
 $("#formusuario_alterasenha").validate({
    // Define as regras
    rules:{
      senha:{
        required: true, minlength: 6
      },
      senhaconfirmacao:{
        equalTo: "#novasenha_senha",   
        required: true, minlength: 6
      }
    },
  
    messages:{
      senha:{
        required: "A senha e obrigatoria",
        minlength: "A senha deve conter, no minimo, 6 caracteres"
      },
      senhaconfirmacao:{
        required: "A senha e obrigatoria",
        minlength: "A senha deve conter, no minimo, 6 caracteres",
        equalTo: "A senha de confirmaçao não esta igual a nova senha"  
      }
    }
  });
    
 $("#formusuario_alterasenha_menu").validate({
   
    rules:{
      senha:{
        required: true, minlength: 6
      },
      senhaconfirmacao:{
        equalTo: "#novasenha_senha_menu",   
        required: true, minlength: 6
      }
    },
   
    messages:{
      senha:{
        required: "A senha e obrigatoria",
        minlength: "A senha deve conter, no minimo, 6 caracteres"
      },
      senhaconfirmacao:{
        required: "A senha e obrigatoria",
        minlength: "A senha deve conter, no minimo, 6 caracteres",
        equalTo: "A senha de confirmaçao não esta igual a nova senha"  
      }
    }
  });    
    
});