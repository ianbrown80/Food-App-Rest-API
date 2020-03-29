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
@Table(name = "fa_users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FaUsers.findAll", query = "SELECT f FROM FaUsers f")
    , @NamedQuery(name = "FaUsers.findByUserId", query = "SELECT f FROM FaUsers f WHERE f.userId = :userId")
    , @NamedQuery(name = "FaUsers.findByName", query = "SELECT f FROM FaUsers f WHERE f.name = :name")
    , @NamedQuery(name = "FaUsers.findByEmail", query = "SELECT f FROM FaUsers f WHERE f.email = :email")
    , @NamedQuery(name = "FaUsers.findByPassword", query = "SELECT f FROM FaUsers f WHERE f.password = :password")})
public class FaUsers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<FaFoodDiary> faFoodDiaryCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<FaUserSymptoms> faUserSymptomsCollection;

    public FaUsers() {
    }

    public FaUsers(Integer userId) {
        this.userId = userId;
    }

    public FaUsers(Integer userId, String name, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public Collection<FaFoodDiary> getFaFoodDiaryCollection() {
        return faFoodDiaryCollection;
    }

    public void setFaFoodDiaryCollection(Collection<FaFoodDiary> faFoodDiaryCollection) {
        this.faFoodDiaryCollection = faFoodDiaryCollection;
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
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FaUsers)) {
            return false;
        }
        FaUsers other = (FaUsers) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.FaUsers[ userId=" + userId + " ]";
    }
    
}
