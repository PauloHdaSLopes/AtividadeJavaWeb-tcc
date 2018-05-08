package tcc.stock.dao;

import tcc.stock.model.Produto;

public class ProdutoDao{
    sqlConnection con = new sqlConnection();
    Produto p = null;
    
    public ProdutoDao(Produto p){
        this.p = p;
    }
    
    public boolean create(){
        try{
            con.addParameters(p.getDescricao());
            con.executeNonQuery("insert into Produto (descricao,ativo) values (?,1)");
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
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
            con.addParameters(p.getDescricao());
            con.addParameters(p.getId().toString());

            con.executeNonQuery("update produto set descricao = ? where id = ?");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
//    private Produto find(){
//        
//    }
}
