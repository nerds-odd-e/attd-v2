**This instruction is only for those who can't use Docker Desktop for Windows due to legal or policy reason. If you
don't have that limitation, please follow [main instruction](README.md) to setup environment.**

* Install wsl2 by following [this instruction](https://docs.microsoft.com/en-us/windows/wsl/install-manual)
    * At the end of this instruction, you will need to install a Linux distribution. Please choose **Ubuntu 20.04 LTS**
* Install docker in Ubuntu 20.4 by following [this instruction](https://docs.docker.com/engine/install/ubuntu/). This
  instruction is a bit complex, here are the key steps you need to follow
    * Please follow the section "Set up the repository" and do everything as it said
    * Then you can install the latest docker as below

```shell
    sudo apt-get update
    sudo apt-get install docker-ce docker-ce-cli containerd.io
```

* Start docker service and verify it's running

```shell
    sudo service docker start
    sudo docker ps
```

* Please add the following host names into your Windows hosts file `c:\Windows\System32\Drivers\etc\hosts`

```ini
127.0.0.1 mysql.tool.net
127.0.0.1 mock-server.tool.net
127.0.0.1 web-driver.tool.net
```

* Start environment for running tests

```shell
    cd env/compose/dc_pc
    docker-compose up
```

After environment started, you should be able to access the chrome for running tests at http://localhost:7900 by "
connecting" with password "secret"

* To run ui tests properly, there are some additional steps
    * Get the Windows host ip from `/etc/resolv.conf` which is after "nameserver"
    * Forward port to Windows host as below

```shell
    iptables -t nat -A PREROUTING -p tcp --dport 10081 -j DNAT --to-destination windows_host_ip:10081
```

Please note that this Windows host ip will change every time when ubuntu restarts so that you need to do this after
restart.