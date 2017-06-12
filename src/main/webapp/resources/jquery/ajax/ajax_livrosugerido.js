/* global alertify */
$(function(){
    $('select[name=codigoDisciplina]').hide();
    
    $('button[name=btnpesquisar]').attr('disabled', 'disabled');
    
  
    $('select[name=codigoCurso]').on('change',function(){
        var codigoDoCursoComboBox = $('select[name=codigoCurso]').val();
		$.ajax({
			type: 'POST',
			url: 'buscardisciplinas',
			data: 'codigoCurso=' +codigoDoCursoComboBox,
			statusCode:{
				404: function(){
					alert("Servidor não encontrado.");
				},
				500: function(){
					alert("Ocorreu um erro interno no servidor.");
				}
			},
			success: function(dados){
                $('select[name=codigoDisciplina]').fadeIn(1000).show();
				$('select[name=codigoDisciplina] option').remove();
				$('select[name=codigoDisciplina]').append('<option value="-1">Selecione a disciplina</option>');
				var pegadados = dados.split(":");
				for(var i = 0; i < pegadados.length - 1; i++){
					var codigoDisciplina = pegadados[i].split("-")[0];
					var nomeDisciplina = pegadados[i].split("-")[1];
					$('select[name=codigoDisciplina]').append(
                        '<option value="'+codigoDisciplina+'">'+nomeDisciplina+'</option>'
                    );
				}
			}
			
		});
	});
    
    
     $('select[name=codigoDisciplina]').on('change',function(){
        var codigoDaDisciplinaComboBox = $('select[name=codigoDisciplina]').val(); 
        if(codigoDaDisciplinaComboBox != -1){
            $('button[name=btnpesquisar]').removeAttr('disabled');
        }else{
            $('button[name=btnpesquisar]').attr('disabled', 'disabled');
        }		
	});
     
});
