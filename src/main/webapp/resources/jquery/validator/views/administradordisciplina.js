$(document).ready( function() {


$("#add_nome_disciplina,#edit_nome_disciplina").bind('keyup', function (e) {
    if (e.which >= 97 && e.which <= 122) {
        var newKey = e.which - 32;
        e.keyCode = newKey;
        e.charCode = newKey;
    }
    $("#add_nome_disciplina").val(($("#add_nome_disciplina").val()).toUpperCase());
    $("#edit_nome_disciplina").val(($("#edit_nome_disciplina").val()).toUpperCase());
});
    

    
  
$("#formdisciplina_adicionar").validate({
 
    rules:{
      nomeDisciplina:{
        required: true, minlength: 3
      }
    },

    messages:{
      nomeDisciplina:{
        required: "O nome da disciplina deve ser preenchido. Por favor insira um registro.",
        minlength: "O nome da disciplina deve conter, no minimo, 3 caracteres"
      }
    }
  });
 $("#formdisciplina_editar").validate({
    // Regras de negócio
    rules:{
      nomeDisciplina:{
        required: true, minlength: 3
      }
    },
    messages:{
      nomeDisciplina:{
        required: "O nome da disciplina deve ser preenchido. Por favor insira um registro.",
        minlength: "O nome da disciplina deve conter, no minimo, 3 caracteres"
      }
    }
  });        
});

