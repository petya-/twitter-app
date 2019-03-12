# Twitter APP
The twitter app is a single-page application, consuming a SOAP and REST webservices responsible for counting words in a post and censoring.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

## Prerequisites

You should have Node.js installed on your machine
```
https://nodejs.org/en/download/
```

## Installing the Twitter App

Go to the client folder and install the dependencies

```
yarn install
```

You can then run the app with

```
yarn start
```

This should open a new page in the browser, running on port 3000.

## Installing the SOAP WebService

Using jax-WS you should be able to simply create a web application client and then 
implement the WSDL from here: (http://wordcounterservice.azurewebsites.net/WordCounter?wsdl)
Depending on your IDE you should be able to simply drag and drop the 
countWords() method into your class source code.

```
countWords("This sentence has five words");
This should return an int with the value of '5'
```

Service can be found on [Azure](http://wordcounterservice.azurewebsites.net/WordCounter?wsdl)


## Using the REST WebService

To filter a message, send the service a HTTP POST request with a message in the body as plain text.

Details:
```
Request type: POST 
URL: https://twitterfilterservice.azurewebsites.net/VeggieFilter
Accepted Content-Type: "plain/text"
Query string parameter options: filter=[root/bulb/all]
```
Response Content-Type: 
```
"application/json"
```
Response JSON elements:
```
  "filter" = name of the applied filter.
  "censored" = true/false based on whether or not the message had words censored.
  "filteredMessage" = the message after being passed through the vegetable filter.
```
cURL HTTP-request example:
```
curl -X POST https://twitterfilterservice.azurewebsites.net/VeggieFilter?filter=bulb -H "Content-Type: text/plain" -d "Just wanted to let you guys know that I just ate celery!" -i
```

Service can be found on [Azure](https://twitterfilterservice.azurewebsites.net/VeggieFilter)

## Built With

* [React](https://reactjs.org/) - The web framework used for the client
* [Maven](https://maven.apache.org/) - Dependency Management


## Authors

* **Petya Buchkova** - *Initial work* - [petya-](https://github.com/petya-)
* **Eric Kofi York** - *Initial work* - [Khadeus](https://github.com/Khadeus)
* **Jeffrey Kwarteng** - *Initial work* - [JeffreyKwart](https://github.com/JeffreyKwart)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
