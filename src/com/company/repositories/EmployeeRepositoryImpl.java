package com.company.repositories;

import com.company.models.Record;
import com.company.repositories.contracts.EmployeeRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private List<Record> allRecords;

    public EmployeeRepositoryImpl() {
        this.allRecords = new ArrayList<>();
    }

    @Override
    public void save(Record record) {
        this.allRecords.add(record);
    }

    @Override
    public void saveAll(Collection<Record> records) {
        this.allRecords.addAll(records);
    }

    @Override
    public List<Record> getAllRecords() {
        return Collections.unmodifiableList(this.allRecords);
    }
}
