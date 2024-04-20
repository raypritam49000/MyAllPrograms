package com.rest.client.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.rest.client.app.services.JsonPlaceholderService;

@Configuration
public class AppConfig {

	@Bean
	public RestClient restClient() {
		return RestClient.builder().baseUrl("https://jsonplaceholder.typicode.com").build();
	}

	@Bean
	public JsonPlaceholderService jsonPlaceholderService() {
		RestClient client = RestClient.create("https://jsonplaceholder.typicode.com");
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(client)).build();
		return factory.createClient(JsonPlaceholderService.class);
	}
}
