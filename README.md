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


## Installing the REST WebService

Explain how to run the application

```
Give an example
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
