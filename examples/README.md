### Some Example Requests and Responses

```http
GET /api/users/all
```

response
```javascript
[
    {
        "id": "615fe6098147ae4917f2184b",
        "username": "mubas",
        "email": "mohalabas@yahoo.com",
        "password": "1234"
    }
]
```

---
```http
POST /api/users/add
```

request body:
```json
{
	"username": "pribas",
	"email": "pribas@company.com",
	"password": "coding4life"
}
```

response with new user id:
```javascript
User Added::615fe81b8147ae4917f2184f
```

---
```http
GET /api/timelines/all
```

response
```javascript
[
    {
        "id": "615fe6098147ae4917f2184e",
        "title": "Great Timeline",
        "description": "important moments",
        "userid": "615fe6098147ae4917f2184b",
        "creation_date": "2021-10-08T09:32:41.304",
        "tags": [
            "tag1",
            "tag2"
        ],
        "moments": [],
        "score": null
    }
]
```

---
```http
PUT /api/timelines/update
```

request body:
```json
{
	"id": "615fe6098147ae4917f2184e",
	"title": "New Pribas Timeline",
	"userid": "615fe81b8147ae4917f2184f"
}
```

response:
```javascript
Timeline Updated::615fe6098147ae4917f2184e
```

---
```http
POST /api/timelines/addMoment
```

request body:
```json
{
	"timelineId": "615fe6098147ae4917f2184e",
	"momentId": "615fe6098147ae4917f2184c"
}
```

response:
```javascript
Moment Added::615fe6098147ae4917f2184e
```

---
updated timeline result:
```javascript
[
    {
        "id": "615fe6098147ae4917f2184e",
        "title": "New Pribas Timeline", **new title**
        "description": "important moments",
        "userid": "615fe81b8147ae4917f2184f", **new userid**
        "creation_date": "2021-10-08T09:32:41.304",
        "tags": [
            "tag1",
            "tag2"
        ],
        "moments": [ **moment added**
            {
                "id": "615fe6098147ae4917f2184c",
                "title": "Company Establishment",
                "description": "Established in Frankfurt",
                "moment_date": "12.12",
                "creation_date": "2021-10-08T09:32:41.292",
                "score": null
            }
        ],
        "score": null
    }
]
```

---
```http
GET /api/moments/all
```

response
```javascript
[
    {
        "id": "615fe6098147ae4917f2184c",
        "title": "Company Establishment",
        "description": "Established in Frankfurt",
        "moment_date": "12.12",
        "creation_date": "2021-10-08T09:32:41.292",
        "score": null
    },
    {
        "id": "615fe6098147ae4917f2184d",
        "title": "B2B Booking",
        "description": "First Implementation",
        "moment_date": "14.14",
        "creation_date": "2021-10-08T09:32:41.293",
        "score": null
    }
]
```

---
```http
GET /api/moments/find?searchText="implementation"
```

response
```javascript
[
    {
        "id": "615fe6098147ae4917f2184d",
        "title": "B2B Booking",
        "description": "First Implementation",
        "moment_date": "14.14",
        "creation_date": "2021-10-08T09:32:41.293",
        "score": 0.75
    }
]
```

---
