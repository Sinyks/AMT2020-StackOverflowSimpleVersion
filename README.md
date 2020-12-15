# AMT2020 - Gamified StackOverflowSimple

| Nom               | email                        |
| ----------------- | ---------------------------- |
| Alban Favre       | alban.favre@heig-vd.ch       |
| Guillaume Zaretti | guillaume.zaretti@heig-vd.ch |
| Sacha Perdrizat   | sacha.perdrizat@heig-vd.ch   |
| Maximilian Vogel  | maximilian.vogel@heig-vd.ch  |



## Introduction

The aim of this project is to link a simplified version of the famous StackOverflow website with a Gamification API which we both made as a team during the AMT 2020 class at HEIG-VD. We will be using concepts learned during that class. This is the final part of this semester's project.


## Technical details

Our application is built over the Jakarta EE standard (Java EE). We are using the following tools/technology to build this project :

| Component                     | Name                                                      | Version      |
| ----------------------------- | --------------------------------------------------------- | ------------ |
| Jakarta EE Application Server | [Open Liberty](openliberty.io/)                           | __>=3.2__    |
| IDE                           | [IntelliJ Idea Ultimate](https://www.jetbrains.com/idea/) | __>=2020.2__ |
| Build/dependency Manager      | [Maven](https://maven.apache.org/)                        | __>= 3.6__   |
| E2E testing tools             | [CodeceptJS](codecept.io/) (with **Puppeteer**)           | __>=2.6__    |
| Unit Testing Framework        | [Junit](https://junit.org/junit5/)                        | __>= 5.6__   |
| Integration Testing tool      | [Arquilian](http://arquillian.org/)                       | __>= 1.1__   |
| Containerization tools        | [Docker](https://www.docker.com/)                         | __>= 19.03__ |

## Run this code (for developpement)

```bash
# clone this repository
$ git clone git@github.com:Sinyks/AMT2020-StackOverflowSimpleVersion.git

$ cd AMT2020-StackOverflowSimpleVersion

$ mvn liberty:run
```

## Run this code (for production)

```bash
# clone this repository
$ git clone git@github.com:Sinyks/AMT2020-StackOverflowSimpleVersion.git

$ cd AMT2020-StackOverflowSimpleVersion/docker

$ ./run.sh
```

You can then visit the http://localhost:9081 page on your browser.

## Docker Image

A workflow launches the tests and commands to validate your push or your merge request. If it pasess the test and build steps then a docker image of openliberty will be created on https://github.com/dev-zaretti?tab=packages.

You can pull it with this command : 

__IMPORTANT: this image is unexploitable without a correct database__ 

```bash
$ docker pull ghcr.io/dev-zaretti/stackoverflowsimpleversion/openliberty:latest
```

Or directly run this container with the following command :

```bash
$ docker run -it -p 9080:9080 ghcr.io/dev-zaretti/stackoverflowsimpleversion/openliberty:latest
```

## Resources

All other resources (Mockup, diagram, ...) can be found on Google Drive :

https://drive.google.com/drive/folders/1nZA1BNT6IPRA33JpV597dQgbJ2IBXqPw?usp=sharing

## Known Issues

Some bugs and failures occur in the actual project :

- E2E tests fail if they are all launched at the same time.
- Integration test for questions fails in the pipeline sometimes.

## Integration with the Gamification Engine API

Three things need to be done in order to link both of our previous projects :

- Create a script-enabled launch of the API, the databases and finally the StackOverflow application that links the API and the app.
- The StackOverflow application sends HTTP POST requests to the API informing the API of user events that impact the gamification engine. 
- The StackOverflow application sends HTTP GET requests to the API when it wants to display gamification information concerning one or all of the app's user(s).

## Not Implemented

- The pagination on the questions and answer page
- The tag repository exist but has not been added to the front-end logic
- ``VoteManagementFacade`` has not been tested with Arquilian IT
- The code has not been documented (ie:Javadoc)
