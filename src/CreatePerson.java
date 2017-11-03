import rx.Observable;

import java.util.ArrayList;
import java.util.List;

public class CreatePerson {
    public CreatePerson() {

    }

    public static Observable<Person> create(Observable<String> names, Observable<Integer> ages) {
        return Observable.zip(names, ages, Person::new);
    }

    public static void outPersons(Observable<Person> persons) {
        persons
                .subscribe(
                        System.out::println,
                        System.out::println,
                        () -> System.out.println("complete")
                );
    }

    public static List<Person> getPersons(Observable<Person> persons) {
        return persons.toList().toBlocking().first();
    }
}
