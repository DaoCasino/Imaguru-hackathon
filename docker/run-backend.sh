#!/usr/bin/env bash

echo "Starting backend..."

java -jar \
    -Dspring.datasource.url=jdbc:postgresql://${POSTGRES_ADDRESS}:${POSTGRES_PORT}/${POSTGRES_DB} \
    -Dspring.datasource.username=${POSTGRES_USER} \
    -Dspring.datasource.password=${POSTGRES_PASSWORD} \
    -Dserver.port=${BACKEND_PORT} \
    ./MBH-Hackathon/build/libs/malta-hackaton-0.0.1-SNAPSHOT.jar
