package algorithms.implemented.permutation;

import com.google.common.collect.Lists;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.paukov.combinatorics3.Generator;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Permutations<T> {

    public List<List<T>> combine(int permutationSize,LinkedList<T> list){

        LinkedList<T> copy = new LinkedList<>(list);

        Random generator = new Random();
        for (int i =0 ; i < generator.nextInt(((Double) Math.sqrt(list.size())).intValue());i++) {
            T head = copy.getFirst();
            copy.remove(head);
            copy.add(head);
        }

        return  Generator.combination(copy.subList(0,((Double) Math.sqrt(list.size())).intValue()))
                .simple(permutationSize)
                .stream().collect(Collectors.toList());
    }

}