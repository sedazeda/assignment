
## Allure TestNG and Selenium Test Automation


This application runs with Java 1.8 platform, this project is maven project
that uses allure for reporting and testng for testing.

To download required dependency file you need to run
$mvn clean install
from command line.

In order to generate Allure Report you should perform:
$ ./mvn clean -Dtest=suite.* test site
```
from command line and report will be generated to
`target/site/allure-maven-plugin` folder.
 open index.html
 open packages menu



###This project coded by Seda Altiokoğlu Atasoy with Intellij Idea Community Edition
on Windows 8.1
