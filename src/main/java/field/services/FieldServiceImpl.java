package field.services;

import field.models.Field;
import field.models.FieldEntity;
import field.repositories.FieldRepository;
import field.utils.FieldUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public Field getFieldById(Long id) {
        return null;
    }

    @Override
    public List<Field> getAllFields() {
        return null;
    }

    @Override
    public void deleteFieldById(Long id) {
    }
}
