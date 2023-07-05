package objectList;

// (c) Larry Herman, 2022.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import java.util.Iterator;

public class Driver {

  public static void main(String args[]) {
    ObjectList list= new ObjectList();
    Iterator<Object> iter;

    list.addObject("giraffe");
    list.addObject(Integer.valueOf(12345));
    list.addObject(Character.valueOf('w'));
    list.addObject(Boolean.valueOf(false));
    list.addObject("gerbil");

    iter= list.iterator();

    while (iter.hasNext())
      System.out.println(iter.next());
  }

}
