## Sample Spring Boot app with a Redis PubSub and Websocket integration
### Prerequsities
* Docker and NodeJS installed

### Features
The application provides an interface for submitting messages/notes in the request body in a format like:
```
{
    "content": "here goes your content",
    "timestamp": "2021-08-23 00:12:12+0100"
}
```
The application then publishes these messages to a topic named `demo-topic` and also consumes this topic.
The consumed messages are persisted in a H2 in-memory database and pushed to a websocket, which is consumed 
by a simple React frontend.

Furthermore the app provides an endpoint for retrieving all the persisted messages.

### Start the application

```
mvn clean install
```
```
docker-compose up -d
```
or
```
docker-compose up --build
```


### Start the react frontend from the `frontend` folder

```
cd frontend && npm start
```
* it will automatically listen to the websocket on `ws://localhost:8080/ws`

### API Docs

#### Create a note

POST

```
curl -X POST http://localhost:8080/api/notes \
  -H 'accept: application/json' \
  -H 'content-type: application/json' \
  -d '{
	"content" : "abrakadabra",
	"timestamp" : "2018-10-09 00:12:12+0100"
}'
```
Returns:
```
202 - If the requestbody is valid
400 - If the request body has invalid parameters
```

#### Get all notes
GET
```
curl -X GET http://localhost:8080/api/notes \
  -H 'accept: application/json' \
  -H 'content-type: application/json' \
}'
```
Returns
```
200 - With the content if there is any in the database
```

### Technologies used
* Spring Boot
* H2 in-memory database
* Redis for the Publisher-Subcriber
* SockJS lib on the React frontend to listen to the Websocket

### Misc comments
* For commit messages the semantic commits were used
  * https://www.conventionalcommits.org/en/v1.0.0/
* All endpoints are unauthenticated
* Basic swagger config added
  * http://localhost:8080/swagger-ui.html/


