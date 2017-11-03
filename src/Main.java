import com.sun.istack.internal.NotNull;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        /*Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6);
        observable.subscribe(
                System.out::println,
                System.out::println,
                //(Throwable throwable) -> System.out.println("some throuble"),
                () -> System.out.println("complete")
        );*/
        /*Observable
                .from(new String[] {"some1", "some2", "some3", "so", "sss"})
                .map(String::toLowerCase)
                .filter(s -> s.length() > 2)
                //.flatMap(s -> "new text")
                .subscribe(System.out::println);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Vasya");
        arrayList.add("Dima");
        arrayList.add("Artur");
        arrayList.add("Petya");
        arrayList.add("Roma");
        task1(arrayList)
                .subscribe(System.out::println);*/

        /*BufferedReader stream = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> array = new ArrayList<>();
        while (true) {
            String str = stream.readLine();
            array.add(str);
            task2(Observable
                    .from(array)
                    .asObservable()
            );
        }*/
        //createPerson();

        Observable<String> obs = Observable.just(
                "x", "x2", "x3", "x4",
                "x3", "x4", "x1", "end", "END", "exit");
        task2(obs)
                .subscribe(System.out::println);
        Observable<Integer> summ = Observable.just(
                1, 2, 3, 4, 5
        );
        sum(summ)
                .subscribe(System.out::println);

        Observable<Integer> o1 = Observable.just(
                1, 3, 95, 25
        );
        Observable<Integer> o2 = Observable.just(
                25, 23, 14, 89,200, 190
        );
        task4(Observable.just(false).asObservable(),
                o1, o2)
                .subscribe(System.out::println);

        System.out.println("---------------------");
        System.out.println(BigInteger.valueOf(100));;
    }

    public static void createPerson() {
        List<String> names = new ArrayList<>();
        names.add("Vasya");
        names.add("Alex");
        names.add("Petr");
        List<Integer> ages = new ArrayList<>();
        ages.add(16);
        ages.add(20);
        CreatePerson.outPersons(CreatePerson.create(Observable.from(names), Observable.from(ages)));
        List<Person> persons = CreatePerson.getPersons(CreatePerson.create(Observable.from(names), Observable.from(ages)));
        System.out.println(persons.toString());
    }

    @NotNull
    public static Observable<Integer> task1(@NotNull List<String> list) {
        return Observable
                .from(list)
                .map(String::toLowerCase)
                .filter(s -> s.contains("r"))
                .map(String::length)
                //.map(s -> s.length())
                .asObservable();
    }

    @NotNull
    public static Observable<String> task2(@NotNull Observable<String> observable) {
        return Observable
                .just("")
                .flatMap(s -> observable.asObservable())
                //.flatMap(s -> s.substring(0, s.indexOf("END")))
                //.map(s -> s.substring(0, s.indexOf("END")-1))
                .toList()
                .map(strings -> strings.subList(0, strings.indexOf("END")))
                .flatMap(strings -> Observable.from(strings))
                //.filter(s -> !s.endsWith("END"))
                .distinct()
                .doOnError(System.out::println)
                .asObservable();
    }

    @NotNull
    public static Observable<Integer> sum(@NotNull Observable<Integer> observable) {
        return Observable.just(0)
                .flatMap(integer -> observable.asObservable())
                //.map(sum(observable).asObservable())
                //.flatMap(r -> Observable.from(r))
                //.flatMap(Observable.)
                .toList()
                .flatMap(integers -> {
                    int sum = 0;
                    for (int i : integers)
                        sum += i;
                    return Observable.just(sum);
                })
                .asObservable();


    }

    @NotNull
    public static Observable<Integer> task4(@NotNull Observable<Boolean> flagObservable,
                                            @NotNull Observable<Integer> first, @NotNull Observable<Integer> second) {
        return Observable
                .just(0)
                .flatMap(integer -> flagObservable.asObservable())
                /*.flatMap(r -> {
                    if (r)
                        return first.asObservable();
                    else
                        return second.asObservable();
                })*/
                .flatMap(r -> (r) ? first.asObservable() : second.asObservable())
                .flatMap(integer -> (integer < 99) ? Observable.just(integer) : Observable.error(new Throwable()))
                //.filter(integer -> integer < 99)
                .doOnError(System.out::println)
                .asObservable();
    }

    public static Observable<Integer> fun(Observable<Integer> o) {
        o.flatMap(integer -> {
            if (integer < 99)
                return o
                        .filter(integer1 -> integer1 < 99)
                        .asObservable();
            else
                return Observable
                        .just(new Throwable())
                        .asObservable();
        })
                .asObservable();
        return o;
    }

    @NotNull
    public static Observable<Integer> gcdsObservable(@NotNull Observable<Integer> first,
                                                     @NotNull Observable<Integer> second) {
        return Observable.just(0)
                ;
    }
}
