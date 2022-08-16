package com.dimka.javapro.utils;

import lombok.experimental.UtilityClass;

import java.util.*;
import java.util.stream.Collectors;

@UtilityClass
public class TextUtils {

    private static final Map<String, String> engTransformMap = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("q", "й"),
            new AbstractMap.SimpleEntry<>("w", "ц"),
            new AbstractMap.SimpleEntry<>("e", "у"),
            new AbstractMap.SimpleEntry<>("r", "к"),
            new AbstractMap.SimpleEntry<>("t", "е"),
            new AbstractMap.SimpleEntry<>("y", "н"),
            new AbstractMap.SimpleEntry<>("u", "г"),
            new AbstractMap.SimpleEntry<>("i", "ш"),
            new AbstractMap.SimpleEntry<>("o", "щ"),
            new AbstractMap.SimpleEntry<>("p", "з"),
            new AbstractMap.SimpleEntry<>("[", "х"),
            new AbstractMap.SimpleEntry<>("]", "ъ"),
            new AbstractMap.SimpleEntry<>("a", "ф"),
            new AbstractMap.SimpleEntry<>("s", "ы"),
            new AbstractMap.SimpleEntry<>("d", "в"),
            new AbstractMap.SimpleEntry<>("f", "а"),
            new AbstractMap.SimpleEntry<>("g", "п"),
            new AbstractMap.SimpleEntry<>("h", "р"),
            new AbstractMap.SimpleEntry<>("j", "о"),
            new AbstractMap.SimpleEntry<>("k", "л"),
            new AbstractMap.SimpleEntry<>("l", "д"),
            new AbstractMap.SimpleEntry<>(";", "ж"),
            new AbstractMap.SimpleEntry<>("'", "э"),
            new AbstractMap.SimpleEntry<>("z", "я"),
            new AbstractMap.SimpleEntry<>("x", "ч"),
            new AbstractMap.SimpleEntry<>("c", "с"),
            new AbstractMap.SimpleEntry<>("v", "м"),
            new AbstractMap.SimpleEntry<>("b", "и"),
            new AbstractMap.SimpleEntry<>("n", "т"),
            new AbstractMap.SimpleEntry<>("m", "ь"),
            new AbstractMap.SimpleEntry<>(",", "б"),
            new AbstractMap.SimpleEntry<>(".", "ю"),
            new AbstractMap.SimpleEntry<>("`", ".")
    );

    private static final Map<String, String> rusTransformMap = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("й", "q"),
            new AbstractMap.SimpleEntry<>("ц", "w"),
            new AbstractMap.SimpleEntry<>("у", "e"),
            new AbstractMap.SimpleEntry<>("к", "r"),
            new AbstractMap.SimpleEntry<>("е", "t"),
            new AbstractMap.SimpleEntry<>("н", "y"),
            new AbstractMap.SimpleEntry<>("г", "u"),
            new AbstractMap.SimpleEntry<>("ш", "i"),
            new AbstractMap.SimpleEntry<>("щ", "o"),
            new AbstractMap.SimpleEntry<>("з", "p"),
            new AbstractMap.SimpleEntry<>("х", "["),
            new AbstractMap.SimpleEntry<>("ъ", "]"),
            new AbstractMap.SimpleEntry<>("ф", "a"),
            new AbstractMap.SimpleEntry<>("ы", "s"),
            new AbstractMap.SimpleEntry<>("в", "d"),
            new AbstractMap.SimpleEntry<>("а", "f"),
            new AbstractMap.SimpleEntry<>("п", "g"),
            new AbstractMap.SimpleEntry<>("р", "h"),
            new AbstractMap.SimpleEntry<>("о", "j"),
            new AbstractMap.SimpleEntry<>("л", "k"),
            new AbstractMap.SimpleEntry<>("д", "l"),
            new AbstractMap.SimpleEntry<>("ж", ";"),
            new AbstractMap.SimpleEntry<>("э", "'"),
            new AbstractMap.SimpleEntry<>("я", "z"),
            new AbstractMap.SimpleEntry<>("ч", "x"),
            new AbstractMap.SimpleEntry<>("с", "c"),
            new AbstractMap.SimpleEntry<>("м", "v"),
            new AbstractMap.SimpleEntry<>("и", "b"),
            new AbstractMap.SimpleEntry<>("т", "n"),
            new AbstractMap.SimpleEntry<>("ь", "m"),
            new AbstractMap.SimpleEntry<>("б", ","),
            new AbstractMap.SimpleEntry<>("ю", "."),
            new AbstractMap.SimpleEntry<>(".", "/")
    );

    public static List<String> transform(String text) {
        List<List<String>> list = Arrays.stream(text.split(" "))
                .map(word -> List.of(transform(word, rusTransformMap), transform(word, engTransformMap)))
                .collect(Collectors.toList());
        List<String> result = new ArrayList<>();
        for (List<String> part : list) {
            result = combine(result, part);
        }
        return result;
    }

    private static String transform(String text, Map<String, String> transformMap) {
        return Arrays.stream(text.split(""))
                .map(literal -> transformMap.getOrDefault(literal, literal))
                .collect(Collectors.joining());
    }

    private static List<String> combine(List<String> firstPart, List<String> secondPart) {
        if (firstPart.isEmpty())
            return secondPart;
        List<String> result = new ArrayList<>();
        for (String firstElement : firstPart) {
            for (String secondElement : secondPart) {
                result.add(firstElement + " " + secondElement);
            }
        }
        return result;
    }
}
