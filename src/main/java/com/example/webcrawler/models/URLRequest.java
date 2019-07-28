package com.example.webcrawler.models;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
@Builder
public @Data class URLRequest {
	
	@NonNull
	private String url;
	
	@NonNull
	private ActionCode action;
	
	private Integer maxDepth;
	
	private Integer maxUrls;

	
	public enum ActionCode {
		CRAWEL;
	}

}
