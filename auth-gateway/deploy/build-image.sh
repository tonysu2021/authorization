#!/bin/sh

#echo "------------------------------------------------"
#echo "Remove Old Image : auth-gateway"
#echo "------------------------------------------------"
#docker rmi auth-gateway:0.0.1

echo "------------------------------------------------"
echo "Build New Image : auth-gateway"
echo "------------------------------------------------"
docker build --network=host --tag auth-gateway:0.0.1 --file ./Dockerfile ./