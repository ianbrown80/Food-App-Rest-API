package web;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import web.FaMealParts;
import web.FaUsers;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-04T19:39:50")
@StaticMetamodel(FaFoodDiary.class)
public class FaFoodDiary_ { 

    public static volatile SingularAttribute<FaFoodDiary, Integer> mealId;
    public static volatile SingularAttribute<FaFoodDiary, Date> mealTime;
    public static volatile SingularAttribute<FaFoodDiary, FaUsers> userId;
    public static volatile CollectionAttribute<FaFoodDiary, FaMealParts> faMealPartsCollection;

}