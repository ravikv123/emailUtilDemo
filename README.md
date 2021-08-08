Email Utility
============
## EMail Util 
The application provides an abstraction between two different email service providers. If one of  the services goes down, the service can quickly failover to a different provider without affecting the customers. 
The CLient/response will see if mail is sent successfully or error in sending mail.

## Techinical Stack
Java with Springboot is used to implement the functionality.
RestTemplete is used for calling the HTTP Request.


## Testing/Deployment process
1. Checkout code
2. Modify the API Keys and from email as required
3. run  - mvn install
4. start the springboot application
5. Application url: http://localhost:8080/email/message

## Adding new Email Client
Implement EmailServiceClient interface , method sendMail.
This service should throw CustomException in case of any validations errors or send mail errors
Autowire the new client with next available qualifier number(eg: service1, service2 etc.,)

## Implementation
To List (email and name), Subject and emailBody is Mandatory. 
CC list and BCC are optional.

    ### Request JSON Structure:
    key       -    Definition
    --------------------------
    to        -   Array of email and name
    cc        -   Array of email and name
    bcc       -   Array of email and name
    subject   -   String
    emailBody -   String

### Sample Request JSON
{
"to":[{"email":"email","name":"name"}],
"cc":[{"email":"email","name":"name"}],
"bcc":[{"email":"email","name":"name"}],
"subject": "Subject",
"emailBody": "EmailBody"
}
### Sample Response JSON
#### Success Response
 Success Response
{
"statusMessage": "Email sent successfully"
}
#### Fail Response
{
    "errorMessage": [
        "server1: Error while sending email - Received status:401 UNAUTHORIZED: [no body]",
        "server2: Error while sending email - Received status:401 Unauthorized: [no body]"
    ]
    }
## logging
logging is not implemented for the functionality

## Test Case scenarios 
[TestCases](docs/testCases.docx)
