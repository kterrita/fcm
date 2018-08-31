1. I assume, that we are in one year range. So there are no checks for year while getting data grouped by month.
2. For launch this application
 - run `./mvnw clean package -DskipTests`
 - go to target dir `cd target`
 - run `java -jar fcm-0.0.1-SNAPSHOT.jar`
 
3. Upload page is available on `http://localhost:8080` path
4. You can overview rest api accessing `http://localhost:8080/swagger-ui.html`