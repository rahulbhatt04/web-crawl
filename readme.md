Problem Statement  --

We'd like you to write a simple web crawler in a programming language you're familiar with.
 Given a URL, it should output a simple textual sitemap, showing the links between pages.
  The crawler should be limited to one subdomain - so when you start with *https://google.com/*, 
  it would crawl all pages within google.com, but not follow external links, for example to facebook.com or 
  

We would like to see your own implementation of a web crawler. 
Please do not use frameworks like scrapy or go-colly which handle all the crawling behind the scenes. 
You are welcome to use libraries to handle things like HTML parsing.

Ideally, write it as you would a production piece of code. 
This exercise is not meant to show us whether you can write code – we are more interested in how you design software.
 This means that we care less about a fancy UI or sitemap format, and more about how your program is structured: the trade-offs you've made,
  what behaviour the program exhibits, and your use of concurrency, test coverage, and so on.

We'd love it if you could do this within the next week or so, but let me know if you'll need more time.
 We will then schedule a hangout with an engineer during which you'll share your screen and discuss your implementation.

Thought process -- 

I will be using Java for the solving the problem. Following are things comes to mind 

1. Every URL can have multiple pages it can go through multiple pages can point to one page we need to keep an record of which pages we visited 

2. We need to have some way to know a particular url is in the same domain or not. the way we can solve it by putting domain name in the example Monzo.com to decide.
it might not cover cases for the same domain name used in the URL param for some external URL like facebook.com/campaign?test=monzo.com. but it will cover cases like http and https.

3. Given a web crawler we need to worry about the memory how many URL can we visit. 

4. We can use BFS or DFS both should be similar 


Solution

Approach 1 Started with single threaded solution  but when I tried complex website it took forever

In case of wrong URL code will return same url back not other url 

Its spring boot application that is getting so that we can show how we will expose this service 

Due to time constraints  I have tested with unit test cases not 

