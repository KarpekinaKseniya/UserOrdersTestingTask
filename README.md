<!-- ABOUT THE PROJECT -->

## About The Orders Project

![Main Page Screen Shot][project-screenshot]

This project contain solution on testing task from  https://github.com/wgnet/wg_forge_frontend with the addition of
the developer's imagination.

Project contains:

* Table with information about orders.
* Orders' statistic information
* Ability to search orders by Transaction|User|Amount|Card Type|Location
* Ability to change currency

### Built With

* [![Kotlin][Kotlin]][kotlin-url]
* [![Spring Boot][Spring_Boot]][spring-url]
* [![Liquibase][Liquibase]][liquibase-url]
* [![MongoDB][mongo]][mongo-url]
* [![JavaScript][JS]][js-url]
* [![React][React.js]][React-url]
* [![Material UI][material]][material-url]
* [![Docker][Docker]][docker-url]
* [![Gradle][Gradle]][gradle-url]

## Getting Started

For starting application prefer docker-compose, but you also can use gradle.
</br>
Backend side coverage by unit and integration tests.
</br>
For looking tests coverage you can use gradle command(Report is in /build/reports/jacoco/test/html/index.html)

````
gradle jacocoTestReport
````

### Gradle Instruction

For starts project use commands

````
gradle clean
````

````
gradle bootRun
````

### Docker Instruction

For generated gradle build folder

````
gradle clean build jar
````

Build and Run Docker Images

````
docker-compose up
````

Rebuild Docker Images

````
docker-compose build
````

Local:

+ http://localhost:8080/
+ http://localhost:8080/swagger-ui/index.html

## Contact

[![LinkedIn][linkedin-shield]][linkedin-url]
[![Gmail][gmail-shield]][gmail-url]

<!-- MARKDOWN LINKS & IMAGES -->

[project-screenshot]: readme_image/main_page.PNG

[Spring_Boot]: https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white

[spring-url]: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/

[Liquibase]: https://img.shields.io/badge/-Liquibase-White?style=for-the-badge

[liquibase-url]: https://docs.liquibase.com/workflows/liquibase-community/using-jdbc-url-in-liquibase.html

[JS]: https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black

[js-url]: https://developer.mozilla.org/en-US/docs/Web/JavaScript

[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB

[React-url]: https://reactjs.org/

[material]: https://img.shields.io/badge/Material--UI-0081CB?style=for-the-badge&logo=material-ui&logoColor=white

[material-url]: https://mui.com/

[Docker]: https://img.shields.io/badge/-Docker-fff?style=for-the-badge&logo=Docker

[docker-url]: https://docs.docker.com/

[Gradle]: https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white

[gradle-url]: https://docs.gradle.org/current/userguide/userguide.html

[Kotlin]: https://img.shields.io/badge/Kotlin-0095D5?&style=for-the-badge&logo=kotlin&logoColor=white

[kotlin-url]: https://kotlinlang.org/docs/home.html

[mongo]: https://img.shields.io/badge/MongoDB-4EA94B?style=for-the-badge&logo=mongodb&logoColor=white

[mongo-url]: https://www.mongodb.com/docs/

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555

[linkedin-url]: https://www.linkedin.com/in/kkarpekina

[gmail-shield]: https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white

[gmail-url]: mailto:feliks.cat37@gmail.com