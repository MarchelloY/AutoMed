package com.marchello.service;

import com.alibaba.fastjson.JSON;
import com.marchello.model.Person;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonService {
    private List<Person> persons;
    private File file = new File("persons.json");

    public PersonService() {}

    private void readPersonsFromFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(this.file);
        this.persons = file.length() == 0 ? new ArrayList<>() : JSON.parseArray(scanner.nextLine(), Person.class);
    }

    private void writePersonsInFile() throws IOException {
        FileWriter fw = new FileWriter(this.file);
        fw.write(JSON.toJSONString(this.persons));
        fw.close();
    }

    public void addPerson(Person person) throws IOException {
        readPersonsFromFile();
        this.persons.add(person);
        writePersonsInFile();
    }
}