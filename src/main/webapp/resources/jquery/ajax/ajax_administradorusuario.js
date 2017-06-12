/* global alertify */
$(function(){

    $('button[name=editarusuario]').click(function(){
        var nomeGrupoPermissao = $(this).closest("tr").find("td[name=tb_permissao]").html();
        var codigoPermissao = $(this).closest("tr").find("td[name=tb_permissao]").attr('id');
        var matricula = $(this).closest("tr").find("td[name=tb_matricula]").html();
        var nome = $(this).closest("tr").find("td[name=tb_nome]").html(); 
        var email = $(this).closest("tr").find("td[name=tb_email]").html(); 
        
        $('#edit_permissao').val(codigoPermissao); 
        $('#edit_matricula').val(matricula); 
        $('#edit_nome').val(nome); 
        $('#edit_email').val(email); 
	});
    
    $('button[name=novasenha]').click(function(){
        var matricula = $(this).closest("tr").find("td[name=tb_matricula]").html();
        var nome = $(this).closest("tr").find("td[name=tb_nome]").html();
        var email = $(this).closest("tr").find("td[name=tb_email]").html();
        var perfil = $(this).closest("tr").find("td[name=tb_permissao]").attr('id');
        $('#novasenha_matricula').val(matricula); 
        $('#novasenha_nome').val(nome);
	});
  
    $('button[name=excluirusuario]').click(function(){
        var matricula = $(this).closest("tr").find("td[name=tb_matricula]").html();
        var nome = $(this).closest("tr").find("td[name=tb_nome]").html(); 
        var $this = $(this); 
        alertify.confirm(
                        "EXCLUSAO DO USUARIO "
                        +nome, "Voce tem certeza que deseja remover o usuario <strong>"
                        +nome+"</strong> ?", "", "").
                        autoCancel(10).set('onok', function(closeEvent){ 
            if (closeEvent) {
                $.ajax({
                        type: 'POST',
                        url: 'removeusuario',
                        data: 'matricula='+matricula,
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
                            alertify.success(" Usuario "+ nome+" removido com sucesso!"); 
                        }
                    });
            }  
        } );   
	});
    
    
    $('#op_pesq_matricula').on('change',function(){
        $('#search_nome').hide();
        $('#search_nome').val("");
        $('#search_matricula').show();
        
    });
    
      
    $('#op_pesq_nome').on('change',function(){
        $('#search_matricula').hide();
        $('#search_matricula').val("");
        $('#search_nome').show();
    });
});
