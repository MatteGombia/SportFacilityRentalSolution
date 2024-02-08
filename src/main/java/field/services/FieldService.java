package field.services;

import field.models.Field;
import field.models.FieldRequest;

import java.util.List;

public interface FieldService {
    Field saveField(Field field);
    Field getFieldById(Long id);
    List<Field> getAllFields();
    Field updateField(Long id, FieldRequest field);
    void deleteFieldById(Long id);
}
