package org.example;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

public class CustomAggregation implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (oldExchange == null) {
            return newExchange;
        }
        else {
            EndpointStatus newExchangeStatus = newExchange.getIn().getBody(EndpointStatus.class);
            EndpointStatus oldExchangeStatus = oldExchange.getIn().getBody(EndpointStatus.class);

            EndPointData newdata = newExchangeStatus.getStatusList().get(0);
            oldExchangeStatus.add(newdata);

            oldExchange.getMessage().setBody(oldExchangeStatus);

            return oldExchange;
        }
    }
}
