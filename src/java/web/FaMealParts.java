/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ian
 */
@Entity
@Table(name = "fa_meal_parts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FaMealParts.findAll", query = "SELECT f FROM FaMealParts f")
    , @NamedQuery(name = "FaMealParts.findByMealId", query = "SELECT f FROM FaMealParts f WHERE f.faMealPartsPK.mealId = :mealId")
    , @NamedQuery(name = "FaMealParts.findByFoodId", query = "SELECT f FROM FaMealParts f WHERE f.faMealPartsPK.foodId = :foodId")
    , @NamedQuery(name = "FaMealParts.findByQuantity", query = "SELECT f FROM FaMealParts f WHERE f.quantity = :quantity")})
public class FaMealParts implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FaMealPartsPK faMealPartsPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "quantity")
    private String quantity;
    @JoinColumn(name = "food_id", referencedColumnName = "food_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private FaFoods faFoods;
    @JoinColumn(name = "meal_id", referencedColumnName = "meal_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private FaFoodDiary faFoodDiary;

    public FaMealParts() {
    }

    public FaMealParts(FaMealPartsPK faMealPartsPK) {
        this.faMealPartsPK = faMealPartsPK;
    }

    public FaMealParts(FaMealPartsPK faMealPartsPK, String quantity) {
        this.faMealPartsPK = faMealPartsPK;
        this.quantity = quantity;
    }

    public FaMealParts(int mealId, int foodId) {
        this.faMealPartsPK = new FaMealPartsPK(mealId, foodId);
    }

    public FaMealPartsPK getFaMealPartsPK() {
        return faMealPartsPK;
    }

    public void setFaMealPartsPK(FaMealPartsPK faMealPartsPK) {
        this.faMealPartsPK = faMealPartsPK;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public FaFoods getFaFoods() {
        return faFoods;
    }

    public void setFaFoods(FaFoods faFoods) {
        this.faFoods = faFoods;
    }

    public FaFoodDiary getFaFoodDiary() {
        return faFoodDiary;
    }

    public void setFaFoodDiary(FaFoodDiary faFoodDiary) {
        this.faFoodDiary = faFoodDiary;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (faMealPartsPK != null ? faMealPartsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FaMealParts)) {
            return false;
        }
        FaMealParts other = (FaMealParts) object;
        if ((this.faMealPartsPK == null && other.faMealPartsPK != null) || (this.faMealPartsPK != null && !this.faMealPartsPK.equals(other.faMealPartsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MealParts[ faMealPartsPK=" + faMealPartsPK + " ]";
    }
    
}
