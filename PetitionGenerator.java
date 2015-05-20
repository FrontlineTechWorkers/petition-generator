import java.util.Set;
// File I/O
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
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
    FileInputStream fstream = null;
    BufferedReader br = null;
    try {
      try {
        fstream = new FileInputStream(fileName);
        br = new BufferedReader(new InputStreamReader(fstream));
        String name;
        //Read File Line By Line
        while ((name = br.readLine()) != null)   {
          System.out.print(name);
          System.out.println(" " + name.length());
        }
      } catch (FileNotFoundException e) {
        System.out.println("Names file not found, plz check if the file exist.");
      } catch (Exception e) {
        System.out.println("Error occurs while reading the name file");
      } finally {
        //Close the input stream
        if (br != null) {
          br.close();
        }
        if (fstream != null) {
          fstream.close();
        }
      }
    } catch (Exception e) {
      System.out.println("Error occurs while reading the name file");
    }
  }

  public static void main(String[] args) {
    PetitionGenerator pg = new PetitionGenerator();
  }
}
