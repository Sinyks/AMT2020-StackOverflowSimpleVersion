#!/bin/bash

# docker-compose up topologie API rest
docker-compose -f ./docker/topologies/gamification-engine/docker-compose.yml

# script setup python/js

python3 setup_api_env.py

# create Application and get application key
# Set badges/Pointscale/rules
# save API-key to .env

# launch stackoverflow toplogies with api key as env variable
