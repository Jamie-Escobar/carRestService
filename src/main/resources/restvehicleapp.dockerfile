FROM openjdk:11

LABEL author = "Jamie Sandison"

ENV JAVA_ENV = development

ENV PORT = 3000

COPY . /usr/src/myapp

WORKDIR /usr/src/myapp

RUN javac Main.java

CMD ["java", "Main"]
