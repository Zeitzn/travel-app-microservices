package com.movi.gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import com.google.common.net.HttpHeaders;
import com.movi.gateway.dto.RequestDto;
import com.movi.gateway.dto.TokenDto;

import reactor.core.publisher.Mono;

/**
 * The name of this class should be used in filters on gateway specific routes
 * @author Isac Huamán Pineda
 *
 */
@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {
	
	private WebClient.Builder webClient;

	public AuthFilter(WebClient.Builder webClient) {
		super(Config.class);
		this.webClient = webClient;
	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain)->{
			if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
				return onError(exchange, HttpStatus.BAD_REQUEST);
			
			String tokenHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
			String [] chunks = tokenHeader.split(" ");
			if(chunks.length!=2 || !chunks[0].equals("Bearer"))
				return onError(exchange, HttpStatus.BAD_REQUEST);
			return webClient.build()
					.post()
					.uri("http://microservice-uaa/auth/validate?token="+chunks[1])
					.bodyValue(RequestDto.builder()
							.uri(exchange.getRequest().getPath().toString())
							.method(exchange.getRequest().getMethod().toString())
							.build())
					.retrieve()
					.bodyToMono(TokenDto.class)
					.map(t->{
						t.getToken();
						return exchange;
					}).flatMap(chain::filter);
			
		});
	}
	
	public Mono<Void> onError(ServerWebExchange exchange, HttpStatus status){
		ServerHttpResponse response = exchange.getResponse();
		response.setStatusCode(status);
		return response.setComplete();
	}
	
	public static class Config {
	}

}
