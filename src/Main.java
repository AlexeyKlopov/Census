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
        System.out.println("Количество несовершеннолетних жителей: ");
        long count = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println(count);
        System.out.println();

        System.out.println("Мужчины призывного возраста: ");
        persons.stream()
                .filter(person -> person.getSex() == Sex.MAN)
                .filter(person -> person.getAge() > 18 && person.getAge() < 27)
                .map(Person::getFamily)
                //.collect(Collectors.toList())//без этой операции список тоже формируется. Idea подсвечивает операцию
                // серым. Что нам дает ее использование здесь? (применение указано в описании к ДЗ)
                .forEach(System.out::println);
        System.out.println();

        System.out.println("Работоспособные мужчины с высшим образованием: ");
        persons.stream()
                .filter(person -> person.getSex() == Sex.MAN)
                .filter(person -> person.getAge() > 18 && person.getAge() < 65)
                .filter(person -> person.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .map(Person::getFamily)
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println();
        System.out.println("Работоспособные женщины с высшим образованием: ");
        persons.stream()
                .filter(person -> person.getSex() == Sex.WOMAN)
                .filter(person -> person.getAge() > 18 && person.getAge() < 60)
                .filter(person -> person.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .map(Person::getFamily)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }
}
