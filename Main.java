package search;

import java.util.*;

public class Main {

    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        PeopleList people = new PeopleList();

        people.readList(args[1]);

        while(true){
            int action = selectAction();
            switch (action){
                case 0: {
                    System.out.println("Bye!");
                    return;
                }
                case 1: {
                    String matchingStrategy = selectMatchingStrategy();
                    System.out.println("Enter a name or email to search all suitable people.");
                    String data = scanner.nextLine();

                    switch (matchingStrategy){
                        case "ALL": {
                            people.setMatchStrategy(new MatchAll());
                            break;
                        }
                        case "ANY": {
                            people.setMatchStrategy(new MatchAny());
                            break;
                        }
                        case "NONE": {
                            people.setMatchStrategy(new MatchNone());
                            break;
                        }
                    }

                    List<String> result = people.matchPeople(data);

                    if(!result.isEmpty()){
                        System.out.printf("%d persons found:\n", result.size());
                        result.forEach(System.out::println);
                    } else {
                        System.out.println("No matching people found.");
                    }
                    System.out.println();
                    break;
                }
                case 2: {
                    System.out.println("=== List of people ===");
                    people.printList();
                    break;
                }
                default: {
                    System.out.println("Incorrect option! Try again.");
                    break;
                }
            }
        }

    }

    public static int selectAction(){
        System.out.println("=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit");
        int action = scanner.nextInt();
        scanner.nextLine();
        return action;
    }

    public static String selectMatchingStrategy(){
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        return scanner.nextLine();
    }
}
