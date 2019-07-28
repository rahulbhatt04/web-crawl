package com.webcrawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.webcrawler.models.Response;
import com.webcrawler.models.URLRequest;
import com.webcrawler.service.WebCrawlerService;

/**
 * Really back and forth on the naming convention here 
 * I started with Crawl as the url post of crawl does not look bad but it does not follow the rest
 * convention. I can not think about any other use case except crawl but keeping
 * it open for now
 * 
 * 
 */
@RestController
@RequestMapping(value = "/v1/url")

public class UrlController {

	@Autowired
	private WebCrawlerService webcrawlerService;

	
	
	@GetMapping(value = "/")
    public String  healthCheck() {
		return "Alliswell";
	}
	
	/**
	 * As of now this method throw the error but We need to configure exception
	 * handler in a bean that can handle it
	 * 
	 * 
	 */

        
	@RequestMapping(value = "/", method = RequestMethod.POST,headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<Response> process(@RequestBody URLRequest request) throws Exception {
		switch (request.getAction()) {
		case CRAWL:
			return ResponseEntity.ok(webcrawlerService.crawl(request));
		}
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
	}

}
