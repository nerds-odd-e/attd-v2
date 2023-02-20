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
#FROM alpine:3.13
FROM ghcr.io/aind-containers/aind:v0.0.5

#RUN apt-get update && \
#  apt-get install -qq -y --no-install-recommends \
#  dkms

#WORKDIR /
#RUN mkdir -p /anbox

#COPY module /usr/src/anbox-ashmem-1
COPY --from=build /usr/src/ashmem_linux/ashmem_linux.ko /lib/modules/5.15.49-linuxkit/ashmem_linux.ko
COPY --from=build /usr/src/binder_linux/binder_linux.ko /lib/modules/5.15.49-linuxkit/binder_linux.ko
#COPY anbox.conf /anbox/anbox.conf
#COPY 99-anbox.rules /anbox/99-anbox.rules
#WORKDIR /anbox

#RUN cp anbox.conf /etc/modules-load.d/ && \
#    cp 99-anbox.rules /lib/udev/rules.d/ && \
#    ls -l /usr/src
    # Then copy the module sources to /usr/src/:
#    cp -rT ashmem /usr/src/anbox-ashmem-1 && \
#RUN dkms mktarball --binaries-only anbox-ashmem/1 /usr/src/anbox-ashmem-1
#RUN insmod /anbox/ashmem_linux.ko && \
RUN depmod -a && \
    modprobe ashmem_linux && \
#    echo 1 > /proc/sys/kernel/sysrq && \
#    echo x > /proc/sysrq-trigger && \
#    modprobe binder_linux && \
    lsmod | grep -e ashmem_linux -e binder_linux
#    ls -alh /dev/ashmem

CMD ["/home/user/docker-2ndboot.sh"]