package org.BusTickets.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ScheduleDto {;
    interface fromDate { LocalDate getFromDate(); }
    interface toDate { LocalDate getToDate(); }
    interface period { String getPeriod(); }


    public enum Request{;
        @Value @Builder public static class Schedule implements fromDate,toDate,period {
            @JsonProperty("fromDate")
            LocalDate fromDate;
            @JsonProperty("toDate")
            LocalDate toDate;
            @JsonProperty("period")
            String period;
        }
    }
}
