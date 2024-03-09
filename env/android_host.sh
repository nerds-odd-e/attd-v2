function setup_host() {
	adb wait-for-device
	adb root
	adb remount
	rm -f ./hosts
	adb pull /system/etc/hosts
	cat hosts | grep -v 'host.docker.internal' > hosts.new
	getent hosts host.docker.internal >> hosts.new
	adb push ./hosts.new /etc/hosts
	adb push ./hosts.new /etc/system/hosts
	adb push ./hosts.new /system/etc/hosts
}

setup_host &
/usr/bin/supervisord --configuration /root/supervisord.conf

