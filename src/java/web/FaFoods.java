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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "fa_foods")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FaFoods.findAll", query = "SELECT f FROM FaFoods f")
    , @NamedQuery(name = "FaFoods.findByFoodId", query = "SELECT f FROM FaFoods f WHERE f.foodId = :foodId")
    , @NamedQuery(name = "FaFoods.findByFoodName", query = "SELECT f FROM FaFoods f WHERE f.foodName = :foodName")})
public class FaFoods implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "food_id")
    private Integer foodId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "food_name")
    private String foodName;
    @JoinColumn(name = "food_type_id", referencedColumnName = "food_type_id")
    @ManyToOne(optional = false)
    private FaFoodTypes foodTypeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "faFoods")
    private Collection<FaMealParts> faMealPartsCollection;

    public FaFoods() {
    }

    public FaFoods(Integer foodId) {
        this.foodId = foodId;
    }

    public FaFoods(Integer foodId, String foodName) {
        this.foodId = foodId;
        this.foodName = foodName;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public FaFoodTypes getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(FaFoodTypes foodTypeId) {
        this.foodTypeId = foodTypeId;
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
        hash += (foodId != null ? foodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FaFoods)) {
            return false;
        }
        FaFoods other = (FaFoods) object;
        if ((this.foodId == null && other.foodId != null) || (this.foodId != null && !this.foodId.equals(other.foodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.FaFoods[ foodId=" + foodId + " ]";
    }
    
}
