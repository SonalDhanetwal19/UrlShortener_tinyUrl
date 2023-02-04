# To start the application:
mvn clean install -DskipTests -Dmaven.wagon.http.ssl.insecure=true

# To create docker image:
docker build -t urlshortener:v1 .

# To run docker-compose and make application up and running. Now you can check whether you application is up and running:
docker-compose -f docker-compose.yml up

# To remove docker image:
docker image rm -f  b11c657791be

# To prune images & volumes
docker image prune
docker volume prune

# learnings
# Always ensure the image that we are created during docker build has the same name and version as mentioned 
# in the docker-compose image

# To add a short url, use the post API's - first create a short url using the below curl that will generate a short url based 
# on the id - eg -id =1 , it will generate b; id =2 - shorturl = c;

curl --location --request POST 'http://localhost:8080/api/v1/create-short-url' \
--header 'Content-Type: application/json' \
--data-raw '{
"longUrl" : "https://www.geeksforgeeks.org/amazon-interview-questions/",
"expiryDate" : "2024-07-20T12:57:25.575+00:00"
}'

# endpoint: http://localhost:8080/api/v1/create-short-url

# body:
{
"longUrl" : "https://www.geeksforgeeks.org/amazon-interview-questions/",
"expiryDate" : "2024-07-20T12:57:25.575+00:00"
}

# To retrieve the original url based on short url - get the short url from database - here it is b:

curl --location --request GET 'http://localhost:8080/api/v1/b?shortUrl=b' \
--data-raw ''

# endpoint: http://localhost:8080/api/v1    followed by short url /b
  # so the url is :
# http://localhost:8080/api/v1/b?shortUrl=b

# and pass as request parameters as well
# shorturl:   b


# mysql - open cli of mysql from docker and then
1) mysql -u root -p
2) when promted for password - provide "password"
3) show databases; (result - shortener)
4) use shortener;
5) show tables; (result - url)
6) desc url; (to see the schema of url table)
7) select * from url; (you can check the database entries and short url created)

# Refer to postman collection - urlshortener
