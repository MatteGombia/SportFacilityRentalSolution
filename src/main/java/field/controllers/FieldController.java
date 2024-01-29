package field.controllers;

import field.models.Field;
import field.models.FieldEntity;
import field.models.FieldRequest;
import field.models.FieldResponse;
import field.services.FieldService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class FieldController {
    @Autowired
    FieldService fieldService;
    @Autowired
    ModelMapper modelMapper;

    FieldResponse allFields(@RequestBody FieldRequest fieldRequest) {
        return null;
    }
    @PostMapping("/fields")
    @ResponseStatus(HttpStatus.CREATED)
    FieldResponse newField(@RequestBody FieldRequest fieldRequest) {

        Field field = modelMapper.map(fieldRequest, Field.class);

        Field savedField = fieldService.saveField(field);

        FieldResponse fieldResponse = modelMapper.map(savedField, FieldResponse.class);

        return fieldResponse;
    }

    @GetMapping("/fields/{id}")
    @ResponseStatus(HttpStatus.OK)
    FieldResponse findOne(@PathVariable Long id) {
        return null;
    }

    
    FieldResponse updateField(@RequestBody FieldRequest fieldRequest, @PathVariable Long id) {
        return null;
    }

    @DeleteMapping("/fields/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteField(@PathVariable Long id) {
    }

}
