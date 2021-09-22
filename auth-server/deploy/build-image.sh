#!/bin/sh

#echo "------------------------------------------------"
#echo "Remove Old Image : auth-server"
#echo "------------------------------------------------"
#docker rmi auth-server:0.0.1

echo "------------------------------------------------"
echo "Build New Image : auth-server"
echo "------------------------------------------------"
docker build --network=host --tag auth-server:0.0.1 --file ./Dockerfile ./