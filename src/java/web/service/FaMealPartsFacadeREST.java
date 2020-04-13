/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;
import web.FaMealParts;
import web.FaMealPartsPK;
import javax.ws.rs.core.Response;
import utilities.Error;

/**
 *
 * @author Ian
 */
@Stateless
@Path("mealparts")
public class FaMealPartsFacadeREST extends AbstractFacade<FaMealParts> {

    @PersistenceContext(unitName = "foodAppPU")
    private EntityManager em;

    private FaMealPartsPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;mealId=mealIdValue;foodId=foodIdValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        web.FaMealPartsPK key = new web.FaMealPartsPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> mealId = map.get("mealId");
        if (mealId != null && !mealId.isEmpty()) {
            key.setMealId(new java.lang.Integer(mealId.get(0)));
        }
        java.util.List<String> foodId = map.get("foodId");
        if (foodId != null && !foodId.isEmpty()) {
            key.setFoodId(new java.lang.Integer(foodId.get(0)));
        }
        return key;
    }

    public FaMealPartsFacadeREST() {
        super(FaMealParts.class);
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createMealPart(FaMealParts entity) {
        
        Error createFail = new Error( 102, "failed", "Unable to create food diary entry");
        Error createSuccess = new Error( 201, "success", "The user food diary entry created successfully");
        
        if (super.create(entity)) {
            return Response.ok(createSuccess, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.ok(createFail, MediaType.APPLICATION_JSON).build();
        }       
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, FaMealParts entity) {
        
        Error updateFail = new Error( 102, "failed", "Unable to update user");
        Error updateSuccess = new Error( 201, "success", "The user was updated successfully");
        
        if (super.edit(entity)) {
            return Response.ok(updateSuccess, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.ok(updateFail, MediaType.APPLICATION_JSON).build();
        }
        
    }
    
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        
        Error deleteFail = new Error( 102, "failed", "Unable to delete");
        Error deleteSuccess = new Error( 201, "success", "The deletion was sucessful");
        
        if (super.remove(super.find(id))) {
            return Response.ok(deleteSuccess, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.ok(deleteFail, MediaType.APPLICATION_JSON).build();
        }
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") PathSegment id) {
        
        web.FaMealPartsPK key = getPrimaryKey(id);
        
        FaMealParts meal_parts = super.find(key);
        Error no_meal_parts = new Error( 101, "no_user", "There is no user with that ID");
            
        if ( meal_parts == null ) {
            return Response.ok(no_meal_parts, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.ok(meal_parts, MediaType.APPLICATION_JSON).build();
        }
    }
   
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        
        List<FaMealParts> meal_parts = super.findAll();
        Error no_meal_parts = new Error( 101, "no_users", "There are no users");
        
        if ( meal_parts.size() > 0 ) {
            GenericEntity<List<FaMealParts>> return_meal_parts = new GenericEntity<List<FaMealParts>>(meal_parts) {}; 
            return Response.ok(return_meal_parts, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.ok(no_meal_parts, MediaType.APPLICATION_JSON).build();
        }
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        
        List<FaMealParts> meal_parts = super.findRange(new int[]{from, to});
        Error no_meal_parts = new Error( 101, "no_users", "There are no users");
        
        if ( meal_parts.size() > 0 ) {
             GenericEntity<List<FaMealParts>> return_meal_parts = new GenericEntity<List<FaMealParts>>(meal_parts) {}; 
            return Response.ok(return_meal_parts, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.ok(no_meal_parts, MediaType.APPLICATION_JSON).build();
        }

    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}