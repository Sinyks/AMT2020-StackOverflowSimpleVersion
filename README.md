# AMT2020-StackOverflowSimpleVersion

| Nom               | email                        |
| ----------------- | ---------------------------- |
| Alban Favre       | alban.favre@heig-vd.ch       |
| Guillaume Zaretti | guillaume.zaretti@heig-vd.ch |
| Sacha Perdrizat   | sacha.perdrizat@heig-vd.ch   |
| Maximillian Vogel | maximillian.vogel@heig-vd.ch  |



## Introduction

The aim of this project is to create a simplified version of the famous StackOverflow website. We will be using concepts learned during the AMT 2020 class at HEIG-VD. This is the first project out of three this semester.

## Technical detail

Our application is build over the Jakarta EE standard (Java EE). We are using the following tools/technology to bring this project up:)

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

There you can visit the http://localhost:9081 page on your browser

## Docker Image

A workflow launch the tests and command for validate your push or your merge request, if it pass the test and build steps then a docker image of openliberty will be create on https://github.com/dev-zaretti?tab=packages.

You can pull it with this command : 

__IMPORTANT: this image is unexploitable without a correct database__ 

```bash
$ docker pull ghcr.io/dev-zaretti/stackoverflowsimpleversion/openliberty:latest
```

Or directly run this container with the following command :

```bash
$ docker run -it -p 9080:9080 ghcr.io/dev-zaretti/stackoverflowsimpleversion/openliberty:latest
```

## Ressources

All other ressources (Mockup, diagram,...) can be found on google Drive

https://drive.google.com/drive/folders/1nZA1BNT6IPRA33JpV597dQgbJ2IBXqPw?usp=sharing

## Known Issues

Some bugs and failures occur in the actual project

- E2E tests fails if launch all at the same time
- Integration test for questions failed in the pipeline sometimes :(

## Changes made after the presentation

- The issues mentioned before on the ``/question`` page were taken care of, the vote buttons behave as intended.
- The homepage only showed wrong static values : the homepage servlet now sends correct statistics to the view which displays them.
- The profile update post servlet not working has been fixed, profile info can be updated and has a nice feedback.
- The Jmeter load were not correctly adapted to the new feature, but still remain in ``./jmeter``
- It is not possible to submit an empty form for questions, answers, comments, login or register.
- Changed the run script
- Cleaned up the code

## Not Implemented

- The pagination on the questions and answer page
- The tag repository exist but has not been added to the front-end logic
- ``VoteManagementFacade`` has not been tested with Arquilian IT
- The code has not been documented (ie:Javadoc)
