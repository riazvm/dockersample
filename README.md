# dockersample
Simple containerized spring boot application that generates a QRCode

Step 1. Start coding and create your initial application or service baseline 
Developing a Docker application is similar to the way you develop an application without Docker. The difference is that while developing for Docker, you are deploying and testing your application or services running within Docker containers in your local environment. The container can be either a Linux container or a Windows container. 
You can start coding your application even before enabling Docker in your application and deploying and testing in Docker. However, it is recommended that you start working on Docker as soon as possible, because that will be the real environment and any issues can be discovered as soon as possible. 


Option A: Creating a project using an existing official Docker image 
You usually build a custom image for your container on top of a base image you can get from an official repository at the Docker Hub registry. 
Using single architecture image repositories 
Using an official  image repository from Docker Hub with a version number ensures that the same language features are available on all machines (including development, testing, and production). 
The following example shows a sample Dockerfile for an java container. 
FROM java:8-jdk-alpine 

# Base image for your docker container

COPY ./target/qrcode-0.0.1-SNAPSHOT.jar /usr/app/ 

# Docker to copy files from the local file-system to a specific folder inside the build image. 
# Here, we copy our .jar file to the build image (Linux image) inside /usr/app.

WORKDIR /usr/app 
# WORKDIR instruction sets the working directory for any RUN, CMD, ENTRYPOINT, COPY and ADD 
# instructions that follow in the Dockerfile. Here we switched the workdir to /usr/app so as 
# we don't have to write the long path again and again.

RUN sh -c 'touch qrcode-0.0.1-SNAPSHOT.jar'
# RUN - This tells Docker to execute a shell command-line within the target system. 
# Here we practically just "touch" our file so that it has its modification time updated 
# (Docker creates all container files in an "unmodified" state by default).

ENTRYPOINT ["java" , "-jar" , "qrcode-0.0.1-SNAPSHOT.jar"]

#ENTRYPOINT - This allows you to configure a container that will run as an executable. 
# It's where you tell Docker how to run your application. 
# We know we run our spring-boot app as java -jar <app-name>.jar, so we put it in an array.

In the Dockerfile, you can also need to instruct Docker to listen on the TCP port you will use at runtime (in this case, port 80, as configured with the EXPOSE setting). 
You can specify additional configuration settings in the Dockerfile, depending on the language and framework you are using. For instance, the ENTRYPOINT line with [“java”, “……]  tells Docker to run a .java  application. 



Step 3. Create your custom Docker images and embed your application or service in them 
For each service in your application, you need to create a related image. If your application is made up of a single service or web application, you just need a single image. 
You, as developer, need to develop and test locally until you push a completed feature or change to your source control system (for example, to GitHub). This means that you need to create the Docker images and deploy containers to a local Docker host (Windows or Linux VM) and run, test, and debug against those local containers. 
To create a custom image in your local environment by using Docker CLI and your Dockerfile, you can use the docker build command. 
docker build -t qrcode-app:latest . 

Note the use of the tag latest

To Check if the built image exists run the following command
docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
qrcode-app          latest              27e7df88f481        5 seconds ago       145MB
java                8-jdk-alpine        3fd9dd82815c        22 months ago       145MB

When an application is made of multiple containers (that is, it is a multi-container application), you can also use the docker-compose up --build command to build all the related images with a single command by using the metadata exposed in the related docker-compose.yml files. 
You can find the existing images in your local repository by using the docker images command. 



Test your Docker application using your local Docker host 

To run the docker image run the following command
docker run -p 8090:8080 qrcode-app:latest
we have to map the port of the host operating system - 8090 and the port inside the container - 8080, which is specified as the -p 8090:8080 argument.
Test using a http client , below is using Postman

Payload 
{
	"merchantID":"123",
	"merchantName":"riaz"
}
Header
Key: Content-Type 		Value : application/json


Method : POST , URL : localhost:8090/generateQRCode

You should get back a QR Code

 


