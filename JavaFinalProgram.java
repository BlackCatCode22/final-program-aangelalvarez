// Angel Alvarez, April 12th 2023, CIT 63






// JavaFinalProgramDemo01.java
// dH 10 Apr 23
//
// Sample code for Java Spring '23 final program
//
// Notes: This is a good  starting place for your final program, but a unique ID must be generated which starts with a
// two-character representation of species, e.g. Hy01, Li02, Ti01. Names must be found for your newly created animal
// from the animalNames.txt file (you may add names to the list). You must also create birthDate and location. Note the
// hardcoded values for these just to get this demo working with two class files, Animal.class and
// JavaFinalProgramDemo01.class.
//
//
import java.io.*;
import java.util.*;

public class JavaFinalProgram {
    public static void main(String[] args) {
        // Create linked lists for each habitat
        LinkedList<Animal> hyenaHabitat = new LinkedList<>();
        LinkedList<Animal> lionHabitat = new LinkedList<>();
        LinkedList<Animal> tigerHabitat = new LinkedList<>();
        LinkedList<Animal> bearHabitat = new LinkedList<>();
        System.out.println("\n\n***** Writing to file... *****\n\n");

        // Read input files and add animals to their respective habitats
        try {
            Scanner scanner = new Scanner(new File("arrivingAnimals.txt"));

            int numAnimals = 0;
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(", ");
                int age = Integer.parseInt(line[0].split(" ")[0]);
                String species = line[0].split(" ")[4];
                // output species
                System.out.println("\nSpecies = " + species);
                String birthSeason = line[1];
                String color = line[2];
                String gender = line[0].split(" ")[3];
                int weight = Integer.parseInt(line[3].split(" ")[0]);
                String origin = line[4] + ", " + line[5];
                String arrivalDate = genArrivalDate(age);
                // Create a simple ID in the formal "An9"
                String id = genAnimalID(species, hyenaHabitat, lionHabitat, tigerHabitat, bearHabitat);
                String name = genAnimalName(species);
                String birthDate = genBirthDay(birthSeason, age);


                // Animal Constructor looks like this:
                // public Animal(String id, String species, String name, int age, String birthDate, String color,
                //               String gender, int weight, String origin, String arrivalDate)
                Animal animal = new Animal(id, species, name, age, birthDate, color, gender, weight, origin, arrivalDate);
                switch (species) {
                    case "hyena":
                        hyenaHabitat.add(animal);
                        break;
                    case "lion":
                        lionHabitat.add(animal);
                        break;
                    case "tiger":
                        tigerHabitat.add(animal);
                        break;
                    case "bear":
                        bearHabitat.add(animal);
                        break;
                }
                // Output the new animal...
                System.out.println("new animal is: " + animal.toString());


                numAnimals++;
            }

                    scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found.");
        }

        // Output the hyena habitat.
        System.out.println("\nHyena Habitat:\n");
        for (Animal animal : hyenaHabitat) {
            System.out.println(animal.toString());
            System.out.println("number of animals is: " + animal.getNumOfAnimals());
        }

        System.out.println("\nLion Habitat:\n");
        for (Animal animal : lionHabitat) {
            System.out.println(animal.toString());
            System.out.println("number of animals is: " + animal.getNumOfAnimals());
        }

        System.out.println("\nTiger Habitat:\n");
        for (Animal animal : tigerHabitat) {
            System.out.println(animal.toString());
            System.out.println("number of animals is: " + animal.getNumOfAnimals());
        }

        System.out.println("\nBear Habitat:\n");
        for (Animal animal : bearHabitat) {
            System.out.println(animal.toString());
            System.out.println("number of animals is: " + animal.getNumOfAnimals());
        }



