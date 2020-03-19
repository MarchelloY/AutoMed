package org.marchello.service;

import com.alibaba.fastjson.JSON;
import org.marchello.model.Svc;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SvcService {
    private static List<Svc> SERVICES;
    private static final File FILE_SERVICES = new File("files/services.json");
    private static final File FILE_ID_SVC = new File("files/id_svc.txt");

    private static void readServicesFromFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(FILE_SERVICES);
        SERVICES = FILE_SERVICES.length() == 0 ? new ArrayList<>() : JSON.parseArray(scanner.nextLine(), Svc.class);
    }

    public static String readIdFromFile() throws IOException {
        Scanner scanner = new Scanner(FILE_ID_SVC);
        int counter = FILE_ID_SVC.length() == 0 ? 0 : Integer.parseInt(scanner.nextLine());
        FileWriter fw = new FileWriter(FILE_ID_SVC);
        fw.write(String.valueOf(++counter));
        fw.close();
        return String.valueOf(counter);
    }

    private static void writeServicesInFile() throws IOException {
        FileWriter fw = new FileWriter(FILE_SERVICES);
        fw.write(JSON.toJSONString(SERVICES));
        fw.close();
    }

    public static void addService(Svc service) throws IOException {
        readServicesFromFile();
        SERVICES.add(service);
        writeServicesInFile();
    }

    public static void deleteServiceById(String id) throws IOException {
        readServicesFromFile();
        if(SERVICES.stream().anyMatch(service -> id.equals(service.getId())))
            SERVICES.remove(searchServiceById(id));
        writeServicesInFile();
    }

    private static Svc searchServiceById(String id) {
        return SERVICES.stream().filter(service -> id.equals(service.getId())).collect(Collectors.toList()).get(0);
    }

    public static List<Svc> getAllServices() throws IOException {
        readServicesFromFile();
        return SERVICES;
    }

    public static Svc getServiceById(String id) throws IOException {
        readServicesFromFile();
        return searchServiceById(id);
    }

    public static List<Svc> searchServicesByFIO(String search) throws FileNotFoundException {
        readServicesFromFile();
        return SERVICES.stream()
                .filter(service -> (service.getName()).toLowerCase().contains(search.toLowerCase()))
                .collect(Collectors.toList());
    }

    public static void updateService(Svc svc) throws IOException {
        deleteServiceById(svc.getId());
        addService(svc);
    }
}
