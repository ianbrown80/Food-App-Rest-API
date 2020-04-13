package web;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import web.FaFoodTypes;
import web.FaMealParts;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-13T17:33:37")
@StaticMetamodel(FaFoods.class)
public class FaFoods_ { 

    public static volatile SingularAttribute<FaFoods, String> foodName;
    public static volatile SingularAttribute<FaFoods, FaFoodTypes> foodTypeId;
    public static volatile SingularAttribute<FaFoods, Integer> foodId;
    public static volatile CollectionAttribute<FaFoods, FaMealParts> faMealPartsCollection;

}