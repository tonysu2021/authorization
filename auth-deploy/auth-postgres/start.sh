#!/bin/sh

echo "------------------------------------------------"
echo "Create auth-postgres stack"
echo "------------------------------------------------"
docker-compose --compatibility -f auth-postgres.yml up --build -d

