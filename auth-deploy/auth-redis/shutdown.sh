#!/bin/sh

echo ------------------------------------------------
echo Remove auth-redis stack
echo ------------------------------------------------
docker-compose --compatibility -f auth-redis.yml down