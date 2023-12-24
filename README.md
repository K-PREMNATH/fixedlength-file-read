**Spring Batch Fixed-Length Flat File Reader**

This repository contains a Spring Batch application that demonstrates how to read data from a fixed-length flat file using Spring Batch.

**Project Structure**
com.controller.JobController: This Spring MVC controller exposes a REST endpoint (/jobs) to trigger the Spring Batch job.

com.config.BatchConfiguration: Configuration class for Spring Batch. Defines the job, step, and task executor. It also configures the reader and writer.

com.job.EmployeeFileReader: Custom item reader for reading from a fixed-length flat file. It uses a custom line mapper (EmployeeLineMapper) and field set mapper (EmployeeFieldSetMapper).

com.job.EmployeeLineMapper: Configures the line tokenizer for the fixed-length file and sets the field set mapper.

com.job.EmployeeFieldSetMapper: Maps fields from the fixed-length file to the Employee model.

com.job.EmployeeWriter: Custom item writer that prints the read items to the console.

com.model.Employee: Simple model class representing an employee with fields employeeId, name, and salary.

**How to Run**
Clone the repository: git clone <repository-url>
Open the project in your preferred IDE.
Run the main application class: Application.java.
Usage
Once the application is running, make a POST request to http://localhost:8080/jobs to trigger the Spring Batch job.
Configuration
The fixed-length file is configured in EmployeeFileReader using a FixedLengthTokenizer.
The job is configured in BatchConfiguration with a step (step1) that uses the custom reader (EmployeeFileReader) and writer (EmployeeWriter).
Task executor (jobTaskExecutor) is configured for parallel processing.
Additional Notes
The application reads the fixed-length file (input.txt) from the resources folder.
The EmployeeWriter currently prints items to the console; you can customize it to write to a database or any other destination.
Feel free to explore and modify the code as needed for your specific use case!
