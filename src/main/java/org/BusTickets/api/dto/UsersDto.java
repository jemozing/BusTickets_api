package org.BusTickets.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import org.BusTickets.store.entities.Role;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsersDto {
    interface login {
        @NotBlank(message = "Login cannot be blank")
        @NotNull(message = "Login cannot be null")
        String getLogin();
    }
    interface password{
        @NotBlank(message = "Password cannot be blank")
        @NotNull(message = "Password cannot be null")
        String getPassword();
    }
    interface userType{
        @NotBlank(message = "Password cannot be blank")
        @NotNull(message = "Password cannot be null")
        Role getUserType();
    }
    public enum Request{;
        @Value @Builder
        public static class SignIn implements login,password{
        String login;
        String password;
        }
        @Value @Builder
        public static class SignUp implements login,password,userType{
            String login;
            String password;
            Role userType;
        }
    }
    public enum Response{;

    }
}
