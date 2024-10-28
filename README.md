# Lost & Found Application
**Overview**
The Lost & Found application is a Spring Boot RESTful service that allows administrators to upload lost item details while users can view and claim them. Built with Java and an H2 database and it's a production-ready prototype. There are areas where we can enhance the application to improve scalability, monitoring, logging and performance. 

**Technologies Used Backend Framework:**

1. Spring Boot

2. Programming Language: Java 21

3. Database: H2 (in-memory)

DEV database env - http://localhost:9092/lostandfound
PROD database env - http://localhost:9093/lostandfound

**Testing:** JUnit and Mockito for unit tests

**Code Coverage**
The application maintains a code coverage of 85%, ensuring a robust testing framework to validate functionality and catch potential issues.

**Clone the repository:**
Repo URL - https://github.com/aichmonali94/lostandfound.git
Branch - main

**Build the application:**
mvn clean install -DskipTests

**Run the application:**
mvn spring-boot:run

**LostAndFount API Endpoints**

**1.Upload Lost Items: Have only ADMIN rights**
1. Method - POST
2. URL - http://localhost:9092/api/v1/lostandfound/file/admin/upload
3. Body - type: form-data , Key:file , value: pdf file shared under resource folder.
4. Authorization - Basic Auth, username-admin, password-admin123

**2.Retrieve Claimed Items: Have User as well Admin rights**
1. method - GET
2. Url - http://localhost:9092/api/v1/lostandfound/lostitem/user/getAllLostItems
3. Authorization - Basic Auth, username-user, password-user123
4. 
**3.Claim Lost Item: Have user as well admin rights**
1. Method - PUT
2. URL - http://localhost:9092/api/v1/lostandfound/claimdetails/user/claimByItem
3. Params - userId:1001
   itemId:2
   quantity:1
4. Authorization - Basic Auth, username-user, password-user123

**4.Retrieve Lost Items + User Details:**
1. Method - GET
2. URL - http://localhost:9092/api/v1/lostandfound/lostitem/admin/getLostItemsWithUserDtls
3. Authorization - Basic Auth, username-admin, password-admin123

**USER Service Endpoints**
1. Method - GET
2. Url - http://localhost:8081/api/v1/userservice/users/1001
3. Mock Users are - 1001 and 1002

**Testing with Jaccoco coverage 81%**
**Run the tests using:**
mvn test

**Open API Swagger Documentation**
http://localhost:9092/swagger-ui/index.html

**PROD Actuator Details**
Health Check: http://localhost:9093/actuator/health
Application Info: http://localhost:9093/actuator/info

**Current Features:**
1. Item Management: Administrators can upload lost item details in PDF format only, streamlining the process of data entry for lost items.

2. Service Interaction: A mock 'User Service' has been implemented to fetch user details, providing a foundation for future user management and interaction.

3. Efficient Data Handling: The application utilizes an H2 in-memory database for quick access and management of lost item records.

4. Basic Validation and Exception Handling: The application includes essential validation and exception handling to ensure data integrity and improve user experience.

5. Swagger Documentation: OpenAPI documentation via Swagger is integrated, making it easy for developers to understand and interact with the API endpoints.

6. All the assessment requirement endpoints are working.

7. Implemented the basic spring security for authorization and authentication.

**Areas for Improvement:**
1. Authentication and Authorization: Will enhance the authentication mechanism (like JWT or OAuth2) for better security.

2. Persistent Database: Transitioning from an H2 in-memory database to a more robust solution like NoSQL or MySQL for data persistence.

3. Search Functionality: Implementing advanced search and filtering options for users to easily find specific lost items.

4. Notifications: Adding email or SMS notifications for users when their lost items are claimed or matched.

5. File Format Support: Expand the item upload capability to support additional file formats (e.g., CSV, images) for enhanced flexibility.

6. Spring Actuator: Integrate Spring Actuator to provide production-ready features such as health checks and metrics, improving application observability.

7. Cloud Deployment: Consider moving the application to Azure for enhanced scalability and reliability.

8. Monitoring and Logging: Implement robust monitoring and logging features to track application performance and troubleshoot issues effectively, using tools.