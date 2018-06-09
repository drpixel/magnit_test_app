package ru.magnit.test_app.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ROUTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Route.findAll", query = "SELECT r FROM Route r"),
    @NamedQuery(name = "Route.findById", query = "SELECT r FROM Route r WHERE r.id = :id"),
    @NamedQuery(name = "Route.findByIsReady", query = "SELECT r FROM Route r WHERE r.isReady = :isReady"),
    @NamedQuery(name = "Route.findByTime", query = "SELECT r FROM Route r WHERE r.time = :time")})
public class Route implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @SequenceGenerator(name = "routeIdSeq", sequenceName = "ROUTE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "routeIdSeq")
    private BigInteger id;
    @Basic(optional = false)
    @Column(name = "IS_READY")
    private char isReady;
    @Basic(optional = false)
    @Column(name = "TIME")
    private BigInteger time;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "routeId", fetch = FetchType.EAGER)
    private List<Point> pointList;

    public Route() {
    }

    public Route(BigInteger id) {
        this.id = id;
    }

    public Route(BigInteger id, char isReady, BigInteger time) {
        this.id = id;
        this.isReady = isReady;
        this.time = time;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public char getIsReady() {
        return isReady;
    }

    public void setIsReady(char isReady) {
        this.isReady = isReady;
    }

    public BigInteger getTime() {
        return time;
    }

    public void setTime(BigInteger time) {
        this.time = time;
    }

    public List<Point> getPointList() {
        return pointList;
    }

    public void setPointList(List<Point> pointList) {
        this.pointList = pointList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Route)) {
            return false;
        }
        Route other = (Route) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Route[ id=" + id + " ]";
    }
    
}
