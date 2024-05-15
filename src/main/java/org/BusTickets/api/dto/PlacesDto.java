package org.BusTickets.api.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum PlacesDto {;
    interface orderId { Long getOrderId();}
    interface firstName { String getFirstName();}
    interface lastName { String getLastName(); }
    interface passport {int getPassport();}
    interface place {int getPlace();}
    interface ticket {String getTicket();}
    interface placesBus {
        List<String> getPlacesBus();}
    public enum Request{;
        @Builder
        @Value public static class ChoosePlace implements orderId,firstName,lastName,passport,place{
            Long orderId;
            String firstName;
            String lastName;
            int passport;
            int place;
        }
    }
    public enum Response{;
        @Builder
        @Value public static class ChoosePlace implements orderId,firstName,lastName,passport,place,ticket{
            Long orderId;
            String firstName;
            String lastName;
            int passport;
            int place;
            String ticket;
        }
        @Builder
        @Value public static class PlacesBus implements placesBus{
            List<String> placesBus;
        }
    }
}
