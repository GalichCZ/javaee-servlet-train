# javaee-servlet-train
Little project to work with Java 8, Java EE, Hibernate, Jackson and TomCat

To run the project add credentials to your PSQL in `persistence.xml` db and create three tables

`
CREATE TABLE COUNTRIES  (
id SERIAL PRIMARY KEY,
country_name VARCHAR(255) NOT NULL
);
`

`CREATE TABLE CITIES (
id SERIAL PRIMARY KEY,
city_name VARCHAR(255) NOT NULL,
country_id INT REFERENCES Countries(id)
);`

`
CREATE TABLE USERS (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    name VARCHAR(255),
    email VARCHAR(255),
    gender VARCHAR(10),
    phone VARCHAR(20),
    cell VARCHAR(20),
    city VARCHAR(255),
    country VARCHAR(255)
);
`

As a Web Container was used Tomcat 8.5.x

To check if your credentials for DB are right use test `HibTest.java`

There are 3 endpoints:

`http://localhost:8080/TestCase_war_exploded/user-generator` to generate and add to DB 20 users

`http://localhost:8080/TestCase_war_exploded/users?offser=1&limit=1` to get exact amount of users from DB, json object will look like this

`{
    "id": 11,  
    "username": "ticklishrabbit682"
    "name": "Mrs Pauline Ortiz",
    "email": "pauline.ortiz@example.com"
}`

`http://localhost:8080/TestCase_war_exploded/user?inc=email,username,gender&id=1` this endpoint will return specific user by its' ID, the inc param will handle what poles do you want to se as response, for this inc it will be 

`
{
    "id": 14,
    "username": "angryswan622",
    "email": "rene.gibson@example.com",
    "gender": "male"
}
`
If you will leave `inc` empty or won't write it at all, the object will contain all keys 

`
{
    "id": 14,
    "username": "angryswan622",
    "password": "starman",
    "name": "Mr Rene Gibson",
    "email": "rene.gibson@example.com",
    "gender": "male",
    "phone": "(254) 232-2602",
    "cell": "(681) 567-6195",
    "city": "Ironville",
    "country": "United States"
}
`
