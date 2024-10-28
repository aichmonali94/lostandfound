# Lost & Found Application
**Overview**
The Lost & Found application is a Spring Boot-based RESTful web service designed to manage lost items effectively. This application allows administrators to upload details of lost items from a file, while users can view and claim these items. It leverages Java 21 and utilizes an H2 in-memory database for efficient data management. The application is built with a focus on best practices in coding, design patterns, and architecture, ensuring it is clean, maintainable, and production-ready.

**Features**
Upload & Store Lost Items: Admins can upload lost item details from a file (e.g., PDF), which are then extracted and stored in the database.
View Lost Items: Users can retrieve a list of all stored lost items.
Claim Lost Items: Users can claim specific lost items, which records the user ID and quantity claimed.
Admin Retrieval of Claims: Admins can view all lost items along with the users who have claimed them.
User Information Retrieval: A mock user service to fetch user details (e.g., name) associated with claims.

**Technologies Used Backend Framework:** Spring Boot
Programming Language: Java 21
Database: H2 (in-memory) 
DEV env - http://localhost:9092/lostandfound
**Testing:** JUnit and Mockito for unit tests

**Code Coverage**
The application maintains a code coverage of 90%, ensuring a robust testing framework to validate functionality and catch potential issues.

**Getting Started**

**Prerequisites**
1. Java 21
2. Maven (for dependency management)
3. H2 database (comes bundled with Spring Boot)
4. Installation

**Clone the repository:**
git clone https://github.com/aichmonali94/lostandfound.git
cd lost-and-found

**Build the application:**
mvn clean install -DskipTests

**Run the application:**
mvn spring-boot:run

**API Endpoints**
**Admin Endpoints:**

**Upload Lost Items:** POST /api/v1/lostandfound/file/upload
**Retrieve Claimed Items:** GET /api/v1/lostandfound/claimdetails/claimByItem

**User Endpoints:**

**Retrieve Lost Items:** GET /api/v1/lostandfound/lostitem/getLostItemsWithUserDtls
**Claim Lost Item:** POST /api/v1/lostandfound/lostitem/getAllLostItems

**Testing**
**Run the tests using:**
mvn test

**Documentation**
The code includes inline comments and Javadoc to provide clarity on functionality and design choices.

**Conclusion**
This Lost & Found application provides a comprehensive solution for managing lost items, facilitating both administrative oversight and user interaction. With its well-structured design and adherence to best practices, it stands ready for production deployment.

**Swagger OpenAPI documentation**
http://localhost:9092/swagger-ui/index.html

**Actuator Details**
Health Check: http://localhost:9093/actuator/health
Application Info: http://localhost:9093/actuator/info