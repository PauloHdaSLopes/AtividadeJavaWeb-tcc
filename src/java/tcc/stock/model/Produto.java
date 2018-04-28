/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.stock.model;

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
 * @author mt11201
 */
@Entity
@Table(name = "produto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p")
    , @NamedQuery(name = "Produto.findById", query = "SELECT p FROM Produto p WHERE p.id = :id")
    , @NamedQuery(name = "Produto.findByDescricao", query = "SELECT p FROM Produto p WHERE p.descricao = :descricao")
    , @NamedQuery(name = "Produto.findByDtcadastro", query = "SELECT p FROM Produto p WHERE p.dtcadastro = :dtcadastro")
    , @NamedQuery(name = "Produto.findByAtivo", query = "SELECT p FROM Produto p WHERE p.ativo = :ativo")})
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 101)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dtcadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtcadastro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private boolean ativo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkproduto")
    private Collection<Estoque> estoqueCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkproduto")
    private Collection<Historicomovimento> historicomovimentoCollection;

    public Produto() {
    }

    public Produto(Integer id) {
        this.id = id;
    }

    public Produto(Integer id, String descricao, Date dtcadastro, boolean ativo) {
        this.id = id;
        this.descricao = descricao;
        this.dtcadastro = dtcadastro;
        this.ativo = ativo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDtcadastro() {
        return dtcadastro;
    }

    public void setDtcadastro(Date dtcadastro) {
        this.dtcadastro = dtcadastro;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @XmlTransient
    public Collection<Estoque> getEstoqueCollection() {
        return estoqueCollection;
    }

    public void setEstoqueCollection(Collection<Estoque> estoqueCollection) {
        this.estoqueCollection = estoqueCollection;
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
        return "tcc.stock.model.Produto[ id=" + id + " ]";
    }
    
}
