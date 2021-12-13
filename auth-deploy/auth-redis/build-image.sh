#!/bin/sh
docker rmi auth-redis:0.0.1
echo "remove old image"
docker build --network=host -t auth-redis:0.0.1 .

