package org.BusTickets.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsersDto {
    interface login {@NotBlank String getLogin(); }
    interface password{@NotBlank String getPassword();}
    public enum Request{;
        @Value @Builder
        public class SignIn implements login,password{
        String login;
        String password;
        }
    }
}
