Dependencies used : 

1.  spring-boot-starter-data-mongodb -- To interat with Mongodb database
2.  spring-boot-starter-web

Application.properties : 

spring.data.mongodb.host=localhost
spring.data.mongodb.port= "port number at which mongodb server is running"
spring.data.mongodb.database= "database name"

Following are the api end points --> 

For UserController : 

1. Register a new user :

   Method: POST
   URL: http://localhost:8080/api/users/register
   Body (JSON):
{
  "username": "sampleUser",
  "password": "samplePassword"
}

2. Login user :
   
   Method: POST
   URL: http://localhost:8080/api/users/login
   Body (JSON):
   json
{
  "username": "sampleUser",
  "password": "samplePassword"
}

3. Upload an assignment with admin verification:

   Method: POST
   URL: http://localhost:8080/api/users/upload?userId={userId}&adminUsername={adminUsername}
   Body (JSON):
   json
{
  "title": "Assignment Title",
  "description": "Assignment Description"
}
Replace {userId} and {adminUsername} with actual values.

4. Fetch all admins:

   Method: GET
   URL: http://localhost:8080/api/users/admins


For AdminController : 

1. Register a new admin:

   Method: POST
   URL: http://localhost:8080/api/admins/register
   Body (JSON):
   json
{
  "username": "adminUsername",
  "password": "adminPassword"
}

2. Admin login:

   Method: POST
   URL: http://localhost:8080/api/admins/login
   Body (JSON):
   json
{
  "username": "adminUsername",
  "password": "adminPassword"
}

3. View assignments tagged to the admin:

   Method: GET
   URL: http://localhost:8080/api/admins/assignments/{adminUsername}
   Replace {adminUsername} with the actual username of the admin.

4. Accept an assignment:

   Method: POST
   URL: http://localhost:8080/api/admins/assignments/{id}/accept
   Replace {id} with the actual assignment ID.

5. Reject an assignment:

   Method: POST
   URL: http://localhost:8080/api/admins/assignments/{id}/reject
   Replace {id} with the actual assignment ID.


To install all the dependencies : 

use command --> mvn clean install 

To run the following project -- > mvn spring-boot:run
