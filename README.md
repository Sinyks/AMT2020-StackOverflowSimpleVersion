# AMT2020-StackOverflowSimpleVersion

Authors :  Alban Favre, Sacha Perdrizat, Maximilian Vogel, Guillaume Zaretti

| Nom               | email                        |
| ----------------- | ---------------------------- |
| Alban Favre       | alban.favre@heig-vd.ch       |
| Guillaume Zaretti | guillaume.zaretti@heig-vd.ch |
| Sacha Perdrizat   | sacha.perdrizat@heig-vd.ch   |
| Maximillian Vogel | maximillian.vogel@heig-vdch  |



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
| Containerization tools        | [Docker](https://www.docker.com/)                         | __>= 19.03__ |

## Run this code (for developpement)

```bash
# clone this repository
$ git clone git@github.com:Sinyks/AMT2020-StackOverflowSimpleVersion.git

$ cd AMT2020-StackOverflowSimpleVersion

$ mvn liberty:run
```

There you can visit the http://localhost:9080 page on your browser

## Docker Image

A workflow launch the tests and command for validate your push or your merge request, if it pass the test and build steps then a docker image of openliberty will be create on https://github.com/dev-zaretti?tab=packages.

You can pull it with this command : 

```bash
$ docker pull ghcr.io/dev-zaretti/stackoverflowsimpleversion/openliberty:latest
```

Or directly run this container with the following command :

```bash
$ docker run -it -p 9080:9080 ghcr.io/dev-zaretti/stackoverflowsimpleversion/openliberty:latest
```

