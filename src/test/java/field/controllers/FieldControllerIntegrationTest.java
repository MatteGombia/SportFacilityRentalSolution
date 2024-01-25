package field.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import field.models.Field;
import field.models.FieldRequest;
import field.models.FieldResponse;
import field.services.FieldService;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class FieldControllerIntegrationTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private FieldService fieldMockService;

    @Test
    public void testCreateValidField() {
        FieldRequest fieldRequest = new FieldRequest("Football field", 20.50, 45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");
        FieldResponse expectedFieldResponse =new FieldResponse(1L, "Football field", 20.50, 45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");

        String endpoint = "/field";

        ResponseEntity<FieldResponse> responseEntity =
                testRestTemplate.postForEntity(endpoint, fieldRequest, FieldResponse.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
}
