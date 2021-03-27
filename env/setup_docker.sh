#!/bin/bash
set -x
project_path=$(dirname $0)

HOSTS_FILE=/etc/hosts

IP=$(ping $1 -c 1 | head -n1 | awk -F\( '{print $2}' | awk -F\) '{print $1}')

echo $IP

DOMAINS=($(grep 'VIRTUAL_HOST=' "${project_path}/compose" -r | awk -F\= '{print $2}' | sort | uniq))

for DOMAIN in "${DOMAINS[@]}"
do
    echo "${IP} ${DOMAIN}" >> ${HOSTS_FILE}
done

