FROM gcc:9.3.0

# unpack kernel sources
WORKDIR /usr/src
#RUN apt-get update && \
#    apt-get install -qq -y --no-install-recommends \
#    wget
RUN wget https://github.com/microsoft/WSL2-Linux-Kernel/archive/refs/tags/linux-msft-wsl-5.15.90.1.tar.gz
RUN mkdir -p /lib/modules/5.15.90.1-microsoft-standard-WSL2 && \
         tar xf linux-msft-wsl-5.15.90.1.tar.gz -C . && \
         mv WSL2-Linux-Kernel-linux-msft-wsl-5.15.90.1 /lib/modules/5.15.90.1-microsoft-standard-WSL2/build

# install dependencies
RUN apt-get update && \
    apt-get install -qq -y --no-install-recommends \
    flex bison bc libelf-dev libssl-dev

RUN wget http://archive.ubuntu.com/ubuntu/pool/universe/d/dwarves-dfsg/dwarves_1.21-0ubuntu1~20.04_amd64.deb && \
    apt install -qq -y --no-install-recommends ./dwarves_1.21-0ubuntu1~20.04_amd64.deb

# build kernel
WORKDIR /lib/modules/5.15.90.1-microsoft-standard-WSL2/build
RUN make -j $(nproc) KCONFIG_CONFIG=Microsoft/config-wsl

# build modules
WORKDIR /usr/src
COPY ashmem_linux ashmem_linux
RUN make -C ashmem_linux
COPY binder_linux binder_linux
RUN make -C binder_linux


# install headers and modules
#RUN make -j $(nproc) KCONFIG_CONFIG=Microsoft/config-wsl headers_install
#RUN make -j $(nproc) KCONFIG_CONFIG=Microsoft/config-wsl modules_install