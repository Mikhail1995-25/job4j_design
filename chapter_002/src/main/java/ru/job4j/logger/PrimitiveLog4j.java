package ru.job4j.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrimitiveLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(PrimitiveLog4j.class.getName());

    public static void main(String[] args) {
        String cat = "Murzik";
        byte age = 5;
        String wood = "Pine";
        short ageWoods = 250;
        String town = "Moskva";
        int ageTown = 870;
        String machine = "Rocket";
        long speed = 29_000;
        String planet = "Earth";
        float weight = 5.972e24F;
        String plan = "Moon";
        double area = 3.793e100;
        String name = "Mikhail Sokolov";
        boolean names = true;
        boolean surname = false;
        String symbol = "Char";
        char c = 'B';
        LOG.debug("Some info : cat : {}, age : {}, wood : {}, ageWoods : {}, town : {}, "
               + " ageTown : {}, machine : {}, speed : {}, planet : {}, weight : {}, "
               + " plan : {}, area : {}, name : {}, names : {}, surname : {}, symbol : {}, c : {}",
                cat, age, wood, ageWoods, town, ageTown, machine, speed, planet, weight, plan,
                 area, name, names, surname, symbol, c);
    }
}
