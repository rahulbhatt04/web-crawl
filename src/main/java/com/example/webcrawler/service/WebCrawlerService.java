package com.example.webcrawler.service;

import java.net.URL;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.webcrawler.models.Response;
import com.example.webcrawler.models.URLRequest;

/**
 * As of now this service handle the Incoming request call the processor and return back response in the format that client needs 
 * also when we have database we can store it right here 
 * 
 * */
@Service
public class WebCrawlerService {

	public Response crawl(URLRequest urlRequest) throws Exception {
		if (StringUtils.isEmpty(urlRequest.getUrl())) {
			return null;
		}
		WebCrawlerProcessor processor = WebCrawlerProcessor.builder().maxDepth(urlRequest.getMaxDepth())
				.maxUrls(urlRequest.getMaxUrls()).build();
		Set<String> result = processor.process(new URL(urlRequest.getUrl()));
		return Response.builder().requestUrl(urlRequest.getUrl()).result(result).build();
	}

}
