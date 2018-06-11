/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.stock.dao;

import java.sql.ResultSet;
import tcc.stock.model.Estoque;

/**
 *
 * @author Paulo
 */
public class EstoqueDao {
    sqlConnection con = new sqlConnection();
    Estoque e = null;
    
    public EstoqueDao(Estoque e){
        this.e = e;
    }
//    public boolean create(){
//        try{
//            con.addParameters(e.getDescricao());
//            con.executeNonQuery("insert into Produto (descricao,ativo) values (?,1)");
//            return true;
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//            return false;
//        }
//    }
    private void delete(){
//        try{
//            con.addParameters(find().getId().toString());
//            con.executeNonQuery("delete from produto where id = ?");
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }
    }
    private void update(){
        try{
            con.addParameters(e.getFkproduto().getId().toString());
            con.addParameters(String.valueOf(e.getPosicao()));

            con.executeNonQuery("update estoque set fkProduto = ? where posicao = ?");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    private ResultSet List(){
        try{
            con.cleanParameters();
            return con.executeQuery("select  B.id, B.descricao,A.posicao, B.dtcadastro from estoque A join produto B on A.fkproduto = B.id");
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
//    private Produto find(){
//        
//    }
}
