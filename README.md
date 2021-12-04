# User Micro Service

## API

- Welcome API
  - curl --location --request GET 'http://localhost:6061/user-service/welcome'

- Register User
  - curl --location --request POST 'http://localhost:6061/user-service/api/v1/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "loginId":"ketan.gote",
    "password":"password",
    "firstName":"Ketan",
    "middleName":"D",
    "lastName":"Gote",
    "email": "ketan.gote@gmail.com",
    "age":38,
    "dob":"1983-09-09"
}'

- Authenticate User
  - curl --location --request POST 'http://localhost:6061/user-service/api/v1/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "loginId":"ketan.gote",
    "password":"password"
}'

- List All Users
  - Send Token Which is received after Authentication
  - curl --location --request GET 'http://localhost:6061/user-service/api/v1/' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfQURNSU4iXSwic3ViIjoia2V0YW4uZ290ZSIsImlhdCI6MTYzODUzNjE4OCwiZXhwIjoxNjM4NTY0OTg4fQ.LlAHXcmUNoEKln2lIlT-g8-dSIuRq7YRfcZxvWAfea-R39jQGSXSBDxuu4XDbPPS7meEsl_lQ4csqvTbgqFFfw'

- Find User By ID
  - Send Token Which is received after Authentication
  - curl --location --request GET 'http://localhost:6061/user-service/api/v1/61aa0648bf8a0476a991fb90' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfQURNSU4iXSwic3ViIjoia2V0YW4uZ290ZSIsImlhdCI6MTYzODUzNjE4OCwiZXhwIjoxNjM4NTY0OTg4fQ.LlAHXcmUNoEKln2lIlT-g8-dSIuRq7YRfcZxvWAfea-R39jQGSXSBDxuu4XDbPPS7meEsl_lQ4csqvTbgqFFfw' \

- Update User
  - Send Token Which is received after Authentication
  - curl --location --request PUT 'http://localhost:6061/user-service/api/v1/update/61aa52ccde6371720035e1bb' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfQURNSU4iXSwic3ViIjoia2V0YW4uZ290ZTIzNDIiLCJpYXQiOjE2Mzg1NTExNTAsImV4cCI6MTYzODU3OTk1MH0.tfm5tgkgnxpD0Lym5UuYtw-gZlgmrQ9GXzOnHNE6zwPnCGYJL46ZMW5cqpOqdRP3hlmdlWIZ0jv_1Y21XnUDOw' \
--header 'Content-Type: application/json' \
--data-raw '{
    "loginId": "ketan.gote",
    "password": "password",
    "firstName": "Ketan",
    "middleName": "D",
    "lastName": "Gote",
    "email": "ketan.gote@gmail.com",
    "age": 38,
    "dob": "1983-09-09T00:00:00.000+00:00"
}'

## License  

Copyright © [MetaMagic Global Inc](http://www.metamagicglobal.com/), 2021-22.  All rights reserved.

Licensed under the Apache 2.0 License.

**Enjoy!**

