#! /bin/bash
if [ $EUID != 0 ]; then
    sudo "$0" "$@"
    exit $?
fi

docker build -f Docker_build.dockerfile -t vremek  .
docker compose -f docker-compose.yml up
