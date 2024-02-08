package field.services;

import field.models.Field;
import field.models.FieldEntity;
import field.models.FieldRequest;
import field.proxy.ProxyFieldService;
import field.repositories.FieldRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import java.util.ArrayList;
import java.util.List;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@SpringBootTest
@ActiveProfiles("test")
public class FieldEntityServiceTest {

    @MockBean
    FieldRepository fieldMockRepository;

    @Autowired
    FieldService fieldService;

    @Autowired
    ModelMapper modelMapper;

    @Test
    public void testSaveField() {
        Field fieldToBeSaved = new Field("Football field", 20.50, 40,45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");
        Field expectedSavedField = new Field(1L,"Football field", 20.50, 40,45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");
        FieldEntity outputFieldEntity = new FieldEntity(1L,"Football field", 20.50, 40,
                45, "Mosta, Brown street 23", "Keys to the field are at watchman post");

        when(fieldMockRepository.save(any(FieldEntity.class))).thenReturn(outputFieldEntity);

        Field savedField = fieldService.saveField(fieldToBeSaved);

        assertThat(savedField).isEqualToComparingFieldByField(expectedSavedField);

        verify(fieldMockRepository, times(1)).save(any(FieldEntity.class));
    }

    @Test
    public void testFindFieldById() {

        Field expectedField = new Field(1L,"Football field", 20.50, 40,45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");

        FieldEntity outputFieldEntity = new FieldEntity(1L,"Football field", 20.50, 40,45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");

        when(fieldMockRepository.getOne(any(Long.class))).thenReturn(outputFieldEntity);

        Field dbField = fieldService.getFieldById(1L);

        assertThat(dbField).isEqualToComparingFieldByField(expectedField);

        verify(fieldMockRepository, times(1)).getOne(any(Long.class));
    }

    @Test
    public void testFindAllFields() {

        FieldEntity mockField_1 = new FieldEntity(1L, "Football field", 20.50, 40,45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");
        FieldEntity mockField_2 = new FieldEntity(2L, "Basketball field", 20.50, 40,40,
                "Mosta, Brown street 22", "Keys to the field are at watchman post");

        List<FieldEntity> outputFieldEntities = new ArrayList<>();

        outputFieldEntities.add(mockField_1);
        outputFieldEntities.add(mockField_2);

        when(fieldMockRepository.findAll()).thenReturn(outputFieldEntities);

        List<Field> dbFields = fieldService.getAllFields();

        for(int i = 0; i < dbFields.size(); i++) {
            assertThat(dbFields.get(i)).isEqualToComparingFieldByField(outputFieldEntities.get(i));
        }

        verify(fieldMockRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateFieldById() {
        FieldRequest fieldRequest = new FieldRequest("Football field", 20.50, 40,45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");
        Field expectedField = new Field(1L, "Football field", 20.50, 40,45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");
        FieldEntity outputField = new FieldEntity(1L, "Football field", 20.50, 40,45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");

        when(fieldMockRepository.getOne(any(Long.class))).thenReturn(outputField);
        when(fieldMockRepository.save(any(FieldEntity.class))).thenReturn(outputField);

        Field updatedField = fieldService.updateField(1L, fieldRequest);

        verify(fieldMockRepository, times(1)).save(any(FieldEntity.class));
        verify(fieldMockRepository, times(1)).getOne(any(Long.class));

        assertThat(updatedField).isEqualToComparingFieldByField(expectedField);
    }

    @Test
    public void testDeleteFieldById() {

        Long fieldId = 1L;

        doNothing().when(fieldMockRepository).deleteById(any(Long.class));

        fieldService.deleteFieldById(fieldId);

        verify(fieldMockRepository, times(1)).deleteById(any(Long.class));
    }

}
