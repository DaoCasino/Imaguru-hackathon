FROM harisekhon/ubuntu-java:jdk8

WORKDIR /

RUN apt-get update -y \
    && apt-get install git -y \
    && git clone https://github.com/kuzma87/MBH-Hackathon.git \
    && cd MBH-Hackathon \
    && ./gradlew build -x test

ADD run-backend.sh /

EXPOSE 80

ENTRYPOINT ["/bin/sh"]

CMD ["./run-backend.sh"]
