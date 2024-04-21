package org.BusTickets.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum BusDto {;
    interface busNumber { String getBusNumber(); }
    interface busName{ String getBusName(); }
    interface busRange{ float getBusRange(); }
    interface numOfSeats{ int getNumOfSeats(); }
    public enum Requests{;
        @Value @Builder
        public static class Create implements BusDto.busNumber, busName, numOfSeats, busRange{
            @JsonProperty("busBrand")
            String busNumber;
            @JsonProperty("busName")
            String busName;
            @JsonProperty("placeCount")
            int numOfSeats;
            @JsonProperty("range")
            float busRange;
        }
    }
    public enum Response{;
        @Value @Builder public static class Information implements BusDto.busNumber, busName, numOfSeats, busRange{
            @JsonProperty("busBrand")
            String busNumber;
            @JsonProperty("busName")
            String busName;
            @JsonProperty("placeCount")
            int numOfSeats;
            @JsonProperty("range")
            float busRange;
        }
        @Value @Builder public static class Create implements BusDto.busNumber, busName, numOfSeats, busRange{
            @JsonProperty("busBrand")
            String busNumber;
            @JsonProperty("busName")
            String busName;
            @JsonProperty("placeCount")
            int numOfSeats;
            @JsonProperty("range")
            float busRange;
        }
        @Value @Builder public static class InfoAboutBusBrands implements busNumber {
            @JsonProperty("busBrand")
            String busNumber;
            @JsonProperty("placeCount")
            int numOfSeats;
        }
    }

}
