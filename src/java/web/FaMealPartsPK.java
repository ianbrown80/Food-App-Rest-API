/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Ian
 */
@Embeddable
public class FaMealPartsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "meal_id")
    private int mealId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "food_id")
    private int foodId;

    public FaMealPartsPK() {
    }

    public FaMealPartsPK(int mealId, int foodId) {
        this.mealId = mealId;
        this.foodId = foodId;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) mealId;
        hash += (int) foodId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FaMealPartsPK)) {
            return false;
        }
        FaMealPartsPK other = (FaMealPartsPK) object;
        if (this.mealId != other.mealId) {
            return false;
        }
        if (this.foodId != other.foodId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.FaMealPartsPK[ mealId=" + mealId + ", foodId=" + foodId + " ]";
    }
    
}
