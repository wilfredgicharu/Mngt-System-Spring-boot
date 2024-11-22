package com.fred.schoolmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StreamDTO {
    private long streamId;
    private long streamCode;
    private String streamName;
}
