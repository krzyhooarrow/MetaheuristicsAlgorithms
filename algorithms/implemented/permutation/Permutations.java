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

        Random generator = new Random();
        for (int i =0 ; i < generator.nextInt(((Double) Math.sqrt(list.size())).intValue());i++) {
            T head = list.getFirst();
            list.remove(head);
            list.add(head);
        }

        List<T> subList = list.subList(0,((Double) Math.sqrt(list.size())).intValue());

        return list.size()-2 - ((Double) Math.sqrt(list.size())).intValue() <= permutationSize
                ?
                Generator.combination(list)
                .simple(permutationSize)
                .stream().collect(Collectors.toList())
                :
                Generator.combination(list.subList(((Double) Math.sqrt(list.size())).intValue()+1,list.size()-1))
                .simple(permutationSize)
                .stream().map(element-> {
                    LinkedList<T> combinations = new LinkedList<>(subList);
                    combinations.addAll(element);
                return combinations;
                })
                .collect(Collectors.toList());
    }

}