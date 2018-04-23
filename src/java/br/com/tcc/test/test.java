package br.com.tcc.test;

import br.com.tcc.model.Usuario;
import br.com.tcc.dao.UsuarioJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

public class test {
    static UsuarioJpaController uDao = new UsuarioJpaController();
    
    public static void main(String args[]){
    
        Usuario user = new Usuario();
        try {
     
            user.setNome("Paulo Lopes");
            user.setSenha("pd071115");
        
            uDao.create(user);
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }   
}
