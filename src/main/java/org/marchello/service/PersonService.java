package org.marchello.service;

import com.alibaba.fastjson.JSON;
import org.marchello.model.Person;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PersonService {
    private static List<Person> PERSONS;
    private static final File FILE_PERSONS = new File("files/persons.json");
    private static final File FILE_ID_PRS = new File("files/id_prs.txt");

    private static void readPersonsFromFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(FILE_PERSONS);
        PERSONS = FILE_PERSONS.length() == 0 ? new ArrayList<>() : JSON.parseArray(scanner.nextLine(), Person.class);
    }

    public static String readIdFromFile() throws IOException {
        Scanner scanner = new Scanner(FILE_ID_PRS);
        int counter = FILE_ID_PRS.length() == 0 ? 0 : Integer.parseInt(scanner.nextLine());
        FileWriter fw = new FileWriter(FILE_ID_PRS);
        fw.write(String.valueOf(++counter));
        fw.close();
        return String.valueOf(counter);
    }

    private static void writePersonsInFile() throws IOException {
        FileWriter fw = new FileWriter(FILE_PERSONS);
        fw.write(JSON.toJSONString(PERSONS));
        fw.close();
    }

    public static void addPerson(Person person) throws IOException {
        readPersonsFromFile();
        PERSONS.add(person);
        writePersonsInFile();
    }

    public static void deletePersonById(String id) throws IOException {
        readPersonsFromFile();
        if (PERSONS.stream().anyMatch(person -> id.equals(person.getId())))
            PERSONS.remove(searchPersonById(id));
        writePersonsInFile();
    }

    private static Person searchPersonById(String id) {
        return PERSONS.stream().filter(person -> id.equals(person.getId())).collect(Collectors.toList()).get(0);
    }

    public static List<Person> getAllPersons() throws IOException {
        readPersonsFromFile();
        return PERSONS;
    }

    public static Person getPersonById(String id) throws IOException {
        readPersonsFromFile();
        return searchPersonById(id);
    }

    public static List<Person> searchPersonsByFIO(String search) throws FileNotFoundException {
        readPersonsFromFile();
        return PERSONS.stream()
                .filter(person -> (person.getSurname() + " " + person.getName() + " " + person.getPatronymic())
                        .toLowerCase().contains(search.toLowerCase()))
                .collect(Collectors.toList());
    }

    public static void updatePerson(Person person) throws IOException {
        deletePersonById(person.getId());
        addPerson(person);
    }
}