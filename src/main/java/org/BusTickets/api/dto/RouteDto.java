package org.BusTickets.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import org.BusTickets.api.json.DurationDeserializer;
import org.BusTickets.api.json.DurationSerializer;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum RouteDto {;
    interface routeId { @NotNull Long getRouteId(); }
    interface busName { @NotBlank String getBusName(); }
    interface fromStation { @NotBlank String getFromStation(); }
    interface toStation { @NotBlank String getToStation(); }
    interface start { @NotNull Time getStart(); }
    interface duration {@NotNull Duration getDuration(); }
    interface price {@NotNull @Positive double getPrice(); }
    interface schedule { ScheduleDto.Request.Schedule getSchedule(); }
    interface dates { List<LocalDate> getDates(); }
    interface bus {@NotBlank BusDto.Response.Information getBus(); }
    interface status {@NotNull boolean isStatus(); }
    public enum Request {;

        @Schema(name = "Create Trips Dto Request")
        @Value @Builder
        public static class Create implements busName,fromStation,toStation,start,duration,price,schedule,dates {
            @JsonProperty("busName")
            String busName;
            @JsonProperty("fromStation")
            String fromStation;
            @JsonProperty("toStation")
            String toStation;
            @JsonProperty("start")
            @Schema(defaultValue = "15:00:00")
            Time start;
            @JsonProperty("duration")
            @JsonDeserialize(using = DurationDeserializer.class)
            @Schema(defaultValue = "1:35")
            Duration duration;
            @JsonProperty("price")
            double price;
            @JsonProperty("schedule")
            ScheduleDto.Request.Schedule schedule;
            @JsonProperty("dates")
            @JsonSetter(nulls = Nulls.SKIP)
            List<LocalDate> dates = new ArrayList<>();
        }
        @Schema(name = "Editing Trips Dto Request")
        @Value @Builder
        public static class Editing implements busName,fromStation,toStation,start,duration,price,schedule,dates {
            @JsonProperty("busName")
            String busName;
            @JsonProperty("fromStation")
            String fromStation;
            @JsonProperty("toStation")
            String toStation;
            @JsonProperty("start")
            @Schema(defaultValue = "15:00:00")
            Time start;
            @JsonProperty("duration")
            @JsonDeserialize(using = DurationDeserializer.class)
            @Schema(defaultValue = "1:35")
            Duration duration;
            @JsonProperty("price")
            double price;
            @JsonProperty("schedule")
            ScheduleDto.Request.Schedule schedule;
            @JsonProperty("dates")
            @JsonSetter(nulls = Nulls.SKIP)
            List<LocalDate> dates = new ArrayList<>();
        }
    }
    public enum Response{;
        @Schema(name = "Create Trips Dto Response")
        @Value @Builder
        public static class Create implements routeId,fromStation,toStation,start,duration,price,bus,status,schedule,dates {
            @JsonProperty("tripId")
            Long routeId;
            @JsonProperty("fromStation")
            String fromStation;
            @JsonProperty("toStation")
            String toStation;
            @JsonProperty("start")
            @Schema(defaultValue = "15:00:00")
            Time start;
            @JsonProperty("duration")
            @JsonSerialize(using = DurationSerializer.class)
            @Schema(defaultValue = "1:35")
            Duration duration;
            @JsonProperty("price")
            double price;
            @JsonProperty("bus")
            BusDto.Response.Information bus;
            @JsonProperty("approved")
            boolean status;
            @JsonProperty("schedule")
            ScheduleDto.Request.Schedule schedule;
            @JsonProperty("dates")
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
            List<LocalDate> dates;
        }
        @Schema(name = "Editing Trips Dto Response")
        @Value @Builder
        public static class Editing implements routeId,fromStation,toStation,start,duration,price,bus,status,schedule,dates {
            @JsonProperty("tripId")
            Long routeId;
            @JsonProperty("fromStation")
            String fromStation;
            @JsonProperty("toStation")
            String toStation;
            @JsonProperty("start")
            @Schema(defaultValue = "15:00:00")
            Time start;
            @JsonProperty("duration")
            @JsonSerialize(using = DurationSerializer.class)
            @Schema(defaultValue = "1:35")
            Duration duration;
            @JsonProperty("price")
            double price;
            @JsonProperty("bus")
            BusDto.Response.Information bus;
            @JsonProperty("approved")
            boolean status;
            @JsonProperty("schedule")
            ScheduleDto.Request.Schedule schedule;
            @JsonProperty("dates")
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
            List<LocalDate> dates;
        }
        @Schema(name = "InformationForClients Trips Dto Response")
        @Value @Builder
        public static class InformationForClients implements routeId,fromStation,toStation,start,duration,price,bus,schedule,dates {
            @JsonProperty("tripId")
            Long routeId;
            @JsonProperty("fromStation")
            String fromStation;
            @JsonProperty("toStation")
            String toStation;
            @JsonProperty("start")
            @Schema(defaultValue = "15:00:00")
            Time start;
            @JsonProperty("duration")
            @JsonSerialize(using = DurationSerializer.class)
            @Schema(defaultValue = "1:35")
            Duration duration;
            @JsonProperty("price")
            double price;
            @JsonProperty("bus")
            BusDto.Response.Information bus;
            @JsonProperty("schedule")
            ScheduleDto.Request.Schedule schedule;
            @JsonProperty("dates")
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
            List<LocalDate> dates;
        }
        @Schema(name = "InformationForAdmins Trips Dto Response")
        @Value @Builder
        public static class InformationForAdmins implements routeId,fromStation,toStation,start,duration,price,bus,status,schedule,dates {
            @JsonProperty("tripId")
            Long routeId;
            @JsonProperty("fromStation")
            String fromStation;
            @JsonProperty("toStation")
            String toStation;
            @JsonProperty("start")
            @Schema(defaultValue = "15:00:00")
            Time start;
            @JsonProperty("duration")
            @JsonSerialize(using = DurationSerializer.class)
            @Schema(defaultValue = "1:35")
            Duration duration;
            @JsonProperty("price")
            double price;
            @JsonProperty("bus")
            BusDto.Response.Information bus;
            @JsonProperty("approved")
            boolean status;
            @JsonProperty("schedule")
            ScheduleDto.Request.Schedule schedule;
            @JsonProperty("dates")
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
            List<LocalDate> dates;
        }
    }
}
