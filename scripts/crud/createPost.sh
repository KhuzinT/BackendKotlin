curl --location --request PUT 'http://localhost:8080/post' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer <TOKEN>' \
--data '{
	"text": "<TEXT>"
}'