# Trading Service
A simple trade application
*****

## Usage
### Docker
```bash
mvn compile jib:dockerBuild
docker run -d -p 80:8080 trade-app
```
### Maven
```bash
mvn spring-boot:run
```
---------------------------------------
### APIs
#### Add trade
```bash
curl -X 'POST' \
  'http://localhost:8080/trades' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "tradeId": "T1",
  "version": 1,
  "counterPartyId": "CP1",
  "bookId": "B1",
  "maturityDate": "10/04/2021"
}'
```
#### List Trade by ID
```bash
curl -X 'GET' \
  'http://localhost:8080/trades/T1?pageSize=20&pageNumber=0' \
  -H 'accept: application/json'
```
#### Get a trade by ID and version
```bash
curl -X 'GET' \
  'http://localhost:8080/trades/T1/2' \
  -H 'accept: application/json'
```
#### List all trades 

```bash
curl -X 'GET' \
  'http://localhost:8080/trades?pageSize=20&pageNumber=0' \
  -H 'accept: application/json'
```
---------------------------------------
## Technology Stack
* Spring boot
* H2 In memory database
* Docker 


