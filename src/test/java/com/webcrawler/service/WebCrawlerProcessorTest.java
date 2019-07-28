package com.webcrawler.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URL;
import java.util.Set;

import org.junit.Test;

import com.webcrawler.service.WebCrawlerProcessor;

public  class WebCrawlerProcessorTest {
	
	@Test
	public void testSimpleWebsite() throws Exception {
		WebCrawlerProcessor processor = WebCrawlerProcessor.builder().maxDepth(2).maxUrls(10).build();
		Set<String> result = processor.process(new URL("http://newvistadevelopers.com"));
		assertNotNull(result);
		assertEquals(7, result.size());
	}
	//Not sure in case of wrong unrechable url what we are suppose to do 
	@Test
	public void testWithWrongUrl() throws Exception {
		WebCrawlerProcessor processor = WebCrawlerProcessor.builder().maxDepth(2).maxUrls(10).build();
		Set<String> result = processor.process(new URL("http://dddd.com"));
		assertNotNull(result);
		assertEquals(1, result.size());
	}
}