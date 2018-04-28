/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.stock.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mt11201
 */
@Entity
@Table(name = "estoque")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estoque.findAll", query = "SELECT e FROM Estoque e")
    , @NamedQuery(name = "Estoque.findById", query = "SELECT e FROM Estoque e WHERE e.id = :id")
    , @NamedQuery(name = "Estoque.findByPosicao", query = "SELECT e FROM Estoque e WHERE e.posicao = :posicao")
    , @NamedQuery(name = "Estoque.findByDtcadastro", query = "SELECT e FROM Estoque e WHERE e.dtcadastro = :dtcadastro")})
public class Estoque implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "posicao")
    private int posicao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dtcadastro")
    private int dtcadastro;
    @JoinColumn(name = "fkproduto", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Produto fkproduto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkestoque")
    private Collection<Historicomovimento> historicomovimentoCollection;

    public Estoque() {
    }

    public Estoque(Integer id) {
        this.id = id;
    }

    public Estoque(Integer id, int posicao, int dtcadastro) {
        this.id = id;
        this.posicao = posicao;
        this.dtcadastro = dtcadastro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public int getDtcadastro() {
        return dtcadastro;
    }

    public void setDtcadastro(int dtcadastro) {
        this.dtcadastro = dtcadastro;
    }

    public Produto getFkproduto() {
        return fkproduto;
    }

    public void setFkproduto(Produto fkproduto) {
        this.fkproduto = fkproduto;
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
        if (!(object instanceof Estoque)) {
            return false;
        }
        Estoque other = (Estoque) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tcc.stock.model.Estoque[ id=" + id + " ]";
    }
    
}
