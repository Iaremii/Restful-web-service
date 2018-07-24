# Restful-web-service
## The project contains: Spring Boot, Spring Data,  RESTful service, ElasticSearch, ValidationApi, H2 (database).



**JPA Resources:**

* `http://localhost:8080/jpa/clients/email={email}`
* `http://localhost:8080/jpa/clients`
* `http://localhost:8080/jpa/clients/{id} = > clients/1`
* `http://localhost:8080/jpa/clients`
* `http://localhost:8080/jpa/clients/{id}/commissions`
* `http://localhost:8080/jpa/projects`
* `http://localhost:8080/jpa/commissions`
* `http://localhost:8080/jpa/commissions/client={idClient}&project={idProject}`



## Table Structure
```
create table client (
id integer not null, 
company varchar(255), 
email varchar(255), 
first_name varchar(255), 
last_name varchar(255), 
phone_number varchar(255), 
primary key (id)
);

create table project (
id integer not null, 
description varchar(255), 
name varchar(255), 
price double, 
primary key (id)
);

create table commission (
id integer not null, 
budget double, 
category varchar(255), 
order_date timestamp, 
primary key (id),
foreign key (client_id) references client,
foreign key (project_id) references project
);
```

## Example Requests: 
*   GET http://localhost:8080/jpa/clients
```
[
    {
        "id": 1,
        "firstName": "Natalia",
        "lastName": "Adamchuk",
        "email": "Natalia@gmail.com",
        "phoneNumber": "123-456-789",
        "company": "VIVA"
    },
    {
        "id": 2,
        "firstName": "Oleksandr",
        "lastName": "Iaremii",
        "email": "Oleksandr@gmail.com",
        "phoneNumber": "987-654-321",
        "company": "Group"
    }
]
```
*   GET http://localhost:8080/jpa/clients/1
```
[
    {
        "id": 1,
        "firstName": "Natalia",
        "lastName": "Adamchuk",
        "email": "Natalia@gmail.com",
        "phoneNumber": "123-456-789",
        "company": "VIVA"
    },
]
```
*   POST http://localhost:8080/jpa/clients/
```
    {
        "firstName": "Oliwia",
        "lastName": "Gago",
        "email": "oliwia.g@gmail.com",
        "phoneNumber": "123-456-789",
        "company": "Tesla"
    }
```
*   POST http://localhost:8080/jpa/commissions/client=1&project=2
```
    {
        "orderDate": "2018-07-24T13:48:55.216+0000",
        "category": "JUPI",
        "budget": 12300.00
    }
```

* POST http://localhost:8080/jpa/clients/ with Validation Errors

``` 
  Request
  "email": "nagmailcom"
```
Response - 400 Bad Request
```
{
    "timestamp": "2018-07-24T18:00:30.504+0000",
    "message": "Validation failed for argument at index 0 in method: public org.springframework.http.ResponseEntity<java.lang.Object> com.example.Restfulwebservices.controller.JPAController.saveClients(com.example.Restfulwebservices.entity.Client) default message [email format xxxx@yyyy.zzz]] ",
    "details": "org.springframework.validation.BeanPropertyBindingResult: 1 errors\nField error in object 'client' on field 'email': rejected value [nagmailcom]; codes [Pattern.client.email,Pattern.email,Pattern.java.lang.String,Pattern]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [client.email,email]; arguments []; default message [email],[Ljavax.validation.constraints.Pattern$Flag;@19a3e92b,org.springframework.validation.beanvalidation.SpringValidatorAdapter$ResolvableAttribute@253debc9]; default message [email format xxxx@yyyy.zzz]"
}
```

*GET http://localhost:8080/jpa/clients/1 with HATEOAS
```
{
    "id": 1,
    "firstName": "Natalia",
    "lastName": "Adamchuk",
    "email": "Natalia@gmail.com",
    "phoneNumber": "123-456-789",
    "company": "VIVA",
    "_links": {
        "all-clients": {
            "href": "http://localhost:8080/jpa/clients"
        }
    }
}
```

/src/main/resources/data.sql
```
insert into client values(1,'VIVA', 'Natalia@gmail.com', 'Natalia','Adamchuk', '123-456-789');
insert into client values(2,'Group', 'Oleksandr@gmail.com', 'Oleksandr','Iaremii', '987-654-321');

insert into project values(1, 'some text', 'CRM', 2000);
insert into project values(2, 'some text', 'Java', 3000);
insert into project values(3, 'some text', 'DashBoards', 4000);

insert into commission values(1, 15000, 'CRM', sysdate(),1,1);
insert into commission values(2, 11000, 'Boards', sysdate(),1,3);
insert into commission values(3, 1000, 'Java', sysdate(),2,2);

```


/src/main/resources/application.properties
```
spring.jpa.show-sql = true
spring.h2.console.enabled=true
```


