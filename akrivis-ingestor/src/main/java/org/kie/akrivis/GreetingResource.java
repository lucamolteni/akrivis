package org.kie.akrivis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.logging.Logger;

@Path("/hello")
public class GreetingResource {

    private static final Logger LOG = Logger.getLogger(GreetingResource.class.getName());


    @Inject
    EntityManager em;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() throws JsonProcessingException {

        RawData rawData = em.find(RawData.class, 1L);

        LOG.info("RawData: " + rawData.data + " created at: " + rawData.createdAt.toString());

        ObjectMapper objectMapper = new ObjectMapper();

        // parse rawdata data into json using jackson
        JsonNode jsonNode = objectMapper.readTree(rawData.data);

        return "Hello " + jsonNode.get("name").asText();
    }
}
