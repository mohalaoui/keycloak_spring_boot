# Keycloak spring-boot simple

## template
- thymeleaf starter 
- spring-boot-starter-thymeleaf
- path : /src/main/resources/templates

## security

### Spring security
<pre><code>
	  http
            .authorizeRequests()
            .antMatchers("/products").hasRole("user")
            //.antMatchers("/products/*").hasRole("admin")
            .anyRequest().permitAll();
      
      http.csrf().disable();
</code></pre>

- use HttpSecurity to set our authorization strategy
- we can use role annotations instead

### keycloak
1. create a realm 
2. create a client
3. create a role(s)
4. create a user 
5. add role(s) to user 

### keycloak spring config

application.properties :
<pre><code>
keycloak.auth-server-url=http://your_keyclok_url:port/auth
keycloak.realm=springDemo 
keycloak.resource=product-app 3 client name
keycloak.public-client=true

keycloak.principal-attribute=preferred_username # default claim send in JWT, you can create a custom one via user attributes

spring.main.allow-bean-definition-overriding=true
</code></pre>



