RESTful
=======

Suggesting the codes for RESTful API including filter/annotator for allowing user access and validating input data, user management, data management and some utils such as protecting data using 128/256-bit AES encryption and so on.

Please take a look at [readme](https://github.com/Samsung/restful/blob/master/doc/readme) to see release note.

##### Guide #####
You need to install MySQL and create database for this. Please, run a sql file named [data.sql](https://github.com/Samsung/restful/blob/master/doc/sql/data.sql) in doc/sql.<br>
And, please, take a look at [jdbc.properties](https://github.com/Samsung/restful/blob/master/src/main/resources/config/jdbc.properties) in src/main/resources/config and edit url, username and password.


#### Usage ####

##### User #####
```
// Retrieving a list of user with paging and search query
GET /user/list
GET /user/list?pn=1
GET /user/list?pn=1&q=ax
Content-Type: application/json

// Retrieving a list of user
GET /user/list 
Content-Type: application/json

// Retrieving a user
GET /user/ax
Content-Type: application/json

// Creating a user
POST /user/create
Content-Type: application/json
{
  "name": "axpower",
  "pwd": "*****",
  "username": "RESTful"
}

// Updating a user
PUT /user/update
Content-Type: application/json
{
  "name": "axpower",
  "username": "Web service"
}

// Deleting a user
DELETE /user/delete
Content-Type: application/json
{
  "name": "axpower"
}

// Retrieving user's information 
GET /user/me
Content-Type: application/json

// Signing in
POST /user/login
Content-Type: application/json
{
  "name": "axpower",
  "pwd": "*****"
}

// Signing out
GET /user/logout
Content-Type: application/json
```

##### Note #####
```
// Retrieving a list of note with paging and search query
GET /note/list
GET /note/list?pn=1
GET /note/list?pn=1&q=hello
Content-Type: application/json

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
```