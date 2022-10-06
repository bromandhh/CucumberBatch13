package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    // CRUD operations we perform
    String token =
            "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjQzOTQ2MjQsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY2NDQzNzgyNCwidXNlcklkIjoiNDUwNyJ9.y-M-BZfNJZwLamGgJtvqQuMSITbknlOVbelStQVONeU";
    static String employee_id;
    @Test
    //To create a regular/normal employee
    public void acreateEmployee(){
        //prepare the request
      RequestSpecification prepareRequest = given().header("Content-Type", "application/json").
                header("Authorization", token).body("{\n" +
                        "  \"emp_firstname\": \"Sadiq\",\n" +
                        "  \"emp_lastname\": \"Naser\",\n" +
                        "  \"emp_middle_name\": \"MS\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2001-09-14\",\n" +
                        "  \"emp_status\": \"Probation\",\n" +
                        "  \"emp_job_title\": \"QA Engineer\"\n" +
                        "}");

      // hitting the endpoint
        Response response = prepareRequest.when().post("/createEmployee.php");
        response.prettyPrint();

        //assertions and validation
        // Verifying the correct status code of the request
        response.then().assertThat().statusCode(201);
        response.then().assertThat().body("Employee.emp_middle_name", equalTo("MS"));
        response.then().assertThat().body("Employee.emp_firstname", equalTo("Sadiq"));
        response.then().assertThat().body("Employee.emp_lastname", equalTo("Naser"));
        response.then().assertThat().body("Message", equalTo("Employee Created"));

        //verifying the response headers
        response.then().assertThat().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");

        //to get the employee ud from the body
        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);
    }

    @Test
    public void bgetCreatedEmployee(){
        //prepare the request
        RequestSpecification preparedRequest =
                given().header("Content-Type", "application/json").
                        header("Authorization",token).
                        queryParam("employee_id", employee_id);
        Response response = preparedRequest.when().get("/getOneEmployee.php");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }
    @Test
    public void cupdatingEmployee(){
        RequestSpecification preparedRequest = given().header("Authorization", token)
                .header("Content-Type", "application/json").body("{\n" +
                        "    \"employee_id\": \""+employee_id+"\",\n" +
                        "    \"emp_firstname\": \"Farhad\",\n" +
                        "    \"emp_middle_name\": \"Shah\",\n" +
                        "    \"emp_lastname\": \"Khan\",\n" +
                        "    \"emp_birthday\": \"2000-09-14\",\n" +
                        "    \"emp_gender\": \"M\",\n" +
                        "    \"emp_job_title\": \"QA Manager\",\n" +
                        "    \"emp_status\": \"Regular\"\n" +
                        "}");

        Response response = preparedRequest.when().put("/updateEmployee.php");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }
    @Test
    public void getUpdatedEmployee(){
        //prepare the request
        RequestSpecification preparedRequest = given().
                header("Content-Type", "application/json")
                .header("Authorization", token).queryParam("employee_id", employee_id);
        //hitting the endpoint
        Response response = preparedRequest.when().get("/getOneEmployee.php");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("employee.emp_lastname", equalTo("Khan"));

    }
}
