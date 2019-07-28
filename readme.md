Problem Statement  --

We'd like you to write a simple web crawler in a programming language you're familiar with.
 Given a URL, it should output a simple textual sitemap, showing the links between pages.
  The crawler should be limited to one subdomain - so when you start with *https://google.com/*, 
  it would crawl all pages within google.com, but not follow external links, for example to facebook.com or 

Thought process -- 

I will be using Java for  solving the problem. Following are things comes to mind 

1. Every URL can have multiple pages 
it can go through multiple pages can point to one page we need to keep an record of which pages we visited 

2. We need to have some way to know a particular url is in the same domain or not.
 the way we can solve it by putting domain name in the example  to decide.

3. Given a web crawler we need to worry about the memory how many URL can we visit. 



Solution

Approach 1 Started with single threaded solution  but when I tried complex website it took forever

Than implemented multithreaded solution. 

In case of wrong URL code will return same url back not other url 

Its spring boot application that is getting so that we can show how we will expose this service 

Due to time constraints  I have tested with unit test cases not with rest application

