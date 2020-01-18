package io.github.ctlove0523.pattern.pipeline;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Demo {
    public static void main(String[] args) throws Exception {
        List<Integer> prices = Arrays.asList(1,2,3,4,5);
        prices.stream()
                .map(integer -> {
                    System.out.print(integer+ " ");
                    return integer * 2;
                }).forEach(integer -> { });
    }
}
