#!/bin/sh
export IMAGE_NAME="${1:-auth-server:0.0.1}"

echo "------------------------------------------------"
echo "Remove Old Stack : auth-server"
echo "------------------------------------------------"
docker stack rm auth-server

echo "------------------------------------------------"
echo "Deploy New Stack : auth-server"
echo "------------------------------------------------"
docker stack deploy -c auth-server.yml --prune auth-server