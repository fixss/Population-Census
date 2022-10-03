import ru.netology.Education;
import ru.netology.Person;
import ru.netology.Sex;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {


        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        List<String> mobilization = persons.stream()
                .filter(f -> f.getSex() == Sex.MAN)
                .filter(f -> f.getAge() >= 18 && f.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(mobilization);

        long noMobilization = persons.stream()
                .filter(f -> f.getAge() < 18)
                .count();
        //System.out.println("Total number of minors: " + noMobilization);

        List<Person> potentiallyMobilization = persons.stream()
                .filter(f -> f.getEducation() == Education.HIGHER)
                .filter(f -> f.getAge() >= 18)
                .filter(f -> f.getSex() == Sex.WOMAN && f.getAge() < 60 || f.getSex() == Sex.MAN && f.getAge() < 65)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        //System.out.println(potentiallyMobilization);

    }
}