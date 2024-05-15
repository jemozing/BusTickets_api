### Bus Company Information System

This project aims to develop an Information System (IS) for a bus company, focused on gathering and processing information to provide bus transportation services. The IS will enable users to purchase tickets for intercity bus trips, while allowing companies to offer convenient transportation services.

#### Features
- [*] Ticket purchasing system
- [*] Schedule information for bus routes
- [*] Availability of seats and ticket prices for future bus trips
- [ ] User interface for accessing purchased tickets

#### Architecture
The server-side of the system is being developed using a monolithic architecture.

#### Database
The database structure has been finalized and is ready for integration.

#### Docker
Dockerfiles are available for building and deploying the application.

### Getting Started
To set up the Bus Company Information System locally, follow these steps:

1. Clone the repository.
2. Navigate to the project directory.
3. Build the Docker compose using the provided docker-compose file. 
Usage:
   ```
   docker compose up --build -d
   ```
4. Run the Docker containers to start the application.

### Technologies Used
- Java
- Spring Framework
- Docker
- PostgreSQL
