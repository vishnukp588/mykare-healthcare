# myKare
post http://localhost:9876/api/1.0/login

get http://localhost:9876/api/1.0/users

post http://localhost:9876/api/1.0/register

delete  http://localhost:9876/api/1.0/remove/{email}

For Registration

post http://localhost:9876/api/1.0/register

    {
        "userName": "john",
        "password": "John@123",
        "email": "john258@gmail.com",
        "gender": "Male"
    }
    
    
 For Login
 
 post http://localhost:9876/api/1.0/login
    
    {
        "email": "john258@gmail.com",
        "password": "John@123",
    }
    
 For delete with emaild
 
 delete  http://localhost:9876/api/1.0/remove/john258@gmail.com
 
 For the list of all registered user
 
  http://localhost:9876/api/1.0/users

