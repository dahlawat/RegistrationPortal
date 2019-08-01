package app;

import app.persistence.entities.Address;
import app.persistence.entities.Student;
import app.persistence.repositories.StudentRepository;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

@EnableJpaRepositories("app.persistence.repositories")
@EntityScan("app/persistence/entities")
@SpringBootApplication
public class Application {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * A CommandLineRunner to load few entities as test inputs initially in the database
     *
     * @param studentRepository
     * @return
     */
    @Bean
    CommandLineRunner init(StudentRepository studentRepository) {
        return args -> {
            // Add some sample students already
            Stream.of("John", "Julie", "Jennifer", "Helen", "Rachel").forEach(name -> {
                Student student = new Student(name, name.toLowerCase() + "_1234", LocalDate.now().minusYears(20),
                        new Address("1234", "Meadow Drive", "1186 we"));
                studentRepository.save(student);
            });
            studentRepository.findAll().forEach(System.out::println);
        };
    }

    /**
     * Mapping the ObjectMapper to stick to the new (de)serialization classes for all LocalDate based inputs
     *
     * @return
     */
    @Bean
    @Primary
    public ObjectMapper serializingObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer());
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        objectMapper.registerModule(javaTimeModule);
        return objectMapper;
    }

    /**
     * Custom Local Date Serializer
     */
    class LocalDateSerializer extends JsonSerializer<LocalDate> {

        @Override
        public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(value.format(FORMATTER));
        }
    }

    /**
     * Custom Local Date Deserializer
     */
    class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

        @Override
        public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return LocalDate.parse(p.getValueAsString(), FORMATTER);
        }
    }

}