# 📰 News Aggregator API

A Spring Boot-based RESTful API that allows users to register, log in, set their news preferences, and fetch news articles from external news APIs. The application uses JWT for secure authentication and supports marking articles as read or favorite, with caching and periodic refresh of news data.

---

## 🚀 Features

- ✅ User registration and login with JWT authentication
- ✅ Store and update user-specific news preferences
- ✅ Fetch personalized news from external APIs (e.g., GNews)
- ✅ Search for news articles by keyword
- ✅ Mark articles as **read** or **favorite**
- ✅ Retrieve read and favorite articles
- ✅ Caching of news articles to reduce API calls
- ✅ Scheduled cache refresh every 10 minutes
- ✅ Secure endpoints with role-based access
- ✅ Input validation and centralized exception handling
- ✅ In-memory H2 database for simplicity

---

## 🛠 Tech Stack

- Java 17+
- Spring Boot
  - Spring Web
  - Spring Security + JWT
  - Spring Data JPA
  - Spring Validation
  - Spring Cache
  - Spring WebClient
- H2 Database (in-memory)
- Maven

---

## 📦 API Endpoints

### 🔐 Authentication
| Method | Endpoint        | Description                |
|--------|------------------|----------------------------|
| POST   | `/api/register`  | Register a new user        |
| POST   | `/api/login`     | Log in and receive JWT     |

### ⚙️ Preferences
| Method | Endpoint              | Description                          |
|--------|------------------------|--------------------------------------|
| GET    | `/api/preferences`     | Get user’s saved preferences         |
| PUT    | `/api/preferences`     | Update news preferences              |

### 📰 News
| Method | Endpoint                        | Description                              |
|--------|----------------------------------|------------------------------------------|
| GET    | `/api/news`                      | Fetch news based on user preferences     |
| GET    | `/api/news/search/{keyword}`     | Search news articles by keyword          |

### ✅ Marking Articles
| Method | Endpoint                        | Description                        |
|--------|----------------------------------|------------------------------------|
| POST   | `/api/news/{id}/read`            | Mark an article as read            |
| POST   | `/api/news/{id}/favorite`        | Mark an article as favorite        |
| GET    | `/api/news/read`                 | Get all read articles              |
| GET    | `/api/news/favorites`            | Get all favorite articles          |

---

## 📂 Example Request

**Register**
```http
POST /api/register
Content-Type: application/json

{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "secure123"
}
