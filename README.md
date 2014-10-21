RESTful
=======

Suggesting the codes for RESTful API including filter/annotator for allowing user access and validating input data, user management, data management and some utils such as protecting data using 128/256-bit AES encryption and so on.

Please take a look at https://github.com/Samsung/restful/blob/master/doc/readme to see how to use.

Release v0.0.6
--------------
Add encrypt text using 128-bit AES encryption algorithm

Release v0.0.5
--------------
Add decrypt text using 128-bit AES encryption algorithm

Release v0.0.4
--------------
Add paging for list
### Usage ###
~~~~
// Retrieving a list of note
GET /note/list, /note/list?pn=1
Content-Type: application/json
~~~~

Release v0.0.3
--------------
Add some classes for handling database using sqlmap 
### Guide ###
You DO need to install MySQL and add user for this. 
Please, take a look at jdbc.properties in src/main/resources/config and edit url, username and password (FYI, I'm planning to add a class to encrypt password).
There is a sql file name data.sql in doc/sql. Please run it.
### Usage ###
~~~~
// Retrieving a list of note
GET /note/list 
Content-Type: application/json

// Retrieving a note
GET /note/1
Content-Type: application/json

// Creating a note
POST /note/create
Content-Type: application/json
{
  "subject": "Hello",
  "content": "Nice to meet you!"
}

// Updating a note
PUT /note/update
Content-Type: application/json
{
  "idx": 1,
  "subject": "Hello",
  "content": "It's really nice to meet you."
}

// Deleting a note
DELETE /note/delete
Content-Type: application/json
{
  "idx": 1
}
~~~~

Release v0.0.2
--------------
Base template including sample codes for RESTful API

Release v0.0.1
--------------
Initial environment for developing RESTful API
