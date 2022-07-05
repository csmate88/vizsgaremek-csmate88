#! /bin/bash
if [ $EUID != 0 ]; then
    sudo "$0" "$@"
    exit $?
fi

docker build -f Docker_test.dockerfile -t vremek_test  .
docker compose -f docker-compose-test.yml up

