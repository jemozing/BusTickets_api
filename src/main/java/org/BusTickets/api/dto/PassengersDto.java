package org.BusTickets.api.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum PassengersDto {;
    interface firstName { String getFirstName();}
    interface lastName { String getLastName(); }
    interface passport {int getPassport();}

        @Value public static class Passenger implements firstName,lastName,passport{
            String firstName;
            String lastName;
            int passport;
        }

}
