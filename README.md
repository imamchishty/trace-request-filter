# Request Id Filter

[![Build Status](https://travis-ci.org/imamchishty/filter-request-id.svg?branch=master "filter-request-id")](https://travis-ci.org/imamchishty/filter-request-id) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.shedhack.filter/filter-request-id/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/com.shedhack.filter/filter-request-id)

## Introduction

The purpose of this library is add a unqiue request Id to the HTTP header of a request object.

__Request Id__: this library was designed for building restful services. Generally each user has a session Id, although this is good, I prefer to have a unique identifier for each request. This lends itself to easy auditing and monitoring. So if we have a unqiue request Id and that request is stored for auditing/logging purposes we can easily use this ID as a foreign key. We could possibly tie this with exceptions, please refer to [exception-core](https://github.com/imamchishty/exception-core/) for details.

The `request-id` property will be added to the HTTP Servlet Request object (as part of the header), only if this doesn't already exist. It many circumstances it may be preferable to use a HTTP server to do this for you, e.g. via nginx. If it isn't set then the filter will set the `request-id` property, unless you pass in a different key via the constructor.

If you're using Spring then [thread-context-aspect](https://github.com/imamchishty/thread-context-aspect) and [thread-context-handler](https://github.com/imamchishty/thread-context-handler) would be able to use the `request-id` and add more contextual details to the thread context. Please refer to those projects for more details.

## Thread Local

The request Id is also stored as a Thread Local variable. A static library, [Thread Local Utility](https://github.com/imamchishty/threadlocal-string-utility) has been used to manage it (set/get/remove).
You can use this library to get access to the variable.

### web.xml

Within the `<web-app>` element you add the following:

	<filter> 
    		<filter-name>requestIdFilter</filter-name>
    		<filter-class>com.shedhack.filter.requestid.RequestIdFilter</filter-class> 
  	</filter> 
  	<filter-mapping> 
    		<filter-name>requestIdFilter</filter-name>
    		<url-pattern>/*</url-pattern> 
  	</filter-mapping> 

### Spring

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(new RequestIdFilter());
        return filter;
    }

## Dependencies

The servlet-api is used, the actual implementation is your projects choice.

    <!-- Only using the API, clients will need to provide concrete implementations of their choice -->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>3.1.0</version>
    </dependency>
        
In order to make the request Id available statically I'm using the following library to create the ThreadLocal variable:
        
    <dependency>
        <groupId>com.shedhack.thread</groupId>
        <artifactId>threadlocal-string-utility</artifactId>
        <version>1.0.0</version>
    </dependency>
    
For further details refer to the project, [Thread String Utility](https://github.com/imamchishty/threadlocal-string-utility).    

## Java requirements

Project has been built using JDK 1.8.

## Maven central

This artifact is available in [Maven Central](https://maven-badges.herokuapp.com/maven-central/com.shedhack.filter/filter-request-id).
 
    <dependency>
        <groupId>com.shedhack.filter</groupId>
        <artifactId>filter-request-id</artifactId>
        <version>x.x.x</version>
    </dependency>    


Contact
-------

	Please feel free to contact me via email, imamchishty@gmail.com
