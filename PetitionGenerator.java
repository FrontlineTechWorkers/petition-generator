import java.util.Set;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PetitionGenerator {
  private Set<String> lastNames;
  private Set<String> firstName;
  static private String defaultFile = "rawname.txt";
  // set.toArray()[rand.nextInt(set.size())]

  public PetitionGenerator() {
    this(defaultFile);
  }

  public PetitionGenerator(String fileName) {
    // Open the file
    try {
      FileInputStream fstream = new FileInputStream(fileName);
    } catch (FileNotFoundException e) {
      System.out.println("Names file not found, plz check if the file exist.");
    }
  }

  public static void main(String[] args) {
    System.out.println("Helo");
    PetitionGenerator pg = new PetitionGenerator();
  }
}
