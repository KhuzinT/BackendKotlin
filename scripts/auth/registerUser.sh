curl --location 'http://localhost:8080/user/register' \
--header 'Content-Type: application/json' \
--data '{
	"username": "<USERNAME>",
  "password": "<PASSWORD>"
}'