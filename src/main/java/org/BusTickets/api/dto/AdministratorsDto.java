package org.BusTickets.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum AdministratorsDto {;
    interface Id { @Positive Long getId();}
    interface firstName { @NotBlank String getFirstName(); }
    interface lastName { @NotBlank String getLastName(); }
    interface login { @NotBlank String getLogin(); }
    interface password { @NotBlank String getPassword(); }
    interface passwordHash {@NotBlank String getPasswordHash();}
    interface oldPassword{ @NotBlank String getOldPassword();}
    interface newPassword{ @NotBlank String getNewPassword();}
    interface  patronymic { String getPatronymic(); }
    interface position { @NotBlank String getPosition(); }

    public enum Response{;
        @Value public static class Registration implements Id, firstName, lastName,patronymic,position {
            @JsonProperty("id")
            Long Id;
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
        @Value public static class Information implements Id, firstName, lastName,patronymic,position {
            @JsonProperty("id")
            Long Id;
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
        @Value public static class Editing implements firstName,lastName,patronymic,position{
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
        @Value public static class Registration implements firstName, lastName, patronymic, position,login,password {
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
        @Value public static class Editing implements firstName,lastName,patronymic,position,oldPassword,newPassword{
            @JsonProperty("firstName")
            String firstName;
            @JsonProperty("lastName")
            String lastName;
            @JsonProperty("patronymic")
            String patronymic;
            @JsonProperty("position")
            String position;
            @JsonProperty("oldPassword")
            String oldPassword;
            @JsonProperty("newPassword")
            String newPassword;
        }
    }
}
