# insta
A social network similar to instagram. Users have the opportunity to register/log in, upload images, like and write comments, and create their own personal page.
The server part is implemented on Spring Boot, with registration and authorization on Spring Security, saving posts, comments in the PostgreSQL database. The client part is implemented using Angular. The project is being built using Maven.

The database stores 4 entities:
- User
- Post
- Comment
- Image
The database also stores tables containing ‘User Role’ – user roles
And ‘Post Like User' – the relationship of the post and the users who liked it.

API
API
Authorization:
- api/auth/signin – POST request, user authentication
- api/auth/signup – POST request, user registration
User:
- api/user/ – GET request, getting the current authorized user
-	api/user/:userId – GET request, getting user data transmitted by :userId
- api/user/update – POST request, update of authorized user data 
Post:
- api/post/create – POST request, creating a new post
- api/post/:postId/delete – POST request, post deletion, id of the transmitted
- api/post/all – GET request, return of all posts
- api/post/user/posts – GET request, return all posts for the current user
Comment:
- api/comment/:postId/create – POST request, creating a new comment to the post transmitted by :postId
- api/comment/:postId/all – GET request, getting all comments to a post
- api/comment/:commentId/delete – POST request, deleting a comment to a post
Image:
- api/image/upload - POST request, upload image for user profile
- api/image/:postId/upload - POST request, uploading an image to a post
- api/image/profileImage - GET request, getting an image from a user profile
- api/image/:postId/image - GET request, getting an image from a user's post

  



