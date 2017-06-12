$(document).ready( function() {
    
  $("#formlivro_adicionar").validate({
    // Regra de negócio
    rules:{
      isbn:{
        required: true
      },
      titulo:{
        required: true
      },
      autor:{
        required: true
      },
      local:{
        required: true
      },
      exemplares:{
       required: true,
       number: true
      }
    },
 
    messages:{
      isbn:{
        required: "O ISBN deve ser preenchido. Por favor insira um registro."
      },
      titulo:{
        required: "O Titulo deve ser preenchido. Por favor insira um registro."
      },
      autor:{
        required: "O campo Autor deve ser preenchido. Por favor insira um registro."
      },
      local:{
        required: "O local deve ser preenchido. Por favor insira um registro."
      }, 
      exemplares:{
        required: "O campo exemplares deve ser preenchido. Por favor insira um registro.",
        number: "A quantidade de exemplares deve ser em numeros"
      }     
    }
  });


$("#formlivro_editar").validate({

    rules:{
      isbn:{
        required: true
      },
      titulo:{
        required: true
      },
      autor:{
        required: true
      },
      local:{
        required: true
      },
      exemplares:{
       required: true,
       number: true
      }
    },

    messages:{
      isbn:{
        required: "O registro ISBN deve ser preenchido. Por favor insira um registro."
      },
      titulo:{
        required: "O Titulo deve ser preenchido. Por favor insira um registro."
      },
      autor:{
        required: "O campo Autor deve ser preenchido. Por favor insira um registro."
      },
      local:{
        required: "O local deve ser preenchido. Por favor insira um registro."
      }, 
      exemplares:{
        required: "O campo exemplares deve ser preenchido. Por favor insira um registro.",
        number: "A quantidade de exemplares deve ser em numeros"
      }     
    }
  });        
});

