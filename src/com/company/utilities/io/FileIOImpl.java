package com.company.utilities.io;

import com.company.utilities.io.contracts.FileIO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileIOImpl implements FileIO {

    @Override
    public List<String> read(String filePath) {
        List<String> content = new ArrayList<>();

        File file = new File(filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    content.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("The file path is not correct.");
        }

        return content;
    }
}
