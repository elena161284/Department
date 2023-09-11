package pro.sky.Department.Controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.Department.Employee;
import pro.sky.Department.Service.DepartmentService;
import pro.sky.Department.Employee;
import pro.sky.Department.Service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }
    @GetMapping("{deptId}/salary/sum")
    public double sumByDept(@PathVariable int deptId) {
        return service.sum(deptId);
    }
    //locahost:8080/department/10/max/salary
    @GetMapping("{deptId}/salary/max")
    public Employee max(@PathVariable int deptId) {
        return service.maxSalary(deptId);
    }
    @GetMapping("{deptId}/salary/min")
    public Employee min(@PathVariable int deptId) {
        return service.minSalary(deptId);
    }
    @GetMapping("{deptId}/employees")
    public List<Employee> find(@PathVariable int deptId) {
        return service.findAllByDept(deptId);
    }
    @GetMapping("/employees")
    public List<Employee> group(@PathVariable int deptId) {
        return service.groupDeDept();
    }
}