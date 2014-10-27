RESTful
=======

Suggesting the codes for RESTful API including filter/annotator for allowing user access and validating input data, user management, data management and some utils such as protecting data using 128/256-bit AES encryption and so on.

Please take a look at https://github.com/Samsung/restful/blob/master/doc/readme to see release note.

##### Guide #####
You DO need to install MySQL and add user for this. 
Please, take a look at jdbc.properties in src/main/resources/config and edit url, username and password.
There is a sql file name data.sql in doc/sql. Please run it.

##### Usage #####
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