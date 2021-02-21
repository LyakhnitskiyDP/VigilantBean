## Vigilant Bean webstore

---
A web store built on Java platform.

### UML diagram of business entities
![UML diagram](VB_UML.svg)

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
    * Grouping products by Categories (Many to many)
    * Adding new Products (In progress)
    * Adding new Categories
  
### Demo

---
#### Main page
![slide_1](showcase/1.jpg)

#### Registration
![slide_2](showcase/2.jpg)
![slide_3](showcase/3.jpg)
![slide_4](showcase/4.jpg)
![slide_5](showcase/5.jpg)
![slide_6](showcase/6.jpg)
![slide_7](showcase/7.jpg)

#### Adding new category (admin)
![slide_8](showcase/8.jpg)
![slide_9](showcase/9.jpg)

  
### How to set up
1. Supply hibernate configuration properties in
```src/main/resources/hibernateConfig.properties```file
2. Supply mail sender configuration properties in 
   ```src/main/resources/hibernateConfig.properties``` file
3. Use Jetty Maven plugin to run the project


    

  