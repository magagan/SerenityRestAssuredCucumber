# Serenity+RestAssured+Cucumber
This is a repository for Serenity Rest API example project
#### Pre-installed dependencies and configuration
```$ Download "java jdk 1.8" and configure JAVA_HOME```
<br>
```$ Downalod maven and configure M2_HOME```


Steps:
1. Clone this repository to any directory of your choice
2. Run the ```rest.jar```  (to run ```java -jar <DIR>/rest.jar --server.port=8085```)
3. Navigate to the root directory where you clone the repository
4. Using terminal execute ```mvn clean verify serenity:aggregate```

The test in example project should be executed after all dependencies is downloaded.

```You can see the generated report in /<Project DIR>/target/site/serenity/index.html```

Enjoy! :)  #SharingIsCaring

Note: By default the end-point of API is set to "http://localhost:8085/student", you can change it in ```TestBase.java``` class
