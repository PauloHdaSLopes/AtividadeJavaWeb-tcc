/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.stock.test;

import tcc.stock.dao.*;
import tcc.stock.model.Produto;

/**
 *
 * @author mt11201
 */
public class Test {
    
    public static void main(String[] args) {
        Produto p = new Produto();
        ProdutoDao pDao = new ProdutoDao(p);
        
        p.setDescricao("Sapato 1 preto n 34");
        
        pDao.create();
    }
}
