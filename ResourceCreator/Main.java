package ResourceCreator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

class Main {

    static String userDirectory = "C://Users/Work/Desktop/RV";
    static StringBuilder log = new StringBuilder();

    public static void main(String[] args) throws UnknownHostException {

        
        List<String> allResources = new LinkedList();
        allResources.add(userDirectory + "/Games/");
        allResources.add(userDirectory + "/Games/src");
        allResources.add(userDirectory + "/Games/src/main");
        allResources.add(userDirectory + "/Games/src/main/ResourceCreator.Main.java");
        allResources.add(userDirectory + "/Games/src/main/Utils.java");
        allResources.add(userDirectory + "/Games/src/test");
        allResources.add(userDirectory + "/Games/res");
        allResources.add(userDirectory + "/Games/res/drawables");
        allResources.add(userDirectory + "/Games/res/vectors");
        allResources.add(userDirectory + "/Games/res/icons");
        allResources.add(userDirectory + "/Games/savegames");
        allResources.add(userDirectory + "/Games/temp");
        allResources.add(userDirectory + "/Games/temp/temp.txt");

        log(allResources);
    }


    public static void log(List<String> allResources) throws UnknownHostException {

        if (!new File(userDirectory + "/Games/temp/temp.txt").exists()) {
            log.append("Date and Time                 Local HostAddress    Local HostName       Answer \n");
        }

        for (int i = 0; i < allResources.size(); i++) {
            LocalDateTime timePoint = LocalDateTime.now();
            InetAddress addr = InetAddress.getLocalHost();
            log.append(timePoint + " | ");
            log.append(addr.getHostAddress() + " | ");
            log.append(addr.getHostName() + " | ");
            log.append(resourceCreator(allResources.get(i)) + "\n");
        }

        try (FileWriter writer = new FileWriter(userDirectory + "/Games/temp/temp.txt", true)) {
            writer.write(log.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


    public static String resourceCreator(String resource) {
        File creator = new File(resource);
        String result = "Ошибка в методе resourceCreator, операция не выполнена";

        if (resource.contains(".")) {

            try {
                if (creator.createNewFile()) {
                    result = ("Файл " + resource + " создан");
                    System.out.println("Файл создан");
                } else {
                    result = ("При создании файла " + resource + " произошла ошибка");
                    System.out.println("При создании файла произошла ошибка");
                }

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        } else {

            if (creator.mkdir()) {
                result = ("Каталог " + resource + " создан");
                System.out.println("Каталог создан");
            } else {
                result = ("При создании каталога " + resource + " произошла ошибка");
                System.out.println("При создании каталога произошла ошибка");
            }
        }
        return result;
    }
}
