# JioTest
JioTest
This is solution for given problem.

"Last Seen" Problem Statement:
Most of you must have used a messaging app like WhatsApp. In these apps, you can see the last seen time of a given person. Typically the logic to calculate the last seen time is as follows:

Solution :

(1) I have used chain of responsability pattern to solve this problem.
(2) This is SpringBoot Microservice based project.
(3) Project can be imported in to any Idea and can be executed.
(4) Curl command to see the output :

curl -X GET \
  'http://localhost:8080/lastSeen?dateTime=01-01-2020T06%3A30%3A15' \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 7582f2e7-ef66-8286-0455-3cb9feae4ecd'
  
  It has RequestParam "dateTime" and value can be provided like "01-01-2020T06:30:15"
  
  Output will be like : Last seen a month ago
