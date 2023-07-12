#!/bin/bash
./gradlew bootRun

printf "\nAdding external api configuration:"
printf "\n"
curl -X POST -H "Content-Type:application/json" -d '{ "type": "GITHUB", "url": "https://api.github.com/users/{parameter}" }}' http://localhost:8080/external-apis

printf "\nDownloading user data from GitHub:"
printf "\n"
curl -X GET -H "Content-Type:application/json" http://localhost:8080/users/ruszkowski89

printf "\nDownloading another user data from GitHub:"
printf "\n"
curl -X GET -H "Content-Type:application/json" http://localhost:8080/users/elon
