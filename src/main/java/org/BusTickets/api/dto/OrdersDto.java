package org.BusTickets.api.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import java.sql.Time;
import java.time.Duration;
import java.util.Date;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum OrdersDto {;
    interface orderId { int getOrderId();}
    interface tripId { int getTripId();}
    interface date { Date getDate();}
    interface passengers { PassengersDto.Passenger[] getPassengers();}
    interface busName { String getBusName(); }
    interface fromStation { String getFromStation(); }
    interface toStation { String getToStation(); }
    interface start { Time getStart(); }
    interface duration { Duration getDuration(); }
    interface price { int getPrice(); }
    interface totalPrice{int getTotalPrice();}

    public enum Request{;
        @Value public static class BookingTickets implements tripId,date,passengers{
            int tripId;
            Date date;
            PassengersDto.Passenger[] passengers;
        }
    }
    public enum Response{;
        @Value public static class BookingTickets implements orderId,tripId,fromStation,toStation,busName,date,start,duration,price,totalPrice,passengers{
            int orderId;
            int tripId;
            String fromStation;
            String toStation;
            String busName;
            Time start;
            Duration duration;
            int price;
            int totalPrice;
            Date date;
            PassengersDto.Passenger[] passengers;
        }
    }
}
