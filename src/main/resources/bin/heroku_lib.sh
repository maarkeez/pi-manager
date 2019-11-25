#!/bin/bash

#·····················································
# HEROKU LIB
#·····················································
# Description:
#   The main purpose of this script is to
#   serve as utility to change heroku application
#   environment variables
# Tips:
#   You can retrieve your API token with the following command:
#        heroku auth:token
#   Or create a new one with:
#        heroku authorizations:create
#   More info at: 
#      - https://devcenter.heroku.com/articles/authentication
#      - https://devcenter.heroku.com/articles/heroku-cli-commands
#   Example of github source url:
#      https://api.github.com/repos/<username>/<repo>/tarball/<branch>?access_token=<github-personal-access-token>
# Dependencies:
#   Main heroku app: heroku
#   Heroku builds plugin: heroku plugins:install heroku-builds

#·····················································
# GLOBAL VARIABLES
#·····················································
APPLICATION_NAME=$1
HEROKU_USER=$2
HEROKU_TOKEN=$3
VARIABLE_NAME=$4
VARIABLE_VALUE=$5
GITHUB_SOURCE_URL=$6

#·····················································
# FUNCTIONS
#·····················································
function printStep() {
	message=$1
	echo "-----> ${message}"
}

function checkNotEmpty(){
	variable=$1
	errorMessage=$2
	if [ -z "$variable" ]
	then
	      echo "Error: ${errorMessage}" >&2;
	      echo "Usage: $0 APPLICATION_NAME HEROKU_USER HEROKU_TOKEN VARIABLE_NAME VARIABLE_VALUE GITHUB_SOURCE_URL" >&2;
	      exit -1;
	fi
}

function overwriteHerokuLocalConfig(){
	HEROKU_USER=$1
	HEROKU_TOKEN=$2

	printStep "Overwritting heroku local config";

	rm ~/.netrc;
	echo "machine api.heroku.com" >> ~/.netrc;
	echo "  login ${HEROKU_USER}" >> ~/.netrc;
	echo "  password ${HEROKU_TOKEN}" >> ~/.netrc;
}

function setHerokuVariable(){
	APPLICATION_NAME=$1
	VARIABLE_NAME=$2
	VARIABLE_VALUE=$3

	printStep "Modifing heroku variable";
	heroku config:set -a "${APPLICATION_NAME}" $VARIABLE_NAME=$VARIABLE_VALUE;
}

function reDeployHerokuApp(){
	APPLICATION_NAME=$1
	GITHUB_SOURCE_URL=$2

	printStep "Re-deploying heroku application";
	heroku builds:create --source-url "${GITHUB_SOURCE_URL}" --app "${APPLICATION_NAME}"
}

#·····················································
# MAIN
#·····················································
checkNotEmpty "${APPLICATION_NAME}" "Application name not defined";
checkNotEmpty "${HEROKU_USER}" "Heroku user not defined";
checkNotEmpty "${HEROKU_TOKEN}" "Heroku token not defined";
checkNotEmpty "${VARIABLE_NAME}" "Variable name not defined";
checkNotEmpty "${VARIABLE_VALUE}" "Variable value not defined";
checkNotEmpty "${GITHUB_SOURCE_URL}" "Github source url not defined";
overwriteHerokuLocalConfig "${HEROKU_USER}" "${HEROKU_TOKEN}";
setHerokuVariable "${APPLICATION_NAME}" "${VARIABLE_NAME}" "${VARIABLE_VALUE}";
reDeployHerokuApp "${APPLICATION_NAME}" "${GITHUB_SOURCE_URL}";

