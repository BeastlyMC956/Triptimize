# Triptimize

Triptimize is a web-based application that provides users with a comprehensive solution for trip
planning. With Triptimize, users can create custom itineraries, book accommodations, and discover
local attractions.

## Full-Stack

Triptimize is a full-stack application that consists of a backend and frontend.

### Backend

The backend of Triptimize is responsible for user registration and authorization using JSON Web
Tokens (JWT). It is built using Java and PostgreSQL, and is designed to be scalable and secure.

More things are coming!

### Frontend

The frontend of Triptimize is currently under development.

---

## Running

1. Make sure to have/add the following environment file added ``database.env`` at the root of the
   project with the following properties:
    * `POSTGRES_URL` (ex: `jdbc:postgresql://<IP>:<PORT>/user`)
    * `POSTGRES_USER`
    * `POSTGRES_PASSWORD`
    * `POSTGRES_DB`
    * `SIGNING_KEY`

2. Open Docker and run `docker-compose up` in the terminal.
3. Then proceed to launch the `TriptimizeApplication` (To successfully add environment variables to
   the `application.yml`as of now) with the following environment variables:
    * `POSTGRES_USER`
    * `POSTGRES_PASSWORD`
    * `POSTGRES_DB`
    * `SIGNING_KEY`

   Alternatively you can just replace the variables in `application.yml` with their respective
   value.

---

## HTML Requests

The base template for HTML requests is `http://<IP>:<PORT>/api/v1`.

Triptimize currently supports
the following requests:

## Posts

### `*/auth/authenticate`

This endpoint takes an `email` and a `password` in JSON format, and returns a new JWT token
associated with the account. If the user details are incorrect the HTTP Status code will be
a `403 Forbidden`

Example request body:

```json
{
  "email": "test123@gmail.com",
  "password": "password123"
}
```

Example response (200):

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MTIzQGdtYWlsLmNvbSIsImlhdCI6MTY4MzA4MDA1NCwiZXhwIjoxNjgzMDgxNDk0fQ.D4eKyTcyYc2fpOLEnA-EPqbNNGoDYDqZ3bRITzMrlZI"
}
```

### `*/auth/register`

This endpoint takes an `email`,
a `username`, `firstName`, `lastName`, `password`, `dateOfBirth`, `country`,
`preferredCurrency`, `travelPreferences` and `profilePicture` in JSON format, and returns a JWT
token associated with the new account and returns a new JWT token associated with the new account.

Example request body:

```json
{
  "email": "test123@gmail.com",
  "username": "testUsername",
  "firstName": "testFirstname",
  "lastName": "testLastname",
  "password": "password123",
  "dateOfBirth": "1335205592410",
  "country": "USA",
  "preferredCurrency": "USD",
  "travelPreferences": "TBA",
  "profilePicture": "url"
}
```

Example response (200):

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MTIzQGdtYWlsLmNvbSIsImlhdCI6MTY4MzA4MDAzOSwiZXhwIjoxNjgzMDgxNDc5fQ.ksnSCQHfv5waDqUpxTAABOost7MuwKK7sXDOwRmTyE0"
}
```

---

## License

Triptimize is released under
the `MIT License`.
---

## Dependencies

For a list of dependencies used in Triptimize, please refer to the `DEPENDENCIES.md` file.
