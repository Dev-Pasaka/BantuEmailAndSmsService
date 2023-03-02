# BANTU EMAIL AND SMS SERVICES (REST API)
## NB (Testing Results)
    During testing we recoded a reponse time of average 3.5 seconds.We are
    working towards reducing it to less than 1 sec.Though the response time might vary
    depending on the client's internet speed.
## Offered Services
    Phone Verification (OTP)
    Email Verification (OTP)
    Promotional Emails
    Sms Notifications

## Templetes and Screen Shots.
## Email Templete
![Email Templete](https://user-images.githubusercontent.com/108231562/219983001-123e21cf-0f08-4554-a393-45352d0c6777.png)


## Sms Templete
![SmsTemplete](https://user-images.githubusercontent.com/108231562/219055855-630fbdbd-79b9-4524-be56-fec0d28e1460.jpg)

## Third Parties Involved
    Mailgun -> For Email Services
        Pricing = $35 50k emails for one month
    
    Africastalking -> For Sms services
        Pricing = Kes 0.08 per sms
## Frameworks and Languages
    Ktor -> Backend Framework for Kotlin
    Kotlin and Java (Legacy code and libararies)

## Authentication(Basic-Auth)
    username & password
### Users(Default credentials)
#### Steve

    username = steve
    password = smutinda
#### Peter
    username = peter
    password = smuthoka
### Register user
### Register Endpoint = "/registerUsers"
    RequestBodyType = Json
    Method  = Post


```json
{
    "username":"your username"
    "password":"your password"
}
    
```
    ResponseBodyType = Json
```json 
{
    "success": true,
    "message": "New user created successfully"
}
```


## Api Endpoints
### Base URL
    http://investbantu.us-east-1.elasticbeanstalk.com/
# Home endpoint = "/"
    Response = Welcome to bantu
    ResponseBodyType = Text
    Method = GET
## Test Endpoint = "/test"
    ResposeType = Json
    Method = Get
```json
{
    "status": "Endpoint is ok",
    "message": "The api endpoint is working well !"
}
```
## VerifyEmail endpoint = "/verifyEmail"
    RequestBodyType = Json
    Method = POST
    ResponseBodyType = Json
## Request body format
```json
{
    "email":"example@gmail.com"
}
```
## Response body format
    On Request sucess
```json
{
    "status": "Sent",
    "code": "042046",
    "email": "example@gmail.com",
    "message": "Success: Code sent to email example@gmail.com"
}
```
    On Request fail
```json
{
    "status": "Not sent",
    "code": "NULL",
    "email": "example@gmail.com",
    "message": "Error:Code not sent"
}
```
## News and Offers Endpoint = "/newsAndOffers"
    RequestBodyType = Json
    Method = POST
    ResponseBodyType = Json
## Request body format
```json
{
    "email":"example@gmail.com",
    "message": "Here is our new offers"
}
```
## Response body format
    On Request sucess
```json
{
  "status": "Sent",
  "email": "example@gmail.com",
  "message": "Promotional email sent"
}
```
    On Request fail
```json
{
  "status": "Not sent",
  "code": "NULL",
  "email": "example@gmail.com",
  "message": "Error: Code not sent"
}
```
## Verify Phone Endpoint = "/verifyPhone"
    RequestBodyType = Json
    Method = POST
    ResponseBodyType = Json
## Request Body Format
```json
{
    "phone":"0102435709"
}
```
## Respose Body Format
    On Resquest Sucess
```json
{
    "status": "Sent",
    "code": "066400",
    "phone": "0102435709",
    "message": "Success: Code sent to  0102435709"
}
```
    On Request Fail
```json
{
  "status": "Not sent",
  "code": "NULL",
  "phone": "0102435709",
  "message": "Error:Code not sent"
}
```
## Sms Notification Endpoint
    RequestBodyType = Json
    Method = POST
    ResponseBodyType = Json
## Request Body Format
```json
{
  "phone":"0102435709",
  "message":"Here is out new Offers for you !!!"
}
```
## Response Body Format
    On Request Success
```json
{
    "status": "sent",
    "phone": "0102435709",
    "message": "Sms notification sent"
}
```
    On Request Fail
```json
{
    "status": "Not sent",
    "phone": "0102435709",
    "message": "Sms notification not sent"
}
```
