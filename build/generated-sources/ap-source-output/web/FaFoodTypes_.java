package web;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import web.FaFoods;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-04T19:39:50")
@StaticMetamodel(FaFoodTypes.class)
public class FaFoodTypes_ { 

    public static volatile SingularAttribute<FaFoodTypes, Integer> foodTypeId;
    public static volatile CollectionAttribute<FaFoodTypes, FaFoods> faFoodsCollection;
    public static volatile SingularAttribute<FaFoodTypes, String> foodTypeName;

}