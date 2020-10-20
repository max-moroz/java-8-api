#!/bin/bash

#==============================================================
# Task 6 - Java 8 API
# Execute from project's root folder
# This is for building project and placing it into newly created directory /dist
# together with all source files and uber-jar
# Java archive is not created if any test failed or code coverage not been met.
#==============================================================

./mvnw clean package

mkdir dist
cp Java8ApiApplication.cmd Java8ApiApplication.sh _README.md ./dist
cd target
cp Java8ApiApplication.jar ../dist
exit 0
