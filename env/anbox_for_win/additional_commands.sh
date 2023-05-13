# forward ports to host
sysctl -w net.ipv4.conf.all.route_localnet=1
alias iptables=iptables-legacy
docker_host_ip=$(getent ahostsv4 host.docker.internal | head -n1 | awk '{print $1}')
port_forward_start=10081
port_forward_end=10081
iptables -t nat -A OUTPUT -p tcp -m tcp -j DNAT \
  --dport $port_forward_start:$port_forward_end \
  --to-destination "$docker_host_ip:$port_forward_start-$port_forward_end"
iptables -t nat -A POSTROUTING -j MASQUERADE

