package com.example.webcrawler.models;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Builder
public @Data class Response {

	private Set<String> result;
    
	private String requestUrl;
	


}
