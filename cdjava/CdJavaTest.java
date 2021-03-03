import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CdJavaTest {
  CdJava cj = new CdJava();

  @Test
  public void testMakeString() {
    assertEquals("C:", cj.makeString("/", "C:"));
    assertEquals("C:/Albedo/MonoBleedingEdge",
        cj.makeString("C:/Albedo", "MonoBleedingEdge"));
    assertEquals("C:", cj.makeString("C:/Albedo", ".."));
    assertEquals("C:", cj.makeString("C:/Albedo", "C:"));
    assertEquals("C:/Algo1", cj.makeString("C:/Albedo", "C:/Algo1"));
    assertEquals("/", cj.makeString("C:/Albedo", "../.."));
    assertEquals("/", cj.makeString("C:/Albedo", "../../.."));
    assertEquals("C:/Albedo", cj.makeString("C:/Albedo", "."));
    assertEquals("C:/Albedo/..Algo1", cj.makeString("C:/Albedo", "..Algo1"));
    assertEquals("/", cj.makeString("C:/Albedo", "/////"));
    assertEquals("C:/Albedo/.....", cj.makeString("C:/Albedo", "....."));
    assertEquals("C:/Animation", cj.makeString("C:/Albedo", "../Algo1///../Animation/."));
  }

  @Test
  public void testMakePath() {
    assertEquals("C:\\Albedo\\MonoBleedingEdge",
        cj.makePath("C:/Albedo/MonoBleedingEdge", "MonoBleedingEdge"));
    assertEquals("\\", cj.makePath("/", "../.."));
    assertEquals("-: No such file or directory", cj.makePath("C:/-", "-"));
    assertEquals("..Algo1: No such file or directory",
        cj.makePath("C:/Albedo/..Algo1", "..Algo1"));
    assertEquals(".....: No such file or directory",
        cj.makePath("C:/Albedo/.....", "....."));
  }
}