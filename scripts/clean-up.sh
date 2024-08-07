#!/bin/sh
docker compose -f docker-compose-api.yml down
docker compose -f docker-compose-web.yml down