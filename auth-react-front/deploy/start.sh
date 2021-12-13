#!/bin/sh

default_image_name="admin-react:0.0.1"
export IMAGE_NAME="${1:-$default_image_name}"

docker stack rm admin-react
echo "remove admin-react stack"
docker stack deploy -c admin-react.yml --prune admin-react
echo "build admin-react stack"