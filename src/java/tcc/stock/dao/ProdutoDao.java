package tcc.stock.dao;

import tcc.stock.model.Produto;

public class ProdutoDao {

    sqlConnection sqlCon = new sqlConnection();
    Produto p = null;

    public ProdutoDao(Produto p) {
        this.p = p;
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
//    private Produto find(){
//        
//    }
}
