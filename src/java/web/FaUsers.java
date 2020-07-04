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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    , @NamedQuery(name = "FaUsers.findByPassword", query = "SELECT f FROM FaUsers f WHERE f.password = :password")
    , @NamedQuery(name = "FaUsers.login", query = "SELECT f FROM FaUsers f WHERE f.email = :email AND f.password = :password")})
public class FaUsers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = true)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = true)
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = true)
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = true)
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    @Column(name = "salt")
    private String salt;
    @Basic(optional = true)
    @Size(min = 1, max = 255)
    @Column(name = "access_token")
    private String accessToken;
    @Basic(optional = true)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "token_expiry")
    private Date tokenExpiry;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<FaFoodDiary> faFoodDiaryCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<FaUserSymptoms> faUserSymptomsCollection;

    public FaUsers() {
    }

    public FaUsers(Integer userId) {
        this.userId = userId;
    }

    public FaUsers(Integer userId, String name, String email, String password, String salt) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.salt = salt;
    }
    
    public FaUsers(Integer userId, String name, String email, String password ) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public FaUsers(Integer userId, String name, String email, String accessToken, Date tokenExpiry) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.accessToken = accessToken;
        this.tokenExpiry = tokenExpiry;
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
    
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
    public Date getTokenExpiry() {
        return tokenExpiry;
    }

    public void setTokenExpiry(Date tokenExpiry) {
        this.tokenExpiry = tokenExpiry;
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
        return "userId=" + userId;
    }
    
}
