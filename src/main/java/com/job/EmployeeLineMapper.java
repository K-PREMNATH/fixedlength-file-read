package com.job;

import com.model.Employee;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;

public class EmployeeLineMapper extends DefaultLineMapper<Employee> {

    public EmployeeLineMapper() {
        setLineTokenizer(createTokenizer());
        setFieldSetMapper(new EmployeeFieldSetMapper());
    }

    private FixedLengthTokenizer createTokenizer() {
        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
        tokenizer.setColumns(new Range[]{new Range(1, 5), new Range(6, 15), new Range(16, 20)});
        tokenizer.setNames(new String[]{"employeeId", "name", "salary"});
        return tokenizer;
    }
}

