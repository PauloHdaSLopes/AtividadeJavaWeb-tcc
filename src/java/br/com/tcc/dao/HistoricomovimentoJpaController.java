/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.dao;

import br.com.tcc.dao.exceptions.NonexistentEntityException;
import br.com.tcc.dao.exceptions.RollbackFailureException;
import br.com.tcc.model.Historicomovimento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import br.com.tcc.model.Produto;
import br.com.tcc.model.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Paulo
 */
public class HistoricomovimentoJpaController implements Serializable {

    public HistoricomovimentoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Historicomovimento historicomovimento) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Produto fkproduto = historicomovimento.getFkproduto();
            if (fkproduto != null) {
                fkproduto = em.getReference(fkproduto.getClass(), fkproduto.getId());
                historicomovimento.setFkproduto(fkproduto);
            }
            Usuario fkusuario = historicomovimento.getFkusuario();
            if (fkusuario != null) {
                fkusuario = em.getReference(fkusuario.getClass(), fkusuario.getId());
                historicomovimento.setFkusuario(fkusuario);
            }
            em.persist(historicomovimento);
            if (fkproduto != null) {
                fkproduto.getHistoricomovimentoCollection().add(historicomovimento);
                fkproduto = em.merge(fkproduto);
            }
            if (fkusuario != null) {
                fkusuario.getHistoricomovimentoCollection().add(historicomovimento);
                fkusuario = em.merge(fkusuario);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
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

    public void edit(Historicomovimento historicomovimento) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Historicomovimento persistentHistoricomovimento = em.find(Historicomovimento.class, historicomovimento.getId());
            Produto fkprodutoOld = persistentHistoricomovimento.getFkproduto();
            Produto fkprodutoNew = historicomovimento.getFkproduto();
            Usuario fkusuarioOld = persistentHistoricomovimento.getFkusuario();
            Usuario fkusuarioNew = historicomovimento.getFkusuario();
            if (fkprodutoNew != null) {
                fkprodutoNew = em.getReference(fkprodutoNew.getClass(), fkprodutoNew.getId());
                historicomovimento.setFkproduto(fkprodutoNew);
            }
            if (fkusuarioNew != null) {
                fkusuarioNew = em.getReference(fkusuarioNew.getClass(), fkusuarioNew.getId());
                historicomovimento.setFkusuario(fkusuarioNew);
            }
            historicomovimento = em.merge(historicomovimento);
            if (fkprodutoOld != null && !fkprodutoOld.equals(fkprodutoNew)) {
                fkprodutoOld.getHistoricomovimentoCollection().remove(historicomovimento);
                fkprodutoOld = em.merge(fkprodutoOld);
            }
            if (fkprodutoNew != null && !fkprodutoNew.equals(fkprodutoOld)) {
                fkprodutoNew.getHistoricomovimentoCollection().add(historicomovimento);
                fkprodutoNew = em.merge(fkprodutoNew);
            }
            if (fkusuarioOld != null && !fkusuarioOld.equals(fkusuarioNew)) {
                fkusuarioOld.getHistoricomovimentoCollection().remove(historicomovimento);
                fkusuarioOld = em.merge(fkusuarioOld);
            }
            if (fkusuarioNew != null && !fkusuarioNew.equals(fkusuarioOld)) {
                fkusuarioNew.getHistoricomovimentoCollection().add(historicomovimento);
                fkusuarioNew = em.merge(fkusuarioNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = historicomovimento.getId();
                if (findHistoricomovimento(id) == null) {
                    throw new NonexistentEntityException("The historicomovimento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Historicomovimento historicomovimento;
            try {
                historicomovimento = em.getReference(Historicomovimento.class, id);
                historicomovimento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historicomovimento with id " + id + " no longer exists.", enfe);
            }
            Produto fkproduto = historicomovimento.getFkproduto();
            if (fkproduto != null) {
                fkproduto.getHistoricomovimentoCollection().remove(historicomovimento);
                fkproduto = em.merge(fkproduto);
            }
            Usuario fkusuario = historicomovimento.getFkusuario();
            if (fkusuario != null) {
                fkusuario.getHistoricomovimentoCollection().remove(historicomovimento);
                fkusuario = em.merge(fkusuario);
            }
            em.remove(historicomovimento);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
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

    public List<Historicomovimento> findHistoricomovimentoEntities() {
        return findHistoricomovimentoEntities(true, -1, -1);
    }

    public List<Historicomovimento> findHistoricomovimentoEntities(int maxResults, int firstResult) {
        return findHistoricomovimentoEntities(false, maxResults, firstResult);
    }

    private List<Historicomovimento> findHistoricomovimentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Historicomovimento.class));
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

    public Historicomovimento findHistoricomovimento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Historicomovimento.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistoricomovimentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Historicomovimento> rt = cq.from(Historicomovimento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
