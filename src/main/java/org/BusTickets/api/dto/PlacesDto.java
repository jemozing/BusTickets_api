package org.BusTickets.api.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum PlacesDto {;
    interface orderId { int getOrderId();}
    interface firstName { String getFirstName();}
    interface lastName { String getLastName(); }
    interface passport {int getPassport();}
    interface place {int getPlace();}
    interface ticket {String getTicket();}
    interface placesBus {int[] getPlacesBus();}
    public enum Request{;
        @Value public static class ChoosePlace implements orderId,firstName,lastName,passport,place{
            int orderId;
            String firstName;
            String lastName;
            int passport;
            int place;
        }
    }
    public enum Response{;
        @Value public static class ChoosePlace implements orderId,firstName,lastName,passport,place,ticket{
            int orderId;
            String firstName;
            String lastName;
            int passport;
            int place;
            String ticket;
        }
        @Value public static class PlacesBus implements placesBus{
            int[] placesBus;
        }
    }
}
