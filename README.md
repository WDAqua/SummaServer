# SummaServer

The SummaServer provides a service for entity summarizatioimg.pngn over RDF graphs. For example for the entity "Die Zeit", a German weekly newspaper, the summary over Wikidata is:

<img src="https://raw.githubusercontent.com/WDAqua/SummaServer/master/Example_Die_Zeit.jpeg" width="40%">

# Setting up

Running the service is simple. Compile it using:

    mvn clean package
    
Run it using:

    java -jar target/summaServer-0.1.0.jar
    
This will start the server at port 3031 and expose the summarization service at:

    localhost:3031/
    
To change the port or the name of the host change the file at (SummaServer/src/main/resources/application.properties).

# API

The service is then available under the following API:

