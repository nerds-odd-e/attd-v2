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

CMD ["/home/user/docker-2ndboot.sh"]