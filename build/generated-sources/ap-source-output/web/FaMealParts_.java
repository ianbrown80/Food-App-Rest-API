package web;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import web.FaFoodDiary;
import web.FaFoods;
import web.FaMealPartsPK;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-04T19:39:50")
@StaticMetamodel(FaMealParts.class)
public class FaMealParts_ { 

    public static volatile SingularAttribute<FaMealParts, String> quantity;
    public static volatile SingularAttribute<FaMealParts, FaMealPartsPK> faMealPartsPK;
    public static volatile SingularAttribute<FaMealParts, FaFoodDiary> faFoodDiary;
    public static volatile SingularAttribute<FaMealParts, FaFoods> faFoods;

}