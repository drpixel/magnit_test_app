package ru.magnit.test_app.model;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "POINT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Point.findAll", query = "SELECT p FROM Point p"),
    @NamedQuery(name = "Point.findById", query = "SELECT p FROM Point p WHERE p.id = :id"),
    @NamedQuery(name = "Point.findByRoutePointId", query = "SELECT p FROM Point p WHERE p.routePointId = :routePointId")})
public class Point implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @SequenceGenerator(name = "pointIdSeq", sequenceName = "POINT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pointIdSeq")
    private BigInteger id;
    @Basic(optional = false)
    @Column(name = "ROUTE_POINT_ID")
    private BigInteger routePointId;
    @JoinColumn(name = "ROUTE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Route routeId;

    public Point() {
    }

    public Point(BigInteger id) {
        this.id = id;
    }

    public Point(BigInteger id, BigInteger routePointId) {
        this.id = id;
        this.routePointId = routePointId;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getRoutePointId() {
        return routePointId;
    }

    public void setRoutePointId(BigInteger routePointId) {
        this.routePointId = routePointId;
    }

    @XmlTransient
    public Route getRouteId() {
        return routeId;
    }

    public void setRouteId(Route routeId) {
        this.routeId = routeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Point)) {
            return false;
        }
        Point other = (Point) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Point[ id=" + id + " ]";
    }
    
}
