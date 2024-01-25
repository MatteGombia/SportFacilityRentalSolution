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

    //Receive and handle incoming HTTP requests from clients.
    //Extract data from the request (path variables, query parameters, request body).

    //Validate incoming data before passing it to the service layer.
    //Ensure that the request data meets the required criteria.

    //Create and send appropriate HTTP responses to clients.
    //Convert data from the service layer (entities or DTOs) into a format suitable for the client (e.g., JSON).

    //Catch and handle exceptions thrown by the service layer.
    //Convert exceptions into meaningful error responses to be sent to clients.

    FieldResponse allFields(@RequestBody FieldRequest fieldRequest) {
        return null;
    }
    @PostMapping("/fields")
    @ResponseStatus(HttpStatus.CREATED)
    FieldResponse newField(@RequestBody FieldRequest fieldRequest) {
        return null;
    }

    FieldResponse findOne(@PathVariable Long id) {
        return null;
    }

    FieldResponse updateField(@RequestBody FieldRequest fieldRequest, @PathVariable Long id) {
        return null;
    }

    void deleteField(@PathVariable Long id) {
    }
}
