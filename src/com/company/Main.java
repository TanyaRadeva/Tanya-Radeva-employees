package com.company;

import com.company.repositories.EmployeeRepositoryImpl;
import com.company.repositories.contracts.EmployeeRepository;
import com.company.services.EmployeeServiceImpl;
import com.company.services.contracts.EmployeeService;
import com.company.utilities.core.Engine;
import com.company.utilities.io.FileIOImpl;
import com.company.utilities.io.contracts.FileIO;

public class Main {

    public static void main(String[] args) {

        FileIO fileIO = new FileIOImpl();
        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
        EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository);

        Engine engine = new Engine(fileIO, employeeService);
        engine.run();
    }
}