# milli-voters-server


## About this Project

Java application written as the backend server for tht milli-voters app.

For more information please contact John by mailing [topzy20@yahoo.com](topzy20@yahoo.com)

### System Requirements
  * Java 8 or higher,

### Building the Frontend Application
  * Clone this repository: git clone https://github.com/JohnTheBeloved/embi-person-mngmt.git 
  * Build the reactjs source :  `cd src_reactjs_frontend && npm run build`
  * Move the front end build into server  : `mv build ../src/main/java/resoures/public`

### Building the Server and App
  * Clone `cd ../`
  * Install all dependencies and build  : `./mvnw clean && ./mvnw compile && ./mvnw install`
  * The exeutable jar file would  be located in `target/embi-core-0.0.1-SNAPSHOT.jar`

### Running the App
  * Move the built jar to a location of your choice
  * Execute the executable jar file:  `java -jar embi-core-0.0.1-SNAPSHOT.jar`

### Running the Ap
  * `mvn test`

### Accessing the app
  * The application is now running on http://localhost:8080
  * Open your browser and access the http://localhost:8080