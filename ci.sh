cd $1

ls

IP=$(hostname -i)

env/setup_host.sh $IP

#apk add --no-cache bash build-base nss libffi-dev openssl-dev npm openjdk11 && \
#cd frontend && npm install && ( npm run serve & ) && \


