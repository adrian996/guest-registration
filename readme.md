# Guest registration backend

Spring boot backend application for managing guests/participants for events.

## Getting started

Backend application can be run separately in IDE or with Docker (relies on a running MySQL service)

```bash
docker run -p 8080:8080 rajanguf/guestregistration-be:1.0
```
or run the entire with [frontend](https://github.com/adrian996/guestregistration-fe) app using compose in the root directory:

```bash
docker-compose up
```

[Database schema](https://imageupload.io/JFOZfjGiuNAoqMv)

## Features

- Add/remove/edit event participants
- Add/remove events

Api endpoints documented with Swagger, available at
http://localhost:8080/swagger-ui


## Tech Stack

Java 17, Spring Boot 3, JUnit5, Docker

#
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](www.linkedin.com/in/adrian-rajangu-a75a75271)




