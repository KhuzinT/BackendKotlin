curl --location --request PATCH 'http://localhost:8080/post/<ID>' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer <TOKEN>' \
--data '{
	"text": "<TEXT>"
}'