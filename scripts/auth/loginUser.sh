curl --location 'http://localhost:8080/user/login' \
--header 'Content-Type: application/json' \
--data '{
	"username": "<USERNAME>",
  "password": "<PASSWORD>"
}'