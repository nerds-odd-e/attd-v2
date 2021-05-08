#!/bin/bash
set -x
project_path=$(dirname $0)

HOSTS_FILE=/etc/hosts

hosts_file=$(docker exec web-driver cat /etc/hosts | grep -v frontend.app.net | grep -v backend.api.net)

echo "$hosts_file
${1} frontend.app.net
${1} backend.api.net" | docker exec -i web-driver sudo sh -c "cat > /etc/hosts"

