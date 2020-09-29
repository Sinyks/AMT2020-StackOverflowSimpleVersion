# AMT2020-StackOverflowSimpleVersion

Authors :  Alban Favre, Sacha Perdrizat, Maximilian Vogel, Guillaume Zaretti

## Introduction

The aim of this project is to create a simplified version of the famous StackOverflow website. We will be using concepts learned during the AMT 2020 class at HEIG-VD. This is the first project out of three this semester.

## Deployment

A script using Docker launches the application in your browser.

Build the stackoverflow-simplified.war into the target folder and exectue the sh scripts deploy.sh into the docker folder

## Pipline

A workflow launche the test and command for validate your push or your merg request, if it's green a docker image of openliberty was created on https://github.com/dev-zaretti?tab=packages.
you can pull it with this command : docker pull ghcr.io/dev-zaretti/stackoverflowsimpleversion/openliberty:latest
