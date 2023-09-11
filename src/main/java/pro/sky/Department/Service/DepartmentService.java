package pro.sky.Department.Service;

import org.springframework.stereotype.Service;
import pro.sky.Department.Employee;
import pro.sky.Department.Exception.EmployeeNotFoundException;
import pro.sky.Department.Employee;
import pro.sky.Department.Exception.EmployeeNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    public Employee maxSalary(int deptId) {
        return employeeService.getAll()
                .stream()
                .filter(e -> e.getDepartment() == deptId)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }
    public Employee minSalary(int deptId) {
        return employeeService.getAll()
                .stream()
                .filter(e -> e.getDepartment() == deptId)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }
    public List<Employee> findAllByDept(int deptId) {
        return employeeService.getAll()
                .stream()
                .filter(e -> e.getDepartment() == deptId)
                .collect(Collectors.toList());
    }

    public List<Employee> groupDeDept() {
        Map<Integer, List<Employee>> map = employeeService.getAll()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        return null;
    }

    public double sum(int deptId) {
        return employeeService.getAll()
                .stream()
                .filter(e -> e.getDepartment() == deptId)
                .mapToDouble(Employee::getSalary)
                .sum();
    }
}