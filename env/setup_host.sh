#!/bin/bash
project_path=$(dirname $0)

HOSTS_FILE=/etc/hosts
get_eth_mac() {
	route -n get default | grep interface | awk '{print $2}'
}

get_eth_linux() {
	netstat -r | grep default | awk '{print $NF}'
}

get_default_ip() {
	uname_out="$(uname -s)"
	case "${uname_out}" in
		Darwin*)    default_eth=$(get_eth_mac);;
		Linux*)     default_eth=$(get_eth_linux);;
	esac
	ifconfig $default_eth | grep 'inet ' | awk '{print $2}'
}

replace() {
	uname_out="$(uname -s)"
	case "${uname_out}" in
		Darwin*)    sed -i '' "$1" "$2";;
		Linux*)     sed -i "$1" "$2";;
	esac
}

IP=${1-$(get_default_ip)}
IP=$(ping $IP -c 1 | head -n1 | awk -F\( '{print $2}' | awk -F\) '{print $1}')

DOMAINS=($(grep 'VIRTUAL_HOST=' "${project_path}/compose" -r | awk -F\= '{print $2}' | sort | uniq))

echo "Setting up /etc/hosts with IP:$IP ..."
for DOMAIN in "${DOMAINS[@]}"
do
	grep -q "${DOMAIN}" ${HOSTS_FILE} && \
        replace "s/.*${DOMAIN}$/${IP} ${DOMAIN}/g" ${HOSTS_FILE} || echo "${IP} ${DOMAIN}" >> ${HOSTS_FILE}
done
echo " Done!"

