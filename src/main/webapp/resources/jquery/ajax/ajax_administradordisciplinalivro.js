/* global alertify */
$(function(){
   
    $('button[name=excluirlivrodadisciplina]').click(function(){
        var isbn = $(this).closest("tr").find("td[name=tb_isbn]").html();
        var codDisciplina = $(this).closest("tr").find("td[name=tb_cod_disciplina]").html(); 
        var nomeDoLivro = $(this).closest("tr").find("td[name=tb_titulo]").html(); 
        var nomeDaDisciplina = $(this).closest("tr").find("td[name=tb_nome_disciplina]").html(); 
        var $this = $(this); 
        
        alertify.confirm(
                        "EXCLUSAO DO LIVRO "
                        +nomeDoLivro, 
                        "Voce tem certeza que deseja remover o livro <strong>"
                        +nomeDoLivro+"</strong> da disciplina <strong>"
                        +nomeDaDisciplina+"</strong> ?", "", "").
                        autoCancel(10).set('onok', function(closeEvent){ 
            if (closeEvent) {
                $.ajax({
                        type: 'POST',
                        url: 'removervinculo',
                        data: {isbn:isbn,codigoDisciplina:codDisciplina},
                        statusCode:{
                            404: function(){
                                alert("Erro 404");
                            },
                            500: function(){
                                alert("Erro no servidor");
                            }
                        },
                        success: function(response){
          
                            $this.closest('tr').remove();
               
                            alertify.success("Livro "+ nomeDoLivro+" foi removido da disciplina "+nomeDaDisciplina+" com sucesso!"); 
                        }
                    });
            }  
        } );   
	});
});
