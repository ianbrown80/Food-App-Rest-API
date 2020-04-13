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
import javax.ws.rs.core.Response;
import web.FaFoods;
import utilities.Error;

/**
 *
 * @author Ian
 */
@Stateless
@Path("foods")
public class FaFoodsFacadeREST extends AbstractFacade<FaFoods> {

    @PersistenceContext(unitName = "foodAppPU")
    private EntityManager em;

    public FaFoodsFacadeREST() {
        super(FaFoods.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createFoodType(FaFoods entity) {
        
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
    public Response edit(@PathParam("id") Integer id, FaFoods entity) {
        
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
    public Response find(@PathParam("id") Integer id) {
        
        FaFoods food = super.find(id);
        Error no_food = new Error( 101, "no_user", "There is no user with that ID");
            
        if ( food == null ) {
            return Response.ok(no_food, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.ok(food, MediaType.APPLICATION_JSON).build();
        }

    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        
        List<FaFoods> food = super.findAll();
        Error no_food = new Error( 101, "no_users", "There are no users");
        
        if ( food.size() > 0 ) {
            GenericEntity<List<FaFoods>> return_food = new GenericEntity<List<FaFoods>>(food) {}; 
            return Response.ok(return_food, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.ok(no_food, MediaType.APPLICATION_JSON).build();
        }
    }
    
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        
        List<FaFoods> food = super.findRange(new int[]{from, to});
        Error no_food = new Error( 101, "no_users", "There are no users");
        
        if ( food.size() > 0 ) {
            GenericEntity<List<FaFoods>> return_food = new GenericEntity<List<FaFoods>>(food) {}; 
            return Response.ok(return_food, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.ok(no_food, MediaType.APPLICATION_JSON).build();
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
