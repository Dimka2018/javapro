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
            new AbstractMap.SimpleEntry<>("й", ""),
            new AbstractMap.SimpleEntry<>("ц", ""),
            new AbstractMap.SimpleEntry<>("у", ""),
            new AbstractMap.SimpleEntry<>("к", ""),
            new AbstractMap.SimpleEntry<>("е", ""),
            new AbstractMap.SimpleEntry<>("н", ""),
            new AbstractMap.SimpleEntry<>("г", ""),
            new AbstractMap.SimpleEntry<>("ш", ""),
            new AbstractMap.SimpleEntry<>("щ", ""),
            new AbstractMap.SimpleEntry<>("з", ""),
            new AbstractMap.SimpleEntry<>("х", ""),
            new AbstractMap.SimpleEntry<>("ъ", ""),
            new AbstractMap.SimpleEntry<>("ф", ""),
            new AbstractMap.SimpleEntry<>("ы", ""),
            new AbstractMap.SimpleEntry<>("в", ""),
            new AbstractMap.SimpleEntry<>("а", ""),
            new AbstractMap.SimpleEntry<>("п", ""),
            new AbstractMap.SimpleEntry<>("р", ""),
            new AbstractMap.SimpleEntry<>("о", ""),
            new AbstractMap.SimpleEntry<>("л", ""),
            new AbstractMap.SimpleEntry<>("д", ""),
            new AbstractMap.SimpleEntry<>("ж", ""),
            new AbstractMap.SimpleEntry<>("э", ""),
            new AbstractMap.SimpleEntry<>("я", ""),
            new AbstractMap.SimpleEntry<>("ч", ""),
            new AbstractMap.SimpleEntry<>("с", ""),
            new AbstractMap.SimpleEntry<>("м", ""),
            new AbstractMap.SimpleEntry<>("и", ""),
            new AbstractMap.SimpleEntry<>("т", ""),
            new AbstractMap.SimpleEntry<>("ь", ""),
            new AbstractMap.SimpleEntry<>("б", ""),
            new AbstractMap.SimpleEntry<>("ю", ""),
            new AbstractMap.SimpleEntry<>(".", "")
    );

    public static List<String> transform(String text) {
        return List.of(transform(text, rusTransformMap), transform(text, engTransformMap));
    }

    private static String transform(String text, Map<String, String> transformMap) {
        return Arrays.stream(text.split(""))
                .map(literal -> transformMap.getOrDefault(literal, literal))
                .collect(Collectors.joining());
    }
}
