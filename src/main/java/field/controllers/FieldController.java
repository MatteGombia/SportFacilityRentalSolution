package field.controllers;

import field.models.Field;
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
        FieldResponse fieldResponse = new FieldResponse(1L, "Football field", 20.50, 45,
                "Mosta, Brown street 23", "Keys to the field are at watchman post");
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
