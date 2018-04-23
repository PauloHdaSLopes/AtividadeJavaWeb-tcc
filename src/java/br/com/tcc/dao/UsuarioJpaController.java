/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.dao;

import br.com.tcc.dao.exceptions.IllegalOrphanException;
import br.com.tcc.dao.exceptions.NonexistentEntityException;
import br.com.tcc.dao.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import br.com.tcc.model.Historicomovimento;
import br.com.tcc.model.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Paulo
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.emf = emf;
        this.utx = utx;
    }
    public UsuarioJpaController() {

    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
        
    }
    
    public void create(Usuario usuario) throws RollbackFailureException, Exception {
        if (usuario.getHistoricomovimentoCollection() == null) {
            usuario.setHistoricomovimentoCollection(new ArrayList<Historicomovimento>());
        }
        EntityManager em = null;
        try {
            
//            utx.begin();
            em = getEntityManager();
            Collection<Historicomovimento> attachedHistoricomovimentoCollection = new ArrayList<Historicomovimento>();
            for (Historicomovimento historicomovimentoCollectionHistoricomovimentoToAttach : usuario.getHistoricomovimentoCollection()) {
                historicomovimentoCollectionHistoricomovimentoToAttach = em.getReference(historicomovimentoCollectionHistoricomovimentoToAttach.getClass(), historicomovimentoCollectionHistoricomovimentoToAttach.getId());
                attachedHistoricomovimentoCollection.add(historicomovimentoCollectionHistoricomovimentoToAttach);
            }
            usuario.setHistoricomovimentoCollection(attachedHistoricomovimentoCollection);
            em.persist(usuario);
            for (Historicomovimento historicomovimentoCollectionHistoricomovimento : usuario.getHistoricomovimentoCollection()) {
                Usuario oldFkusuarioOfHistoricomovimentoCollectionHistoricomovimento = historicomovimentoCollectionHistoricomovimento.getFkusuario();
                historicomovimentoCollectionHistoricomovimento.setFkusuario(usuario);
                historicomovimentoCollectionHistoricomovimento = em.merge(historicomovimentoCollectionHistoricomovimento);
                if (oldFkusuarioOfHistoricomovimentoCollectionHistoricomovimento != null) {
                    oldFkusuarioOfHistoricomovimentoCollectionHistoricomovimento.getHistoricomovimentoCollection().remove(historicomovimentoCollectionHistoricomovimento);
                    oldFkusuarioOfHistoricomovimentoCollectionHistoricomovimento = em.merge(oldFkusuarioOfHistoricomovimentoCollectionHistoricomovimento);
                }
            }
//            utx.commit();
        } catch (Exception ex) {
            try {
//                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
//            utx.begin();
            em = getEntityManager();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getId());
            Collection<Historicomovimento> historicomovimentoCollectionOld = persistentUsuario.getHistoricomovimentoCollection();
            Collection<Historicomovimento> historicomovimentoCollectionNew = usuario.getHistoricomovimentoCollection();
            List<String> illegalOrphanMessages = null;
            for (Historicomovimento historicomovimentoCollectionOldHistoricomovimento : historicomovimentoCollectionOld) {
                if (!historicomovimentoCollectionNew.contains(historicomovimentoCollectionOldHistoricomovimento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historicomovimento " + historicomovimentoCollectionOldHistoricomovimento + " since its fkusuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Historicomovimento> attachedHistoricomovimentoCollectionNew = new ArrayList<Historicomovimento>();
            for (Historicomovimento historicomovimentoCollectionNewHistoricomovimentoToAttach : historicomovimentoCollectionNew) {
                historicomovimentoCollectionNewHistoricomovimentoToAttach = em.getReference(historicomovimentoCollectionNewHistoricomovimentoToAttach.getClass(), historicomovimentoCollectionNewHistoricomovimentoToAttach.getId());
                attachedHistoricomovimentoCollectionNew.add(historicomovimentoCollectionNewHistoricomovimentoToAttach);
            }
            historicomovimentoCollectionNew = attachedHistoricomovimentoCollectionNew;
            usuario.setHistoricomovimentoCollection(historicomovimentoCollectionNew);
            usuario = em.merge(usuario);
            for (Historicomovimento historicomovimentoCollectionNewHistoricomovimento : historicomovimentoCollectionNew) {
                if (!historicomovimentoCollectionOld.contains(historicomovimentoCollectionNewHistoricomovimento)) {
                    Usuario oldFkusuarioOfHistoricomovimentoCollectionNewHistoricomovimento = historicomovimentoCollectionNewHistoricomovimento.getFkusuario();
                    historicomovimentoCollectionNewHistoricomovimento.setFkusuario(usuario);
                    historicomovimentoCollectionNewHistoricomovimento = em.merge(historicomovimentoCollectionNewHistoricomovimento);
                    if (oldFkusuarioOfHistoricomovimentoCollectionNewHistoricomovimento != null && !oldFkusuarioOfHistoricomovimentoCollectionNewHistoricomovimento.equals(usuario)) {
                        oldFkusuarioOfHistoricomovimentoCollectionNewHistoricomovimento.getHistoricomovimentoCollection().remove(historicomovimentoCollectionNewHistoricomovimento);
                        oldFkusuarioOfHistoricomovimentoCollectionNewHistoricomovimento = em.merge(oldFkusuarioOfHistoricomovimentoCollectionNewHistoricomovimento);
                    }
                }
            }
//            utx.commit();
        } catch (Exception ex) {
            try {
//                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getId();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
//            utx.begin();
            em = getEntityManager();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Historicomovimento> historicomovimentoCollectionOrphanCheck = usuario.getHistoricomovimentoCollection();
            for (Historicomovimento historicomovimentoCollectionOrphanCheckHistoricomovimento : historicomovimentoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Historicomovimento " + historicomovimentoCollectionOrphanCheckHistoricomovimento + " in its historicomovimentoCollection field has a non-nullable fkusuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(usuario);
//            utx.commit();
        } catch (Exception ex) {
            try {
//                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
