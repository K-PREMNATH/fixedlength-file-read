package com.job;

import com.model.Employee;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class EmployeeFieldSetMapper implements FieldSetMapper<Employee> {

    @Override
    public Employee mapFieldSet(FieldSet fieldSet) throws BindException {
        Employee employee = new Employee();
        employee.setEmployeeId(fieldSet.readString("employeeId"));
        employee.setName(fieldSet.readString("name"));
        employee.setSalary(fieldSet.readString("salary"));
        return employee;
    }
}

