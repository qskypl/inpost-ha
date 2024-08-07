#!/bin/sh

TESTS_TYPE=$1

if [ -z "$TESTS_TYPE" ]; then
  echo "Script to run tests in the container and copy test report"
  echo "Usage: $0 <tests_type>"
  echo "Supported types: api, web"
  exit 1
fi

TEST_REPORT_FOLDER=
TEST_CONTAINER_NAME=

case "$TESTS_TYPE" in
  api)
    TEST_REPORT_FOLDER=test-report-api
    TEST_CONTAINER_NAME=restassured-tests
    docker compose -f docker-compose-api.yml build
    docker compose -f docker-compose-api.yml up --force-recreate --abort-on-container-exit
    ;;
  web)
    TEST_REPORT_FOLDER=test-report-web
    TEST_CONTAINER_NAME=selenium-tests
    docker compose -f docker-compose-web.yml build
    docker compose -f docker-compose-web.yml up --force-recreate --abort-on-container-exit
    ;;
  *)
    echo "Unknown tests type: $TESTS_TYPE"
    exit 1
    ;;
esac

rm -rf ../${TEST_REPORT_FOLDER}
mkdir ../${TEST_REPORT_FOLDER}
docker cp ${TEST_CONTAINER_NAME}:/test-automation-solution/target/site/allure-maven-plugin ../${TEST_REPORT_FOLDER}