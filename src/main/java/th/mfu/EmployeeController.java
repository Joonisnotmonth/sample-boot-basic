package th.mfu;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    // create hashmap for employee
    private HashMap<Long, Employee> employeesDB = new HashMap<Long, Employee>();

    // select all employeee
    @GetMapping("/employees")
    public Collection<Employee> getAllEmployees() {
        return employeesDB.values();
    }

    // select employee by id
    @GetMapping("/employees/{id}")
    public ResponseEntity getEmployeeById(@PathVariable long id) {
        // check if id exists in db
        if (!employeesDB.containsKey(id)) {
            // return error message 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }
        return ResponseEntity.ok(employeesDB.get(id));
    }

    // create new employee
    @PostMapping("/employees")
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
        // check if id exists
        if (employeesDB.containsKey(employee.getId())) {
            // return error message
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Employee id already exists");
        }

        // add employee to hashmap
        employeesDB.put(employee.getId(), employee);

        // return created success message
        return ResponseEntity.ok("Employee created");
    }

    // update employee
    @PutMapping("/employees/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable long id, @RequestBody Employee employee) {
        // check if id not exists
        if (!employeesDB.containsKey(id)) {
            // return error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }

        // update employee
        employeesDB.put(id, employee);

        // return success message
        return ResponseEntity.ok("Employee updated");
    }

    // update employee with some fields using patch
    @PatchMapping("/employees/{id}")
    public ResponseEntity<String> patchEmployee(@PathVariable long id,
            @RequestBody HashMap<String, Object> fieldstoupdate) {
        // check if id not exists
        if (!employeesDB.containsKey(id)) {
            // return error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }

        // get employee from db
        Employee emp = employeesDB.get(id);
        // loop throught fields to update
        fieldstoupdate.forEach((key, value) -> {
            // check if field is firstname
            if (key.equals("first_name")) {
                // update firstname
                emp.setFirstname((String) value);
            }
            // check if field is lastname
            if (key.equals("last_name")) {
                // update lastname
                emp.setLastname((String) value);
            }

            // check if field is salary
            if (key.equals("salary")) {
                // update salary
                emp.setSalary(Long.valueOf("" + value));
            }

        });

        // update employee
        employeesDB.put(id, emp);

        // return success message
        return ResponseEntity.ok("Employee updated");
    }

    // delete employee
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long id) {
        // check if id not exists
        if (!employeesDB.containsKey(id)) {
            // return error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }

        // delete employee
        employeesDB.remove(id);

        // return success message
        return ResponseEntity.ok("Employee deleted");
    }

}