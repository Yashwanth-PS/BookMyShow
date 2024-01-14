# Create a New Ubuntu Machine that has Open JDK 17 - Base Image
FROM openjdk:17

# Maven  BookMyShow  LifeCycle  Package  JAR File will be created
# Copy the Jar File of Our Application to the Home Folder of the Machine
COPY target/BookMyShow-0.0.1-SNAPSHOT.jar app.jar

# Command to run the Java Application --> java -jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

# Executes the Docker File and creates a Snap Shot of the Machine
# docker build -t bookmyshow:v0.1 .

# docker tag bookmyshow:v0.1 yashwanthps/bms:v0.1

# docker login -u "user_name" -p "password" docker.io

# Push the Snap Shot to a Repository
# docker push yashwanthps/bms:v0.1

# docker pull yashwanthps/bms:v0.1

# Docker Creates a container on the laptop
# docker run yashwanthps/bms:v0.1