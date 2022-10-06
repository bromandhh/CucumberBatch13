Feature: API workflow resource

  @api
  Scenario: First scenario to create the employee
    Given a JWT is generated
    Given a request is prepared for creating an employee
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as global variable

  @api
  Scenario: Retrieving recently created employee
    Given a request is prepared to get the created employee
    When a GET call is made to get the details of the employee
    Then the status code is 200
    And the employee id "employee.employee_id" must match with globally stored employee id
    And the received data from "employee" object matches with the data used to create employee
      |emp_firstname|emp_lastname|emp_middle_name|emp_gender|emp_birthday|emp_status|emp_job_title|
      |Sadiq     |Naser    |MS             |Male      |2001-09-14  |Probation    |QA Engineer  |

  @json
  Scenario: Creating an employee via json payload
    Given a request is prepared for creating an employee via json payload
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as global variable

  @update
  Scenario: Creating an employee using more dynamic payload
    Given a request is prepared for creating an employee via more dynamic payload "Sadiq" , "Naser" , "MS" , "M" , "2001-09-14" , "Probation" , "QA Engineer"
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as global variable

  @update
  Scenario: updating the employee details
    Given a request is prepared for updating an employee
    When a PUT call is made to update the employee
    Then the status code is 200