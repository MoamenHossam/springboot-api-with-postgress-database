# Read Me First
you can run start the application and the database instance as well as a pgadmin instance to view the database by running the run.sh file.

# Run Instructions

please make sure that JDK 17 is installed on your system.

please make sure that JAVA_HOME env variable is set to point at JDK 17 directory.

please make sure that maven is installed on your system.

please make sure that docker is installed on your system.

please make sure that ports 3000, 5432 and 80 are free before starting the application.

if not you can change the configuration in docker-compose file.

#Helpful
you can find in the root directory a postman collection to test the api under the name STC.postman_collection.json.

Swagger is enabled but not fully configured at http://localhost:3000/swagger-ui/index.html.

graphiql is enabled at http://localhost:3000/graphiql
you can use this query to start off
query{authorizedFiles(userEmail: "mary@gmail.com") {
id
item{
id
name
permissionGroup{
id
groupName
}
}
}}


