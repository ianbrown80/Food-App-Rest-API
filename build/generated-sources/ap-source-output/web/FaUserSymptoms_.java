package web;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import web.FaSymptoms;
import web.FaUsers;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-04T19:39:50")
@StaticMetamodel(FaUserSymptoms.class)
public class FaUserSymptoms_ { 

    public static volatile SingularAttribute<FaUserSymptoms, Integer> userSymptomId;
    public static volatile SingularAttribute<FaUserSymptoms, Short> severity;
    public static volatile SingularAttribute<FaUserSymptoms, Date> symptomTime;
    public static volatile SingularAttribute<FaUserSymptoms, FaSymptoms> symptomId;
    public static volatile SingularAttribute<FaUserSymptoms, FaUsers> userId;

}