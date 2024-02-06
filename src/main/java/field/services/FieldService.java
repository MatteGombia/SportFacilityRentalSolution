package field.services;

import field.models.Field;

import java.util.List;
import java.util.Optional;

public interface FieldService {

    //Interact with the repository (database) to perform
    //CRUD operations on fields.

    //Convert between different data representations, if necessary.

    //Handle exceptions and errors that may occur during business logic execution or data access.
    //Convert exceptions into meaningful error messages for clients.

    Field saveField(Field field);

    List<Field> getAllFields();

    Field getFieldById(Long id);

    void deleteFieldById(Long id);
}
