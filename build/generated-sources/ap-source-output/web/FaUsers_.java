package web;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import web.FaFoodDiary;
import web.FaUserSymptoms;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-04T19:39:50")
@StaticMetamodel(FaUsers.class)
public class FaUsers_ { 

    public static volatile SingularAttribute<FaUsers, String> password;
    public static volatile SingularAttribute<FaUsers, String> salt;
    public static volatile CollectionAttribute<FaUsers, FaFoodDiary> faFoodDiaryCollection;
    public static volatile SingularAttribute<FaUsers, String> name;
    public static volatile CollectionAttribute<FaUsers, FaUserSymptoms> faUserSymptomsCollection;
    public static volatile SingularAttribute<FaUsers, Date> tokenExpiry;
    public static volatile SingularAttribute<FaUsers, String> accessToken;
    public static volatile SingularAttribute<FaUsers, Integer> userId;
    public static volatile SingularAttribute<FaUsers, String> email;

}