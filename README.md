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
# FIXIT: Step failed
org.openqa.selenium.WebDriverException: Unable to parse remote response: 
	at org.openqa.selenium.remote.ProtocolHandshake.createSession(ProtocolHandshake.java:115)
	at org.openqa.selenium.remote.ProtocolHandshake.createSession(ProtocolHandshake.java:74)
	at org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:136)
	at org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:552)
	at org.openqa.selenium.remote.RemoteWebDriver.startSession(RemoteWebDriver.java:213)
	at org.openqa.selenium.remote.RemoteWebDriver.<init>(RemoteWebDriver.java:131)
	at org.openqa.selenium.remote.RemoteWebDriver.<init>(RemoteWebDriver.java:144)
	at com.odde.atddv2.TestSteps.createWebDriver(TestSteps.java:22)
	at com.odde.atddv2.TestSteps.getWebDriver(TestSteps.java:59)
	at com.odde.atddv2.TestSteps.测试环境(TestSteps.java:32)
	at ✽.测试环境(file:///Users/binwu/OOR-local/katas/atdd-v2-exercise/src/test/resources/homework.feature:30)
Caused by: org.openqa.selenium.json.JsonException: Expected to read a START_MAP but instead have: END. Last 0 characters read: 
Build info: version: '3.141.59', revision: 'e82be7d358', time: '2018-11-14T08:17:03'
System info: host: 'Bensmbp', ip: '2408:8207:c40:ec72:0:0:0:f17%en0', os.name: 'Mac OS X', os.arch: 'aarch64', os.version: '13.5.2', java.version: '11.0.20'
Driver info: driver.version: RemoteWebDriver
	at org.openqa.selenium.json.JsonInput.expect(JsonInput.java:290)
	at org.openqa.selenium.json.JsonInput.beginObject(JsonInput.java:220)
	at org.openqa.selenium.json.MapCoercer.lambda$apply$1(MapCoercer.java:64)
	at org.openqa.selenium.json.JsonTypeCoercer.lambda$null$6(JsonTypeCoercer.java:145)
	at org.openqa.selenium.json.JsonTypeCoercer.coerce(JsonTypeCoercer.java:126)
	at org.openqa.selenium.json.Json.toType(Json.java:69)
	at org.openqa.selenium.json.Json.toType(Json.java:55)
	at org.openqa.selenium.json.Json.toType(Json.java:50)
	at org.openqa.selenium.remote.ProtocolHandshake.createSession(ProtocolHandshake.java:112)
	at org.openqa.selenium.remote.ProtocolHandshake.createSession(ProtocolHandshake.java:74)
	at org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:136)
	at org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:552)
	at org.openqa.selenium.remote.RemoteWebDriver.startSession(RemoteWebDriver.java:213)
	at org.openqa.selenium.remote.RemoteWebDriver.<init>(RemoteWebDriver.java:131)
	at org.openqa.selenium.remote.RemoteWebDriver.<init>(RemoteWebDriver.java:144)
	at com.odde.atddv2.TestSteps.createWebDriver(TestSteps.java:22)
	at com.odde.atddv2.TestSteps.getWebDriver(TestSteps.java:59)
	at com.odde.atddv2.TestSteps.测试环境(TestSteps.java:32)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at io.cucumber.java.Invoker.doInvoke(Invoker.java:66)
	at io.cucumber.java.Invoker.invoke(Invoker.java:24)
	at io.cucumber.java.AbstractGlueDefinition.invokeMethod(AbstractGlueDefinition.java:47)
	at io.cucumber.java.JavaStepDefinition.execute(JavaStepDefinition.java:29)
	at io.cucumber.core.runner.CoreStepDefinition.execute(CoreStepDefinition.java:66)
	at io.cucumber.core.runner.PickleStepDefinitionMatch.runStep(PickleStepDefinitionMatch.java:63)
	at io.cucumber.core.runner.ExecutionMode$1.execute(ExecutionMode.java:10)
	at io.cucumber.core.runner.TestStep.executeStep(TestStep.java:92)
	at io.cucumber.core.runner.TestStep.run(TestStep.java:64)
	at io.cucumber.core.runner.PickleStepTestStep.run(PickleStepTestStep.java:51)
	at io.cucumber.core.runner.TestCase.run(TestCase.java:104)
	at io.cucumber.core.runner.Runner.runPickle(Runner.java:73)
	at io.cucumber.core.runtime.Runtime.lambda$execute$5(Runtime.java:110)
	at io.cucumber.core.runtime.CucumberExecutionContext.runTestCase(CucumberExecutionContext.java:117)
	at io.cucumber.core.runtime.Runtime.lambda$execute$6(Runtime.java:110)
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
	at io.cucumber.core.runtime.Runtime$SameThreadExecutorService.execute(Runtime.java:233)
	at java.base/java.util.concurrent.AbstractExecutorService.submit(AbstractExecutorService.java:118)
	at io.cucumber.core.runtime.Runtime.lambda$run$2(Runtime.java:86)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:195)
	at java.base/java.util.stream.SliceOps$1$1.accept(SliceOps.java:199)
	at java.base/java.util.ArrayList$ArrayListSpliterator.tryAdvance(ArrayList.java:1632)
	at java.base/java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:127)
	at java.base/java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:502)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:488)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
	at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:913)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:578)
	at io.cucumber.core.runtime.Runtime.run(Runtime.java:87)
	at io.cucumber.core.cli.Main.run(Main.java:92)
	at io.cucumber.core.cli.Main.main(Main.java:34)
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
