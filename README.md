## Vigilant Bean webstore

---
A web store built on Java platform.

### Technologies and frameworks used:
* Spring
    * Spring MVC
    * Spring ORM
    * Spring Security
* Hibernate ORM
* Hibernate Validator
* JUnit5
* Mockito

### Features
* User management
  * Registration with Email confirmation
  * Password hashing  
  * Control access based on roles
  
* Product management
    * Paginated listing of Products
    * Adding/Editing new Products (In progress)
    * Grouping products by Categories (Many to many)
  
### How to set up
1. Supply hibernate configuration properties in
```src/main/resources/hibernateConfig.properties```file
2. Supply mail sender configuration properties in 
   ```src/main/resources/hibernateConfig.properties``` file
3. Use Jetty Maven plugin to run the project


    

  