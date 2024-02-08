import booking.models.BookingRequest;
import field.models.FieldRequest;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;
import user.models.UserRequest;

@SpringBootApplication
public class UserInterface {
    @Autowired
    private static RestTemplate RestTemplate = new RestTemplate();
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

        while (true){
            System.out.println("Choose what field you want to edit: \n1 - User\n2-Field\n3-Booking\n4-Report\n");
            try {
                choice = scanner.nextInt();
                endpoint = "http://localhost:8080/";
            }
            catch (Exception e){
                System.out.println("Error, make sure to enter only a number. " + e.getMessage());

                //avoid infinite loop
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
                        endpoint += "/users";
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

                            userRequest = new UserRequest(fullNameUser, phoneUser, emailUser);
                            responseEntity =
                                    RestTemplate.postForEntity(endpoint, userRequest, String.class);

                            if(responseEntity.getStatusCode() != HttpStatus.CREATED)
                                System.out.println("Error. Code returned: " + responseEntity.getStatusCode());

                            break;
                        case 2:
                            //READ
                            System.out.println("Insert the Id of the user");
                            idUser = scanner.nextLong();

                            endpoint += "/" + idUser;

                            responseEntity =
                                    RestTemplate.getForEntity(endpoint, String.class);

                            if(responseEntity.getStatusCode() != HttpStatus.OK)
                                System.out.println("Error. Code returned: " + responseEntity.getStatusCode());
                            else
                                System.out.println(responseEntity.getBody());
                            break;
                        case 3:
                            //READ ALL
                            responseEntity =
                                    RestTemplate.getForEntity(endpoint, String.class);

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

                            endpoint += "/" + idUser;

                            RestTemplate.put(endpoint, String.class);

                            break;
                        case 5:
                            //DELETE
                            System.out.println("Insert the Id of the user to eliminate");
                            idUser = scanner.nextLong();

                            endpoint += "/" + idUser;

                            RestTemplate.delete(endpoint);

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
                        endpoint += "/fields";
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
                            System.out.println();
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

                            fieldRequest = new FieldRequest(nameField, priceField,maintenanceField,maxCapacityField, locationField, descriptionField);

                            responseEntity =
                                    RestTemplate.postForEntity(endpoint, fieldRequest, String.class);

                            if(responseEntity.getStatusCode() != HttpStatus.CREATED)
                                System.out.println("Error. Code returned: " + responseEntity.getStatusCode());
                            break;
                        case 2:
                            //READ
                            System.out.println("Insert the Id of the field");
                            idField = scanner.nextLong();

                            endpoint += "/" + idField;

                            responseEntity =
                                    RestTemplate.getForEntity(endpoint, String.class);

                            if(responseEntity.getStatusCode() != HttpStatus.OK)
                                System.out.println("Error. Code returned: " + responseEntity.getStatusCode());
                            else
                                System.out.println(responseEntity.getBody());

                            break;
                        case 3:
                            //READ ALL
                            responseEntity =
                                    RestTemplate.getForEntity(endpoint, String.class);

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

                            endpoint += "/" + idField;

                            RestTemplate.put(endpoint, String.class);

                            break;
                        case 5:
                            //DELETE
                            System.out.println("Insert the Id of the field to eliminate");
                            idField = scanner.nextLong();

                            endpoint += "/" + idField;

                            RestTemplate.delete(endpoint);

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
                        endpoint += "/booking";
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

                            bookingRequest = new BookingRequest(userBooking,fieldBooking, numPeopleBooking, dateBooking,timeStartBooking,timeEndBooking);

                            responseEntity =
                                    RestTemplate.postForEntity(endpoint, bookingRequest, String.class);

                            if(responseEntity.getStatusCode() != HttpStatus.CREATED)
                                System.out.println("Error. Code returned: " + responseEntity.getStatusCode());
                            break;
                        case 2:
                            //READ
                            System.out.println("Insert the Id of the booking\n");
                            idBooking = scanner.nextLong();

                            endpoint += "/" + idBooking;

                            responseEntity =
                                    RestTemplate.getForEntity(endpoint, String.class);

                            if(responseEntity.getStatusCode() != HttpStatus.OK)
                                System.out.println("Error. Code returned: " + responseEntity.getStatusCode());
                            else
                                System.out.println(responseEntity.getBody());

                            break;
                        case 3:
                            //READ ALL
                            responseEntity =
                                    RestTemplate.getForEntity(endpoint, String.class);

                            if(responseEntity.getStatusCode() != HttpStatus.OK)
                                System.out.println("Error. Code returned: " + responseEntity.getStatusCode());
                            else
                                System.out.println(responseEntity.getBody());

                            break;
                        case 4:
                            //READ BY USER
                            System.out.println("Insert the Id of the user\n");
                            userBooking = scanner.nextLong();
                            endpoint += "/user/" + userBooking;

                            responseEntity =
                                    RestTemplate.getForEntity(endpoint, String.class);

                            if(responseEntity.getStatusCode() != HttpStatus.OK)
                                System.out.println("Error. Code returned: " + responseEntity.getStatusCode());
                            else
                                System.out.println(responseEntity.getBody());

                            break;
                        case 5:
                            //READ BY FIELD
                            System.out.println("Insert the Id of the field\n");
                            fieldBooking = scanner.nextLong();

                            endpoint += "/field/" + fieldBooking;

                            responseEntity =
                                    RestTemplate.getForEntity(endpoint, String.class);

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

                            endpoint += "/" + idBooking;

                            RestTemplate.put(endpoint, String.class);

                            break;
                        case 7:
                            //DELETE
                            System.out.println("Insert the Id of the booking to delete\n");
                            idBooking = scanner.nextLong();

                            endpoint += "/" + idBooking;

                            RestTemplate.delete(endpoint);

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
                                    RestTemplate.getForEntity(endpoint, String.class);

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
                                    RestTemplate.getForEntity(endpoint, String.class);

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
                                    RestTemplate.getForEntity(endpoint, String.class);

                            if(responseEntity.getStatusCode() != HttpStatus.OK)
                                System.out.println("Error. Code returned: " + responseEntity.getStatusCode());
                            else
                                System.out.println(responseEntity.getBody());

                            break;
                        case 4:
                            //READ ALL
                            endpoint = "/report";

                            responseEntity =
                                    RestTemplate.getForEntity(endpoint, String.class);

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

                            RestTemplate.delete(endpoint);
                            break;
                    }
            }
        }
    }
}
