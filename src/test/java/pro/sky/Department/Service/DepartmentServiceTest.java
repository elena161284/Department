package pro.sky.Department.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.Department.Employee;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {
    @Mock
    EmployeeService employeeService;
    @InjectMocks
    DepartmentService departmentService;

    @Test
    void testSum() {
        var employees = List.of(
                new Employee("test", "test_test", 10, 1),
                new Employee("test2", "test_test2", 20, 3),
                new Employee("test3", "test_test3", 40, 2),
                new Employee("test4", "test_test4", 50, 4));
        when(employeeService.getAll()).thenReturn(employees);
        Assertions.assertThat(departmentService.sum(1)).isEqualTo(40d);
    }
}