package field.proxy;

import field.models.Field;
import field.models.FieldRequest;
import field.services.FieldServiceImpl;

import java.util.List;

public class ProxyFieldService implements field.services.ProxyFieldService {

    @Override
    public Field saveField(Field field) {

        FieldServiceImpl realService = new FieldServiceImpl();

        if ( field.getPrice() >= 0 || field.getMaintenance() >= 0 || field.getMaxCapacity() >= 0 ) {
            return realService.saveField(field);
        }
        return null;
    }

    @Override
    public Field getFieldById(Long id) {
        FieldServiceImpl realService = new FieldServiceImpl();

        return realService.getFieldById(id);
    }

    @Override
    public List<Field> getAllFields() {
        FieldServiceImpl realService = new FieldServiceImpl();

        return realService.getAllFields();
    }

    @Override
    public Field updateField(Long id, FieldRequest field) {
        FieldServiceImpl realService = new FieldServiceImpl();

        if( field.getPrice() >= 0 || field.getMaintenance() >= 0 || field.getMaxCapacity() >= 0 ) {
            return realService.updateField(id, field);
        }
        return null;
    }

    @Override
    public void deleteFieldById(Long id) {

        FieldServiceImpl realService = new FieldServiceImpl();

        realService.deleteFieldById(id);
    }
}
