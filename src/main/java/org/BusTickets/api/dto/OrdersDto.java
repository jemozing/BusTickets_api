package org.BusTickets.api.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import java.sql.Time;
import java.time.Duration;
import java.util.Date;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum OrdersDto {;
    interface orderId { long getOrderId();}
    interface tripId { long getTripId();}
    interface date { Date getDate();}
    interface passengers { PassengersDto.Passenger[] getPassengers();}
    interface busName { String getBusName(); }
    interface fromStation { String getFromStation(); }
    interface toStation { String getToStation(); }
    interface start { Time getStart(); }
    interface duration { Duration getDuration(); }
    interface price { double getPrice(); }
    interface totalPrice{double getTotalPrice();}

    public enum Request{;
        @Builder @Value public static class BookingTickets implements tripId,date,passengers{
            long tripId;
            Date date;
            PassengersDto.Passenger[] passengers;
        }
    }
    public enum Response{;
        @Builder
        @Value public static class BookingTickets implements orderId,tripId,fromStation,toStation,busName,date,start,duration,price,totalPrice,passengers{
            long orderId;
            long tripId;
            String fromStation;
            String toStation;
            String busName;
            Time start;
            Duration duration;
            double price;
            double totalPrice;
            Date date;
            PassengersDto.Passenger[] passengers;
        }
    }
}
