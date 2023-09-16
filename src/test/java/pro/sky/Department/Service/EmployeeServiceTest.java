package pro.sky.Department.Service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pro.sky.Department.Employee;
import pro.sky.Department.Exception.EmployeeAlreadyAddException;
import pro.sky.Department.Exception.EmployeeNotFoundException;
import pro.sky.Department.Exception.EmployeeStorageIsFullException;

import java.util.Collection;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeServiceTest {
    EmployeeService employeeService = new EmployeeService();

    @Test
    void testAdd() {
        employeeService.addEmployee("test", "test2", 10000, 1);
        Collection<Employee> allEmployees = employeeService.getAll();
        assertEquals(1, allEmployees.size());
        var employee = allEmployees.iterator().next();
        assertEquals("Test", employee.getFirstName());
        assertEquals("Test2", employee.getLastName());
        assertEquals(10000, employee.getSalary());
        assertEquals(1, employee.getDepartment());
    }

    @Test
    void testExtraEmployee() {
        for (int i = 0; i < 10; i++) {
            employeeService.addEmployee("test" + i,
                    "test_test_" + i, 0d, 0);
        }
        assertThrows(EmployeeStorageIsFullException.class,
                () -> employeeService.addEmployee("test",
                        "test", 0d, 0));
    }

    @Test
    void testTheEmployeeRepeats() {
        employeeService.addEmployee("test", "test", 0d, 0);
        assertThrows(EmployeeAlreadyAddException.class, () -> employeeService.addEmployee("test",
                "test", 0d, 0));
    }

    @Test
    void testFind() {
        employeeService.addEmployee("test", "test_test", 10000, 1);
        var actual = employeeService.findEmployee("test", "test_test");
        assertEquals("Test", actual.getFirstName());
        assertEquals("Test_test", actual.getLastName());
        assertEquals(10000, actual.getSalary());
        assertEquals(1, actual.getDepartment());
    }

    @Test
    void testFindWhenNotExist() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployee("test", "test_test"));
    }

    @Test
    void testRemove() {
        employeeService.addEmployee("test", "test_test", 10, 1);
        assertEquals(1, employeeService.getAll().size());
        assertTrue(employeeService.removeEmployee("test", "test_test"));
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.removeEmployee("test", "test_test"));
    }
    @Test
    void testGetAll() {
        employeeService.addEmployee("test1", "test_test1", 10, 1);
        employeeService.addEmployee("test2", "test_test2", -10, 1);
        employeeService.addEmployee("test3", "test_test3", 10, -1);
        var all = employeeService.getAll();
         assertEquals(3,all.size());
        Assertions.assertThat(all)
        .containsExactlyInAnyOrder(
                new Employee("Test1", "Test_test1", 10, 1),
                new Employee("Test2", "Test_test2", -10, 1),
                new Employee("Test3", "Test_test3", 10, -1));
    }
}