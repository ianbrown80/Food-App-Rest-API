package web;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import web.FaUserSymptoms;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-04T19:39:50")
@StaticMetamodel(FaSymptoms.class)
public class FaSymptoms_ { 

    public static volatile CollectionAttribute<FaSymptoms, FaUserSymptoms> faUserSymptomsCollection;
    public static volatile SingularAttribute<FaSymptoms, Integer> symptomId;
    public static volatile SingularAttribute<FaSymptoms, String> symptomName;

}