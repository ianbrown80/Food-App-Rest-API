/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import web.FaUsers;
import utilities.Password;
import utilities.Error;

/**
 *
 * @author Ian
 */
@Stateless
@Path("users")
public class FaUsersFacadeREST extends AbstractFacade<FaUsers> {

    @PersistenceContext(unitName = "foodAppPU")
    private EntityManager em;

    public FaUsersFacadeREST() {
        super(FaUsers.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createUser(FaUsers entity) {
        
        Error createFail = new Error( 1001, "user_create_failed", "Unable to create user");
        Error userExists = new Error( 1002, "user_already_exists", "The email is already registered, please login");
        Error noName = new Error( 1003, "name_empty", "Please enter your name");
        Error noEmail = new Error( 1004, "email_empty", "Please enter an email address");
        Error noPassword = new Error( 1005, "password_empty", "Please enter a password");
        Error notvalidEmail = new Error( 1006, "email_invalid", "Please enter a valid email address");
        Error notvalidPassword = new Error( 1007, "password_invalid", "The password is not strong enough");
        
        String emailTest = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
        String passwordTest = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
        
        if (entity.getEmail() == null || entity.getEmail().length() == 0 ) {
            return Response.ok(noEmail, MediaType.APPLICATION_JSON).build();
        }
        
        if (!entity.getEmail().matches(emailTest) ) {
            return Response.ok(notvalidEmail, MediaType.APPLICATION_JSON).build();
        }
        
        if (entity.getName() == null || entity.getName().length() == 0 ) {
            return Response.ok(noName, MediaType.APPLICATION_JSON).build();
        }
        
        if (entity.getPassword() == null || entity.getPassword().length() == 0 ) {
            return Response.ok(noPassword, MediaType.APPLICATION_JSON).build();
        }
        
         if (!entity.getPassword().matches(passwordTest) ) {
            return Response.ok(notvalidPassword, MediaType.APPLICATION_JSON).build();
        } 
           
        FaUsers user = super.getEmail(entity.getEmail());

        if (user == null) {

            String salt = Password.generateSalt(512).get();
            String accessToken = Password.generateSalt(24).get();
            Date now = new Date();
            Date tokenExpiry = new Date(now.getTime() + 3600000);
            
            entity.setSalt(salt);
            entity.setPassword(Password.hashPassword(entity.getPassword(),salt).get());
            entity.setAccessToken(accessToken);
            entity.setTokenExpiry(tokenExpiry);

            if (super.create(entity)) {
                FaUsers returnUser = new FaUsers( entity.getUserId(), entity.getName(), entity.getEmail(), entity.getAccessToken(), entity.getTokenExpiry() );
                return Response.ok(returnUser, MediaType.APPLICATION_JSON).build();
            } else {
                return Response.ok(createFail, MediaType.APPLICATION_JSON).build();
            }
        } else {
            return Response.ok(userExists, MediaType.APPLICATION_JSON).build();
        }
      
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public Response editUser(FaUsers entity) {
        
        Error updateFail = new Error( 1041, "update_failed", "Unable to update user");
        Error updateSuccess = new Error( 2041, "update_success", "The user was updated successfully");
        Error incorrectPassword = new Error( 2042, "incorrect_password", "Your password was incorrect.");
        FaUsers user = super.find(entity.getUserId());
        
        if ( Password.verifyPassword(entity.getPassword(), user.getPassword(), user.getSalt())) {
        
            String salt = user.getSalt();
            String accessToken = user.getAccessToken();
            Date tokenExpiry = user.getTokenExpiry();

            if (entity.getSalt() == null || entity.getSalt().length() == 0 ) {
                entity.setPassword(user.getPassword());
            } else {
                entity.setPassword(Password.hashPassword(entity.getSalt(),salt).get());
            }

            if (entity.getName() == null || entity.getName().length() == 0 ) {
                entity.setName(user.getName());
            }

            if (entity.getEmail() == null || entity.getEmail().length() == 0 ) {
                entity.setEmail(user.getEmail());
            }
            
            entity.setSalt(salt);
            entity.setAccessToken(accessToken);
            entity.setTokenExpiry(tokenExpiry);

            if (super.edit(entity)) {
                return Response.ok(updateSuccess, MediaType.APPLICATION_JSON).build();
            } else {
                return Response.ok(updateFail, MediaType.APPLICATION_JSON).build();
            }
            
        } else {
            
            return Response.ok(incorrectPassword, MediaType.APPLICATION_JSON).build();
            
        }
        
    }

    @POST
    @Path("delete")
    public Response removeAccess(FaUsers entity) {
        
        Error deleteFail = new Error( 1031, "delete_failed", "Unable to delete");
        Error deleteSuccess = new Error( 2031, "delete_success", "The deletion was sucessful");
        
        FaUsers user = super.find(entity.getUserId());
        
        if ( user.getAccessToken().equals(entity.getAccessToken()) ) {
            if (super.remove(user)) {
                return Response.ok(deleteSuccess, MediaType.APPLICATION_JSON).build();
            } else {
                return Response.ok(deleteFail, MediaType.APPLICATION_JSON).build();
            }
        } else {
            return Response.ok(deleteFail, MediaType.APPLICATION_JSON).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Integer id) {
        
        FaUsers user = super.find(id);
        Error no_user = new Error( 101, "no_user", "There is no user with that ID");
            
        if ( user == null ) {
            return Response.ok(no_user, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.ok(user, MediaType.APPLICATION_JSON).build();
        }
    }
    
    @POST
    @Path("login")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response login(FaUsers entity) {
        
        String email = entity.getEmail();
        FaUsers user = super.getEmail(email);
   
        Error loginFailed = new Error(1011, "login_failed", "Unable to log you in, please try again");
        
        if ( user == null ) {
            
            return Response.ok(loginFailed, MediaType.APPLICATION_JSON).build();
            
        } else {
            
            if ( Password.verifyPassword(entity.getPassword(), user.getPassword(), user.getSalt())) {

                String accessToken = Password.generateSalt(24).get();
                Date now = new Date();
                Date tokenExpiry = new Date(now.getTime() + 3600000);

                user.setAccessToken(accessToken);
                user.setTokenExpiry(tokenExpiry);  
                super.edit(user);

                FaUsers returnUser = new FaUsers( user.getUserId(), user.getName(), user.getEmail(), user.getAccessToken(), user.getTokenExpiry() );

                return Response.ok(returnUser, MediaType.APPLICATION_JSON).build();
            } else {
                return Response.ok(loginFailed, MediaType.APPLICATION_JSON).build();
            }
            
        }

    }
    
    @POST
    @Path("checkToken")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response checkToken(FaUsers entity) {
        
        Integer userId = entity.getUserId(); 
        FaUsers user = super.find(userId);
        Date now = new Date();
   
        Error invalidToken = new Error(1021, "invalid_token", "Access token is invalid");
        Error expiredToken = new Error(1022, "session_expired", "Session has expired");
        Error noUpdateToken = new Error(1022, "no_update_token", "Unable to update token");
        
        if ( !entity.getAccessToken().equals(user.getAccessToken()) ) {
            return Response.ok(invalidToken, MediaType.APPLICATION_JSON).build();
        } else if ( now.after(user.getTokenExpiry()) ) {
            return Response.ok(expiredToken, MediaType.APPLICATION_JSON).build();
        } else {
            
            String accessToken = Password.generateSalt(24).get();
            Date tokenExpiry = new Date(now.getTime() + 3600000);
            
            user.setAccessToken(accessToken);
            user.setTokenExpiry(tokenExpiry);
            
            if (super.edit(user)) {
                FaUsers returnUser = new FaUsers( user.getUserId(), user.getName(), user.getEmail(), user.getAccessToken(), user.getTokenExpiry() );
                return Response.ok(returnUser, MediaType.APPLICATION_JSON).build();
            } else {
                return Response.ok(noUpdateToken, MediaType.APPLICATION_JSON).build();
            }
            
            
        }

    }
    
}
