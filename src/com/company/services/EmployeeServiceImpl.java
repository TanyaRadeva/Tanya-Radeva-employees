package com.company.services;

import com.company.factories.TeamFactory;
import com.company.models.Record;
import com.company.models.Team;
import com.company.repositories.contracts.EmployeeRepository;
import com.company.services.contracts.EmployeeService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class EmployeeServiceImpl implements EmployeeService {
    private static final int INDEX_ZERO = 0;
    private static final int VALUE_ONE = 1;
    private static final int DEFAULT_JOINT_DURATION = 0;

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Method which save all records to the 'allRecords' using EmployeeRepository
    @Override
    public void addEmployeeRecords(List<Record> records) {
        this.employeeRepository.saveAll(records);
    }

    // Method which finding all teams, couples which have joint duration and save them into List<Team>
    @Override
    public List<Team> findAllTeamsWithJointDuration() {
        List<Record> allRecords = this.employeeRepository.getAllRecords();

        List<Team> teams = new ArrayList<>();
        for (int i = INDEX_ZERO; i < allRecords.size() - VALUE_ONE; i++) {
            for (int j = i + VALUE_ONE; j < allRecords.size(); j++) {
                Record firstEmployee = allRecords.get(i);
                Record secondEmployee = allRecords.get(j);

                if (firstEmployee.getProjectId().equals(secondEmployee.getProjectId())
                        && hasJointDuration(firstEmployee, secondEmployee)) {
                    long jointDurationDays = calculateJointDuration(firstEmployee, secondEmployee);

                    if (jointDurationDays > DEFAULT_JOINT_DURATION) {
                        updateTeamCollection(teams, firstEmployee, secondEmployee, jointDurationDays);
                    }
                }
            }
        }
        return teams;
    }

    // Method which calculating the total joint duration and returning it
    private long calculateJointDuration(Record firstEmployee, Record secondEmployee) {
        LocalDate periodStartDate =
                firstEmployee.getDateFrom().isBefore(secondEmployee.getDateFrom()) ?
                        secondEmployee.getDateFrom() : firstEmployee.getDateFrom();

        LocalDate periodEndDate =
                firstEmployee.getDateTo().isBefore(secondEmployee.getDateTo()) ?
                        firstEmployee.getDateTo() : secondEmployee.getDateTo();

        //This method work good and when we have involved leap years too
        //from 2019-01-01 to 2019-01-15 will return 14days in result not 15, which will accept for correct
        return Math.abs(ChronoUnit.DAYS.between(periodStartDate, periodEndDate));
    }

    // Method returning true if two employees have joint duration
    private boolean hasJointDuration(Record firstEmployee, Record secondEmployee) {
        //have overlap if (startA <= endB) and (endA >= startB)
        return (firstEmployee.getDateFrom().isBefore(secondEmployee.getDateTo())
                || firstEmployee.getDateFrom().isEqual(secondEmployee.getDateTo()))
                && (firstEmployee.getDateTo().isAfter(secondEmployee.getDateFrom())
                || firstEmployee.getDateTo().isEqual(secondEmployee.getDateFrom()));
    }

    // Method check and returning true if the current team is already present in team collection
    private boolean isTeamPresent(Team team, long firstEmployeeId, long secondEmployeeId) {
        return (team.getFirstEmployeeId() == firstEmployeeId
                && team.getSecondEmployeeId() == secondEmployeeId)
                || (team.getFirstEmployeeId() == secondEmployeeId
                && team.getSecondEmployeeId() == firstEmployeeId);
    }

    // If the team is already present, it's total joint duration will be updated with the new value,
    // otherwise will be create and add new team with the current data
    private void updateTeamCollection(List<Team> teams, Record firstEmployee, Record secondEmployee, long jointDurationDays) {
        AtomicBoolean isPresent = new AtomicBoolean(false);
        teams.forEach(team -> {
            if (isTeamPresent(team, firstEmployee.getEmployeeId(), secondEmployee.getEmployeeId())) {
                team.addJointDuration(firstEmployee.getProjectId(), jointDurationDays);
                isPresent.set(true);
            }
        });
        // If the team isn't present -> create and add new team with the current data
        if (!isPresent.get()) {
            Team newTeam = TeamFactory.execute(
                    firstEmployee.getEmployeeId(),
                    secondEmployee.getEmployeeId(),
                    jointDurationDays);
            newTeam.setProjects(firstEmployee.getProjectId());
            teams.add(newTeam);
        }
    }
}
