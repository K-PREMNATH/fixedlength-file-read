package com.job;

import com.model.Employee;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.core.io.FileSystemResource;

public class EmployeeFileReader implements ItemReader<Employee> {

    FlatFileItemReader<Employee> reader = new FlatFileItemReader<>();

    public EmployeeFileReader(String filename,String filePath,String jobName){

        String fileAbsolutePath = filePath + filename;

        reader.setResource(new FileSystemResource(fileAbsolutePath));

        EmployeeLineMapper lineMapper = new EmployeeLineMapper();

        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
        tokenizer.setColumns(new Range[]{new Range(1, 5), new Range(6, 15), new Range(16, 20)});
        tokenizer.setNames(new String[]{"employeeId", "name", "salary"});

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(new EmployeeFieldSetMapper());
        reader.setLineMapper(lineMapper);

        reader.setLinesToSkip(1);
        reader.setSaveState(false);
        reader.open(new ExecutionContext());

    }

    @Override
    public Employee read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return reader.read();
    }
}
