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

The frontend of Triptimize is currently under development however, current development is using Next.js 
as the selected framework with TypeScript.

---

## Running

1. Make sure to have/add the following environment file added `database.env` at the root of the
   project with the following properties:
    * `POSTGRES_USER`
    * `POSTGRES_PASSWORD`
2. Add an `.env` environment file at the root of the `resources`, (so that the `.env` file is next to the `application.yml`) 
   make sure to have the following variables present in the `.env` file:
   * POSTGRES_URL (e.g. `jdbc:postgresql://<IP>:<PORT>/<MAIN_DATABASE_NAME>`)
   * POSTGRES_USER
   * POSTGRES_PASSWORD
   * SIGNING_KEY
   
(Alternatively you can just replace the variables in `application.yml` & `docker-compose.yml` with their respective value)

3. Open Docker and run `docker-compose up` in the terminal.
   
   a) You may need to create the main database here using the following commands
     * `docker exec -it <container_name> bash`
     * `psql -U <username>`
     * `CREATE DATABASE <MAIN_DATABASE_NAME>;`
4. Then proceed to launch the `TriptimizeApplication`
   
---

## API

UNDER DEVELOPMENT FOR NOW

---

## License

Triptimize is released under
the [MIT License](https://github.com/BeastlyMC956/Triptimize/blob/master/LICENSE.md).

---

## Dependencies

For a list of dependencies used in Triptimize, please refer to the [DEPENDENCIES.md](https://github.com/BeastlyMC956/Triptimize/blob/master/DEPENDENCIES.md) file.
