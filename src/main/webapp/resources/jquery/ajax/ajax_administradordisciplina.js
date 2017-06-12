/* global alertify */

$(function(){

    $('button[name=editardisciplina]').click(function(){
    
        var nomeDoCurso = $(this).closest("tr").find("td[name=tb_curso_disciplina]").html();
      
        var codigoDoCurso = $(this).closest("tr").find("td[name=tb_curso_disciplina]").attr('id');
      
        var codigoDaDisc = $(this).closest("tr").find("td[name=tb_cod_disciplina]").html(); 
        
        var nomeDaDisc = $(this).closest("tr").find("td[name=tb_nome_disciplina]").html();
        
     
        $('#edit_nome_curso').val(codigoDoCurso);
       
        $('#edit_cod_disciplina').val(codigoDaDisc);
      
        $('#edit_nome_disciplina').val(nomeDaDisc); 
	});
  
    $('button[name=excluirdisciplina]').click(function(){
      
        var codigoDaDisc = $(this).closest("tr").find("td[name=tb_cod_disciplina]").html(); 
       
        var nomeDaDisc = $(this).closest("tr").find("td[name=tb_nome_disciplina]").html();
        
        var $this = $(this); 
        alertify.confirm(
                        "EXCLUSAO DA DISCIPLINA "
                        +nomeDaDisc, 
                        "Voce tem certeza que deseja remover a disciplina  <strong>"
                        +nomeDaDisc+
                        "</strong> ?", "", "").
                        autoCancel(10).set('onok', function(closeEvent){ 
            if (closeEvent) {
                $.ajax({
                        type: 'POST',
                        url: 'removedisciplina',
                        data: 'codigoDisciplina='+codigoDaDisc,
                        statusCode:{
                            404: function(){
                                alert("/404");
                            },
                            500: function(){
                                alert("Erro no servidor");
                            }
                        },
                        success: function(response){
                    
                            $this.closest('tr').remove(); 
                           
                            alertify.success(" Disciplina "+ nomeDaDisc+" removida com sucesso!"); 
                        }
                    });
            }
        } );   
	});
    
    
    $('#op_pesq_cod').on('change',function(){
        $('#search_nome_disciplina').hide();
        $('#search_nome_disciplina').val("");
        $('#search_cod_disciplina').show();
        
    });
    
    $('#op_pesq_nome').on('change',function(){
        $('#search_cod_disciplina').hide();
        $('#search_cod_disciplina').val("");
        $('#search_nome_disciplina').show();
    });
    
});
