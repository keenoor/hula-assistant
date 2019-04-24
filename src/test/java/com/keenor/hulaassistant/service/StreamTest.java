package com.keenor.hulaassistant.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static void main(String[] args) {
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

}
