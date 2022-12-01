test
test
![image](https://user-images.githubusercontent.com/117408677/204982544-f1c9483b-8d4e-46c9-98a1-0b47d424793d.png)

About Project
---

Getting Started
---

Prerequisites
* Windows 10 x64 19H2 or above
* JDK / JRE
* Maven
* IDE
* Docker
* MySQL
* Apache
* Navicat for MySQL
* Levo.AI

Installing


Conduct test in Powershell
1. Register and login Levo account
2. API Catelog
* Click import new schema
* Choose testing type, application or servvice
* Upload the project OpenAPI specification file at Levo 
3. Test Plan
* Data driven testing
* Choose the imported API assets
* Click advanced test plan settings and select testing indicators in checkbox
* Copy LRN (Levo Resource Number) and replace it to test command
4. Run the application or service in docker
5. Run test plan in Powershell  
* Install and update the Levo CLI
 
Function Launch_Levo {docker run --rm -v ${HOME}/.config/configstore:/home/levo/.config/configstore:rw -v  ${pwd}:/home/levo/work:rw -e TERM=xterm-256color -ti levoai/levo:stable $args}

Set-Alias -Name levo -Value Launch_Levo

docker pull levoai/levo:stable
* Login Levo

levo login
* Run test command
* Wait for test results
6. Detailed Test results
* Click Test Runs on the Levo Dashboard
* Click the test


Authors
---


License
---
