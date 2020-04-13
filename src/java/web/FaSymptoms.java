/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ian
 */
@Entity
@Table(name = "fa_symptoms")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FaSymptoms.findAll", query = "SELECT f FROM FaSymptoms f")
    , @NamedQuery(name = "FaSymptoms.findBySymptomId", query = "SELECT f FROM FaSymptoms f WHERE f.symptomId = :symptomId")
    , @NamedQuery(name = "FaSymptoms.findBySymptomName", query = "SELECT f FROM FaSymptoms f WHERE f.symptomName = :symptomName")})
public class FaSymptoms implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "symptom_id")
    private Integer symptomId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "symptom_name")
    private String symptomName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "symptomId")
    private Collection<FaUserSymptoms> faUserSymptomsCollection;

    public FaSymptoms() {
    }

    public FaSymptoms(Integer symptomId) {
        this.symptomId = symptomId;
    }

    public FaSymptoms(Integer symptomId, String symptomName) {
        this.symptomId = symptomId;
        this.symptomName = symptomName;
    }

    public Integer getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(Integer symptomId) {
        this.symptomId = symptomId;
    }

    public String getSymptomName() {
        return symptomName;
    }

    public void setSymptomName(String symptomName) {
        this.symptomName = symptomName;
    }

    @XmlTransient
    public Collection<FaUserSymptoms> getFaUserSymptomsCollection() {
        return faUserSymptomsCollection;
    }

    public void setFaUserSymptomsCollection(Collection<FaUserSymptoms> faUserSymptomsCollection) {
        this.faUserSymptomsCollection = faUserSymptomsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (symptomId != null ? symptomId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FaSymptoms)) {
            return false;
        }
        FaSymptoms other = (FaSymptoms) object;
        if ((this.symptomId == null && other.symptomId != null) || (this.symptomId != null && !this.symptomId.equals(other.symptomId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Symptoms[ symptomId=" + symptomId + " ]";
    }
    
}
