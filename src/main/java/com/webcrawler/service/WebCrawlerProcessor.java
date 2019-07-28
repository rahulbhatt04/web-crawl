package com.webcrawler.service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Builder;
import lombok.Data;

/**
 * This is basically a processor and manager  it does following things 
 * 1. Start basic url  
 * 2. Manager thread to make sure that proper shutdown happened
 * 
 * 
 * 
 * */
@Builder
public @Data class WebCrawlerProcessor {
	//can be configured from etcd
	public static final int THREAD_COUNT = 5;
	private static final long PAUSE_TIME = 1000;

	final private Set<String> visitedUrl = new HashSet<>();
	final private List<Future<WebCrawlerTask>> runningTask = new ArrayList<>();
	final private ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
	final Logger logger = LoggerFactory.getLogger(WebCrawlerProcessor.class);
	private String domainName;

	private final int maxDepth;
	private final int maxUrls;

	public Set<String> process(URL start) throws IOException, InterruptedException {

		// stay within same site
		domainName = start.getHost();

		addUrlForCrawling(start, 0);

		while (allThreadsAreDone());
		
		return visitedUrl;
	}

	/**
	 * 
	 * 
	 * */
	private void addUrlForCrawling(URL url, int depth) {
		if (keepGoing(url, depth)) {
			visitedUrl.add(url.toString());
			WebCrawlerTask grabPage = new WebCrawlerTask(url, depth);
			Future<WebCrawlerTask> future = executorService.submit(grabPage);
			runningTask.add(future);
		}
	}

	private boolean allThreadsAreDone() throws InterruptedException {
		Thread.sleep(PAUSE_TIME);
		Set<WebCrawlerTask> pageSet = new HashSet<>();
		Iterator<Future<WebCrawlerTask>> iterator = runningTask.iterator();

		while (iterator.hasNext()) {
			Future<WebCrawlerTask> future = iterator.next();
			if (future.isDone()) {
				iterator.remove();
				try {
					pageSet.add(future.get());
				} 
				catch (InterruptedException | ExecutionException e) {
					//e.printStackTrace();
				}
			}
		}

		for (WebCrawlerTask grabPage : pageSet) {
			addNewURLs(grabPage);
		}

		return (runningTask.size() > 0);
	}

	private void addNewURLs(WebCrawlerTask task) {
		for (URL url : task.getUrlList()) {
			addUrlForCrawling(url, task.getDepth() + 1);
		}
	}

	private boolean keepGoing(URL url, int depth) {
		if (visitedUrl.contains(url.toString())) {
			return false;
		}
		if (!url.getHost().equals(domainName)) {
			return false;
		}
		if (depth > maxDepth) {
			return false;
		}
		if (visitedUrl.size() >= maxUrls) {
			return false;
		}
		return true;
	}

}