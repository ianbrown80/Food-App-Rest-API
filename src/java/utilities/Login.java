///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package utilities;
//
//import java.io.Serializable;
//
///**
// *
// * @author brown
// */    
//public class Login implements Serializable {
//
//    private static final long serialVersionUID = 1L;
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    @Basic(optional = false)
////    @Column(name = "user_id")
//    private Integer errorCode;
////    @Basic(optional = false)
////    @NotNull
////    @Size(min = 1, max = 255)
////    @Column(name = "name")
//    private String errorName;
////    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
////    @Basic(optional = false)
////    @NotNull
////    @Size(min = 1, max = 255)
////    @Column(name = "email")
//    private String errorDetails;
////    @Basic(optional = false)
////    @NotNull
////    @Size(min = 1, max = 255)
////    @Column(name = "password")
////    private String password;
////    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
////    private Collection<FaFoodDiary> faFoodDiaryCollection;
////    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
////    private Collection<FaUserSymptoms> faUserSymptomsCollection;
//
//    public Error() {
//    }
//
//
//    public Error(Integer errorCode, String errorName, String errorDetails) {
//        this.errorCode = errorCode;
//        this.errorName = errorName;
//        this.errorDetails = errorDetails;
//    }
//
//    public Integer getErrorCode() {
//        return errorCode;
//    }
//
//    public void setErrorCode(Integer errorCode) {
//        this.errorCode = errorCode;
//    }
//
//    public String getErrorName() {
//        return errorName;
//    }
//
//    public void setErrorName(String errorName) {
//        this.errorName = errorName;
//    }
//
//    public String getErrorDetails() {
//        return errorDetails;
//    }
//
//    public void setErrorDetails(String errorDetails) {
//        this.errorDetails = errorDetails;
//    }
//
//
//    public int hashCode() {
//        int hash = 0;
//        hash += (errorCode != null ? errorCode.hashCode() : 0);
//        return hash;
//    }
//
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Error)) {
//            return false;
//        }
//        Error other = (Error) object;
//        if ((this.errorCode == null && other.errorCode != null) || (this.errorCode != null && !this.errorCode.equals(other.errorCode))) {
//            return false;
//        }
//        return true;
//    }
//
//    public String toString() {
//        return "Error Code=" + errorCode;
//    }
//    
//}
//
//
//    
//}
