# cinema-theatre-assignment

## Assignment:

### ![To test API in swagger](http://movietheatremanagement-env.eba-eu5tvrpd.ap-south-1.elasticbeanstalk.com/swagger-ui.html#/)

### application.yml:
```
profiles: dev
dbName: Your MySQL database Name
dbUser: Your MySQL server User Name
dbPass: Your MySQL server User Pass
port: Port number on which you want to run your server
emailId: your email ID
emailPass: your Email Pass(if you are getting error like authontication fail so need to chage setting in email
  - Manage Your Google Account
  - Security
  - 2-Step verification -> Off
  - Less secure app access -> On
```

## Libraries used
- Spring Boot
- Spring Configuration
- Spring REST Controller
- Spring JPA
- Development Tools
- Swagger
- MySQL
- ControllerAdvice
- JMS

## Git 2.10.0

## Compilation Command
- `mvn clean install` - Plain maven clean and install

## To run server and test in swagger:
`http://localhost:{Your_Port_Number}/swagger-ui.html`

## Database Mapping
![](https://user-images.githubusercontent.com/25608527/93095620-f6068e80-f6c0-11ea-92d7-da54660a3f62.png)


## Need to Refactor Code.
