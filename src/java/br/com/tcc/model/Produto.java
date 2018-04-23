/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Paulo
 */
@Entity
@Table(name = "produto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p")
    , @NamedQuery(name = "Produto.findById", query = "SELECT p FROM Produto p WHERE p.id = :id")
    , @NamedQuery(name = "Produto.findByModelo", query = "SELECT p FROM Produto p WHERE p.modelo = :modelo")
    , @NamedQuery(name = "Produto.findByCor", query = "SELECT p FROM Produto p WHERE p.cor = :cor")
    , @NamedQuery(name = "Produto.findByDescricao", query = "SELECT p FROM Produto p WHERE p.descricao = :descricao")
    , @NamedQuery(name = "Produto.findByStatus", query = "SELECT p FROM Produto p WHERE p.status = :status")
    , @NamedQuery(name = "Produto.findByDtinsert", query = "SELECT p FROM Produto p WHERE p.dtinsert = :dtinsert")})
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 201)
    @Column(name = "modelo")
    private String modelo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 101)
    @Column(name = "cor")
    private String cor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "descricao")
    private int descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dtinsert")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtinsert;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkproduto")
    private Collection<Historicomovimento> historicomovimentoCollection;

    public Produto() {
    }

    public Produto(Integer id) {
        this.id = id;
    }

    public Produto(Integer id, String modelo, String cor, int descricao, int status, Date dtinsert) {
        this.id = id;
        this.modelo = modelo;
        this.cor = cor;
        this.descricao = descricao;
        this.status = status;
        this.dtinsert = dtinsert;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getDescricao() {
        return descricao;
    }

    public void setDescricao(int descricao) {
        this.descricao = descricao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDtinsert() {
        return dtinsert;
    }

    public void setDtinsert(Date dtinsert) {
        this.dtinsert = dtinsert;
    }

    @XmlTransient
    public Collection<Historicomovimento> getHistoricomovimentoCollection() {
        return historicomovimentoCollection;
    }

    public void setHistoricomovimentoCollection(Collection<Historicomovimento> historicomovimentoCollection) {
        this.historicomovimentoCollection = historicomovimentoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.tcc.model.Produto[ id=" + id + " ]";
    }
    
}
