#!/bin/sh
export IMAGE_NAME="${1:-auth-gateway:0.0.1}"

echo "------------------------------------------------"
echo "Remove Old Stack : auth-gateway"
echo "------------------------------------------------"
docker stack rm auth-gateway

echo "------------------------------------------------"
echo "Deploy New Stack : auth-gateway"
echo "------------------------------------------------"
docker stack deploy -c auth-gateway.yml --prune auth-gateway