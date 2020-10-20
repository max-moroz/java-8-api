#!/bin/bash

#==========================================================
# Task 6 - Java 8 API
# This should be at the same folder as Java8ApiApplication.jar
# This is for executing Java8ApiApplication application
#==========================================================


# Check for Java8ApiApplication.jar file
if [ ! -f $(dirname "$0")/Java8ApiApplication.jar ]; then
echo "Error: Java8ApiApplication.jar file is not found"
exit 1
fi

# Iterate through the list of given command line arguments
java -jar $(dirname "$0")/Java8ApiApplication.jar $@
exit 0

