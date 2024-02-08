package field.services;

import field.models.Field;
import field.models.FieldEntity;
import field.models.FieldRequest;
import field.repositories.FieldRepository;
import field.utils.FieldUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FieldServiceImpl implements ProxyFieldService {

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private FieldUtils fieldUtils;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Field saveField(Field field) {

        if(field.getPrice() <= 0 || field.getMaintenance() <= 0 || field.getMaxCapacity() <= 0) {
            throw new IllegalArgumentException("Price, maintenance, and maxCapacity must be non-negative values.");
        }

        FieldEntity fieldEntity = modelMapper.map(field, FieldEntity.class);

        fieldEntity = fieldRepository.save(fieldEntity);

        Field savedField = modelMapper.map(fieldEntity, Field.class);

        return savedField;
    }

    @Override
    public Field getFieldById(Long id) {
        FieldEntity fieldEntity = fieldRepository.getOne(id);

        Field field = modelMapper.map(fieldEntity, Field.class);

        return field;
    }

    @Override
    public List<Field> getAllFields() {
        List<Field> dbFields = new ArrayList<>();
        List<FieldEntity> fieldEntities = fieldRepository.findAll();

        for(int i = 0; i < fieldEntities.size(); i++) {
            FieldEntity fieldEntity = fieldEntities.get(i);
            dbFields.add(modelMapper.map(fieldEntity, Field.class));
        }
        return dbFields;
    }

    @Override
    public Field updateField(Long id, FieldRequest field) {
        Field dbField = getFieldById(id);

        dbField.setId(id);
        dbField.setName(field.getName());
        dbField.setPrice(field.getPrice());
        dbField.setMaintenance(field.getMaintenance());
        dbField.setMaxCapacity(field.getMaxCapacity());
        dbField.setLocation(field.getLocation());
        dbField.setDescription(field.getDescription());

        dbField = saveField(dbField);

        return dbField;
    }

    @Override
    public void deleteFieldById(Long id) {

        try {
            fieldRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {

            throw new EntityNotFoundException("Couldn't resolve ID: " + id);
        }
    }
}
