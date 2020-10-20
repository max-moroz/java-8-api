package ua.com.foxminded.java8api;

import ua.com.foxminded.java8api.collector.DataCollector;
import ua.com.foxminded.java8api.context.Context;
import ua.com.foxminded.java8api.table.TableCreator;


public class Main {

    private static final Context context = new Context();
    private static final DataCollector dataCollector = context.getObject(DataCollector.class);
    private static TableCreator tableCreator = new TableCreator(dataCollector);

    public static void main(String[] args) {
        System.out.println(tableCreator.createTable());
    }

    public static void setTableCreator(TableCreator newTableCreator) {
        tableCreator = newTableCreator;
    }
}
