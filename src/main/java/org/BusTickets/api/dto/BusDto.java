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
    interface busBrand{ String getBusBrand(); }
    interface busName{ String getBusName(); }
    interface busRange{ float getBusRange(); }
    interface numOfSeats{ int getNumOfSeats(); }
    public enum Requests{;

    }
    public enum Response{;
        @Value public static class Information implements busBrand, busName, numOfSeats, busRange{
            @JsonProperty("busBrand")
            String busBrand;
            @JsonProperty("busName")
            String busName;
            @JsonProperty("placeCount")
            int numOfSeats;
            @JsonProperty("range")
            float busRange;
        }
        @Value public static class InfoAboutBusBrands implements busBrand{
            @JsonProperty("busBrand")
            String busBrand;
            @JsonProperty("placeCount")
            int numOfSeats;
        }
    }

}
