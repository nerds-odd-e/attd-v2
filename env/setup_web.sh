#!/bin/bash
set -x
project_path=$(dirname $0)

HOSTS_FILE=/etc/hosts

IP=$(ping $1 -c 1 | head -n1 | awk -F\( '{print $2}' | awk -F\) '{print $1}')

hosts_file=$(sudo docker exec web-driver cat /etc/hosts | grep -v frontend.app.net | grep -v backend.api.net)

echo "$hosts_file
${IP} frontend.app.net
${IP} backend.api.net" | docker exec -i web-driver sudo sh -c "cat > /etc/hosts"

