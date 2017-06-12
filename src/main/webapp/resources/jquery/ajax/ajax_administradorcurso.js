/* global alertify */

$(function(){

    $('button[name=editarcurso]').click(function(){
        var codigoDoCurso = $(this).closest("tr").find("td[name=tb_codigo]").html();
        var nomeDoCurso = $(this).closest("tr").find("td[name=tb_nome]").html();
        var descDoCurso = $(this).closest("tr").find("td[name=tb_descricao_curso]").html(); 
        $('#cursoedit_codigo').val(codigoDoCurso);
        $('#cursoedit_nome').val(nomeDoCurso);
        $('#edit_desc_curso').val(descDoCurso); 
	});
    
  
    $('button[name=excluircurso]').click(function(){
        var codigoDoCurso = $(this).closest("tr").find("td[name=tb_codigo]").html();
        var nomeDoCurso = $(this).closest("tr").find("td[name=tb_nome]").html();
        var $this = $(this); 
        alertify.confirm(
                        "EXCLUSAO DO CURSO "
                        +nomeDoCurso, 
                        "Voce tem certeza que deseja remover o registro <strong>"+nomeDoCurso+"</strong> ?",
                        "", "").
                        autoCancel(10).set('onok', function(closeEvent){ 
            if (closeEvent){
             
                $.ajax({
                        type: 'POST',
                        url: 'removecurso',
                        data: 'codigoCurso='+codigoDoCurso,
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
                            
                            alertify.success("Curso "+ nomeDoCurso+" removido com sucesso!"); 
                        }
                    });
            }
        } );   
	});
    
   
        $('#op_pesq_cod').on('change',function(){
        $('#search_nome').hide();
        $('#search_nome').val("");
        $('#search_codigo').show();
        
    });
    
        $('#op_pesq_nome').on('change',function(){
        $('#search_codigo').hide();
        $('#search_codigo').val(null);
        $('#search_nome').show();
    });
});
