package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PeopleList {

    private List<String> people;

    private Map<String, Set<Integer>> invertedSearchIndex;

    private MatchStrategy matchStrategy;

    PeopleList() {
        people = new ArrayList<>();
    }

    public void setMatchStrategy(MatchStrategy strategy) {
        matchStrategy = strategy;
    }

    public List<String> matchPeople(String data){
        Set<Integer> indexes = matchStrategy.match(invertedSearchIndex, data);
        List<String> result = new ArrayList<>();
        indexes.forEach(index -> {
            result.add(people.get(index));
        });
        return result;
    }

    public void calculateInvertedSearchIndex() {
        invertedSearchIndex = new HashMap<>();
        people.forEach(person -> {
            Arrays.asList(person.split(" ")).forEach(data -> {
                String dataLC = data.toLowerCase();
                if (!invertedSearchIndex.containsKey(dataLC)) {
                    invertedSearchIndex.put(dataLC, new HashSet<>());
                }
                invertedSearchIndex.get(dataLC).add(people.indexOf(person));
            });
        });
    }

    public void readList(String filePath){
        File file = new File(filePath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                people.add(scanner.nextLine());
            }
        } catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
            System.out.println("No file found: " + file.getAbsolutePath());
        }
        calculateInvertedSearchIndex();
    }

    public void printList() {
        people.forEach(System.out::println);
    }

}
