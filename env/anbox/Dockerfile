ARG KERNEL_REF
ARG BUILD_REF

FROM ${KERNEL_REF} as kernel-source
FROM ${BUILD_REF} as build

# unpack kernel sources
WORKDIR /usr/src/linuxkit
COPY --from=kernel-source / .
RUN tar xf kernel-dev.tar -C /
RUN tar xf kernel.tar -C /

# install dependencies
RUN apk add -U build-base git curl perl libelf

# copy module sources
WORKDIR /usr/src
COPY ashmem_linux ashmem_linux
RUN make -C ashmem_linux
COPY binder_linux binder_linux
RUN make -C binder_linux

# copy output
FROM ghcr.io/aind-containers/aind:v0.0.5

RUN mkdir -p /lib/modules/$(uname -r)
COPY --from=build /usr/src/ashmem_linux/ashmem_linux.ko /lib/modules/ashmem_linux.ko
COPY --from=build /usr/src/binder_linux/binder_linux.ko /lib/modules/binder_linux.ko
RUN mv /lib/modules/ashmem_linux.ko /lib/modules/$(uname -r)/ashmem_linux.ko
RUN mv /lib/modules/binder_linux.ko /lib/modules/$(uname -r)/binder_linux.ko

# reload modules
RUN depmod -a

#=====================
# Install Android SDK
#=====================
RUN apt-get update && \
  apt-get install -qq -y --no-install-recommends \
  wget unzip openjdk-11-jdk

ENV SDK_VERSION=commandlinetools-linux-8512546_latest
ENV ANDROID_BUILD_TOOLS_VERSION=33.0.0
ENV ANDROID_FOLDER_NAME=cmdline-tools
ENV ANDROID_DOWNLOAD_PATH=/home/user/${ANDROID_FOLDER_NAME} \
    ANDROID_HOME=/usr/lib/android-sdk \
    ANDROID_TOOL_HOME=/usr/lib/android-sdk/${ANDROID_FOLDER_NAME}

WORKDIR /home/user
RUN wget -O tools.zip https://dl.google.com/android/repository/${SDK_VERSION}.zip && \
    unzip tools.zip && rm tools.zip && \
    chmod a+x -R ${ANDROID_DOWNLOAD_PATH} && \
    chown -R 1300:1301 ${ANDROID_DOWNLOAD_PATH} && \
    mkdir -p ${ANDROID_TOOL_HOME} && \
    mv ${ANDROID_DOWNLOAD_PATH} ${ANDROID_TOOL_HOME}/tools
ENV PATH=$PATH:${ANDROID_TOOL_HOME}/tools:${ANDROID_TOOL_HOME}/tools/bin

# https://askubuntu.com/questions/885658/android-sdk-repositories-cfg-could-not-be-loaded
RUN mkdir -p ~/.android && \
    touch ~/.android/repositories.cfg && \
    rm -rf $ANDROID_HOME/platform-tools && \
    rm -rf $ANDROID_HOME/build-tools && \
    echo y | sdkmanager "platform-tools" && \
    echo y | sdkmanager "build-tools;$ANDROID_BUILD_TOOLS_VERSION" && \
    mv ~/.android .android && \
    chown -R 1300:1301 .android
ENV PATH=$PATH:$ANDROID_HOME/platform-tools:$ANDROID_HOME/build-tools

# adb additional commands
RUN sed -i '/# docker-2ndboot.sh is executed as a non-root user via `unsudo`./a adb -a nodaemon server start &' /home/user/docker-2ndboot.sh
RUN sed -i '/adb wait-for-device/a adb reverse tcp:10081 tcp:10081\n' /home/user/docker-2ndboot.sh
# system additional commands
COPY additional_commands.sh /home/user/additional_commands.sh
RUN sed -i '/#!\/bin\/bash/r additional_commands.sh' /docker-entrypoint.sh
# run vnc without password
RUN sed -i 's/x11vnc -usepw/x11vnc/g' /home/user/docker-2ndboot.sh

CMD ["/home/user/docker-2ndboot.sh"]