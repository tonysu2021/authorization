#!/bin/sh

#echo "------------------------------------------------"
#echo "Remove Old Image : mb-andmin-react"
#echo "------------------------------------------------"
#docker rmi admin-react:0.0.1

echo "------------------------------------------------"
echo "Build New Image : admin-react"
echo "------------------------------------------------"
docker build --network=host --tag admin-react:0.0.1 --file ./Dockerfile ../