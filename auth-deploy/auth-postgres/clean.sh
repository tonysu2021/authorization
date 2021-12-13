#!/bin/sh

echo "------------------------------------------------"
echo "Remove auth-postgres stack"
echo "------------------------------------------------"
docker-compose --compatibility -f auth-postgres.yml down -v
