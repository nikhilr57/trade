# Trading Service
A simple trading service

This manages the following data 
Data | Type
------------ | -------------
Trade ID | String
Version | Long
Counter Party ID | String
Book ID | String 
Maturity Date | Date (dd/mm/yyyy)
Expired | Boolean

*Trading Rules*
* Only latest version of trade is accepted
* Maturity date past the current date is not allowed
* All trades past the maturity date are marked as expired

*****
## Usage
### Docker
```bash
mvn compile jib:dockerBuild
docker run -d -p 8080:8080 trade-app
```
### Maven
```bash
mvn spring-boot:run
```
---------------------------------------
### APIs / Endpoints
#### Swagger URL

`<http>://<host>:<port>/swagger-ui/index.html` <br>
<br>
e.g.
http://localhost:8080/swagger-ui/index.html

#### H2 URL

`<http>://<host>:<port>/h2-console/login.jsp` <br>

e.g.
http://localhost:8080/h2-console/login.jsp

JDBC URL: jdbc:h2:mem:tradedb <br>
User Name: sa <br>
Passowrd: password <br>

---------------------------------------

#### Add a trade
* Validates the request
* Rejects the request if maturity date is less than current date
* Rejects the request if request version is older than latest version
* Updates the present trade if the request version matches with existing latest version
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
* Takes page number and page size for pagination
* Returns all available trades
```bash
curl -X 'GET' \
  'http://localhost:8080/trades/T1?pageSize=20&pageNumber=0' \
  -H 'accept: application/json'
```
#### Get a trade by ID and version
* Takes page number and page size for pagination
* Returns all available trades
```bash
curl -X 'GET' \
  'http://localhost:8080/trades/T1/2' \
  -H 'accept: application/json'
```
#### List all trades 
* Takes page number and page size for pagination
* Returns all available trades
```bash
curl -X 'GET' \
  'http://localhost:8080/trades?pageSize=20&pageNumber=0' \
  -H 'accept: application/json'
```

---------------------------------------
## Technology Stack
* Java 11
* Spring boot 2.4.4
    * JPA
    * Swagger with OpenAPI 3 specification
    * Scheduler
    * Zalando Problem support
    * Mockito and JUnit5
* H2 In memory database
* Docker support



