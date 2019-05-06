This project has been written using Spring MVC framework. I have used gradle as my build tool.

Requirements:
1. JDK 1.8 or up
2. Gradle 4 or up

Steps to execute:
1. Pull the project from this git repo
2. Build the project. If using bash/terminal use command: ./gradlew build . I used intellij IDEA as my editor, it has a build option on the top bar. You can click that.
3. Run the project. Use command: ./gradlew bootRun

Use any HTTP clients to access urls(I used postman for testing my services):
1. For HTTP GET example:
    http://localhost:8080/getRecentTx?user-id=1
    
2. For HTTP POST example:
    http://localhost:8080/saveTx
    Request Body:
    {
    "user-id" : 1,
    "merchant" : "Bartell Drugs",
    "price" : "5.78",
    "purchase-date" : "2019-01-01T22:45:40",
    "tx-id" : 100
    }
    
I have the csv file stored inside the project and made a CSV reader helper class to read the csv file and store the data as a List on application startup.     
