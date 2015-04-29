RESTful
=======

Suggesting the codes for RESTful API including filter/annotator for allowing user access and validating input data, user management, data management and some utils such as protecting data using 128/256-bit AES encryption and so on.

Please take a look at [readme](/doc/readme) to see release note.

### Guide ###
You need to install MySQL and create database for this. Please, run a sql file named [data.sql](/doc/sql/data.sql) in doc/sql.<br>
And, please, take a look at [jdbc.properties](/src/main/resources/config/jdbc.properties) in [/src/main/resources/config](/src/main/resources/config) and edit url, username and password.

### Usage ###

#### html ####
##### User #####
  - [x] [/restful/user/signup.html](/src/main/webapp/user/signup.html "Creating new user")
  - [x] [/restful/user/signin.html](/src/main/webapp/user/signin.html "Signing in")
  - [x] [/restful/user/profile.html](/src/main/webapp/user/profile.html "Retrieving my information")
  - [x] [/restful/user/setting.html](/restful/user/setting.html "Updating/Deleting user's information")
  - [x] [/restful/user/user.html](/restful/user/user.html "Retrieving user's information") (_admin only_)
  - [ ] /restful/user/list.html (_admin only_)

##### Note #####
  - [ ] /restful/note/create.html
  - [ ] /restful/note/update.html
  - [ ] /restful/note/delete.html
  - [ ] /restful/note/note.html
  - [ ] /restful/note/list.html

#### Restful ####
##### User #####
```json
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

// Retrieving a user (admin only)
GET /restful/api/user/ax
Content-Type: application/json

// Retrieving a list of user with paging and search query (admin only)
GET /restful/api/user/list 
GET /restful/api/user/list?pn=1
GET /restful/api/user/list?pn=1&q=ax
Content-Type: application/json
```

##### Note #####
```json
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

// Retrieving a list of note with paging and search query
GET /restful/api/note/list 
GET /restful/api/note/list?pn=1
GET /restful/api/note/list?pn=1&q=hello
Content-Type: application/json
```