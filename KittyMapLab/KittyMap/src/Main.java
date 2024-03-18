import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.*;

class Main {
  public static void main(String[] args) {
    KittyMap km = new KittyMap(10, 10);
		System.out.println( km + "\n\n");
		km.printKittyCount();
  }
}