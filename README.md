# Setup environment

* Install docker and docker compose
* Please add the following host names into your hosts file

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

# Run tests in Intellij

* Install Intellij IDEA (either Ultimate or Community version)
  * when installing the community version, please also install the "Cucumber for Java" plugin
* Open the repo root folder with Intellij and wait for this gradle project loaded completely
* Open the feature file at `backend/src/test/resources/init.feature` and run it by clicking the green run test gutter
  icon on the left bar and test should pass

# Run the application

# For Linux and Mac

```shell
./gradlew bootRun
```

# For Windows

```shell
gradlew.bat bootRun
```

