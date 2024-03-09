ccflags-y += -I$(src) -Wno-int-conversion -DCONFIG_ANDROID_BINDER_DEVICES="\"binder\"" -DCONFIG_ANDROID_BINDERFS="y"
obj-m := binder_linux.o
binder_linux-y := deps.o binder.o binder_alloc.o binderfs.o

#KERNEL_SRC ?= /lib/modules/$(shell uname -r)/build
KERNEL_SRC ?= /lib/modules/5.15.90.1-microsoft-standard-WSL2/build

all:
	$(MAKE) -C $(KERNEL_SRC) V=0 M=$$PWD

install:
	cp binder_linux.ko $(DESTDIR)/

clean:
	rm -rf *.o *.ko *.mod.c *.symvers *.order .*.cmd .tmp_versions
