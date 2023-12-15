# User Authentication System

## Authentication ( “/authentication” )

Route for the login and registration operations. Free access for all.

- [x]  GET (”/login”) — Given the credentials, return a JWT if successfully authenticated.

Possible Results:

- 202 Accepted — Returns the JWT.
- 403 Forbidden — Failed Authentication, wrong username or password.
- 500 Internal Server Error

### Expected Body

```json
{
	"username":"String",
	"password":"String"
}
```

---

- [x]  GET ("/486217935") — Creates a default admin. username: admin, password: admin.

Possible Results:

- 200 Ok — Returns the info of the admin created.
- 500 Internal Server Error

## End User (”/users”)

- [x]  GET (”/users”) — Retrieves all users in the database.

Possible Results:

- 200 Ok — Receives a array of End User Objects.
- 204 No Content — When there are no users saved in the database.
- 500 Internal Server Error

---

- [x]  POST (”/users”) — Creates a new user in the database.

Possible responses:

- 201 Created — Successfully created the user in the database.
- 400 Bad Request
- 500 Internal Server Error

### Expected Body

```json
{
	"email":"String",
	"username":"String",
	"password":"String"
}
```

---

- [x]  GET (”/users/search?u=username”) — Find a user by it’s username

Possible responses:

- 200 Ok — User found
- 404 Not Found — User not found
- 500 Internal Server Error

### Key Parameters:

| u | String username |
| --- | --- |

---

- [x]  DELETE (”/users?id=id”) — Finds a user by id and deletes it

Possible responses:

- 200 Ok — User found and deleted
- 404 Not Found — User ID not found
- 418 ****I'm a teapot — If the user is found but not deleted for some reason
- 500 Internal Server Error

### Key Parameters:

| id | Long id |
| --- | --- |

## Roles (”/roles”)

- [x]  GET (”/roles”) — Get all roles saved in the database.

Possible responses:

- 200 Ok — Receives a array of Role objects.
- 204 No Content — When there are no users saved in the database.
- 500 Internal Server Error

---

- [x]  POST (”/roles”) — Saves a role in the database.

Possible responses:

- 201 Created — Successfully created the role in the database.
- 400 Bad Request
- 500 Internal Server Error

### Expected Body

```json
{
	"name":"String"
}
```

---

- [x]  DELETE (”/roles?id=id”) — Deletes a role in the database.

Possible responses:

- 200 Ok — Role found and deleted
- 404 Not Found — Role ID not found
- 418 I'm a teapot — If the role is found but not deleted for some reason
- 500 Internal Server Error

### Key Parameters:

| id | Integer id |
| --- | --- |
