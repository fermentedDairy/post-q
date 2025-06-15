clean:
	./mvnw clean

build:
	./mvnw clean package

publishLocal:
	./mvnw clean install