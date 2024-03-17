package org.example.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PessoaModel {
    private String cpf;
    private String nome;
    private String sobrenome;
    private String endereco;
    private String cidade;

    public PessoaModel(String cpf, String nome, String sobrenome, String endereco, String cidade) {
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.endereco = endereco;
        this.cidade = cidade;
    }

    public void inserirNoBanco(Connection conn) throws SQLException {
        String sql = "INSERT INTO pessoas(cpf,nome,sobrenome,endereco,cidade) VALUES(?,?,?,?,?);";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Configurar os valores dos parâmetros
            pstmt.setString(1, this.cpf);
            pstmt.setString(2, this.nome);
            pstmt.setString(3, this.sobrenome);
            pstmt.setString(4, this.endereco);
            pstmt.setString(5, this.cidade);

            // Executar a instrução de inserção
            pstmt.executeUpdate();
        } catch (Exception e) {
            // Lançar a exceção para ser tratada por quem chamou o método
            throw e;
        }
    }

    public void alterarNoBanco(Connection conn) throws SQLException {
        String sql = "UPDATE pessoas SET nome=?, sobrenome=?, endereco = ?, cidade = ? WHERE cpf = ?;";
        
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
        	pstmt.setString(1,this.nome);
        	pstmt.setString(2,this.sobrenome);
        	pstmt.setString(3,this.endereco);
        	pstmt.setString(4,this.cidade);
        	// CPF é usado para identificar a linha a ser atualizada
        	pstmt.setString(5,this.cpf);
        	
        	 // Executar a instrução de atualização
            int affectedRows = pstmt.executeUpdate();
            
            //Verificar se todas as linhas foram atualizadas
            if (affectedRows == 0) {
                throw new SQLException("Atualização falhou, nenhuma linha afetada.");
            }
        }
        
        catch(SQLException e) {
        	throw e;
        }
        
    }

    public void deletarNoBanco(Connection conn) throws SQLException {
        String sql = "DELETE FROM pessoas WHERE nome = ?;";
        
        try(PreparedStatement pstmt = conn.prepareStatement(sql) ){
        	
        	pstmt.setString(1,nome);
        	
        	int affectedRows = pstmt.executeUpdate();
        	
        	if(affectedRows == 0) {
        		throw new SQLException("Apagar os Dados, Falhou" + cpf);
        	}
        	
        }catch(SQLException e) {
        	throw e;
        }
        
        
        
    }

}