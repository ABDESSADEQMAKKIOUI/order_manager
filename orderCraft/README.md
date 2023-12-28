# Project Setup Instructions (Windows & MacOS)

Follow these steps to set up the project:

1. **Remove existing JDK and Maven installations:**
    - Uninstall all JDK installations and delete all JDK files from your computer.
    - Remove the JDK path from your environment variables.
    - Uninstall Maven and remove its path from your environment variables.

2. **Install JDK and Maven:**
    - Download JDK 1.8.0_202 from [Oracle's website](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html).
    - Download Maven from the [official Maven website](https://maven.apache.org/download.cgi).
    - Install JDK and Maven on your computer.
    - Set the paths of JDK and Maven in your environment variables.

3. **Verify the installations:**
    - Open the command line and run `mvn -version`. If the installed Maven version is displayed, the installation was successful.
    - Run `java -version`. If the installed Java version is displayed, the installation was successful.
    - Ensure that Maven is using JDK 1.8.0_202.

4. **Clone the project:**
    - Run `git clone https://github.com/ayoub-ait-si-ahmad/orderCraftSolution.git`.
    - Navigate to the project directory with `cd orderCraftSolution`.

5. **Configure the database:**
    - Change the database name, user, and password in the `/WEB-INF/dispatcher-servlet.xml` file. The configuration should look like this:
      ```xml
      <!-- DataSource configuration -->
      <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
          <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
          <property name="url" value="jdbc:mysql://localhost:3306/yourDatabaseName"/>
          <property name="username" value="yourUsername"/>
          <property name="password" value="yourPassword"/>
      </bean>
      ```

6. **Build and run the project:**
    - Run `mvn clean compile package`.
    - Run `mvn tomcat7:run`.

Good luck!