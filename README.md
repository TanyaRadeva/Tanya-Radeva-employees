# Tanya-Radeva-employees
Couple of employees who have worked together on common projects for the longest time.

It's given a text file in format ```EmpID, ProjectID, DateFrom, DateTo```
Example data: 
   ```
      143, 12, 2013-11-01, 2014-01-05
      218, 10, 2012-05-16, NULL
      143, 10, 2009-01-01, 2011-04-27
      ...
   ```
   
* To write an application that finds the couple of employees who have worked together on common projects for the longest time.
* DateTo can accept value „NULL“ (this is equal to „today“).
* The data can be passed to the program from a text file.
* The program have to be started without the need to do any code changes, after “checkout” on the code and import in IDE, 
  the program have to run and show the result in the console.
* Comply with the “code convention”, depending on the programming language in which it is developed
  the program::

   a. Java - (http://www.oracle.com/technetwork/java/codeconvtoc-136057.html )

   b. C # - (https://docs.microsoft.com/en-us/dotnet/csharp/programming-guide / inside-a-program / coding-conventions)

   c. See others here (https://en.wikipedia.org/wiki/Coding_conventions)
  
* The solution to the problem to be put in github
  
  a. Repository Name to be „FirstName-LastName-employees (example: ivan-ivanov-employees)
  
**Bonus conditions**
1) Make a UI where the user can choose a file from the file system and after choosing the file to see the result in "datagrid" 
   with the following columns: 
   ```Employee ID #1, Employee ID #2, Project ID, Days worked```
2) To support more than one or all date formats.

# Assignment Guide

This is a small object-oriented project - a console application. Read data from a text file. 
The user must enter the full file path into the console. for example: ```/home/develop/TeamLongestPeriod/src/resources/employees.txt```
Calculates the longest joint work duration and print the team (Couple of employees) that has the longest total joint work duration of common projects.
The default data format is ```yyyy-MM-dd```. If you want to work with other must be enter it, 
otherwise the aplication uses the format by default.

**For tests**

You can use the text files which is placed in the project: 
```employees.txt``` (default format) or ```employees1.txt``` (`dd/MM/yyyy` format).

## Getting Started
### Application is supposed to be run using an IDE

* Clone repository: using client for the [Git](https://git-scm.com/) version control system.
* Import project
* Use [JDK 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)


#### Repository URL
```
https://github.com/TanyaRadeva/Tanya-Radeva-employees
```

## Technologies

* Java - [JDK11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)

## IDE 

* IntelliJ Idea Ultimate - [JetBrains](https://www.jetbrains.com/idea/)

## Author

**Tanya Radeva** 
* [GitHub](https://github.com/TanyaRadeva)
* [LinkedIn](https://www.linkedin.com/in/tanya-radeva-b841b320a/)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE) file for details
