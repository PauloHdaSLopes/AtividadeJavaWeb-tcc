package tcc.stock.dao;

import java.sql.ResultSet;
import tcc.stock.model.Produto;

public class ProdutoDao {

    sqlConnection sqlCon = new sqlConnection();
    Produto p = null;

    public ProdutoDao(Produto p) {
        this.p = p;
    }
   public ProdutoDao() {

    }
    public boolean create() {
        try {
            sqlCon.openConnection();
            sqlCon.cleanParameters();
            sqlCon.addParameters(p.getDescricao());
            sqlCon.executeNonQuery("insert into Produto (descricao,ativo) values (?,1)");
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
        try {
            sqlCon.openConnection();
            sqlCon.cleanParameters();
            sqlCon.addParameters(p.getDescricao());
            sqlCon.addParameters(p.getId().toString());

            sqlCon.executeNonQuery("update produto set descricao = ? where id = ?");
            sqlCon.closeConnection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public Produto find(String id){
        Produto pReturn = new Produto();
        try {
            sqlCon.openConnection();
            sqlCon.cleanParameters();
            sqlCon.addParameters(id);
            ResultSet rs = sqlCon.executeQuery("select * from produto where id = ?");
            while (rs.next()) {                
               pReturn.setAtivo(rs.getBoolean("Ativo"));
               pReturn.setDescricao(rs.getString("Descricao"));
               pReturn.setDtcadastro(rs.getDate("DtCadastro"));
               pReturn.setId(rs.getInt("Id"));
            }
            sqlCon.closeConnection();
            return pReturn;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
