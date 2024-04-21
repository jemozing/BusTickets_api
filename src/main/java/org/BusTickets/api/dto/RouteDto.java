package org.BusTickets.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import java.sql.Time;
import java.time.Duration;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum RouteDto {;
    interface routeId { Long getRouteId(); }
    interface busName { String getBusName(); }
    interface fromStation { String getFromStation(); }
    interface toStation { String getToStation(); }
    interface start { Time getStart(); }
    interface duration { Duration getDuration(); }
    interface price { double getPrice(); }
    interface schedule { ScheduleDto.Request.Schedule getSchedule(); }
    interface dates { ScheduleDto.Request.Dates getDates(); }
    interface bus { BusDto.Response.Information getBus(); }
    interface status { boolean isStatus(); }
    public enum Request {;
        @Value @Builder
        public static class Create implements busName,fromStation,toStation,start,duration,price,schedule,dates {
            @JsonProperty("busName")
            String busName;
            @JsonProperty("fromStation")
            String fromStation;
            @JsonProperty("toStation")
            String toStation;
            @JsonProperty("start")
            Time start;
            @JsonProperty("duration")
            Duration duration;
            @JsonProperty("price")
            double price;
            @JsonProperty("schedule")
            ScheduleDto.Request.Schedule schedule;
            ScheduleDto.Request.Dates dates;
        }
        @Value @Builder
        public static class Editing implements busName,fromStation,toStation,start,duration,price,schedule,dates {
            @JsonProperty("busName")
            String busName;
            @JsonProperty("fromStation")
            String fromStation;
            @JsonProperty("toStation")
            String toStation;
            @JsonProperty("start")
            Time start;
            @JsonProperty("duration")
            Duration duration;
            @JsonProperty("price")
            double price;
            @JsonProperty("schedule")
            ScheduleDto.Request.Schedule schedule;
            ScheduleDto.Request.Dates dates;
        }
    }
    public enum Response{;
        @Value @Builder
        public static class Create implements routeId,fromStation,toStation,start,duration,price,bus,status,schedule,dates {
            @JsonProperty("tripId")
            Long routeId;
            @JsonProperty("fromStation")
            String fromStation;
            @JsonProperty("toStation")
            String toStation;
            @JsonProperty("start")
            Time start;
            @JsonProperty("duration")
            Duration duration;
            @JsonProperty("price")
            double price;
            @JsonProperty("bus")
            BusDto.Response.Information bus;
            @JsonProperty("approved")
            boolean status;
            @JsonProperty("schedule")
            ScheduleDto.Request.Schedule schedule;
            ScheduleDto.Request.Dates dates;
        }
        @Value @Builder
        public static class Editing implements routeId,fromStation,toStation,start,duration,price,bus,status,schedule,dates {
            @JsonProperty("tripId")
            Long routeId;
            @JsonProperty("fromStation")
            String fromStation;
            @JsonProperty("toStation")
            String toStation;
            @JsonProperty("start")
            Time start;
            @JsonProperty("duration")
            Duration duration;
            @JsonProperty("price")
            double price;
            @JsonProperty("bus")
            BusDto.Response.Information bus;
            @JsonProperty("approved")
            boolean status;
            @JsonProperty("schedule")
            ScheduleDto.Request.Schedule schedule;
            ScheduleDto.Request.Dates dates;
        }
        @Value @Builder
        public static class Information implements routeId,fromStation,toStation,start,duration,price,bus,status,schedule,dates {
            @JsonProperty("tripId")
            Long routeId;
            @JsonProperty("fromStation")
            String fromStation;
            @JsonProperty("toStation")
            String toStation;
            @JsonProperty("start")
            Time start;
            @JsonProperty("duration")
            Duration duration;
            @JsonProperty("price")
            double price;
            @JsonProperty("bus")
            BusDto.Response.Information bus;
            @JsonProperty("approved")
            boolean status;
            @JsonProperty("schedule")
            ScheduleDto.Request.Schedule schedule;
            ScheduleDto.Request.Dates dates;
        }
        @Value @Builder
        public static class Approval implements routeId,fromStation,toStation,start,duration,price,bus,status,schedule,dates {
            @JsonProperty("tripId")
            Long routeId;
            @JsonProperty("fromStation")
            String fromStation;
            @JsonProperty("toStation")
            String toStation;
            @JsonProperty("start")
            Time start;
            @JsonProperty("duration")
            Duration duration;
            @JsonProperty("price")
            double price;
            @JsonProperty("bus")
            BusDto.Response.Information bus;
            @JsonProperty("approved")
            boolean status;
            @JsonProperty("schedule")
            ScheduleDto.Request.Schedule schedule;
            ScheduleDto.Request.Dates dates;
        }
    }
}
