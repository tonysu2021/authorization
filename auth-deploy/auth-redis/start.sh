#!/bin/sh

echo ------------------------------------------------
echo Build auth-redis stack
echo ------------------------------------------------
docker-compose --compatibility -f auth-redis.yml up --build -d