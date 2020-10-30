#!/bin/sh
cp ../target/stackoverflow-simplified.war images/openliberty/apps
cp ../src/main/liberty/config/server.xml images/openliberty/configs
cp ../src/main/liberty/config/postgresql*.jar images/openliberty/configs
cd topologies
docker-compose up
