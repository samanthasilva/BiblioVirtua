$(document).ready( function() {
$("#cursoadd_nome,#cursoedit_nome").bind('keyup', function (e) {
    if (e.which >= 97 && e.which <= 122) {
        var newKey = e.which - 32;
        e.keyCode = newKey;
        e.charCode = newKey;
    }
    $("#cursoadd_nome").val(($("#cursoadd_nome").val()).toUpperCase());
    $("#cursoedit_nome").val(($("#cursoedit_nome").val()).toUpperCase());
});    

$("#formcurso_adicionar").validate({
    // Define as regras
    rules:{
      nomeCurso:{
        required: true, minlength: 3
      }
    },

    messages:{
      nomeCurso:{
        required: "O nome do curso deve ser preenchido. Por favor insira um registro.",
        minlength: "O nome do curso deve conter, no minimo, 3 caracteres"
      }
    }
  });


 $("#formcurso_editar").validate({

    rules:{
      nomeCurso:{
        required: true, minlength: 3
      }
    },

    messages:{
      nomeCurso:{
        required: "O nome do curso deve ser preenchido. Por favor insira um registro.",
        minlength: "O nome do curso deve conter, no minimo, 3 caracteres"
      }
    }
  });        
});

