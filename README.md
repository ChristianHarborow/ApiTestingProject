# API Testing Mini Project

## Team Members

- Kar-Ho Lee (Scrum master)
- Christian Harborow
- Lachlan Caldwell
- Duncan McCabe
- Owen Graham

## Task

For this project we were tasked with testing [the PetStore API](https://petstore3.swagger.io/), specifically testing all the data in the response for at least three different endpoints.

This project uses RestAssured for interacting with the API, and JUnit and Hamcrest for testing.

## File Structure

Tests can be found for the following endpoints:

- pets `src/test/java/pets`
- store `src/test/java/store`
- user `src/test/java/user`

## Setup/Usage Instructions

- Fork the repo
- Clone your forked repo

```
git clone https://github.com/{yourusername}/ApiTestingProject.git
```

## Configuration

To use the test framework, you will need to create a config.properties file as below:

```properties
api_key=<your api key>
api_url=<base url>
```

and put it in `src/test/resources`. The api_key can be generated from an online api generator such as https://codepen.io/corenominal/pen/rxOmMJ.

## Installing Docker

To get the Petstore API to work consistently across different environments, you can require to containerise the application. Here is the walkthrough for setting this up https://github.com/swagger-api/swagger-petstore
