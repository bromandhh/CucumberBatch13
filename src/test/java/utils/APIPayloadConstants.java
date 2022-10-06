package utils;

import APIsteps.APIWorkflowSteps;
import org.json.JSONObject;

public class APIPayloadConstants {

    public static String createEmployeePayload(){
        String createEmployeePayload = "{\n" +
                "  \"emp_firstname\": \"Sadiq\",\n" +
                "  \"emp_lastname\": \"Naser\",\n" +
                "  \"emp_middle_name\": \"MS\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"2001-09-14\",\n" +
                "  \"emp_status\": \"Probation\",\n" +
                "  \"emp_job_title\": \"QA Engineer\"\n" +
                "}";
        return createEmployeePayload;
    }

    public static String createEmployeePayloadJson(){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", "Sadiq");
        obj.put("emp_lastname", "Naser");
        obj.put("emp_middle_name", "MS");
        obj.put("emp_gender", "M");
        obj.put("emp_birthday", "2001-09-14");
        obj.put("emp_status", "Probation");
        obj.put("emp_job_title", "QA Engineer");
        return obj.toString();
    }
    public static String createDynamicEmployeePayloadJson
            (String firstName, String lastName, String middleName,
             String gender, String birthDay, String empStatus, String jobTitle){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", firstName);
        obj.put("emp_lastname", lastName);
        obj.put("emp_middle_name", middleName);
        obj.put("emp_gender", gender);
        obj.put("emp_birthday", birthDay);
        obj.put("emp_status", empStatus);
        obj.put("emp_job_title", jobTitle);
        return obj.toString();
    }
    public static String updateEmployeePayload() {
        JSONObject obj = new JSONObject();
        obj.put("employee_id", APIWorkflowSteps.employee_id);
        obj.put("emp_firstname", "Sadiq");
        obj.put("emp_lastname", "Khan");
        obj.put("emp_middle_name", "Majnoon");
        obj.put("emp_gender", "M");
        obj.put("emp_birthday", "2001-09-14");
        obj.put("emp_status", "Normal");
        obj.put("emp_job_title", "QA Engineer");
        return obj.toString();
    }
}
