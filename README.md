# Setup environment

For Windows, if you can't install Docker Desktop due to legal or policy reason, please
follow [this instruction](windows_without_docker_desktop.md) to setup environment

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
docker-compose up web
```

After environment started, you should be able to access the chrome for running tests at http://localhost:7900. Just
click the connect button

Also, you can access the database at http://localhost:20000 with username `admin` and password `123456`. The database
name is `db`.

If you are running tests of web on arm based Mac (e.g. Macbook M1 or M2), please run `docker-compose up web-arm`

If you are running tests of android on Mac, please run `docker-compose up android-mac`. After environment started, you
should be able to access the docker based emulator for running tests at http://localhost:18080/vnc_auto.html

If you are running tests of andriod on Linux, please run `docker-compose up android-linux`. After environment started,
you should be able to access the docker based emulator for running tests at http://localhost:6080

If you are running tests of android on Windows, there are two options. 
1. Connect your device or local emulator and then run `docker-compose up android-win`.
2. To use docker based emulator, please follow the steps below.
    * Add a wsl config file under your user home directory. The full file path should be like `C:/Users/your_user_name/.wslconfig` and the content should be like below. Please replace the `your_user_name` with your actual username.
    ```ini
    [wsl2]
    kernel=<path_of_this_code_repo>\\env\\kernel_image_for_wsl\\bzImage
    ```
    * Restart your computer and Docker Desktop
    * Run `docker-compose up android-win-anbox`. After environment started, you should be able to access the docker based emulator for running tests at http://localhost:18080/vnc_auto.html

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
