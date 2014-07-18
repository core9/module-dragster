/**
 *  Copyright 2012 Wordnik, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.core9.dragster.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import io.core9.dragster.data.PageData;
import io.core9.dragster.exception.NotFoundException;
import io.core9.dragster.model.Owner;
import io.core9.dragster.model.Page;
import io.core9.dragster.resource.JavaRestResourceUtil;
import io.core9.dragster.resource.OwnerResource;

@Path("/dragster")
@Api(value = "/dragster", description = "Operations about pages")
@Produces({"application/json"})
public class DragsterResource {
	static PageData pageData = new PageData();
	static JavaRestResourceUtil ru = new JavaRestResourceUtil();

	@GET
	@Path("/{pageId : [0-9]}")
	@ApiOperation(
		value = "Find page by ID", 
		notes = "Returns a page when ID < 10. ID > 10 or nonintegers will simulate API error conditions", 
		response = Page.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Page not found") })
	public Response getPageById(
			@ApiParam(value = "ID of page that needs to be fetched", allowableValues = "range[1,5]", required = true) @PathParam("pageId") String pageId)
			throws NotFoundException {
		Page page = pageData.getPagebyId(ru.getLong(0, 100000, 0, pageId));
		if (null != page) {
			return Response.ok().entity(page).build();
		} else {
			throw new NotFoundException(404, "Page not found");
		}
	}

	@POST
	@Path("/page")
	@ApiOperation(value = "Add a new page to the store")
	@ApiResponses(value = { @ApiResponse(code = 405, message = "Invalid input") })
	public Response addPage(
			@ApiParam(value = "Page object that needs to be added to the store", required = true) Page page) {
		pageData.addPage(page);
		return Response.ok().entity("SUCCESS").build();
	}

	@PUT
	@ApiOperation(value = "Update an existing page")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Page not found"),
			@ApiResponse(code = 405, message = "Validation exception") })
	public Response updatePage(
			@ApiParam(value = "Page object that needs to be added to the store", required = true) Page page) {
		pageData.addPage(page);
		return Response.ok().entity("SUCCESS").build();
	}

	@GET
	@Path("/findByStatus")
	@ApiOperation(
		value = "Finds Pages by status", 
		notes = "Multiple status values can be provided with comma seperated strings", 
		response = Page.class,
		responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid status value") })
	public Response findPagesByStatus(
			@ApiParam(value = "Status values that need to be considered for filter", required = true, defaultValue = "available", allowableValues = "available,pending,sold", allowMultiple = true) @QueryParam("status") String status) {
		return Response.ok(pageData.findPageByStatus(status)).build();
	}

	@GET
	@Path("/findByVersions")
	@ApiOperation(
		value = "Finds Pages by tags", 
		notes = "Muliple tags can be provided with comma seperated strings. Use tag1, tag2, tag3 for testing.", 
		response = Page.class, 
		responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid tag value") })
	@Deprecated
	public Response findPagesByVersions(
			@ApiParam(value = "Versions to filter by", required = true, allowMultiple = true) @QueryParam("tags") String tags) {
		return Response.ok(pageData.findPageByVersions(tags)).build();
	}
	
	@GET
	  @Path("/{pageId}/owner")
	  @ApiOperation(
	    value = "Gets the owner of a page", 
	    response = OwnerResource.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Page not found") })
	  public Response getOwner(@PathParam("pageId") String pageId) {
	    
		Owner o = new Owner();
	    o.setName("Tony");
	    o.setId(1);
	    return Response.ok(o).build();
	  }
}
