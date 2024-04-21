package org.BusTickets.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ClientsDto {;
    interface Id {
        /**
        * Clients id in database
         */
        @Positive Long getId();}
    interface firstName { @NotBlank String getFirstName(); }
    interface lastName { @NotBlank String getLastName(); }
    interface login { @NotBlank String getLogin(); }
    interface password { @NotBlank String getPassword(); }
    interface passwordHash {@NotBlank String getPasswordHash();}
    interface oldPassword{ @NotBlank String getOldPassword();}
    interface newPassword{ @NotBlank String getNewPassword();}
    interface  patronymic { String getPatronymic(); }
    interface email { @Email(message = "Email введен неправильно") @NotBlank String getEmail(); }
    interface phone { @NotBlank String getPhone(); }
    public enum Request{;
        @Schema(name = "Registration ClientDTO Request",description = "")
        @Value @Builder
        public static class Registration implements firstName,lastName,patronymic,email,phone,login,password{
            @JsonProperty("firstName")
            String firstName;
            @JsonProperty("lastName")
            String lastName;
            @JsonProperty("patronymic")
            String patronymic;
            @JsonProperty("email")
            String email;
            @JsonProperty("login")
            String login;
            @JsonProperty("password")
            String password;
            @JsonProperty("phone")
            String phone;
        }
        @Schema(name = "Editing ClientDTO Request",description = "")
        @Value @Builder public static class Editing implements firstName, lastName, patronymic, email, phone, oldPassword, newPassword {
            @JsonProperty("firstName")
            String firstName;
            @JsonProperty("lastName")
            String lastName;
            @JsonProperty("patronymic")
            String patronymic;
            @JsonProperty("email")
            String email;
            @JsonProperty("phone")
            String phone;
            @JsonProperty("oldPassword")
            String oldPassword;
            @JsonProperty("newPassword")
            String newPassword;
        }
    }
    public enum Response{;
        @Schema(name = "Registration ClientDTO Response",description = "")
        @Value @Builder
        public static class Registration implements Id,firstName,lastName,patronymic,email,phone{
            @JsonProperty("id")
            Long id;
            @JsonProperty("firstName")
            String firstName;
            @JsonProperty("lastName")
            String lastName;
            @JsonProperty("patronymic")
            String patronymic;
            @JsonProperty("email")
            String email;
            @JsonProperty("phone")
            String phone;
            @JsonProperty("userType")
            String userType;
        }
        @Schema(name = "Information ClientDTO Response",description = "")
        @Value @Builder
        public static class Information implements Id,firstName,lastName,patronymic,email,phone{
            @JsonProperty("id")
            Long id;
            @JsonProperty("firstName")
            String firstName;
            @JsonProperty("lastName")
            String lastName;
            @JsonProperty("patronymic")
            String patronymic;
            @JsonProperty("email")
            String email;
            @JsonProperty("phone")
            String phone;
            @JsonProperty("userType")
            String userType;
        }
        @Schema(name = "Editing ClientDTO Response",description = "")
        @Value @Builder public static class Editing implements firstName, lastName, patronymic, email, phone {
            @JsonProperty("firstName")
            String firstName;
            @JsonProperty("lastName")
            String lastName;
            @JsonProperty("patronymic")
            String patronymic;
            @JsonProperty("email")
            String email;
            @JsonProperty("phone")
            String phone;
            @JsonProperty("userType")
            String userType;
        }
    }

}
