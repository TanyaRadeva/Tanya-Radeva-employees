package com.company.services.contracts;

import com.company.models.Record;
import com.company.models.Team;

import java.util.List;

public interface EmployeeService {

    void addEmployeeRecords(List<Record> records);

    List<Team> findAllTeamsWithJointDuration();
}
