# LMS Backend

### Before running the application

- Install Java 21
- Install PostgreSQL 16
- Create database named "lms"

### Run it locally

`./gradlew bootRun`

Server will start up on port 8080.

# Available APIs

### Course Administration

- Create course
    - POST (/api/admin/courses)
    - Request body example:

        ```json
        {
          "courses": [
            {
              "coursePrefix": "CS",
              "courseNumber": "1301",
              "courseName": "Introduction to Computing"
            }
          ]
        }
        ```
    - Response body example:
        ```json
        {
            "courses": [
                {
                    "coursePrefix": "CS",
                    "courseNumber": 1301,
                    "courseName": "Introduction to Computing",
                    "numberOfStudents": 0,
                    "numberOfStaff": 0,
                    "courseID": "1595300445779525"
                }
            ]
        }
        ```
- Get all courses
    - GET (/api/admin/courses)
    - Response body example:
    ```json
    {
        "courses": [
            {
                "coursePrefix": "ART",
                "courseNumber": 2990,
                "courseName": "Drawing",
                "numberOfStudents": 0,
                "numberOfStaff": 2,
                "courseID": "{course-id}"
            },
            {
                "coursePrefix": "CS",
                "courseNumber": 1301,
                "courseName": "Introduction to Computing",
                "numberOfStudents": 29,
                "numberOfStaff": 3,
                "courseID": "{course-id}"
            }
        ]
    }
    ```

- Get course by id
    - GET (/api/admin/courses/{course-id})
    - Response body example
    ```json
        {
            "courses": [
                {
                    "coursePrefix": "CS",
                    "courseNumber": 1301,
                    "courseName": "Introduction to Computing",
                    "numberOfStudents": 29,
                    "numberOfStaff": 3,
                    "courseID": "{course-id}"
                }
            ]
        }
    ```

### User Administration

- Create user
    - POST (/api/admin/courses/{course-id}/users)
    - Request body example:
      ```json
      {
        "users": [
          {
            "classID": "{course-id}",
            "username": "zaknorman",
            "password": "secret",
            "firstName": "Zak",
            "lastName": "Norman",
            "role": "Student"
          }
        ]
      }
      ```
    - Response body example
        ```json
        {
            "users": [
                {
                    "username": "zaknorman",
                    "password": "secret",
                    "firstName": "Zak",
                    "lastName": "Norman",
                    "role": "Student",
                    "userID": "{user-id}",
                    "classID": "{course-id}"
                }
            ]
        }
        ```
- Get all users
    - GET (/api/admin/courses/{course-id}/users)
    - Response body example:
    ```json
    {
        "users": [
            {
                "username": "zaknorman",
                "password": "secret",
                "firstName": "Zak",
                "lastName": "Norman",
                "role": "Student",
                "userID": "{user-id}",
                "classID": "{course-id}"
            },
            {
                "username": "ewittenbrook5",
                "password": "secret",
                "firstName": "Elizabeth",
                "lastName": "Wittenbrook",
                "role": "Staff",
                "userID": "{user-id}",
                "classID": "{course-id}"
            }
        ]
    }
    ```
- Get user by id
    - GET (/api/admin/courses/{course-id}/users/{user-id})
    - Response body example:

    ```json
    {
        "firstName": "Zak",
        "lastName": "Norman",
        "role": "Student",
        "userID": "{user-id}",
        "classID": "{course-id}"
    }
    ```

### Discussion Post

- Create discussion post
    - POST (/api/courses/{course-id}/discussion-posts)
    - Request body example:
      ```json
      {
        "discussionPosts:": [
          {
            "authorID": "{user-id}",
            "title": "Discussion post title",
            "content": "Can anyone help me with this problem?",
            "tags": [
              {
                "tagID": "{tag-id}"
              }
            ]
          }
        ]
      }
      ```
    - Response body example:
        ```json
        {
            "discussionPosts": [
                {
                    "firstName": "Zak",
                    "lastName": "Norman",
                    "createdOn": "2024-07-01T01:46:24.249922512Z",
                    "title": "Assignment 1 post title",
                    "voteCount": 0,
                    "tags": [
                        {
                            "name": "Assignment 1",
                            "color": "#FFFFFF",
                            "tagID": "{tag-id}"
                        }
                    ],
                    "discussionPostID": "{post-id}",
                    "authorID": "{user-id}"
                }
            ]
        }
        ```
- Get all discussion posts
    - GET (/api/courses/{course-id}/discussion-posts)
    - Response body example:
    ```json
    {
        "discussionPosts": [
            {
                "firstName": "Zak",
                "lastName": "Norman",
                "createdOn": "2024-07-01T01:46:24.249923Z",
                "title": "Assignment 1 post title",
                "voteCount": 0,
                "tags": [
                    {
                        "name": "Assignment 1",
                        "color": "#FFFFFF",
                        "tagID": "{tag-id}"
                    }
                ],
                "discussionPostID": "{post-id}",
                "authorID": "{user-id}"
            },
            {
                "firstName": "Elizabeth",
                "lastName": "Wittenbrook",
                "createdOn": "2024-06-30T20:08:15.532911Z",
                "title": "Title 2",
                "voteCount": 0,
                "tags": [],
                "discussionPostID": "{post-id}",
                "authorID": "{user-id}"
            }
        ]
    }
    ```
- Get discussion post by id
    - GET (/api/courses/{course-id}/discussion-posts/{post-id})
    - Response body example:
    ```json
    {
        "discussionPosts": [
            {
                "firstName": "Zak",
                "lastName": "Norman",
                "createdOn": "2024-07-01T01:46:24.249923Z",
                "title": "Assignment 1 post title",
                "content": "I'm very confused about assignment 1",
                "voteCount": 0,
                "responses": [],
                "tags": [
                    {
                        "name": "Assignment 1",
                        "color": "#FFFFFF",
                        "tagID": "{tag-id}"
                    }
                ],
                "discussionPostID": "{post-id}",
                "authorID": "{user-id}"
            }
        ]
    }
    ```

### Tag administration

- Create tag
    - POST (/api/courses/{course-id}/tags)
    - Request body example:
    ```json
    {
        "tags": [
            {
                "name": "Assignment 1",
                "color": "#FFFFFF"
            }
        ]
    }
    ```
    - Response body example:
    ```json
    {
        "tags": [
            {
                "name": "Assignment 1",
                "color": "#FFFFFF",
                "tagID": "{tag-id}"
            }
        ]
    }
    ```
- Get all course tags
    - GET (/api/courses/{course-id}/tags)
    - Response body example
    ```json
    {
        "tags": [
            {
                "name": "Assignment 1",
                "color": "#FFFFFF",
                "tagID": "{user-id}"
            }
        ]
    }
    ```