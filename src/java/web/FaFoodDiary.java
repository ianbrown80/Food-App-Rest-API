/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ian
 */
@Entity
@Table(name = "fa_food_diary")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FaFoodDiary.findAll", query = "SELECT f FROM FaFoodDiary f")
    , @NamedQuery(name = "FaFoodDiary.findByMealId", query = "SELECT f FROM FaFoodDiary f WHERE f.mealId = :mealId")
    , @NamedQuery(name = "FaFoodDiary.findByMealTime", query = "SELECT f FROM FaFoodDiary f WHERE f.mealTime = :mealTime")})
public class FaFoodDiary implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "meal_id")
    private Integer mealId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "meal_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mealTime;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private FaUsers userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "faFoodDiary")
    private Collection<FaMealParts> faMealPartsCollection;

    public FaFoodDiary() {
    }

    public FaFoodDiary(Integer mealId) {
        this.mealId = mealId;
    }

    public FaFoodDiary(Integer mealId, Date mealTime) {
        this.mealId = mealId;
        this.mealTime = mealTime;
    }

    public Integer getMealId() {
        return mealId;
    }

    public void setMealId(Integer mealId) {
        this.mealId = mealId;
    }

    public Date getMealTime() {
        return mealTime;
    }

    public void setMealTime(Date mealTime) {
        this.mealTime = mealTime;
    }

    public FaUsers getUserId() {
        return userId;
    }

    public void setUserId(FaUsers userId) {
        this.userId = userId;
    }

    @XmlTransient
    public Collection<FaMealParts> getFaMealPartsCollection() {
        return faMealPartsCollection;
    }

    public void setFaMealPartsCollection(Collection<FaMealParts> faMealPartsCollection) {
        this.faMealPartsCollection = faMealPartsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mealId != null ? mealId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FaFoodDiary)) {
            return false;
        }
        FaFoodDiary other = (FaFoodDiary) object;
        if ((this.mealId == null && other.mealId != null) || (this.mealId != null && !this.mealId.equals(other.mealId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.FaFoodDiary[ mealId=" + mealId + " ]";
    }
    
}
