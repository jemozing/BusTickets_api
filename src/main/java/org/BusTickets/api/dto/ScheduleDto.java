package org.BusTickets.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ScheduleDto {;
    interface fromDate { Date getFromDate(); }
    interface toDate { Date getToDate(); }
    interface period { String getPeriod(); }
    interface dates { List<Date> getDates(); }

    public enum Request{;
        @Value @Builder public static class Schedule implements fromDate,toDate,period {
            @JsonProperty("fromDate")
            Date fromDate;
            @JsonProperty("toDate")
            Date toDate;
            @JsonProperty("period")
            String period;
        }
        @Value @Builder public static class Dates implements dates {
            @JsonProperty("dates")
            List<Date> dates;
        }
    }
}
