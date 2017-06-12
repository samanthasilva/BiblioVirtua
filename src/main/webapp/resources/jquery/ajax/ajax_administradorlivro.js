/* global alertify */

$(function(){

    $('button[name=editarlivro]').click(function(){

        var id = $(this).closest("tr").find("td[name=tb_id]").html();  
        var isbn = $(this).closest("tr").find("td[name=tb_isbn]").html(); 
        var tituloDoLivro = $(this).closest("tr").find("td[name=tb_titulo]").html();
        var autorDoLivro = $(this).closest("tr").find("td[name=tb_autor]").html();
        var descDoLivro = $(this).closest("tr").find("td[name=tb_descricao]").html();
        var localDoLivro = $(this).closest("tr").find("td[name=tb_local]").html();
        var exemplaresLivro = $(this).closest("tr").find("td[name=tb_exemplares]").html();
        var statusLivro = $(this).closest("tr").find("td[name=tb_status]").html();
 

        $('#edit_id').val(id);

        $('#edit_isbn').val(isbn);
        $('#edit_titulo').val(tituloDoLivro);
        $('#edit_autor').val(autorDoLivro);
        $('#edit_desc_livro').val(descDoLivro); 
        $('#edit_local').val(localDoLivro);
        $('#edit_exemplares').val(exemplaresLivro);
        $('#edit_status').val(statusLivro);
        console.log(statusLivro);
	});
    
  
    $('button[name=excluirlivro]').click(function(){
        var isbn = $(this).closest("tr").find("td[name=tb_isbn]").html();
        var codDisciplina = $(this).closest("tr").find("td[name=tb_livro_disciplina]").attr('id');
        var nomeDoLivro = $(this).closest("tr").find("td[name=tb_titulo]").html(); 
        var $this = $(this);
        
        alertify.confirm(
                        "EXCLUSAO DO LIVRO "
                        +nomeDoLivro, 
                        "Voce tem certeza que deseja remover o livro <strong>"
                        +nomeDoLivro+"</strong> ?", "", "").
                        autoCancel(10).set('onok', function(closeEvent){ 
            if (closeEvent) { 
                $.ajax({
                        type: 'POST',
                        url: 'removelivro',
                        data: 'isbn='+isbn,
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
                            alertify.success("Livro "+ nomeDoLivro+" removido com sucesso!"); 
                        }
                    });
            }  
        } );   
	});
    
   
    $('#op_pesq_isbn').on('change',function(){
        $('#search_titulo').hide();
        $('#search_titulo').val("");
        $('#search_isbn').show();
    });
    
  
    $('#op_pesq_titulo').on('change',function(){
        $('#search_isbn').hide();
        $('#search_isbn').val("");
        $('#search_titulo').show();
    });
    
});
