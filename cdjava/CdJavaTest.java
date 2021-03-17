import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CdJavaTest {
  CdJava cj = new CdJava();

  @Test
  public void testMakeString() {
    assertEquals("/abc", cj.makeString("/", "abc"));
    assertEquals("/abc/def/ghi", cj.makeString("/abc/def", "ghi"));
    assertEquals("/abc", cj.makeString("/abc/def", ".."));
    assertEquals("/abc", cj.makeString("/abc/def", "/abc"));
    assertEquals("/abc/klm", cj.makeString("/abc/def", "/abc/klm"));
    assertEquals("/", cj.makeString("/abc/def", "../.."));
    assertEquals("/", cj.makeString("/abc/def", "../../.."));
    assertEquals("/abc/def", cj.makeString("/abc/def", "."));
    assertEquals("..klm: No such file or directory", cj.makeString("/abc/def", "..klm"));
    assertEquals("/", cj.makeString("/abc/def", "/////"));
    assertEquals("/abc/klm", cj.makeString("/abc/def", "../gh///../klm/."));
    assertEquals("/aaa/bbb/ccc/ddd", cj.makeString("/aaa/bbb/ccc", "ddd"));
    assertEquals("/a/b/c/d", cj.makeString("/a/b/c", "d"));
  }
}
