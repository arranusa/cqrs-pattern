# cqrs-pattern

CQRS Pattern with Spring Boot, ActiveMQ

## Usage

```bash
# Install Docker MySQL with user and password : root/ root
docker run --detach --name=mysql -p 3306:3306  --env="MYSQL_ROOT_PASSWORD=root" mysql

# Download and run ActiveMQ
- https://activemq.apache.org/components/classic/download/
- go to activemq/bin
- run activemq.bat start

# Compile Project
- clone this project
- go to folder
- run mvn clean install

# Run Command Service
- go to folder command-api, run java -jar target/command-api***.jar
- go to browser, run localhost:8081/command/swagger-ui/
- login with admin/admin for Basic Authentication
- execute any API

# Run Query Service
- go to folder query-api, run java -jar target/query-api***.jar
- go to browser, run localhost:8082/query/swagger-ui/
- login with admin/admin for Basic Authentication
- execute any API

```

