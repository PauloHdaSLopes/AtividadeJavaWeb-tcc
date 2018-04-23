/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.model;

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
    , @NamedQuery(name = "Historicomovimento.findByDescricao", query = "SELECT h FROM Historicomovimento h WHERE h.descricao = :descricao")
    , @NamedQuery(name = "Historicomovimento.findByStatus", query = "SELECT h FROM Historicomovimento h WHERE h.status = :status")
    , @NamedQuery(name = "Historicomovimento.findByDtinsert", query = "SELECT h FROM Historicomovimento h WHERE h.dtinsert = :dtinsert")})
public class Historicomovimento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 201)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 101)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dtinsert")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtinsert;
    @JoinColumn(name = "fkproduto", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Produto fkproduto;
    @JoinColumn(name = "fkusuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario fkusuario;

    public Historicomovimento() {
    }

    public Historicomovimento(Integer id) {
        this.id = id;
    }

    public Historicomovimento(Integer id, String descricao, String status, Date dtinsert) {
        this.id = id;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDtinsert() {
        return dtinsert;
    }

    public void setDtinsert(Date dtinsert) {
        this.dtinsert = dtinsert;
    }

    public Produto getFkproduto() {
        return fkproduto;
    }

    public void setFkproduto(Produto fkproduto) {
        this.fkproduto = fkproduto;
    }

    public Usuario getFkusuario() {
        return fkusuario;
    }

    public void setFkusuario(Usuario fkusuario) {
        this.fkusuario = fkusuario;
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
        return "br.com.tcc.model.Historicomovimento[ id=" + id + " ]";
    }
    
}
