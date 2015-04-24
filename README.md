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
// Creating a user
POST /restful/api/user/signup
Content-Type: application/json
{
  "name": "axpower",
  "pwd": "*****",
  "username": "RESTful",
  "role": "User"
}

// Signing in
POST /restful/api/user/signin
Content-Type: application/json
{
  "name": "axpower",
  "pwd": "*****"
}

// Signing out
GET /restful/api/user/signout
Content-Type: application/json

// Retrieving my information 
GET /restful/api/user/profile
Content-Type: application/json

// Updating a user
PUT /restful/api/user/update
Content-Type: application/json
{
  "name": "axpower",
  "username": "Web service"
}

// Deleting a user
DELETE /restful/api/user/delete
Content-Type: application/json
{
  "name": "axpower"
}

// Retrieving a user
GET /restful/api/user/ax
Content-Type: application/json

// Retrieving a list of user
GET /restful/api/user/list 
Content-Type: application/json

// Retrieving a list of user with paging and search query
GET /restful/api/user/list?pn=1
GET /restful/api/user/list?pn=1&q=ax
Content-Type: application/json
```

##### Note #####
```
// Creating a note
POST /restful/api/note/create
Content-Type: application/json
{
  "subject": "Hello",
  "content": "Nice to meet you!"
}

// Updating a note
PUT /restful/api/note/update
Content-Type: application/json
{
  "idx": 1,
  "subject": "Hello",
  "content": "It's really nice to meet you."
}

// Deleting a note
DELETE /restful/api/note/delete
Content-Type: application/json
{
  "idx": 1
}

// Retrieving a note
GET /restful/api/note/1
Content-Type: application/json

// Retrieving a list of note
GET /restful/api/note/list 
Content-Type: application/json

// Retrieving a list of note with paging and search query
GET /restful/api/note/list?pn=1
GET /restful/api/note/list?pn=1&q=hello
Content-Type: application/json
```