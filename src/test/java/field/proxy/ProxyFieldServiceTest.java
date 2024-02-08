package field.proxy;

import field.models.Field;
import field.models.FieldRequest;
import field.models.FieldResponse;
import field.models.FieldEntity;
import field.proxy.ProxyFieldService;
import field.repositories.FieldRepository;
import field.services.FieldService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class ProxyFieldServiceTest {
    @Autowired
    ProxyFieldService proxyFieldService;

    @MockBean
    FieldService realFieldService;




    @Test
    public void testSaveField_WithValidField() {
        Field fieldToBeSaved = new Field("Football field", 20.50, 40, 45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");
        Field expectedSavedField = new Field(1L, "Football field", 20.50, 40, 45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");

        when(realFieldService.saveField(any(Field.class))).thenReturn(expectedSavedField);

        Field savedField = proxyFieldService.saveField(fieldToBeSaved);

        assertThat(savedField).isEqualToComparingFieldByField(expectedSavedField);

        verify(realFieldService, times(1)).saveField(any(Field.class));
    }
}