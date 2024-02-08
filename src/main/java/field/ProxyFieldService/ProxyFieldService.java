package field.ProxyFieldService;

import field.models.Field;
import field.models.FieldRequest;
import field.services.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class ProxyFieldService {

    @Autowired
    FieldService fieldService;

    public Field saveField(Field field) {
        if(field.getPrice() >= 0 || field.getMaintenance() >= 0 || field.getMaxCapacity() >= 0) {
            return fieldService.saveField(field);
        }
        throw new IllegalArgumentException("price, maintenance and capacity can't be a negative value");
    }
    public Field getFieldById(Long id) {
        return fieldService.getFieldById(id);
    }
    public List<Field> getAllFields() {
        return fieldService.getAllFields();
    }
    public Field updateField(Long id, FieldRequest field) {
        if(field.getPrice() >= 0 || field.getMaintenance() >= 0 || field.getMaxCapacity() >= 0) {
            return fieldService.updateField(id, field);
        }
        throw new IllegalArgumentException("price, maintenance and capacity can't be a negative value");
    }
    public void deleteFieldById(Long id) {
        fieldService.deleteFieldById(id);
    }
}
