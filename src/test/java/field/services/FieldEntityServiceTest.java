package field.services;

import field.models.Field;
import field.models.FieldEntity;
import field.repositories.FieldRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

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

        Field fieldToBeSaved = new Field("Football field", 20.50, 45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");
        Field expectedSavedField = new Field(1L,"Football field", 20.50, 45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");

        FieldEntity outputFieldEntity = new FieldEntity(1L,"Football field", 20.50, 45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");

        when(fieldMockRepository.save(any(FieldEntity.class))).thenReturn(outputFieldEntity);

        Field savedField = fieldService.saveField(fieldToBeSaved);

        assertThat(savedField).isEqualToComparingFieldByField(expectedSavedField);

        //sprawdzenie czy save zostało wywołane tylko i wyłacznie raz
        verify(fieldMockRepository, times(1)).save(any(FieldEntity.class));

    }

    @Test
    public void testDeleteField() {

    }

}
