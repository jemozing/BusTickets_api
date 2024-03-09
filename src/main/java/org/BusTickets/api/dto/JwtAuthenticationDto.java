package org.BusTickets.api.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtAuthenticationDto {
    interface token{@NotBlank String getToken();}
    public enum Response{;
        @Value @Builder
        public static class JwtToken implements token{
            String token;
        }
    }
}
