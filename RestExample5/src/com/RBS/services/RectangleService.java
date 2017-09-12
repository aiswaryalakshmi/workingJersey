package com.RBS.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.RBS.businessLogic.Rectangle;

@Path("/RectangleService")
public class RectangleService {
	 @GET
     @Path("{length}/{breadth}")
      @Produces(MediaType.TEXT_PLAIN)
      public String findArea(@PathParam("length") double length,@PathParam("breadth") double breadth) {
         Rectangle rectangle = new Rectangle(length, breadth);
        double area =  rectangle.calculateArea();
        System.out.println(area) ;
        return  "area = " + area ;
      }
	
}
