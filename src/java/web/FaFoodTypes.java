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
@Table(name = "fa_food_types")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FaFoodTypes.findAll", query = "SELECT f FROM FaFoodTypes f")
    , @NamedQuery(name = "FaFoodTypes.findByFoodTypeId", query = "SELECT f FROM FaFoodTypes f WHERE f.foodTypeId = :foodTypeId")
    , @NamedQuery(name = "FaFoodTypes.findByFoodTypeName", query = "SELECT f FROM FaFoodTypes f WHERE f.foodTypeName = :foodTypeName")})
public class FaFoodTypes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "food_type_id")
    private Integer foodTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "food_type_name")
    private String foodTypeName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "foodTypeId")
    private Collection<FaFoods> faFoodsCollection;

    public FaFoodTypes() {
    }

    public FaFoodTypes(Integer foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    public FaFoodTypes(Integer foodTypeId, String foodTypeName) {
        this.foodTypeId = foodTypeId;
        this.foodTypeName = foodTypeName;
    }

    public Integer getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(Integer foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    public String getFoodTypeName() {
        return foodTypeName;
    }

    public void setFoodTypeName(String foodTypeName) {
        this.foodTypeName = foodTypeName;
    }

    @XmlTransient
    public Collection<FaFoods> getFaFoodsCollection() {
        return faFoodsCollection;
    }

    public void setFaFoodsCollection(Collection<FaFoods> faFoodsCollection) {
        this.faFoodsCollection = faFoodsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (foodTypeId != null ? foodTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FaFoodTypes)) {
            return false;
        }
        FaFoodTypes other = (FaFoodTypes) object;
        if ((this.foodTypeId == null && other.foodTypeId != null) || (this.foodTypeId != null && !this.foodTypeId.equals(other.foodTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.FaFoodTypes[ foodTypeId=" + foodTypeId + " ]";
    }
    
}
