# Rest Service with Java Spring Boot and MongoDB
A RESTful service used to create, delete, edit and list timelines to track events and their information. The API accepts different types of requests.

## Quick Introduction

### Used Technologies
- Java, jdk8
- Spring Boot, [initializr](https://start.spring.io/)
- MongoDB
- Docker Desktop

### pom.xml file
- Declared dependencies:
	- mongodb
	- web
	- lombok

### application.properties file
- port=27017
- host=localhost
- auto-index-creation=true

### docker-compose.yaml file
- **compose version:** 3.7
- **services:**
	- mongo:latest (driver)
		- port: 27017
	- mongo-express (GUI)
		- port: 8081
- **network name:** mongodb_network

### MVC Structure
- ##### [Entities/Collections](src/main/java/com/pribas/task/rest/entity)
- ##### [Controllers](src/main/java/com/pribas/task/rest/controller)
- ##### [Services](src/main/java/com/pribas/task/rest/service)
- ##### [Repositories](src/main/java/com/pribas/task/rest/repository)
- ##### [Runner Class](src/main/java/com/pribas/task/rest/RestServiceApplication.java)

# API Requests
All API requests are mapped to `/api`

**[Examples Available Here](examples/README.md)**

## User
##### Mapped to `/api/users`

```http
GET /api/users/all?page=0&pageSize=3
```
| Parameter | Description |
| :--- | :--- |
| **optional** `page`, **optional** `pageSize` | fetch all users and their details |

---
```http
GET /api/users/find/{id}
```
| Parameter | Description |
| :--- | :--- |
| **required** `id` | fetch user by id |

---
```http
POST /api/users/add
```
| Parameter | Description |
| :--- | :--- |
| **required** `JSON body` | add new user to collection |

**request body example:**
```json
{
	"username": "mubas",
	"email": "mohalabas@yahoo.com",
	"password": "1234"
}
```

---
```http
DELETE /api/users/delete/{id}
```
| Parameter | Description |
| :--- | :--- |
| **required** `id` | remove user from collection by id |

---
## Timeline
##### Mapped to `/api/timelines`

```http
GET /api/timelines/all?page=0&pageSize=3
```
| Parameter | Description |
| :--- | :--- |
| **optional** `page`, **optional** `pageSize` | fetch all timelines and their details |

---
```http
GET /api/timelines/find?searchText=""&page=0&pageSize=3
```
| Parameter | Description |
| :--- | :--- |
| **required** `searchText`, **optional** `page`, **optional** `pageSize` | search timelines by title and description |

---
```http
POST /api/timelines/add
```
| Parameter | Description |
| :--- | :--- |
| **required** `JSON body` | add new timeline to collection |

**request body example:**
```json
{
	"title": "My Timeline",
	"description": "2021 Summary",
	"userid": "1",
	"tags": "survival, family"
}
```

---
```http
DELETE /api/timelines/delete/{id}
```
| Parameter | Description |
| :--- | :--- |
| **required** `id` | remove timeline from collection by id |

---
```http
POST /api/timelines/addMoment
```
| Parameter | Description |
| :--- | :--- |
| **required** `JSON body` | add new moment to timeline's moments list |

**request body example:**
```json
{
	"timelineId": "123",
	"momentId": "789"
}
```

---
```http
DELETE /api/timelines/deleteMoment
```
| Parameter | Description |
| :--- | :--- |
| **required** `JSON body` | remove moment from timeline's moments list |

**request body example:**
```json
{
	"timelineId": "123",
	"momentId": "789"
}
```

---
```http
PUT /api/timelines/update
```
| Parameter | Description |
| :--- | :--- |
| `JSON body`: <ul><li>**required** `id`</li><li>**optional** rest of fields</li></ul>  | update timeline's details |

**request body example:**
```json
{
	"id": "123",
	"title": "New Title", optional
	"desciption": "New Description", optional
	"userid": "2", optional
	"tags": "business, health" optional
}
```

---
## Moment
##### Mapped to `/api/moments`

```http
GET /api/moments/all?page=0&pageSize=3
```
| Parameter | Description |
| :--- | :--- |
| **optional** `page`, **optional** `pageSize` | fetch all moments and their details |

---
```http
GET /api/moments/find?searchText=""&page=0&pageSize=3
```
| Parameter | Description |
| :--- | :--- |
| **required** `searchText`, **optional** `page`, **optional** `pageSize` | search moments by title and description |

---
```http
POST /api/moments/add
```
| Parameter | Description |
| :--- | :--- |
| **required** `JSON body` | add new moment to collection |

**request body example:**
```json
{
	"title": "My Moment",
	"description": "Graduation",
	"moment_date": "07.07.2021"
}
```

---
```http
DELETE /api/moments/delete/{id}
```
| Parameter | Description |
| :--- | :--- |
| **required** `id` | remove moment from collection by id |

---
```http
PUT /api/moments/update
```
| Parameter | Description |
| :--- | :--- |
| `JSON body`: <ul><li>**required** `id`</li><li>**optional** rest of fields</li></ul>  | update moment's details |

**request body example:**
```json
{
	"id": "789",
	"title": "New Moment Title", optional
	"desciption": "New Moment Description", optional
	"moment_date": "10.10.2021" optional
}
```
