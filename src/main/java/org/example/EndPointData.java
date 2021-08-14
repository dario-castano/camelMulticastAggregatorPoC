package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EndPointData {

    @JsonProperty("name")
    private String name;

    @JsonProperty("status")
    private String status;
}
