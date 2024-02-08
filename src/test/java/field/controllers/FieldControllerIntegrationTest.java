package field.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import field.models.Field;
import field.models.FieldRequest;
import field.models.FieldResponse;
import field.proxy.ProxyFieldService;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class FieldControllerIntegrationTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private ProxyFieldService fieldMockService;

    @Test
    public void testCreateValidField() throws Exception {

        FieldRequest fieldRequest = new FieldRequest("Football field", 20.50, 400, 45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");
        FieldResponse expectedFieldResponse =new FieldResponse(1L, "Football field", 20.50, 400,
                45, "Mosta, Brown street 23", "Keys to the field are at watchman post");


        String expectedResponseBody = om.writeValueAsString(expectedFieldResponse);

        String endpoint = "/fields";

        Field serviceField = new Field(1L, "Football field", 20.50, 400, 45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");


        when(fieldMockService.saveField(any(Field.class))).thenReturn(serviceField);

        ResponseEntity<String> responseEntity =
                testRestTemplate.postForEntity(endpoint, fieldRequest, String.class);

        verify(fieldMockService, times(1)).saveField(any(Field.class));

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        JSONAssert.assertEquals(expectedResponseBody, responseEntity.getBody(), true);
    }

    @Test
    public void testGetOneValidField() throws JsonProcessingException {
        FieldResponse expectedFieldResponse =new FieldResponse(1L, "Football field", 20.50, 400, 45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");

        String expectedResponseBody = om.writeValueAsString(expectedFieldResponse);

        Long fieldId = 1L;

        String endpoint = "/fields/" + fieldId;

        Field serviceField = new Field(1L, "Football field", 20.50, 400,45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");

        when(fieldMockService.getFieldById(any(Long.class))).thenReturn(serviceField);

        ResponseEntity<String> responseEntity =
                testRestTemplate.getForEntity(endpoint, String.class);

        verify(fieldMockService, times(1)).getFieldById(any(Long.class));

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertThat(responseEntity.getBody()).isEqualTo(expectedResponseBody);
    }

    @Test
    public void testGetAllValidFields() throws JsonProcessingException {

        List<Field> mockFields = new ArrayList<>();

        String endpoint = "/fields";

        Field mockField_1 = new Field(1L, "Football field", 20.50, 400,45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");
        Field mockField_2 = new Field(2L, "Basketball field", 20.50, 400,40,
                "Mosta, Brown street 22", "Keys to the field are at watchman post");

        List<String> fields = new ArrayList<>();

        mockFields.add(mockField_1);
        mockFields.add(mockField_2);

        for(Field field : mockFields) {
            fields.add(om.writeValueAsString(field));
        }

        when(fieldMockService.getAllFields()).thenReturn(mockFields);

        ResponseEntity<List<Field>> responseEntity = testRestTemplate.exchange(
                endpoint,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Field>>() {});


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<String> responseFields = new ArrayList<>();

        for(Field field : responseEntity.getBody()) {
            responseFields.add(om.writeValueAsString(field));
        }

        assertThat(responseFields).isEqualTo(fields);
    }

    @Test
    public void testDeleteValidField() {
        Long fieldId = 1L;

        String endpoint = "/fields/{id}";

        ResponseEntity<String> responseEntity = testRestTemplate.exchange(
                endpoint,
                HttpMethod.DELETE,
                null,
                String.class,
                fieldId);

        verify(fieldMockService, times(1)).deleteFieldById(any(Long.class));

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        assertNull(responseEntity.getBody());
    }

}
