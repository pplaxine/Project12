# Project12 - Biocycle : Microservice architectured information system 

This is a microservice architectured information system tailored for Biocycle organisation needs. For more information please see documentation provided. 
 
## Getting Started
   
   #### Config-server 
    *WARNING* The .properties files of each microservice beeing stored on Github repository and pulled by config-server, you must :
        1) fork this project on a new repository.
        2) configure "application.properties" in "Project12/biocycleProjectGit/Biocycle/config-server/target/classes/".

    All .properties files are located in "Project12/biocycleProjectGit/Biocycle/config-server-repo" and named by the microservice it belongs to.  

  #### DataBase
    *WARNING* In accordance with microservice architecture, each CRUD microservice has its own database. 
    The current configuration is a Postgresql database, with a private schema per CRUD microservice individual database. 

    For each microservice CRUD you must : 
        1) configure the datasource properties
        2) configure the schema properties (for table creation from .sql file).

    All CRUD microservices are using a profile system to run either "H2" database for internal testing or "Postgresql" database for other microservice integration tests. Each profile has a dedicated .properties file containing the configurations for the corresponding database. Those files are located as follow : 
        - Testing profile : MicroServiceCRUD/src/test/resources/bootstrap.properties (h2 in-memory database already configured).
        - App running profile : MicroServiceCRUD/src/main/resources/bootstrap.properties (contains datasource and schema properties to be configured).

  #### Microservices

    I] Biocycle Information System contains multiples microservice having different rôles. 

    - Edge microservices : responsible for main body microservices orchestration. 
        . config-server   
        . eureka-server
        . zuul-server 

    - CRUD microservices : responsible for data CRUD operations.
        . giveAwayCRUD
        . offerCRUD 
        . organisationCRUD
        . productBatchCRUD 
        . poductRequestCRUD
        . redistributionCRUD
        . staffCRUD 
        . storageContainerCRUD 

    - Service microservices : responsible for business logic 
        . customerManagmentService
        . giveAwayService
        . InventoryService 
        . mailService 
        . productDispatchService
        . productStorageService

    - UI microservices : User interfaces 
        . customerWebApp
        . entWebApp 

    II] Deployment 
    
    As integration test of particular microservice requires the microservice, it is tests against, to be up, the deployement of the System (via mvn clean install) needs to follow a particular order: 
    
        1) The Edge microservice config-server (as used by all other microservice of the SI). 
        2) All other Edge microservice.
        3) All CRUD microservice (in any order).
        4) The productStorageService (as used by inventoryService).
        5) All other Service microservice.
        6) All UI microservice. 

     All microservices are SpringBoot selfcontained application. 
     You can run a micro service via "java -jar microservice.jar" command in /microservice/target folder. The "Edge microservice first" running order must be applied.  


## Prerequisites

Install Java JRE version 8 or higher. (https://www.oracle.com/technetwork/java/javase/downloads/index.html).

Install the latest version of Maven (for more information : https://maven.apache.org/).

## Built With 

* [Spring Tool Suit](https://spring.io/tools).

## Authors

* **Philippe Plaxine** - *Initial work* - [PPlaxine](https://github.com/pplaxine)
