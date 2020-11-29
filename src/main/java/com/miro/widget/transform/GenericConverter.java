package com.miro.widget.transform;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import static java.util.stream.Collectors.toList;

public interface GenericConverter<T, U, R> extends BiFunction<T, U, R> {

    default R convert(final T input, U propsToIgnore) {
        R output = null;
        if (input != null) {
            output = this.apply(input, propsToIgnore);
        }
        return output;
    }

    default List<R> convert(final List<T> input, final U propsToIgnore) {
        List<R> output = new ArrayList<>();
        if (input != null) {
            output = input.stream().map(element -> this.apply(element, propsToIgnore)).collect(toList());
        }
        return output;
    }

}
