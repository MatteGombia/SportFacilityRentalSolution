package field.services;

import field.models.Field;
import field.models.FieldRequest;

import java.util.List;

public interface ProxyFieldService {

    //Interact with the repository (database) to perform
    //CRUD operations on fields.

    //Convert between different data representations, if necessary.

    //Handle exceptions and errors that may occur during business logic execution or data access.
    //Convert exceptions into meaningful error messages for clients.

    Field saveField(Field field);
    Field getFieldById(Long id);
    List<Field> getAllFields();
    Field updateField(Long id, FieldRequest field);
    void deleteFieldById(Long id);
}
