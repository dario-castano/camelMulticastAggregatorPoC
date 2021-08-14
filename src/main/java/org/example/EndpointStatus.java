package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class EndpointStatus {

    @JsonProperty("data")
    private List<EndPointData> statusList;

    public void add(EndPointData data) {
        if (statusList == null) {
            statusList = new ArrayList<EndPointData>();
        }
        statusList.add(data);
    }
}
