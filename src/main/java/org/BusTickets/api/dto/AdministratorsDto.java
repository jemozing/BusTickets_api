package org.BusTickets.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * DTO for {@link org.BusTickets.store.entities.AdministratorsEntity}
 */
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum AdministratorsDto {;
    interface Id { @Positive Long getId();}
    interface firstName {
        /**
         * Administrator First Name
        **/
        @NotBlank String getFirstName();}
    interface lastName {
        /**
         * Administrator Last Name
         **/
        @NotBlank String getLastName(); }
    interface login {
        /**
         * Administrator Login/Username
         **/
        @NotBlank String getLogin(); }
    interface password { @NotBlank String getPassword(); }
    interface passwordHash {@NotBlank String getPasswordHash();}
    interface oldPassword{ @NotBlank String getOldPassword();}
    interface newPassword{ @NotBlank String getNewPassword();}
    interface  patronymic { String getPatronymic(); }
    interface position { @NotBlank String getPosition(); }
    interface userType {@NotBlank String getUserType();}

    public enum Response{;

        /**
         * DTO for {@link org.BusTickets.store.entities.AdministratorsEntity}
         * Accepts a registration request
         */
        @Schema(name = "RegistrationAdminDTO Response",description = "Response Dto to registration Admin")
        @Value @Builder
        public static class Registration implements Id, firstName, lastName,patronymic,position,userType {
            @Schema(description = "Admin id in DB")
            @JsonProperty("id")
            Long Id;
            @Schema(description = "Admin's First Name", defaultValue = "Ivan")
            @JsonProperty("firstName")
            String firstName;
            @JsonProperty("lastName")
            String lastName;
            @JsonProperty("patronymic")
            String patronymic;
            @JsonProperty("position")
            String position;
            @JsonProperty("userType")
            String userType;
        }
        /**
         * DTO for {@link org.BusTickets.store.entities.AdministratorsEntity}
         * Accepts a information request
         */
        @Schema(name = "InforamtionAboutAdminDTO Response", description = "Response Dto to get information about Admin")
        @Value @Builder
        public static class Information implements Id, firstName, lastName,patronymic,position,userType {
            @JsonProperty("id")
            Long Id;
            @Schema(description = "Admin's First Name", defaultValue = "Ivan")
            @JsonProperty("firstName")
            String firstName;
            @JsonProperty("lastName")
            String lastName;
            @JsonProperty("patronymic")
            String patronymic;
            @JsonProperty("position")
            String position;
            @JsonProperty("userType")
            String userType;
        }
        /**
         * DTO for {@link org.BusTickets.store.entities.AdministratorsEntity}
         * Accepts a editing request
         */
        @Schema(name = "EditingAdminDTO Response",description = "Response Dto to editing Admin data")
        @Value @Builder
        public static class Editing implements firstName,lastName,patronymic,position,userType{
            @Schema(description = "Admin's First Name", defaultValue = "Ivan")
            @JsonProperty("firstName")
            String firstName;
            @JsonProperty("lastName")
            String lastName;
            @JsonProperty("patronymic")
            String patronymic;
            @JsonProperty("position")
            String position;
            @JsonProperty("userType")
            String userType;
        }
    }
    public enum Request{;
        @Schema(name = "RegistrationAdminDTO Request",description = "Request Dto to registration Admin")
        @Value @Builder
        public static class Registration implements firstName, lastName, patronymic, position,login,password {
            @Schema(description = "Admin's First Name", defaultValue = "Ivan")
            @JsonProperty("firstName")
            String firstName;
            @JsonProperty("lastName")
            String lastName;
            @JsonProperty("patronymic")
            String patronymic;
            @JsonProperty("position")
            String position;
            @JsonProperty("login")
            String login;
            @JsonProperty("password")
            String password;
        }
        @Schema(name = "EditingAdminDTO Request",description = "Request Dto to editing Admin data")
        @Value @Builder
        public static class Editing implements firstName,lastName,patronymic,position,oldPassword,newPassword{
            @Schema(description = "Admin's First Name", defaultValue = "Ivan")
            @JsonProperty("firstName")
            String firstName;
            @Schema(description = "Admin's Last Name", defaultValue = "Ivanov")
            @JsonProperty("lastName")
            String lastName;
            @Schema(description = "Admin's Patronymic", defaultValue = "Ivanovich")
            @JsonProperty("patronymic")
            String patronymic;
            @Schema(description = "Position worker", defaultValue = "Admin")
            @JsonProperty("position")
            String position;
            @JsonProperty("oldPassword")
            String oldPassword;
            @JsonProperty("newPassword")
            String newPassword;
        }
    }
}
