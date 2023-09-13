package pro.sky.Department.Service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.Department.Employee;
import pro.sky.Department.Exception.EmployeeNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {
    @Mock
    EmployeeService employeeService;
    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        var employees = List.of(
                new Employee("Test", "Test_test", 10, 1),
                new Employee("Test2", "Test_test2", 20, 1),
                new Employee("Test3", "Test_test3", 40, 2),
                new Employee("Test4", "Test_test4", 10, 3),
                new Employee("Test5", "Test_test5", 10, 4));
        when(employeeService.getAll()).thenReturn(employees);
    }

    @Test
    void testSum() {
        Assertions.assertThat(departmentService.sum(1)).isEqualTo(30d);
    }
    @Test
    void testMaxSalary() {
        Assertions.assertThat(departmentService.maxSalary(1)).isEqualTo(20d);
    }
    @Test
    void testMinSalary() {
        Assertions.assertThat(departmentService.minSalary(1)).isEqualTo(10d);
    }
    @Test
    void testWenEmployeesIsEmpty() {
        when(employeeService.getAll()).thenReturn(Collections.emptyList());
        Assertions.assertThatThrownBy(()->departmentService.minSalary(1))
                .isInstanceOf(EmployeeNotFoundException.class);
        Assertions.assertThatThrownBy(()->departmentService.maxSalary(1))
                .isInstanceOf(EmployeeNotFoundException.class);
        Assertions.assertThat(departmentService.sum(1)).isEqualTo(0d);

    }
    @Test
    void testFindAllByDept() {
        var employees = departmentService.findAllByDept(1);
        Assertions.assertThat(employees.size()).isEqualTo(1);
        Assertions.assertThat(employees.get(0)).isEqualTo(new Employee("Test","Test_test",10,1));
    }
    @Test
    void testGroupDeDept() {
       Map<Integer, List<Employee>> actual = departmentService.groupDeDept();
        Assertions.assertThat(actual.keySet()).containsExactly(1,2,3,4);
        Assertions.assertThat(actual.get(1)).containsExactly(
                new Employee("Test", "Test_test", 10, 1),
                new Employee("Test2", "Test_test2", 20, 1));
        Assertions.assertThat(actual.get(2)).containsExactly(new Employee("Test3", "Test_test3", 40, 2));
        Assertions.assertThat(actual.get(3)).containsExactly(new Employee("Test4", "Test_test4", 10, 3));

        Map<Integer, List<Employee>> expected= Map.of(
                1, List.of(
                        new Employee("Test", "Test_test", 10, 1),
                        new Employee("Test2", "Test_test2", 20, 1)),
                2, List.of(
                        new Employee("Test3", "Test_test3", 40, 2)),
                3, List.of(new Employee("Test4", "Test_test4", 10, 3)),
                4, List.of(new Employee("Test5", "Test_test5", 10, 4)));
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}