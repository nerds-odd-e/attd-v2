# Setup environment

* Install Google Chrome
* Install docker and docker compose
* Please add the following host names into your hosts file. The ip address should be your Wifi ip address

```ini
192.168.0.100 mysql.tool.net
192.168.0.100 mock-server.tool.net
```

* Start environment for running tests

```shell
cd env/compose/dc_pc
docker-compose up
```

# Run all tests

# For Linux and Mac

```shell
./gradlew cucumber
```

# For Windows

```shell
gradlew.bat cucumber
```

You may encounter character encoding issue in terminal. If so, please try to fix it by following this
link https://akr.am/blog/posts/using-utf-8-in-the-windows-terminal

# Run the application

# For Linux and Mac

```shell
./gradlew bootRun
```

# For Windows

```shell
gradlew.bat bootRun
```

