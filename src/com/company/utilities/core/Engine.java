package com.company.utilities.core;

import com.company.factories.RecordFactory;
import com.company.models.Record;
import com.company.models.Team;
import com.company.services.contracts.EmployeeService;
import com.company.utilities.io.contracts.FileIO;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Engine {
    private static final String BEST_TEAM_MSG =
            "The pair of employees which are worked together on joint projects longest are:%n employeeID:%d and employeeID:%d - %d total days worked%n";
    private static final String NO_TEAMS_MSG = "Doesn't exists pair of employees which are worked together on joint projects.";
    private static final int EMPTY_COLLECTION = 0;
    private static final int INDEX_ZERO = 0;
    public static final String CAPTION = "-".repeat(20);
    public static final String PRINT_RESULT_TEAM_PROJECTS = "| %20d | %20d | %20d | %20d |";
    public static final String PRINT = String.format("| %20s | %20s | %20s | %20s |","First employee ID", "Second employee ID", "Project ID", "Days worked");
    public static final String PRINT1 = String.format("| %20s | %20s | %20s | %20s |",CAPTION, CAPTION, CAPTION, CAPTION);
    public static final String DEFAULT_DATE_FORMATTER = "yyyy-MM-dd";
    private static String dateFormatter;

    private FileIO fileIO;
    private EmployeeService employeeService;

    public Engine(FileIO fileIO, EmployeeService employeeService) {
        this.fileIO = fileIO;
        this.employeeService = employeeService;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the file path: ");
        String filePath = sc.nextLine();
        System.out.println("Enter the date pattern. To use default(yyyy-MM-dd) press Enter: ");
        dateFormatter = sc.nextLine();
        if (dateFormatter.trim().isEmpty())
            dateFormatter = DEFAULT_DATE_FORMATTER;

        // Read all records data from .txt file
        List<Record> records = this.fileIO.read(filePath)
                .stream()
                .map(RecordFactory::execute)
                .collect(Collectors.toList());

        // Save all employee records into "allRecords"
        this.employeeService.addEmployeeRecords(records);

        // Find all team, couple of employees which are worked under same project and have joint duration
        List<Team> teams = this.employeeService.findAllTeamsWithJointDuration();

        printResult(teams);
    }

    // If don't have couple of employees which are worked together under same project will be print message with text NO_TEAMS_MSG,
    // otherwise will be find and print the team with best joint duration under their joint projects.
    private void printResult(List<Team> teams) {
        if (teams.size() != EMPTY_COLLECTION) {
            teams.sort((team1, team2) ->
                    (int) (team2.getTotalJointDuration() - team1.getTotalJointDuration()));
            Team bestTeam = teams.get(INDEX_ZERO);

            System.out.println(
                    String.format(BEST_TEAM_MSG,
                            bestTeam.getFirstEmployeeId(),
                            bestTeam.getSecondEmployeeId(),
                            bestTeam.getTotalJointDuration()
                    )
            );
            System.out.println(PRINT);
            System.out.println(PRINT1);
            Map<Long, Long> projects = bestTeam.getProjects();
            for (Map.Entry<Long, Long> entry : projects.entrySet()) {
                System.out.println(
                        String.format(PRINT_RESULT_TEAM_PROJECTS,
                                bestTeam.getFirstEmployeeId(),
                                bestTeam.getSecondEmployeeId(),
                                entry.getKey(),
                                entry.getValue()
                        )
                );
            }
        } else {
            System.out.print(NO_TEAMS_MSG);
        }
    }

    public static String getDateFormatter() {
        return dateFormatter;
    }
}
