# Environment Setup by Ben Wu

## Ubuntu environment setup for Web end-to-end testing

- install JDK 11 by sdkman
- Install docker for Linux (not docker desktop)
- Add the following into /etc/hosts

```shell
127.0.0.1 mysql.tool.net
127.0.0.1 mock-server.tool.net
127.0.0.1 web-driver.tool.net
127.0.0.1 host.docker.internal
```

- Run the infrastructure of the web system

```shell
git clone  git@github.com:wubin28/attd-v2.git
cd attd-v2/env/compose/dc_pc
docker compose up web
# you can run command "docker compose stop" to stop all 6 containers
# you can run command "docker compose down" to remove all 6 containers
# you can visit https://localhost:9443 with admin/admin123456789 to check the status of the 6 containers
```

- Check out the infrastructure of the web system

Selenium grid: http://localhost:7900

Phpmyadmin for mysql: http://localhost:20000, admin/123456

- Run web application

```shell
cd attd-v2
sdk use java 11.0.20-tem
./gradlew bootRun
```

- Check out the web application

http://localhost:10081

http://localhost:10081/swagger-ui/index.html

- Do exercises

```shell
git clone git@github.com:wubin28/atdd-v2-exercise.git
```

Install cucumber for java plugin in IntelliJ IDEA

run all tests

```shell
// To fix the java version issue in intellij idea on Ubuntu
sdk default java 11.0.20-tem
./gradlew cucumber
```

keep the infrastructure running: ```docker compose up web```

keep the web running:  ```docker compose up web```

Run ```src/test/resource/homework.feature``` in IntelliJ IDEA and see there is a page displayed quickly on selenium grid page

## Mac environment setup for Web end-to-end testing

- install JDK 11 by sdkman
- Install docker desktop for Mac
- Add the following into /etc/hosts

```shell
127.0.0.1 mysql.tool.net
127.0.0.1 mock-server.tool.net
127.0.0.1 web-driver.tool.net
127.0.0.1 host.docker.internal
```

- Run the infrastructure of the web system

Run docker desktop

```shell
git clone  git@github.com:wubin28/attd-v2.git
cd attd-v2/env/compose/dc_pc
docker compose up web
# you can run command ?docker compose stop? to stop all 6 containers
# you can run command ?docker compose down? to remove all 6 containers
# you can visit http://localhost:9443 with admin/admin123456789 to check the status of the 6 containers
```

- Check out the infrastructure of the web system

Selenium grid: http://localhost:7900

Phpmyadmin for mysql: http://localhost:20000, admin/123456

- Run web application

```shell
cd attd-v2
sdk use java 11.0.20-tem
java -version
./gradlew bootRun
```

- Check out the web application

http://localhost:10081

http://localhost:10081/swagger-ui/index.html

- Do exercises

```shell
git clone git@github.com:wubin28/atdd-v2-exercise.git
```

Install cucumber for java plugin in IntelliJ IDEA

run all tests

```shell
# FIXIT: java.sql.SQLNonTransientConnectionException: Could not create connection to database server. Attempted reconnect 3 times. Giving up.
./gradlew cucumber
```

keep the infrastructure running: ```docker compose up web```

keep the web running:  ```docker compose up web```

```shell
# You have to add bypass proxy for web-driver.tool.net in clash and system proxy settings
```
Run ```src/test/resource/homework.feature``` in IntelliJ IDEA and see there is a page displayed quickly on selenium grid page

## Windows 10 environment setup for Web end-to-end testing

- install JDK 11 by Jabba or chocolatey
- Install wsl2
- Install docker desktop for windows 10
- Add the following into c:\Windows\System32\drivers\etc\hosts

```shell
127.0.0.1 mysql.tool.net
127.0.0.1 mock-server.tool.net
127.0.0.1 web-driver.tool.net
127.0.0.1 host.docker.internal
```

- Run the infrastructure of the web system

```shell
git clone  git@github.com:wubin28/attd-v2.git
cd attd-v2/env/compose/dc_pc
docker compose up web
# you can run command ?docker compose stop? to stop all 6 containers
# you can run command ?docker compose down? to remove all 6 containers
# you can visit http://localhost:9443 with admin/admin123456789 to check the status of the 6 containers
```

- Check out the infrastructure of the web system

Selenium grid: http://localhost:7900

Phpmyadmin for mysql: http://localhost:20000, admin/123456

- Run web application

Run PowerShell 7 as Admin

```shell
jabba ls
jabba use openjdk@1.11.0-2
~/SetProxies.ps1
cd attd-v2
./gradlew bootRun
```

- Check out the web application

http://localhost:10081

http://localhost:10081/swagger-ui/index.html

- Do exercises

```shell
git clone git@github.com:wubin28/atdd-v2-exercise.git
```

Install cucumber for java plugin in IntelliJ IDEA

run all tests

In PowerShell 7

```shell
jabba ls
jabba use openjdk@1.11.0-2
~/SetProxies.ps1
cd atdd-v2-exercise
# FIXIT: > Task :compileTestJava FAILED
./gradlew cucumber
```

keep the infrastructure running: ```docker compose up web```

keep the web running:  ```docker compose up web```

Run ```src/test/resource/homework.feature``` in IntelliJ IDEA and see there is a page displayed quickly on selenium grid page

---

# Setup environment

For Windows, if you can't install Docker Desktop due to legal or policy reason, please
follow [this instruction](windows_without_docker_desktop.md) to setup environment

* Install JDK 11
* Install docker and docker compose
* Please add the following host names into your hosts file

```ini
127.0.0.1 mysql.tool.net
127.0.0.1 mock-server.tool.net
127.0.0.1 web-driver.tool.net
127.0.0.1 host.docker.internal
```

* Start environment for running tests of web

```shell
cd env/compose/dc_pc
docker compose up web
```

After environment started, you should be able to access the chrome for running tests at http://localhost:7900. Just
click the connect button

Also, you can access the database at http://localhost:20000 with username `admin` and password `123456`. The database
name is `db`.

If you are running tests of android on Mac (not good for M1), please run `docker compose up android-mac`. After environment started, you
should be able to access the docker based emulator for running tests at http://localhost:18080/vnc_auto.html

If you are running tests of andriod on Linux, please run `docker compose up android-linux`. After environment started,
you should be able to access the docker based emulator for running tests at http://localhost:6080

If you are running tests of android on Windows, there are two options. 
1. Connect your device or local emulator and then run `docker compose up android-win`.
2. To use docker based emulator, please follow the steps below.
    * Add a wsl config file under your user home directory. The full file path should be like `C:/Users/your_user_name/.wslconfig` and the content should be like below. Please replace the `your_user_name` with your actual username.
    ```ini
    [wsl2]
    kernel=<path_of_this_code_repo>\\env\\kernel_image_for_wsl\\bzImage
    ```
    * Restart your computer and Docker Desktop
    * Run `docker compose up android-win-anbox`. After environment started, you should be able to access the docker based emulator for running tests at http://localhost:18080/vnc_auto.html

# Run the application

## For Linux and Mac

```shell
./gradlew bootRun
```

## For Windows

```shell
gradlew.bat bootRun
```

# Open the application

After run the application, you can open the application at http://localhost:10081

And, the swagger document is available at http://localhost:10081/swagger-ui/index.html

# Architecture

![](arch.jpg)
