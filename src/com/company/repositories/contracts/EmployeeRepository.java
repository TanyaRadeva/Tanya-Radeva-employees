package com.company.repositories.contracts;

import com.company.models.Record;

import java.util.Collection;
import java.util.List;

public interface EmployeeRepository {

    void save(Record record);

    void saveAll(Collection<Record> records);

    List<Record> getAllRecords();
}
