package application;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;

public class DoorElementComparator implements Comparator<Node> {

  @Override
  public int compare(Node o1, Node o2) {
    System.out.println(o1.getId());
    System.out.println(o2.getId());
    Map<String, Integer> priority =  new HashMap<String, Integer>();
    priority.put("left", 0);
    priority.put("top", 1);
    priority.put("right", 2);
    priority.put("bottom", 3);
    priority.put("box", 4);
    return priority.get(o1.getId()).compareTo(priority.get(o2.getId()));
  }
  
}
