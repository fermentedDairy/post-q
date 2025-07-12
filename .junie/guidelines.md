# Post-Q Project Guidelines

## Build/Configuration Instructions

### Prerequisites
- Java 24 or higher
- Maven (or use the provided Maven wrapper)

### Build Commands
The project includes a Makefile with the following commands:

```bash
# Clean the project
make clean
# or directly
./mvnw clean

# Build the project (clean and package)
make build
# or directly
./mvnw clean package

# Install the project to local Maven repository
make install
# or directly
./mvnw clean install

# Run tests
make test
# or directly
./mvnw test
```

## Testing Information

### Running Tests
Tests can be run using the Maven wrapper:

```bash
# Run all tests
./mvnw test

# Run a specific test class
./mvnw test -Dtest=ClassName

# Run a specific test method
./mvnw test -Dtest=ClassName#methodName
```

### Writing Tests
The project uses JUnit Jupiter (JUnit 5) for testing. Follow these guidelines when writing tests:

1. **Test Structure**:
   - Use `@Test` annotation for test methods
   - Use `@DisplayName` to provide descriptive test names
   - Use `@ParameterizedTest` with `@MethodSource` for data-driven tests

2. **Assertions**:
   - Group related assertions using `assertAll` with a descriptive heading:

   ```java
   import static org.junit.jupiter.api.Assertions.assertAll;
   import static org.junit.jupiter.api.Assertions.assertEquals;
   import static org.junit.jupiter.api.Assertions.assertTrue;
   import static org.junit.jupiter.api.Assertions.assertNotNull;

   assertAll("Validation checks",
       () -> assertEquals(expected1, actual1, "First value should match"),
       () -> assertTrue(condition, "Condition should be true"),
       () -> assertNotNull(object, "Object should not be null")
   );
   ```

3. **Test Examples**:
   - For serialization/deserialization tests, see `DefaultJacksonStrategyTest` and `JavaSerializationStrategyTest`
   - For general object testing, see `ImmutableArrayTest`

## Additional Development Information

### Code Style
- The project uses Java 24 features
- Follow the existing code style in the project
- Use immutable objects where possible (see `ImmutableArray` class)
- Method parameters should always be final
- Local variables should be final unless required to not be final
- If the result of a method can be null, it should be returning an empty ```java.util.Optional``` instead of a null.
- Private members do not need comments.

### Serialization
The project provides two serialization strategies:
- `JavaSerializationStrategy` - Uses Java's built-in serialization
- `DefaultJacksonStrategy` - Uses Jackson for JSON serialization

When implementing serializable classes:
- Implement the `Serializable` interface when using java serialization
- Include a `serialVersionUID` for Java serialization when using java serialization
- For Jackson serialization, use appropriate annotations (`@JsonProperty`, etc.)

### Error Handling
- Use specific exception types for different error scenarios
- Include descriptive error messages
- Handle null values appropriately

## AI Assistant Guidelines

### Permissions
- The AI assistant is allowed to run tests automatically using the `run_test` command
- The AI assistant can suggest code changes and modifications to existing files

### Restrictions
- The AI assistant is NOT allowed to create new files without explicit authorization
- The AI assistant is NOT allowed to delete existing files without explicit authorization
- Any file creation or deletion operations require explicit user confirmation
