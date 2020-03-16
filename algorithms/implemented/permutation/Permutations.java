package algorithms.permutation;

import com.google.common.collect.Lists;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.paukov.combinatorics3.Generator;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Permutations<T> {

    public List<List<T>> combine(int permutationSize,LinkedList<T> list){
        return  Generator.combination(list)
                .simple(permutationSize)
                .stream().collect(Collectors.toList());
    }

    public  long factorial(int n) {
        if (n > 20 || n < 0) throw new IllegalArgumentException(n + " is out of range");
        return LongStream.rangeClosed(2, n).reduce(1, (a, b) -> a * b);
    }
    public  <T> List<T> permutation(long no, List<T> items) {
        return permutationHelper(no,
                new LinkedList<>(Objects.requireNonNull(items)),
                new ArrayList<>());
    }
    private  <T> List<T> permutationHelper(long no, LinkedList<T> in, List<T> out) {
        if (in.isEmpty()) return out;
        long subFactorial = factorial(in.size() - 1);
        out.add(in.remove((int) (no / subFactorial)));
        return permutationHelper((int) (no % subFactorial), in, out);
    }
    @SafeVarargs
    @SuppressWarnings("varargs") // Creating a List from an array is safe
    public  final <T> Stream<Stream<T>> of(T... items) {
        List<T> itemList = Arrays.asList(items);
        return LongStream.range(0, factorial(items.length))
                .mapToObj(no -> permutation(no, itemList).stream());
    }

}