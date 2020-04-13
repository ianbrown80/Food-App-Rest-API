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
import web.FaFoodDiary;
import utilities.Error;

/**
 *
 * @author Ian
 */
@Stateless
@Path("fooddiary")
public class FaFoodDiaryFacadeREST extends AbstractFacade<FaFoodDiary> {

    @PersistenceContext(unitName = "foodAppPU")
    private EntityManager em;

    public FaFoodDiaryFacadeREST() {
        super(FaFoodDiary.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createFoodDiary(FaFoodDiary entity) {
        
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
    public Response edit(@PathParam("id") Integer id, FaFoodDiary entity) {
        
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
        
        FaFoodDiary food_diary_entry = super.find(id);
        Error no_food_diary_entry = new Error( 101, "no_user", "There is no user with that ID");
            
        if ( food_diary_entry == null ) {
            return Response.ok(no_food_diary_entry, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.ok(food_diary_entry, MediaType.APPLICATION_JSON).build();
        }

    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        
        List<FaFoodDiary> food_diary_entries = super.findAll();
        Error no_food_diary_entries = new Error( 101, "no_users", "There are no users");
        
        if ( food_diary_entries.size() > 0 ) {
            GenericEntity<List<FaFoodDiary>> food_diary_entries_return = new GenericEntity<List<FaFoodDiary>>(food_diary_entries) {}; 
            return Response.ok(food_diary_entries_return, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.ok(no_food_diary_entries, MediaType.APPLICATION_JSON).build();
        }
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        
        List<FaFoodDiary> food_diary_entries = super.findRange(new int[]{from, to});
        Error no_food_diary_entries = new Error( 101, "no_users", "There are no users");
        
        if ( food_diary_entries.size() > 0 ) {
            GenericEntity<List<FaFoodDiary>> return_food_diary_entries = new GenericEntity<List<FaFoodDiary>>(food_diary_entries) {}; 
            return Response.ok(return_food_diary_entries, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.ok(no_food_diary_entries, MediaType.APPLICATION_JSON).build();
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
