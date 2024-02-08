package field.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import field.ProxyFieldService.ProxyFieldService;
import field.models.Field;
import field.models.FieldRequest;
import field.models.FieldResponse;
import field.services.FieldService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import field.services.FieldServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FieldUpdateTest {
    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProxyFieldService fieldMockService;

    @Test
    public void testUpdateField() throws Exception {
        Long fieldId = 1L;

        String endpoint = "/fields/" + fieldId;

        FieldRequest fieldRequest = new FieldRequest("Football field", 20.50, 40,45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");
        Field expectedField = new Field(1L, "Football field", 20.50, 40,45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");

        String update = om.writeValueAsString(fieldRequest);

        Field serviceField = new Field(fieldId, "Football field", 20.50, 40,45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");

        when(fieldMockService.updateField(any(Long.class), any(FieldRequest.class))).thenReturn(serviceField);

        ResultActions result = mockMvc.perform(put(endpoint)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(update)
                        .accept(MediaType.APPLICATION_JSON_UTF8));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Football field"))
                .andExpect(jsonPath("$.price").value(20.50))
                .andExpect(jsonPath("$.maintenance").value(40))
                .andExpect(jsonPath("$.maxCapacity").value(45))
                .andExpect(jsonPath("$.location").value("Mosta, Brown street 23"))
                .andExpect(jsonPath("$.description").value("Keys to the field are at watchman post"));

        verify(fieldMockService, times(1)).updateField(any(Long.class), any(FieldRequest.class));
    }
}
