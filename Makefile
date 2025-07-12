clean:
	./mvnw clean

build:
	./mvnw clean package

install:
	./mvnw clean install

test:
	./mvnw test