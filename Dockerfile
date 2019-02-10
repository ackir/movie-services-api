FROM java:openjdk-8
 
EXPOSE 8080
COPY . ./movie-services-api
WORKDIR ./movie-services-api
RUN bash -c 'touch /movie-services-api.jar'
ADD /target/movie-services-api.jar /movie-services-api/movie-services-api.jar
CMD java -jar movie-services-api.jar