package field.services;

import field.models.Field;
import field.models.FieldEntity;
import field.repositories.FieldRepository;
import field.utils.FieldUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private FieldUtils fieldUtils;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Field saveField(Field field) {

        FieldEntity fieldEntity = modelMapper.map(field, FieldEntity.class);

        fieldEntity = fieldRepository.save(fieldEntity);

        Field savedField = modelMapper.map(fieldEntity, Field.class);

        return savedField;
    }

    @Override
    public Field getFieldById(Long id) {

        try {
            fieldRepository.getOne(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Couldn't resolve ID: " + id);
        }
        return null;
    }

    @Override
    public List<Field> getAllFields() {
        return null;
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
