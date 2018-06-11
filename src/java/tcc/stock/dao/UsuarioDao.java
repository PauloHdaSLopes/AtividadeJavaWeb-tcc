/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.stock.dao;

import java.sql.ResultSet;
import java.util.HashSet;
import tcc.stock.model.Usuario;

/**
 *
 * @author mt11201
 */
public class UsuarioDao {

    Usuario usuario;
    sqlConnection sqlCon = new sqlConnection();

    public UsuarioDao(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario isValid() {

        Usuario user = new Usuario();
        try {
            sqlCon.openConnection();
            sqlCon.cleanParameters();
            sqlCon.addParameters(usuario.getNome());
            sqlCon.addParameters(String.valueOf(usuario.getSenha()));
            ResultSet rs = sqlCon.executeQuery("Select id,nome,senha,dtcadastro from usuario where nome = ? and senha = ?");
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setSenha(rs.getString("senha"));
                user.setDtcadastro(rs.getDate("dtcadastro"));
            }
            sqlCon.closeConnection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public void create() throws Exception {
        try {
            sqlCon.openConnection();
            sqlCon.cleanParameters();
            sqlCon.addParameters(usuario.getNome());
            sqlCon.addParameters(String.valueOf(usuario.getSenha()));
            sqlCon.executeNonQuery("insert into usuario (nome,senha) values (?,?)");

            sqlCon.closeConnection();
        } catch (Exception e) {
           throw e;
        }
    }
}
