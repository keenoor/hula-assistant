package com.keenor.hulaassistant.service;

import com.google.common.collect.Lists;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.observers.FutureObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:
 * -----------------------------------------------
 * Author:      chenliuchun
 * Date:        2019-03-13 10:59
 * Revision history:
 * Date         Description
 * --------------------------------------------------
 */

public class StreamTest {

    @Test
    public void stream() {
        String[] arr1 = {"a", "b", "c", "d", "e", "f"};
        List<String> listA = new ArrayList<>(Arrays.asList(arr1));

        String[] arr2 = {"d", "e", "f", "g", "h"};
        List<String> listB = new ArrayList<>(Arrays.asList(arr2));

        Set<String> set = new HashSet<>(listA);
        set.addAll(listB);
        List<String> list = new ArrayList<>(set);
        System.out.println(list);

        List<String> collect = Stream.of(listA, listB)
                .flatMap(new Function<List<String>, Stream<? extends String>>() {
                    @Override
                    public Stream<? extends String> apply(List<String> strings) {
                        return strings.stream();
                    }
                })
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect);

    }

    public static void main(String[] args) throws InterruptedException {
        //        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3);
        //        ArrayList<String> strings = Lists.newArrayList("a", "b", "c");
        //        ArrayList<Double> doubles = Lists.newArrayList(1.1, 2.2, 3.3);
        ArrayList<Integer> integers = Lists.newArrayList(1);
        ArrayList<Integer> integers2 = Lists.newArrayList(2);
        ArrayList<String> strings = Lists.newArrayList("a");
        ArrayList<String> strings2 = Lists.newArrayList("b");
        ArrayList<Double> doubles = Lists.newArrayList(0.1);

        Observable<String> callable = Observable.fromCallable(() -> {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "-> " + new Date());
            return System.currentTimeMillis() + "";
        });


        //        Disposable subscribe1 = Observable.zip(Observable.fromIterable(integers),
        //                Observable.fromIterable(strings),
        //                (t1, t2) -> t1 + t2)
        //                .subscribe(System.out::println);
        System.out.println(Thread.currentThread().getName() + "-> " + new Date());
        Observable<Integer> zip = Observable.zip(
                Lists.newArrayList(
                        Observable.fromIterable(integers),
                        Observable.fromIterable(integers2)),
                a -> (Integer) a[0] + (Integer) a[1]);

        Observable<String> zip2 = Observable.zip(
                Lists.newArrayList(callable, callable, callable),
                a -> {
                    String collect = Stream.of(a)
                            .map(i -> (String) i)
                            .map(i -> i.concat("-"))
                            .collect(Collectors.joining());
                    return collect;
                });
        zip2.subscribeOn(Schedulers.computation())
                .subscribe(System.out::println);

        Thread.sleep(4000);
        System.out.println(Thread.currentThread().getName() + "-> " + new Date());

        //        Observable.zip(zip, zip2, (a, b) -> a + "_" + b)
        //                .subscribeOn(Schedulers.io())
        //                .blockingSubscribe(System.out::println);

        System.out.println(new Date());
    }

    @Test
    public void flatmap() {
        Observable.range(1, 5)
                .flatMap(i -> Observable.just(String.valueOf(i)))
                .subscribe(System.out::println);
    }

}