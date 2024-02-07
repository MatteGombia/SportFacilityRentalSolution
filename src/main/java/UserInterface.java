import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.json.JSONObject;

public class UserInterface {
    @Autowired
    private static TestRestTemplate testRestTemplate;
    public static void main(String[] args) throws JSONException {

        /* User parameters */
        Long idUser;
        String fullNameUser;
        String phoneUser;
        String emailUser;

        /* FIELD */
        Long idField;
        String nameField;
        int maxCapacityField;
        double maintenanceField;
        double priceField;
        String locationField;
        String descriptionField;

        /* BOOKING */
        Long idBooking;
        Long userBooking;
        Long fieldBooking;
        int numPeopleBooking;
        LocalDate dateBooking;
        LocalTime timeStartBooking;
        LocalTime timeEndBooking;

        /* REPORT */
        Long idReport;
        Long userReport;
        Long fieldReport;
        /*
        double incomeReport;
        double TotMaintenanceReport;
        int days;
         */

        /* Interface Param */
        Scanner scanner = new Scanner(System.in);
        int choice;

        /* API Param */
        String endpoint;
        UserRequest userRequest;
        FieldRequest fieldRequest;
        BookingRequest bookingRequest;
        ResponseEntity<String> responseEntity;
        JSONObject jsonResponse;

        while (true){
            System.out.println("Choose what field you want to edit: \n1 - User\n2-Field\n3-Booking\n4-Report\n");
            try {
                choice = scanner.nextInt();
            }
            catch (Exception e){
                System.out.println("Error, make sure to enter only a number. " + e.getMessage());

                //avoid infinite loop
                choice = 0;
                scanner.nextLine();

                continue;
            }
            switch (choice) {
                case 1:
                    // USER
                    System.out.println("Choose what operation do: \n1 - Create a user\n2-See details of a user\n" +
                            "3-Read all the user\n4-Edit an existing user\n5-Delete a user\n");
                    try {
                        choice = scanner.nextInt();
                    }
                    catch (Exception e){
                        System.out.println("Error, make sure to enter only a number. " + e.getMessage());

                        //avoid infinite loop
                        choice = 0;
                        scanner.nextLine();

                        continue;
                    }
                    switch (choice) {
                        case 1:
                            //CREATE
                            System.out.println("Insert the full name");
                            fullNameUser = scanner.nextLine();
                            System.out.println("Insert the email");
                            emailUser = scanner.nextLine();
                            System.out.println("Insert the phone number");
                            phoneUser = scanner.nextLine();

                            endpoint = "/users";

                            userRequest = new UserRequest();
                            responseEntity =
                                    testRestTemplate.postForEntity(endpoint, userRequest, String.class);

                            if(responseEntity.getStatusCode() != HttpStatus.CREATED)
                                System.out.println("Error. Code returned: " + responseEntity.getStatusCode());

                            break;
                        case 2:
                            //READ
                            System.out.println("Insert the Id of the user");
                            idUser = scanner.nextLong();

                            endpoint = "/users/" + idUser;

                            responseEntity =
                                    testRestTemplate.getForEntity(endpoint, String.class);

                            if(responseEntity.getStatusCode() != HttpStatus.OK)
                                System.out.println("Error. Code returned: " + responseEntity.getStatusCode());
                            else
                                System.out.println(responseEntity.getBody());
                            break;
                        case 3:
                            //READ ALL
                            endpoint = "/users";

                            responseEntity =
                                    testRestTemplate.getForEntity(endpoint, String.class);

                            if(responseEntity.getStatusCode() != HttpStatus.OK)
                                System.out.println("Error. Code returned: " + responseEntity.getStatusCode());
                            else
                                System.out.println(responseEntity.getBody());

                            break;
                        case 4:
                            //EDIT
                            System.out.println("Insert the Id of the user");
                            idUser = scanner.nextLong();
                            System.out.println("Insert the full name");
                            fullNameUser = scanner.nextLine();
                            System.out.println("Insert the email");
                            emailUser = scanner.nextLine();
                            System.out.println("Insert the phone number");
                            phoneUser = scanner.nextLine();

                            endpoint = "/users/" + idUser;

                            testRestTemplate.put(endpoint, String.class);

                            break;
                        case 5:
                            //DELETE
                            System.out.println("Insert the Id of the user to eliminate");
                            idUser = scanner.nextLong();

                            endpoint = "/users/" + idUser;

                            testRestTemplate.delete(endpoint);

                            break;
                        default:
                            continue;
                    }
                    break;
                case 2:
                    // FIELD
                    System.out.println("Choose what operation do: \n1 - Create a field\n2-See details of a field\n" +
                            "3-Read all the field\n4-Edit an existing field\n5-Delete a field\n");
                    try {
                        choice = scanner.nextInt();
                    }
                    catch (Exception e){
                        System.out.println("Error, make sure to enter only a number. " + e.getMessage());

                        //avoid infinite loop
                        choice = 0;
                        scanner.nextLine();

                        continue;
                    }
                    switch (choice) {
                        case 1:
                            //CREATE
                            System.out.println("Insert the name of the field");
                            nameField = scanner.nextLine();
                            System.out.println("Insert the description");
                            descriptionField = scanner.nextLine();
                            System.out.println("Insert the location");
                            locationField = scanner.nextLine();
                            System.out.println("Insert the maximum capacity of the field");
                            maxCapacityField = scanner.nextInt();
                            System.out.println("Insert the daily maintenance");
                            maintenanceField = scanner.nextDouble();
                            System.out.println("Insert the hour cost");
                            priceField = scanner.nextDouble();

                            endpoint = "/fields";

                            fieldRequest = FieldRequest(nameField, priceField,maintenanceField,maxCapacityField, locationField, descriptionField);

                            responseEntity =
                                    testRestTemplate.postForEntity(endpoint, fieldRequest, String.class);

                            if(responseEntity.getStatusCode() != HttpStatus.CREATED)
                                System.out.println("Error. Code returned: " + responseEntity.getStatusCode());
                            break;
                        case 2:
                            //READ
                            System.out.println("Insert the Id of the field");
                            idField = scanner.nextLong();

                            endpoint = "/fields/" + idField;

                            responseEntity =
                                    testRestTemplate.getForEntity(endpoint, String.class);

                            if(responseEntity.getStatusCode() != HttpStatus.OK)
                                System.out.println("Error. Code returned: " + responseEntity.getStatusCode());
                            else
                                System.out.println(responseEntity.getBody());

                            break;
                        case 3:
                            //READ ALL
                            endpoint = "/fields";

                            responseEntity =
                                    testRestTemplate.getForEntity(endpoint, String.class);

                            if(responseEntity.getStatusCode() != HttpStatus.OK)
                                System.out.println("Error. Code returned: " + responseEntity.getStatusCode());
                            else
                                System.out.println(responseEntity.getBody());

                            break;
                        case 4:
                            //EDIT
                            System.out.println("Insert the Id of the field");
                            idField = scanner.nextLong();
                            System.out.println("Insert the name of the field");
                            nameField = scanner.nextLine();
                            System.out.println("Insert the description");
                            descriptionField = scanner.nextLine();
                            System.out.println("Insert the location");
                            locationField = scanner.nextLine();
                            System.out.println("Insert the maximum capacity of the field");
                            maxCapacityField = scanner.nextInt();
                            System.out.println("Insert the daily maintenance");
                            maintenanceField = scanner.nextDouble();
                            System.out.println("Insert the hour cost");
                            priceField = scanner.nextDouble();

                            endpoint = "/fields/" + idField;

                            testRestTemplate.put(endpoint, String.class);

                            break;
                        case 5:
                            //DELETE
                            System.out.println("Insert the Id of the field to eliminate");
                            idField = scanner.nextLong();

                            endpoint = "/fields/" + idField;

                            testRestTemplate.delete(endpoint);

                            break;
                        default:
                            continue;
                    }
                    break;
                case 3:
                    // BOOKING
                    System.out.println("Choose what operation do: \n1 - Book a field\n2-See details of a booking" +
                            "\n3-Read all the booking\n4-Read all the booking of a certain user\n5-Read all the booking" +
                            " of a certain field\n6-Edit an existing booking\n7-Delete a field\n");
                    try {
                        choice = scanner.nextInt();
                    }
                    catch (Exception e){
                        System.out.println("Error, make sure to enter only a number. " + e.getMessage());

                        //avoid infinite loop
                        choice = 0;
                        scanner.nextLine();

                        continue;
                    }
                    switch (choice) {
                        case 1:
                            //CREATE
                            System.out.println("Insert the Id of the user\n");
                            userBooking = scanner.nextLong();
                            System.out.println("Insert the Id of the field\n");
                            fieldBooking = scanner.nextLong();
                            System.out.println("Insert the number of the people\n");
                            numPeopleBooking = scanner.nextInt();
                            System.out.println("Insert the date [yyyy-mm-dd]\n");
                            dateBooking = LocalDate.parse(scanner.nextLine());
                            System.out.println("Insert the starting time [hh:mm:ss]\n");
                            timeStartBooking = LocalTime.parse(scanner.nextLine());
                            System.out.println("Insert the ending time [hh:mm:ss]\n");
                            timeEndBooking = LocalTime.parse(scanner.nextLine());

                            endpoint = "/booking";

                            bookingRequest = new BookingRequest(userBooking,fieldBooking, numPeopleBooking, dateBooking,timeStartBooking,timeEndBooking);

                            responseEntity =
                                    testRestTemplate.postForEntity(endpoint, bookingRequest, String.class);

                            if(responseEntity.getStatusCode() != HttpStatus.CREATED)
                                System.out.println("Error. Code returned: " + responseEntity.getStatusCode());
                            break;
                        case 2:
                            //READ
                            System.out.println("Insert the Id of the booking\n");
                            idBooking = scanner.nextLong();

                            endpoint = "/booking/" + idBooking;

                            responseEntity =
                                    testRestTemplate.getForEntity(endpoint, String.class);

                            if(responseEntity.getStatusCode() != HttpStatus.OK)
                                System.out.println("Error. Code returned: " + responseEntity.getStatusCode());
                            else
                                System.out.println(responseEntity.getBody());

                            break;
                        case 3:
                            //READ ALL
                            endpoint = "/booking";

                            responseEntity =
                                    testRestTemplate.getForEntity(endpoint, String.class);

                            if(responseEntity.getStatusCode() != HttpStatus.OK)
                                System.out.println("Error. Code returned: " + responseEntity.getStatusCode());
                            else
                                System.out.println(responseEntity.getBody());

                            break;
                        case 4:
                            //READ BY USER
                            System.out.println("Insert the Id of the user\n");
                            userBooking = scanner.nextLong();
                            endpoint = "/booking/user/" + userBooking;

                            responseEntity =
                                    testRestTemplate.getForEntity(endpoint, String.class);

                            if(responseEntity.getStatusCode() != HttpStatus.OK)
                                System.out.println("Error. Code returned: " + responseEntity.getStatusCode());
                            else
                                System.out.println(responseEntity.getBody());

                            break;
                        case 5:
                            //READ BY FIELD
                            System.out.println("Insert the Id of the field\n");
                            fieldBooking = scanner.nextLong();

                            endpoint = "/booking/field/" + fieldBooking;

                            responseEntity =
                                    testRestTemplate.getForEntity(endpoint, String.class);

                            if(responseEntity.getStatusCode() != HttpStatus.OK)
                                System.out.println("Error. Code returned: " + responseEntity.getStatusCode());
                            else
                                System.out.println(responseEntity.getBody());

                            break;
                        case 6:
                            //EDIT
                            System.out.println("Insert the Id of the booking\n");
                            idBooking = scanner.nextLong();
                            System.out.println("Insert the Id of the user\n");
                            userBooking = scanner.nextLong();
                            System.out.println("Insert the Id of the field\n");
                            fieldBooking = scanner.nextLong();
                            System.out.println("Insert the number of the people\n");
                            numPeopleBooking = scanner.nextInt();
                            System.out.println("Insert the date [yyyy-mm-dd]\n");
                            dateBooking = LocalDate.parse(scanner.nextLine());
                            System.out.println("Insert the starting time [hh:mm:ss]\n");
                            timeStartBooking = LocalTime.parse(scanner.nextLine());
                            System.out.println("Insert the ending time [hh:mm:ss]\n");
                            timeEndBooking = LocalTime.parse(scanner.nextLine());

                            endpoint = "/booking/" + idBooking;

                            testRestTemplate.put(endpoint, String.class);

                            break;
                        case 7:
                            //DELETE
                            System.out.println("Insert the Id of the booking to delete\n");
                            idBooking = scanner.nextLong();

                            endpoint = "/booking/" + idBooking;

                            testRestTemplate.delete(endpoint);

                            break;
                        default:
                            continue;
                    }
                    break;
                case 4:
                    // REPORT
                    System.out.println("Choose what operation do: \n1 - Ask a report by user\n2 - Ask a report by field\n" +
                            "3 - Read old report\n4 - Read all old reports\n5 - Delete a old report\n");
                    try {
                        choice = scanner.nextInt();
                    }
                    catch (Exception e){
                        System.out.println("Error, make sure to enter only a number. " + e.getMessage());

                        //avoid infinite loop
                        choice = 0;
                        scanner.nextLine();

                        continue;
                    }
                    switch (choice) {
                        case 1:
                            //BY USER
                            System.out.println("Insert the Id of the user\n");
                            userReport = scanner.nextLong();

                            endpoint = "/report/user/" + userReport;
                            System.out.println("Insert the Id of the user\n");
                            userReport = scanner.nextLong();

                            responseEntity =
                                    testRestTemplate.getForEntity(endpoint, String.class);

                            if(responseEntity.getStatusCode() != HttpStatus.OK)
                                System.out.println("Error. Code returned: " + responseEntity.getStatusCode());
                            else
                                System.out.println(responseEntity.getBody());
                            break;
                        case 2:
                            //BY FIELD
                            System.out.println("Insert the Id of the field\n");
                            fieldReport = scanner.nextLong();

                            endpoint = "/report/field/" + fieldReport;

                            responseEntity =
                                    testRestTemplate.getForEntity(endpoint, String.class);

                            if(responseEntity.getStatusCode() != HttpStatus.OK)
                                System.out.println("Error. Code returned: " + responseEntity.getStatusCode());
                            else
                                System.out.println(responseEntity.getBody());

                            break;
                        case 3:
                            //READ
                            System.out.println("Insert the Id of the report\n");
                            idReport = scanner.nextLong();

                            endpoint = "/report/" + idReport;

                            responseEntity =
                                    testRestTemplate.getForEntity(endpoint, String.class);

                            if(responseEntity.getStatusCode() != HttpStatus.OK)
                                System.out.println("Error. Code returned: " + responseEntity.getStatusCode());
                            else
                                System.out.println(responseEntity.getBody());

                            break;
                        case 4:
                            //READ ALL
                            endpoint = "/report";

                            responseEntity =
                                    testRestTemplate.getForEntity(endpoint, String.class);

                            if(responseEntity.getStatusCode() != HttpStatus.OK)
                                System.out.println("Error. Code returned: " + responseEntity.getStatusCode());
                            else
                                System.out.println(responseEntity.getBody());
                            break;
                        case 5:
                            //DELETE
                            System.out.println("Insert the Id of the report\n");
                            idReport = scanner.nextLong();
                            
                            endpoint = "/report/" + idReport;

                            testRestTemplate.delete(endpoint);
                            break;
                    }
            }
        }
    }
}
