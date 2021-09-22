#!/bin/sh

echo "------------------------------------------------"
echo "Create postgres-basic stack"
echo "------------------------------------------------"
docker-compose --compatibility -f postgres-basic.yml up --build -d

