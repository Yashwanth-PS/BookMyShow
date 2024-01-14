# Create a New Ubuntu Machine that has Open JDK 17 - Base Image
FROM openjdk:17

# Maven  BookMyShow  LifeCycle  Package  JAR File will be created
# Copy the Jar File of Our Application to the Home Folder of the Machine
COPY target/BookMyShow-0.0.1-SNAPSHOT.jar app.jar

# Command to run the Java Application --> java -jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]