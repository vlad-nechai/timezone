# Timezone App

Stylish web application that shows current time based on timezone input.

Built with Spring boot and Angular.

## Prerequisites

* Java 8 (Java 11)
* Maven 3.x
* npm 6.x

## Run locally

### Install Angular dependencies

Go to frontend Angular app

`cd  src/main/resources/frontend/timezone`

Install dependencies

`npm i`

### Build Spring Boot app

Go back to the project root

`mvn clean package`

### Run

Simply run application without any additional profiles or params 👍👍👍 

If needed the Angular App can be run separately.

## Run with docker

You can also simply run App with docker

`docker run -d -p 18080:8080 volodymyrnechai/timezone_app:1.0.0`


## Swagger Documentation

[Swagger documentaion](http://neverhomemeuserapi-env.mu9heiaiex.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html?configUrl=/api-docs/swagger-config)



## Live Demo 

[Life demo](http://neverhomemeuserapi-env.mu9heiaiex.eu-central-1.elasticbeanstalk.com/)
