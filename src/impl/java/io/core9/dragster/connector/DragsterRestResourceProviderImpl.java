package io.core9.dragster.connector;

import io.core9.dragster.resource.DragsterResource;
import io.core9.plugin.rest.RestResource;
import io.core9.plugin.rest.RestResourceConfig;
import io.core9.plugin.rest.RestResourceConfigImpl;
import io.core9.plugin.rest.RestResourceProvider;
import io.core9.plugin.rest.RestUtils;

import java.util.HashMap;
import java.util.Map;

import net.xeoh.plugins.base.annotations.PluginImplementation;

import com.wordnik.swagger.config.SwaggerConfig;


@PluginImplementation
public class DragsterRestResourceProviderImpl implements RestResourceProvider {

	private Map<String, RestResource> resourceMap = new HashMap<>();
	
	@Override
	public Map<String, RestResource> getResources() {


		RestResourceConfig restResourceConfig =  new RestResourceConfigImpl();
		
	    SwaggerConfig config = new SwaggerConfig();
	    config.setApiVersion("1.0.1");
	    config.setBasePath("http://localhost:9090/api");

		restResourceConfig.setSwaggerConfig(config);
		restResourceConfig.setModelPackage("io.core9.dragster.model");


		resourceMap.putAll(RestUtils.addRestResource(restResourceConfig, new DragsterResource()));
		
/*		resourceMap.putAll(RestUtils.addRestResource(restResourceConfig, new PetResource()));
		
		resourceMap.putAll(RestUtils.addRestResource(restResourceConfig, new PetStoreResource()));
		
		resourceMap.putAll(RestUtils.addRestResource(restResourceConfig, new UserResource()));*/
		
		
		return resourceMap;
	}





}
