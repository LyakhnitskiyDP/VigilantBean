# Vigilant Bean webstore

A web store built on Java platform.

---

## Table of contents
1. [UML diagram](#UML)
2. [Technologies and Frameworks](#technologies)
3. [Features](#features)
4. [Demo](#showcase)
    1. [Main Page](#showcaseMainPage)
    2. [Registration](#showcaseRegistration)
    3. [Adding new Category](#showcaseNewCategory)
    4. [Adding new Product](#showcaseNewProduct)
5. [How to set up](#howToSetUp)    


<a name="UML"></a>
### UML diagram of business entities
![UML diagram](VB_UML.svg)

<a name="technologies"></a>
### Technologies and frameworks used:
* Spring
    * Spring MVC
    * Spring ORM
    * Spring Security
* Hibernate ORM
* Hibernate Validator
* JUnit5
* Mockito

<a name="features"></a>
### Features
* User management
  * Registration with Email confirmation
  * Password hashing  
  * Access control based on roles
  
* Product management
    * Paginated listing of Products
    * Grouping products by Categories (Many to many)
    * Adding new Products
    * Adding new Categories

<a name="showcase"></a>
### Demo

---
<a name="showcaseMainPage"></a>
#### Main page
![slide_1](showcase/1.jpg)

<a name="showcaseRegistration"></a>
#### Registration
![slide_2](showcase/2.jpg)
![slide_3](showcase/3.jpg)
![slide_4](showcase/4.jpg)
![slide_5](showcase/5.jpg)
![slide_6](showcase/6.jpg)
![slide_7](showcase/7.jpg)

<a name="showcaseNewCategory"></a>
#### Adding new category (admin)
![slide_8](showcase/8.jpg)
![slide_9](showcase/9.jpg)

<a name="showcaseNewProduct"></a>
#### Adding new product (admin)
![slide_10](showcase/10.PNG)
![slide_11](showcase/11.PNG)
![slide_12](showcase/12.PNG)


<a name="howToSetUp"></a>
### How to set up
1. Supply hibernate configuration properties in
```src/main/resources/hibernateConfig.properties```file
2. Supply mail sender configuration properties in 
   ```src/main/resources/hibernateConfig.properties``` file
3. Use Jetty Maven plugin to run the project


    

  