        // Create output file and write animal info for each habitat
        // Note the method "toString" -- you must override this in your Animal class or else you will see the object's
        //   name that looks something like: @1d56ce6a.
        // Angel Alvarez, April 12th 2023, CIT 63
        try {
            PrintWriter writer = new PrintWriter(new File("habitatAnimalsFinalJava.txt"));
            writer.write("Final Program Output; by Angel Alvarez, Spring 2023, Fresno, CA\n\n\n");

            // Hyena Habitat
            writer.println("Hyena Habitat:\n");
            for (Animal animal : hyenaHabitat) {
                writer.println(animal.toString());
            }
            writer.println();

            // Lion Habitat
            writer.println("Lion Habitat:\n");
            for (Animal animal : lionHabitat) {
                writer.println(animal.toString());
            }
            writer.println();

            // Tiger Habitat
            writer.println("Tiger Habitat:\n");
            for (Animal animal : tigerHabitat) {
                writer.println(animal.toString());
            }
            writer.println();

            // Bear Habitat
            writer.println("Bear Habitat:\n");
            for (Animal animal : bearHabitat) {
                writer.println(animal.toString());
            }
            writer.println();

            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Output file not found.");
        }

        System.out.println("\n\n***** sucess *****\n\n");
    }

    public static String getRandomMonth() {
        String[] array = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int rand = new Random().nextInt(array.length);
        return array[rand];
    }

    public static String genArrivalDate(int age) {
        // Generate a random year between (year born) + 1 and 2023
        int arrivalYear = new Random().nextInt(2023 - (2023 - age)) + (2023 - age);
        int randomDay = new Random().nextInt(30) + 1;
        return "arrived on " + getRandomMonth() + " " + randomDay + ", " + arrivalYear; 
    }

    public static String genAnimalID(String species, LinkedList<Animal> hyenaHabitat, LinkedList<Animal> lionHabitat, LinkedList<Animal> tigerHabitat, LinkedList<Animal> bearHabitat) {
        String id = "Unknown";
        switch (species) {
            case "hyena":
                id = "Hy" + Integer.toString(hyenaHabitat.size() + 1);
                break;
            case "lion":
                id = "Li" + Integer.toString(lionHabitat.size() + 1);
                break;
            case "tiger":
                id = "Ti" + Integer.toString(tigerHabitat.size() + 1);
                break;
            case "bear":
                id = "Be" + Integer.toString(bearHabitat.size() + 1);
                break;
        }
        return id;
    }

    public static String genBirthDay(String birthSeason, int age) {
        String birthDate = "";
        String month = "";
        int rand = new Random().nextInt(3);
        String[] springMonths = {"March", "April", "May"};
        String[] summerMonths = {"June", "July", "August"};
        String[] fallMonths = {"September", "October", "November"};
        String[] winterMonths = {"December", "January", "February"};
        switch(birthSeason.split(" ")[2]) {
            case "spring":
                month = springMonths[rand];
                break;
            case "summer":
                month = summerMonths[rand];
                break;
            case "fall":
                month = fallMonths[rand];
                break;
            case "winter":
                month = winterMonths[rand];
                break;
            default:
                return "birth date is unknown";
        }
        int year = 2023 - age;
        int randomDay = new Random().nextInt(30) + 1;
        birthDate = "birth date " + month + " " + randomDay + ", " + year; 

        return birthDate;
    }

    public static String genAnimalName(String species) {
        String name = "No name";
        try {
            File file = new File("animalNames.txt");
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                String kind = scanner.nextLine();
                String emptyLine = scanner.nextLine();
                String names = scanner.nextLine();
                if (scanner.hasNextLine()) {
                    String anotherEmptyLine = scanner.nextLine();
                }
                String[] namesArray = names.split(", ");
                if (kind.split(" ")[0].toLowerCase().equals(species)) {
                    int randomIndex = new Random().nextInt(namesArray.length);
                    name = namesArray[randomIndex];
                    return name;
                } 
            } 
            scanner.close();
        } catch (IOException e) {
            System.out.println("File IO exception caught!");
            e.printStackTrace();
        }
        return name;
    }
}