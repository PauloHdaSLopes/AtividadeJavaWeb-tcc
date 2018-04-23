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
import br.com.tcc.model.Produto;
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
public class ProdutoJpaController implements Serializable {

    public ProdutoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Produto produto) throws RollbackFailureException, Exception {
        if (produto.getHistoricomovimentoCollection() == null) {
            produto.setHistoricomovimentoCollection(new ArrayList<Historicomovimento>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Historicomovimento> attachedHistoricomovimentoCollection = new ArrayList<Historicomovimento>();
            for (Historicomovimento historicomovimentoCollectionHistoricomovimentoToAttach : produto.getHistoricomovimentoCollection()) {
                historicomovimentoCollectionHistoricomovimentoToAttach = em.getReference(historicomovimentoCollectionHistoricomovimentoToAttach.getClass(), historicomovimentoCollectionHistoricomovimentoToAttach.getId());
                attachedHistoricomovimentoCollection.add(historicomovimentoCollectionHistoricomovimentoToAttach);
            }
            produto.setHistoricomovimentoCollection(attachedHistoricomovimentoCollection);
            em.persist(produto);
            for (Historicomovimento historicomovimentoCollectionHistoricomovimento : produto.getHistoricomovimentoCollection()) {
                Produto oldFkprodutoOfHistoricomovimentoCollectionHistoricomovimento = historicomovimentoCollectionHistoricomovimento.getFkproduto();
                historicomovimentoCollectionHistoricomovimento.setFkproduto(produto);
                historicomovimentoCollectionHistoricomovimento = em.merge(historicomovimentoCollectionHistoricomovimento);
                if (oldFkprodutoOfHistoricomovimentoCollectionHistoricomovimento != null) {
                    oldFkprodutoOfHistoricomovimentoCollectionHistoricomovimento.getHistoricomovimentoCollection().remove(historicomovimentoCollectionHistoricomovimento);
                    oldFkprodutoOfHistoricomovimentoCollectionHistoricomovimento = em.merge(oldFkprodutoOfHistoricomovimentoCollectionHistoricomovimento);
                }
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

    public void edit(Produto produto) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Produto persistentProduto = em.find(Produto.class, produto.getId());
            Collection<Historicomovimento> historicomovimentoCollectionOld = persistentProduto.getHistoricomovimentoCollection();
            Collection<Historicomovimento> historicomovimentoCollectionNew = produto.getHistoricomovimentoCollection();
            List<String> illegalOrphanMessages = null;
            for (Historicomovimento historicomovimentoCollectionOldHistoricomovimento : historicomovimentoCollectionOld) {
                if (!historicomovimentoCollectionNew.contains(historicomovimentoCollectionOldHistoricomovimento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historicomovimento " + historicomovimentoCollectionOldHistoricomovimento + " since its fkproduto field is not nullable.");
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
            produto.setHistoricomovimentoCollection(historicomovimentoCollectionNew);
            produto = em.merge(produto);
            for (Historicomovimento historicomovimentoCollectionNewHistoricomovimento : historicomovimentoCollectionNew) {
                if (!historicomovimentoCollectionOld.contains(historicomovimentoCollectionNewHistoricomovimento)) {
                    Produto oldFkprodutoOfHistoricomovimentoCollectionNewHistoricomovimento = historicomovimentoCollectionNewHistoricomovimento.getFkproduto();
                    historicomovimentoCollectionNewHistoricomovimento.setFkproduto(produto);
                    historicomovimentoCollectionNewHistoricomovimento = em.merge(historicomovimentoCollectionNewHistoricomovimento);
                    if (oldFkprodutoOfHistoricomovimentoCollectionNewHistoricomovimento != null && !oldFkprodutoOfHistoricomovimentoCollectionNewHistoricomovimento.equals(produto)) {
                        oldFkprodutoOfHistoricomovimentoCollectionNewHistoricomovimento.getHistoricomovimentoCollection().remove(historicomovimentoCollectionNewHistoricomovimento);
                        oldFkprodutoOfHistoricomovimentoCollectionNewHistoricomovimento = em.merge(oldFkprodutoOfHistoricomovimentoCollectionNewHistoricomovimento);
                    }
                }
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
                Integer id = produto.getId();
                if (findProduto(id) == null) {
                    throw new NonexistentEntityException("The produto with id " + id + " no longer exists.");
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
            utx.begin();
            em = getEntityManager();
            Produto produto;
            try {
                produto = em.getReference(Produto.class, id);
                produto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The produto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Historicomovimento> historicomovimentoCollectionOrphanCheck = produto.getHistoricomovimentoCollection();
            for (Historicomovimento historicomovimentoCollectionOrphanCheckHistoricomovimento : historicomovimentoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Produto (" + produto + ") cannot be destroyed since the Historicomovimento " + historicomovimentoCollectionOrphanCheckHistoricomovimento + " in its historicomovimentoCollection field has a non-nullable fkproduto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(produto);
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

    public List<Produto> findProdutoEntities() {
        return findProdutoEntities(true, -1, -1);
    }

    public List<Produto> findProdutoEntities(int maxResults, int firstResult) {
        return findProdutoEntities(false, maxResults, firstResult);
    }

    private List<Produto> findProdutoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Produto.class));
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

    public Produto findProduto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Produto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProdutoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Produto> rt = cq.from(Produto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
