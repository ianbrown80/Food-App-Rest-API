/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ian
 */
@Entity
@Table(name = "fa_user_symptoms")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FaUserSymptoms.findAll", query = "SELECT f FROM FaUserSymptoms f")
    , @NamedQuery(name = "FaUserSymptoms.findByUserSymptomId", query = "SELECT f FROM FaUserSymptoms f WHERE f.userSymptomId = :userSymptomId")
    , @NamedQuery(name = "FaUserSymptoms.findBySeverity", query = "SELECT f FROM FaUserSymptoms f WHERE f.severity = :severity")
    , @NamedQuery(name = "FaUserSymptoms.findBySymptomTime", query = "SELECT f FROM FaUserSymptoms f WHERE f.symptomTime = :symptomTime")})
public class FaUserSymptoms implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_symptom_id")
    private Integer userSymptomId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "severity")
    private short severity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "symptom_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date symptomTime;
    @JoinColumn(name = "symptom_id", referencedColumnName = "symptom_id")
    @ManyToOne(optional = false)
    private FaSymptoms symptomId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private FaUsers userId;

    public FaUserSymptoms() {
    }

    public FaUserSymptoms(Integer userSymptomId) {
        this.userSymptomId = userSymptomId;
    }

    public FaUserSymptoms(Integer userSymptomId, short severity, Date symptomTime) {
        this.userSymptomId = userSymptomId;
        this.severity = severity;
        this.symptomTime = symptomTime;
    }

    public Integer getUserSymptomId() {
        return userSymptomId;
    }

    public void setUserSymptomId(Integer userSymptomId) {
        this.userSymptomId = userSymptomId;
    }

    public short getSeverity() {
        return severity;
    }

    public void setSeverity(short severity) {
        this.severity = severity;
    }

    public Date getSymptomTime() {
        return symptomTime;
    }

    public void setSymptomTime(Date symptomTime) {
        this.symptomTime = symptomTime;
    }

    public FaSymptoms getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(FaSymptoms symptomId) {
        this.symptomId = symptomId;
    }

    public FaUsers getUserId() {
        return userId;
    }

    public void setUserId(FaUsers userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userSymptomId != null ? userSymptomId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FaUserSymptoms)) {
            return false;
        }
        FaUserSymptoms other = (FaUserSymptoms) object;
        if ((this.userSymptomId == null && other.userSymptomId != null) || (this.userSymptomId != null && !this.userSymptomId.equals(other.userSymptomId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.FaUserSymptoms[ userSymptomId=" + userSymptomId + " ]";
    }
    
}
