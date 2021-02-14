# Messaging REST API

This is a REST API coded in Java for handling Users and Messages for a theroetical company. Requests can be sent using [Postman](https://www.postman.com/).

## Usage
- Create users:
  * /createUser > POST > Person type (KEYS (Strings): name, emailAddress, location) in Body 
    * E.g.: 
        {
          "name": "Foo Bar",
          "emailAddress": "foo@gmail.com",
          "location": "Dublin"
        }
      
- Get Users:
  * /getUserDetails > GET > Headers > emailAddress
    * E.g.: emailAddress : foo@gmail.com
	
- Update Users:
  * /updateUser > PUT > Person type (KEYS (Strings): name, emailAddress, location) in Body
    * E.g.:
      {
          "emailAddress": "foo@gmail.com",
          "name": "Foo Bar",
          "location": "Galway"
      }
		
- Delete Users:
  * /deleteUser > DELETE > Headers > emailAddess
    * E.g.: emailAddress : foo@gmail.com
  
  
- Send a message to one other user:
    * __Initial:__
  * /sendMessage > POST >  Message type (KEYS (Strings): emailAddress1, emailAddress2, chatHistory) in Body
    * E.g.:
      {
        "emailAddress1" : "foo@gmail.com",
        "emailAddress2" : "bar@gmail.con",
        "chatHistory" :  "Hello, I am having an issue with product X, could you help?"
      }

   * __Subsequent:__
  * /updateChatHistory > PUT > Headers > sender > Body > Message type > message
    * E.g.:
    * Header
    * sender : bar@gmail.com
    * Body
      {
        "emailAddress1" : "foo@gmail.com",
        "emailAddress2" : "bar@gmail.con",
        "chatHistory" :  "Sure! What seems to be the issue?"
      }

- Editing messages:
  * Imagine in the above message, bar had a grammatical error e.g. "Sure! What seems to be the issu?" and is embarrassed and wants to edit this:
  * /editMessage > GET > Headers > emailAddress > Copy response and paste into body
  * Make necessary changes > change method to PUT
  * __NOTE:__ You could just remove the get method and use /getChatHistory instead to retrieve the message instead

- Get all the messages sent between two users:
  * /getChatHistory > GET > Headers > emailAddress

- Deleting messages
  * /deleteMessages > DELETE > Headers > emailAddressToDelete
 
# __Technologies:__
  * REST API
  * Java 
  * Github
  * Firestore
  * Postman 
