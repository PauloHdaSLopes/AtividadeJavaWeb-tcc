/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.stock.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Paulo
 */
@Entity
@Table(name = "historicomovimento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historicomovimento.findAll", query = "SELECT h FROM Historicomovimento h")
    , @NamedQuery(name = "Historicomovimento.findById", query = "SELECT h FROM Historicomovimento h WHERE h.id = :id")
    , @NamedQuery(name = "Historicomovimento.findByTipomovimento", query = "SELECT h FROM Historicomovimento h WHERE h.tipomovimento = :tipomovimento")
    , @NamedQuery(name = "Historicomovimento.findByObs", query = "SELECT h FROM Historicomovimento h WHERE h.obs = :obs")
    , @NamedQuery(name = "Historicomovimento.findByDtcadastro", query = "SELECT h FROM Historicomovimento h WHERE h.dtcadastro = :dtcadastro")})
public class Historicomovimento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipomovimento")
    private int tipomovimento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 101)
    @Column(name = "obs")
    private String obs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dtcadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtcadastro;
    @JoinColumn(name = "fkUsuario", referencedColumnName = "id")
    @ManyToOne
    private Usuario fkUsuario;
    @JoinColumn(name = "fkproduto", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Produto fkproduto;
    @JoinColumn(name = "fkestoque", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Estoque fkestoque;

    public Historicomovimento() {
    }

    public Historicomovimento(Integer id) {
        this.id = id;
    }

    public Historicomovimento(Integer id, int tipomovimento, String obs, Date dtcadastro) {
        this.id = id;
        this.tipomovimento = tipomovimento;
        this.obs = obs;
        this.dtcadastro = dtcadastro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipomovimento() {
        return tipomovimento;
    }

    public void setTipomovimento(int tipomovimento) {
        this.tipomovimento = tipomovimento;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Date getDtcadastro() {
        return dtcadastro;
    }

    public void setDtcadastro(Date dtcadastro) {
        this.dtcadastro = dtcadastro;
    }

    public Usuario getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Usuario fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public Produto getFkproduto() {
        return fkproduto;
    }

    public void setFkproduto(Produto fkproduto) {
        this.fkproduto = fkproduto;
    }

    public Estoque getFkestoque() {
        return fkestoque;
    }

    public void setFkestoque(Estoque fkestoque) {
        this.fkestoque = fkestoque;
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
        if (!(object instanceof Historicomovimento)) {
            return false;
        }
        Historicomovimento other = (Historicomovimento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tcc.stock.model.Historicomovimento[ id=" + id + " ]";
    }
    
}
