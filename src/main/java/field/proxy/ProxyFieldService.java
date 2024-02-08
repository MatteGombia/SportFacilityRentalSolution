package field.proxy;

import field.models.Field;
import field.models.FieldRequest;
import field.services.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class ProxyFieldService implements FieldService {

    private final FieldService realService;

    @Autowired
    public ProxyFieldService(FieldService realService) {
        this.realService = realService;
    }

    @Override
    public Field saveField(Field field) {
        if (field.getPrice() >= 0 && field.getMaintenance() >= 0 && field.getMaxCapacity() >= 0) {
            return realService.saveField(field);
        }
        return null;
    }

    @Override
    public Field getFieldById(Long id) {
        return realService.getFieldById(id);
    }

    @Override
    public List<Field> getAllFields() {
        return realService.getAllFields();
    }

    @Override
    public Field updateField(Long id, FieldRequest field) {
        if (field.getPrice() >= 0 && field.getMaintenance() >= 0 && field.getMaxCapacity() >= 0) {
            return realService.updateField(id, field);
        }
        return null;
    }

    @Override
    public void deleteFieldById(Long id) {
        realService.deleteFieldById(id);
    }
}
