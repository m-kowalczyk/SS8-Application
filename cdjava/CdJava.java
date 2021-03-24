import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CdJava {
  protected CdJava() {}

  /* Creates a string representing the path based on path1 which represents the current directory
     and path2 which represents the new directory
   */
  protected String makeString(String path1, String path2) {
    Character first = path2.charAt(0);
    Character last = path2.charAt(path2.length() - 1);
    Scanner breakPath;
    if (first.equals('/') && last.equals('/')) {
      breakPath = new Scanner(path2.substring(0, path2.length() - 1));
    }
    else if (first.equals('/')) {
      breakPath = new Scanner(path2);
    }
    else if (last.equals('/')) {
      breakPath = new Scanner(path1 + "/" + path2.substring(0, path2.length() - 1));
    }
    else {
      breakPath = new Scanner(path1 + "/" + path2);
    }
    breakPath.useDelimiter("/");

    List<String> parts = new ArrayList<String>();

    /* Edits the final list of path pieces based on whether a piece is ".", "..",
       "" (representing "/"), or a regular string
    */
    while (breakPath.hasNext()) {
      String part = breakPath.next();
      for (int i = 0; i < parts.size(); i++) {
        String s = parts.get(i);
        if (part.equals(s)) {
          parts = parts.subList(0, i);
          break;
        }
      }
      switch (part) {
        case ".":
          break;
        case "..":
          if (parts.size() > 0) {
            parts.remove(parts.size() - 1);
          }
          break;
        case "":
          if (!breakPath.hasNext()) {
            parts.clear();
          }
          break;
        default:
          parts.add(part);
          break;
      }
    }
    breakPath.close();

    /* Checks whether or not a path consists of valid characters and combines all the parts in the
       the List parts into a new path or an error message
     */
    String finalPath = "/";
    if (parts.size() == 0) {
      finalPath = "/";
    }
    else if (parts.size() == 1) {
      finalPath = finalPath + parts.get(0);
    }
    else {
      finalPath = finalPath + parts.get(0);
      for (int i = 1; i < parts.size(); i++) {
        String p = parts.get(i);
        boolean isValid = p.chars().allMatch(Character::isLetterOrDigit) || p.equals("..")
            || p.equals(".");
        if (isValid) {
          finalPath = finalPath + "/" + p;
        }
        else {
          finalPath = path2 + ": No such file or directory";
          break;
        }
      }
    }

    return finalPath;
  }

  public static void main(String[] args) {
    Scanner scanInput = new Scanner(System.in);
    String path1 = scanInput.next();
    String path2 = scanInput.next();
    scanInput.close();

    CdJava cj = new CdJava();
    String finalString = cj.makeString(path1, path2);
    System.out.println(finalString);
  }
}
