#! /bin/bash
if [ $EUID != 0 ]; then
    sudo "$0" "$@"
    exit $?
fi

docker build -t vremek .
docker compose up
