/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.stock.dao;
import tcc.stock.model.Historicomovimento;
/**
 *
 * @author Paulo
 */
public class HistoricomovimentoDao {
    
    Historicomovimento historico;
    sqlConnection sqlCon = new sqlConnection();
    
    public HistoricomovimentoDao(Historicomovimento historico){
        this.historico = historico;
    }
    public boolean create() {
        try {
            sqlCon.openConnection();
            sqlCon.cleanParameters();
            sqlCon.addParameters(String.valueOf(historico.getTipomovimento()));
            sqlCon.addParameters(String.valueOf(historico.getObs()));
            sqlCon.addParameters(String.valueOf(historico.getFkproduto().getId()));
            sqlCon.addParameters(String.valueOf(historico.getFkproduto().getId()));
            sqlCon.addParameters(String.valueOf(historico.getFkUsuario().getId()));

            sqlCon.executeNonQuery("insert into historicomovimento (tipomovimento,obs,fkproduto,fkestoque,fkusuario) values (?,?,?,?,?)");
            sqlCon.closeConnection();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private void delete() {
//        try{
//            con.addParameters(find().getId().toString());
//            con.executeNonQuery("delete from produto where id = ?");
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }
    }

    private void update() {
//        try {
//            sqlCon.openConnection();
//            sqlCon.cleanParameters();
//            sqlCon.addParameters(p.getDescricao());
//            sqlCon.addParameters(p.getId().toString());
//
//            sqlCon.executeNonQuery("update produto set descricao = ? where id = ?");
//            sqlCon.closeConnection();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
    }
//    private Produto find(){
//        
//    }
}
