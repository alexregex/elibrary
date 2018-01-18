# elibrary
Carcass of web-application for electronic library. Project uses MVC pattern and next technologies: 
1. Spring MVC, Security;
2. Hibernate; 
3. MyQL;
4. JUnit;
5. Bootstrap. 


Database will created automatically with `hibernate.hbm2ddl.auto` and data will be inserted from `dummy-data.sql`file. In `application.properties` need to be enter **MySQL personal credentials**. After running use next login and password to get access in admin panel:
* **login**: admin
* **password**: abc125

or
* **login**: user
* **password**: u12345

Deployed with **TomCat 9.0.1**. In `elibrary/src/db_schema/` folder contains Workbench schema (model) for current database. 
