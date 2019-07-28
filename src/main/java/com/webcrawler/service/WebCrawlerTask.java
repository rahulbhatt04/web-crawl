package com.webcrawler.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lombok.Data;

public @Data class WebCrawlerTask implements Callable<WebCrawlerTask> {
	static final int TIMEOUT = 60000; // one minute

	private URL url;
	private int depth;
	private Set<URL> urlList = new HashSet<>();

	public WebCrawlerTask(URL url, int depth) {
		this.url = url;
		this.depth = depth;
	}

	@Override
	public WebCrawlerTask call() throws Exception {
		Document document = null;
		document = Jsoup.parse(url, TIMEOUT);
		processLinks(document.select("a[href]"));
		return this;
	}

	private void processLinks(Elements links) {
		for (Element link : links) {
			String href = link.attr("href");

			try {
				URL nextUrl = new URL(url, href);
				urlList.add(nextUrl);
			} catch (MalformedURLException e) { // ignore bad urls
			}
		}
	}
}