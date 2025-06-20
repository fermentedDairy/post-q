### post-q
The article [Postgres is Too Good (And Why That's Actually a Problem)](https://dev.to/shayy/postgres-is-too-good-and-why-thats-actually-a-problem-4imc#:~:text=Postgres%20might%20be%20too%20good,than%20they%20need%20to%20be.) claims that postgres databases can be used to facilitate queue. Let's see how good it actually is.

This is not a replacement for ActiveMQ/RabbitMQ/any MQ/Kafka. 
Using the DB to facilitate queues would be terrible for inter-system comms and will be a nightmare to match their feature richness.
Spring integration already has a mode that allows for using the DB to mediate queues.

## Features
- [ ] Single Message Interface
  - [x] JSON Message (JSON text SerDe)
  - [x] MAP message (Key-value pairs (as a map))
  - [ ] Object Message (serialised java object in the body)
- [ ] Message processing in virtual threads
- [ ] Bulk message fetching
- [ ] ACID (message level)
- [ ] Micronaut support
- [ ] Spring support
- [ ] Define DB schema to be used for persistence
- [ ] Simply manage schema (not flyway, can interfere with app schema)