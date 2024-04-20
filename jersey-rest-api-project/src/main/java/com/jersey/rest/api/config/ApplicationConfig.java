package com.jersey.rest.api.config;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.jersey.rest.api.controller.AuthenticationController;
import com.jersey.rest.api.controller.FileUploadController;
//import com.jersey.rest.api.controller.FileUploadController;
import com.jersey.rest.api.controller.StudentController;
import com.jersey.rest.api.filters.ContextListener;
import com.jersey.rest.api.filters.JWTTokenNeededFilter;

public class ApplicationConfig extends ResourceConfig {

	public ApplicationConfig() {
		register(AuthenticationController.class);
		register(FileUploadController.class);
		register(StudentController.class);
		register(ContextListener.class);
		register(JWTTokenNeededFilter.class);
		register(MultiPartFeature.class);
		register(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME), Level.INFO,
				LoggingFeature.Verbosity.PAYLOAD_ANY, 10000));
	}

}
