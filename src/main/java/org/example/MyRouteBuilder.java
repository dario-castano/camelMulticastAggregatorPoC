package org.example;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

/**
 * A Camel Java DSL Router
 */
public class MyRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

        // here is a sample which processes the input files
        // (leaving them in place - see the 'noop' flag)
        // then performs content based routing on the message using XPath
        from("timer:simple?repeatCount=1")
                .multicast(new CustomAggregation())
                .parallelProcessing()
                .parallelAggregate()
                .to("direct:fast", "direct:slow")
                .end()
                .log("${body}");

        from("direct:fast")
                .to("http:localhost:3001/fast")
                .process(exchange -> {
                    exchange.getMessage().setHeader("correlation_id", 1);
                })
                .unmarshal().json(JsonLibrary.Jackson, EndpointStatus.class)
                .log("${body} -> ${headers.correlation_id}")
                .end();

        from("direct:slow")
                .to("http:localhost:3001/slow")
                .process(exchange -> {
                    exchange.getMessage().setHeader("correlation_id", 1);
                })
                .unmarshal().json(JsonLibrary.Jackson, EndpointStatus.class)
                .log("${body} -> ${headers.correlation_id}")
                .end();
    }
}
