package field.proxy;

import field.ProxyFieldService.ProxyFieldService;
import field.models.Field;
import field.models.FieldEntity;
import field.models.FieldRequest;
import field.services.FieldService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class ProxyFieldServiceTest {

    @Autowired
    ProxyFieldService proxyFieldService;
    @MockBean
    FieldService fieldService;

    @Test
    public void testSaveValidField() {
        Field fieldToBeSaved = new Field("Football field", 20.50, 40,45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");
        Field expectedSavedField = new Field(1L,"Football field", 20.50, 40,45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");

        when(fieldService.saveField(any(Field.class))).thenReturn(expectedSavedField);

        Field savedField = proxyFieldService.saveField(fieldToBeSaved);

        assertThat(savedField).isEqualToComparingFieldByField(expectedSavedField);

        verify(fieldService, times(1)).saveField(any(Field.class));
    }

    

    @Test
    public void testFindValidFieldById() {
        Field expectedField = new Field(1L,"Football field", 20.50, 40,45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");

        Field outputField = new Field(1L,"Football field", 20.50, 40,45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");

        when(fieldService.getFieldById(any(Long.class))).thenReturn(outputField);

        Field field = proxyFieldService.getFieldById(1L);

        assertThat(field).isEqualToComparingFieldByField(expectedField);

        verify(fieldService, times(1)).getFieldById(any(Long.class));
    }

    @Test
    public void testFindAllValidFields() {
        Field field_1 = new Field(1L, "Football field", 20.50, 40,45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");
        Field field_2 = new Field(2L, "Basketball field", 20.50, 40,40,
                "Mosta, Brown street 22", "Keys to the field are at watchman post");

        List<Field> outputFields  = new ArrayList<>();

        outputFields.add(field_1);
        outputFields.add(field_2);

        when(fieldService.getAllFields()).thenReturn(outputFields);

        List<Field> proxyFields = proxyFieldService.getAllFields();

        for(int i = 0; i < proxyFields.size(); i++) {
            assertThat(proxyFields.get(i)).isEqualToComparingFieldByField(outputFields.get(i));
        }

        verify(fieldService, times(1)).getAllFields();
    }

    @Test
    public void testUpdateValidFieldById() {
        FieldRequest fieldRequest = new FieldRequest("Football field", 20.50, 40,45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");
        Field expectedField = new Field(1L, "Football field", 20.50, 40,45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");
        Field outputField = new Field(1L, "Football field", 20.50, 40,45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");

        when(fieldService.updateField(any(Long.class), any(FieldRequest.class))).thenReturn(outputField);

        Field updatedField = proxyFieldService.updateField(1L, fieldRequest);

        verify(fieldService, times(1)).updateField(any(Long.class), any(FieldRequest.class));

        assertThat(updatedField).isEqualToComparingFieldByField(expectedField);
    }

    @Test
    public void testValidDeleteFieldById() {

        Long fieldId = 1L;

        doNothing().when(fieldService).deleteFieldById(any(Long.class));

        proxyFieldService.deleteFieldById(fieldId);

        verify(fieldService, times(1)).deleteFieldById(any(Long.class));
    }

}
