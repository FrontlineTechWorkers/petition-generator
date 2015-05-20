import java.util.Set;
import java.util.HashSet;
import java.lang.reflect.Array;
// I/O
import java.util.Scanner;
// File I/O
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
// Iterator
import java.util.Iterator;

public class PetitionGenerator {
  private Set<String> lastNames = new HashSet<String>();
  private Set<String> doubleLastNames = new HashSet<String>();
  private Set<String> firstNames = new HashSet<String>();
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
          int length = name.length();
          if (length == 4) {
            // Dealing with double Last Name
            String lastName = "" + name.charAt(0) + name.charAt(1);
            doubleLastNames.add(lastName);
            String firstName = "" + name.charAt(2);
            firstNames.add(firstName);
            firstName = "" + name.charAt(3);
            firstNames.add(firstName);
          } else {
            for (int i = 0; i < length; ++i) {
              String nameChar = "" + name.charAt(i);
              if (i == 0) {
                // Storing Last Name
                lastNames.add(nameChar);
              } else {
                // Storing First Name
                firstNames.add(nameChar);
              }
            }
          }
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

  public void list() {
    Iterator<String> iter = lastNames.iterator();
    while (iter.hasNext()) {
      System.out.println(iter.next());
    }
    System.out.println();
    iter = doubleLastNames.iterator();
    while (iter.hasNext()) {
      System.out.println(iter.next());
    }
    System.out.println();
    iter = firstNames.iterator();
    while (iter.hasNext()) {
      System.out.println(iter.next());
    }
    System.out.println();
  }

  public void generate() {

  }

  public static void main(String[] args) {
    System.out.println("How many names do you want?");
    Scanner cin = new Scanner(System.in);
    int number;
    try {
      number = Integer.parseInt(cin.next());
    } catch (Exception e) {
      System.out.println("Input must be a positive integer");
      return;
    }
    PetitionGenerator pg = new PetitionGenerator();
    //pg.list();
    for (int i = 0; i < number; ++i) {
      pg.generate();
    }
  }
}
