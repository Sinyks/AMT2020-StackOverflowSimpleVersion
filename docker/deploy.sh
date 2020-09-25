#!/bin/sh
cp ../target/stackoverflow-simplified.war images/payara/apps
cp ../target/stackoverflow-simplified.war images/wildfly/apps
cd topologies
docker-compose up --build
