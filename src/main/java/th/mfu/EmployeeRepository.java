package th.mfu;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    public List<Employee> findAll();

    public List<Employee> findByFirstname(String firstname);

    public List<Employee> findByFirstnameStartingWith(String prefix);
}
