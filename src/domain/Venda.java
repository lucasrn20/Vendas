package domain;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "venda")
public class Venda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idvenda")
    private Integer idvenda;
   
    @Column(name = "data")
    private Date data;
    
    @JoinColumn(name = "idproduto", referencedColumnName = "idproduto")
    @ManyToOne(optional = false)
    private int idproduto;
    
    @JoinColumn(name = "idvendedor", referencedColumnName = "idvendedor")
    @ManyToOne(optional = false)
    private int idvendedor;

    public Venda() {
    }

    public Venda(Integer idvenda) {
        this.idvenda = idvenda;
    }

    public Venda(Integer idvenda, Date data) {
        this.idvenda = idvenda;
        this.data = data;
    }

    public Integer getIdvenda() {
        return idvenda;
    }

    public void setIdvenda(Integer idvenda) {
        this.idvenda = idvenda;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(int idproduto) {
        this.idproduto = idproduto;
    }

    public int getIdvendedor() {
        return idvendedor;
    }

    public void setIdvendedor(int idvendedor) {
        this.idvendedor = idvendedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvenda != null ? idvenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venda)) {
            return false;
        }
        Venda other = (Venda) object;
        if ((this.idvenda == null && other.idvenda != null) || (this.idvenda != null && !this.idvenda.equals(other.idvenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Venda[ idvenda=" + idvenda + " ]";
    }
    
}
