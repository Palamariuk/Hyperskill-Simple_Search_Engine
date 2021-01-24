package search;

import java.util.*;

public interface MatchStrategy {

    Set<Integer> match(Map<String, Set<Integer>> invertedSearchIndex, String searchData);

}

class MatchAll implements MatchStrategy {

    @Override
    public Set<Integer> match(Map<String, Set<Integer>> invertedSearchIndex, String searchData){
        Set<Integer> indexes = new TreeSet<>();
        invertedSearchIndex.values().forEach(indexes::addAll);
        String[] args = searchData.split(" ");

        for (int i = 0; i < args.length; i++) {
            String argLC = args[i].toLowerCase();
            if(invertedSearchIndex.containsKey(argLC)){
                indexes.retainAll(invertedSearchIndex.get(argLC));
            } else {
                indexes.clear();
                break;
            }
        }

        return indexes;
    }

}

class MatchAny implements MatchStrategy {

    @Override
    public Set<Integer> match(Map<String, Set<Integer>> invertedSearchIndex, String searchData){
        Set<Integer> indexes = new TreeSet<>();
        String[] args = searchData.split(" ");

        for (int i = 0; i < args.length; i++) {
            String argLC = args[i].toLowerCase();
            if(invertedSearchIndex.containsKey(argLC)){
                indexes.addAll(invertedSearchIndex.get(argLC));
            }
        }

        return indexes;
    }

}

class MatchNone implements MatchStrategy {

    @Override
    public Set<Integer> match(Map<String, Set<Integer>> invertedSearchIndex, String searchData){
        Set<Integer> indexes = new TreeSet<>();
        invertedSearchIndex.values().forEach(indexes::addAll);
        String[] args = searchData.split(" ");

        for (int i = 0; i < args.length; i++) {
            String argLC = args[i].toLowerCase();
            if(invertedSearchIndex.containsKey(argLC)){
                indexes.removeAll(invertedSearchIndex.get(argLC));
            }
        }

        return indexes;
    }

}