1, What’s the objective of this project?

The proxy module provides API security services that can prevent injection-related attacks. The users (the service provider) do not need to consider security issues in their system; they can focus on implementing functions or processes in their business. 
![image](https://user-images.githubusercontent.com/117408677/204991557-b405ff18-9ecd-42c4-81f5-3429399f296b.png)

 

2, How to use it?

Step1: Configurate the IP address and port the service provider in table ‘source_match’. It will be used by the proxy server to forward requests to the service provider.
![image](https://user-images.githubusercontent.com/117408677/204991592-999b2f0c-f1d9-4d7c-b4a7-fc8573a97386.png)

 
Column description:
service_name : The servlet context path of the service provider.
Service_ip: The ip address of the service provider.
Service_port: The port of the service provider.

Step2: Use the API provided by the proxy server instead of the service provider.
For example: 
The API of the service provider shows below.
![image](https://user-images.githubusercontent.com/117408677/204991643-ed3d7866-dec7-4cbb-acff-740c80076036.png)

 
While finishing the configuration in the proxy server, the user should use the api provided by the proxy server as below.
 ![image](https://user-images.githubusercontent.com/117408677/204991666-3e86f182-506f-4c10-a8f2-fd1662768b89.png)


After above settings, clients can use the API as normal, the proxy server will receive requests and analyze the parameters, and then forward those normal requests and block those malicious ones.

3, Does it work?
We use the LEVO API testing tool to test if it works, follow the instructions below to test it. For more information, visit https://app.levo.ai/

Preparation:
1, LEVO uses OPENAPI as the API format, we should firstly generate the .yml file of our service.
1.1, Use Postman as an example, first export the json file of a collection.
 ![image](https://user-images.githubusercontent.com/117408677/204991716-df09231e-e359-47dd-afe9-6946a4c57829.png)

1.2, After the first step we will get a Json file which contains the parameters of our API.
Then we need to transform the Json file to OPENAPI format file.
Transform it online on this site: https://kevinswiber.github.io/postman2openapi/
Save the content as servicename.yml.
![image](https://user-images.githubusercontent.com/117408677/204991764-47269500-7afd-4eb1-983e-3674c31ca1b8.png)

 

2. Register and login Levo account here: https://app.levo.ai/login
![image](https://user-images.githubusercontent.com/117408677/204991779-837bac67-fcf3-4194-8856-a43616217add.png)

 
3. Select API Catalog
* Click import new schema
* ![image](https://user-images.githubusercontent.com/117408677/204991816-88d603db-6c88-4733-a628-16db1ec9a248.png)

 
* Choose testing type, application.
 ![image](https://user-images.githubusercontent.com/117408677/204991896-4f45e845-cb24-4bb6-90dd-1e4554ec7497.png)

*  Choose the .yml file we generated before.
 
4. Select Test Plan, click new test plan.
 ![image](https://user-images.githubusercontent.com/117408677/204991920-9b242c5b-8b4f-4649-8c6c-43962b997396.png)

* Choose Data driven testing
 ![image](https://user-images.githubusercontent.com/117408677/204991952-86d34618-49d5-4f39-b53b-1097fcc46ed7.png)

* Choose the imported API assets
* Click advanced test plan settings and select testing indicators in checkbox
![image](https://user-images.githubusercontent.com/117408677/204991982-927a196d-a8ac-4ca3-9f21-2c7a14c02f78.png)

 
* Copy LRN (Levo Resource Number) and replace it to test command
 ![image](https://user-images.githubusercontent.com/117408677/204992006-4253483d-f246-4714-a3d4-f65aa5975a2b.png)


5. Run the application or service in docker or local developing environment.
 ![image](https://user-images.githubusercontent.com/117408677/204992035-c9ff6909-6dc2-4a30-a928-6119ba02126f.png)

6. Run test plan in Powershell  
* Install and update the Levo CLI
 Command 1:
Function Launch_Levo {docker run --rm -v ${HOME}/.config/configstore:/home/levo/.config/configstore:rw -v  ${pwd}:/home/levo/work:rw -e TERM=xterm-256color -ti levoai/levo:stable $args}

Command 2:
Set-Alias -Name levo -Value Launch_Levo

Command 3:
docker pull levoai/levo:stable

Command 4:
levo login

Command 5:
levo test --test-plan <LRN value copied to clipboard> --target-url <your live API's base URL> 

For example:

levo test --test-plan twang35_nyit_edu:ws/twang35:app/proxy_orderservice:tp/proxy_orderservice --target-url host.docker.internal:8080

7. The test will start then as below.
 ![image](https://user-images.githubusercontent.com/117408677/204992082-17b83c5f-adf5-494b-b72a-6515712bef15.png)

7. When it is finished, we can see detailed testing result on the Levo webpage.
 ![image](https://user-images.githubusercontent.com/117408677/204992105-751705f5-138c-4968-ae26-d5fded22dfc6.png)

 
