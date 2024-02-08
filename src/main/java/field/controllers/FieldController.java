package field.controllers;

import field.models.Field;
import field.models.FieldRequest;
import field.models.FieldResponse;
import field.services.ProxyFieldService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FieldController {
    @Autowired
    ProxyFieldService proxyFieldService;
    @Autowired
    ModelMapper modelMapper;

    //FieldResponse allFields(@RequestBody FieldRequest fieldRequest) { return null; }
    @PostMapping("/fields")
    @ResponseStatus(HttpStatus.CREATED)
    FieldResponse newField(@RequestBody FieldRequest fieldRequest) {

        Field field = modelMapper.map(fieldRequest, Field.class);

        Field savedField = proxyFieldService.saveField(field);

        FieldResponse fieldResponse = modelMapper.map(savedField, FieldResponse.class);

        return fieldResponse;
    }

    @GetMapping("/fields/{id}")
    @ResponseStatus(HttpStatus.OK)
    FieldResponse findOne(@PathVariable Long id) {
        Field dbField = proxyFieldService.getFieldById(id);

        FieldResponse fieldResponse = modelMapper.map(dbField, FieldResponse.class);

        return fieldResponse;
    }

    @GetMapping("/fields")
    @ResponseStatus(HttpStatus.OK)
    List<FieldResponse> FindAll() {
        List<FieldResponse> fieldResponses = new ArrayList<>();

        List<Field> fields = proxyFieldService.getAllFields();

        for(Field field : fields) {
            FieldResponse fieldResponse = modelMapper.map(field, FieldResponse.class);
            fieldResponses.add(fieldResponse);
        }
        return fieldResponses;
    }

    @PutMapping("/fields/{id}")
    @ResponseStatus(HttpStatus.OK)
    FieldResponse updateField(@RequestBody FieldRequest fieldRequest, @PathVariable Long id) {
        Field savedField = proxyFieldService.updateField(id, fieldRequest);

        FieldResponse fieldResponse = modelMapper.map(savedField, FieldResponse.class);

        return fieldResponse;
    }

    @DeleteMapping("/fields/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteField(@PathVariable Long id) {
        proxyFieldService.deleteFieldById(id);
    }

}
