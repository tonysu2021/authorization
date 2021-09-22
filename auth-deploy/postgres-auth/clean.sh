#!/bin/sh

echo "------------------------------------------------"
echo "Remove postgres-basic stack"
echo "------------------------------------------------"
docker-compose --compatibility -f postgres-basic.yml down -v
