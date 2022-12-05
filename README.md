# fw-api-testing

Please take the following steps when trying to set-up the project for the first time:

Preconditions:
1. Have java installed and the JAVA_HOME property set. (latest version is 19, but anything above 11 should work)
    - Useful link: https://www.oracle.com/java/technologies/downloads/
    - Useful link: https://confluence.atlassian.com/doc/setting-the-java_home-variable-in-windows-8895.html
2. Have maven installed(latest version) and the MAVEN_HOME property set.
    - Useful link: https://www.baeldung.com/install-maven-on-windows-linux-mac
3. Have IntelliJ IDEA or any other IDE that supports Java and Maven installed.
    - I was using IntelliJ IDEA CE version 2022.2.3, but they should all work

Steps:
1. Download or clone the project.
2. Import the project into Intellij. (Or other IDE of choice)
   -If asked for SDK, choose the latest java version you have.
3. If the project is not automatically recognised by maven, right-click the pom file and choose Maven->Reload Project
4. Run the following command in the terminal : "mvn clean install"
   -The command will also run the tests, in order to skip them use "mvn clean install -DskipTests"
5. Once everything is set up, you can run the tests by opening the Class File and clicking run manually.
   -To run the full suite (There is currently only one), right click on it and choose the run option.
7. The tests can also be run from the command line with different parameters.