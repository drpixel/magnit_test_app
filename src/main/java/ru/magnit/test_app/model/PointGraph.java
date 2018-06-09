package ru.magnit.test_app.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "POINT_GRAPH")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PointGraph.findAll", query = "SELECT p FROM PointGraph p"),
    @NamedQuery(name = "PointGraph.findByIdPointOne", query = "SELECT p FROM PointGraph p WHERE p.idPointOne = :idPointOne"),
    @NamedQuery(name = "PointGraph.findByIdPointTwo", query = "SELECT p FROM PointGraph p WHERE p.idPointTwo = :idPointTwo"),
    @NamedQuery(name = "PointGraph.findByTimeInfo", query = "SELECT p FROM PointGraph p WHERE p.timeInfo = :timeInfo"),
    @NamedQuery(name = "PointGraph.findById", query = "SELECT p FROM PointGraph p WHERE p.id = :id")})
public class PointGraph implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigInteger id;
    @Basic(optional = false)
    @Column(name = "ID_POINT_ONE")
    private BigInteger idPointOne;
    @Basic(optional = false)
    @Column(name = "ID_POINT_TWO")
    private BigInteger idPointTwo;
    @Basic(optional = false)
    @Column(name = "TIME_INFO")
    private BigInteger timeInfo;

    public PointGraph() {
    }

    public PointGraph(BigInteger id) {
        this.id = id;
    }

    public PointGraph(BigInteger id, BigInteger idPointOne, BigInteger idPointTwo, BigInteger timeInfo) {
        this.id = id;
        this.idPointOne = idPointOne;
        this.idPointTwo = idPointTwo;
        this.timeInfo = timeInfo;
    }

    public BigInteger getIdPointOne() {
        return idPointOne;
    }

    public void setIdPointOne(BigInteger idPointOne) {
        this.idPointOne = idPointOne;
    }

    public BigInteger getIdPointTwo() {
        return idPointTwo;
    }

    public void setIdPointTwo(BigInteger idPointTwo) {
        this.idPointTwo = idPointTwo;
    }

    public BigInteger getTimeInfo() {
        return timeInfo;
    }

    public void setTimeInfo(BigInteger timeInfo) {
        this.timeInfo = timeInfo;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PointGraph)) {
            return false;
        }
        PointGraph other = (PointGraph) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PointGraph[ id=" + id + " ]";
    }
    
}
