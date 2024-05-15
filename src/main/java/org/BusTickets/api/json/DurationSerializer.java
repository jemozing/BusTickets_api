package org.BusTickets.api.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import java.io.IOException;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

@JsonComponent
public class DurationSerializer extends JsonSerializer<Duration> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    //duration=PT1H35M
    @Override
    public void serialize(Duration duration, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String formattedDuration = duration.toString(); // Assuming duration is already in format PTxxHxxM
        String[] parts = formattedDuration.split("T");
        if (parts.length > 1) {
            String[] timeParts = parts[1].split("H");
            if (timeParts.length > 1) {
                String serializedDuration = timeParts[0] + ":" + timeParts[1].split("M")[0];
                jsonGenerator.writeString(serializedDuration);
            } else {
                // Handle invalid duration format
                jsonGenerator.writeString("");
            }
        } else {
            // Handle invalid duration format
            jsonGenerator.writeString("");
        }
    }
}
