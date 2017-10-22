import java.util.Vector;

public class Main {
	private static final int MB = 1024 * 1024;
	public static void main(String[] args) {
		System.out.println("available processors: " + Runtime.getRuntime().availableProcessors());
		System.out.println("max memory: " + (Runtime.getRuntime().maxMemory() / MB));
		System.out.println("total memory: " + (Runtime.getRuntime().totalMemory() / MB));
		Vector v = new Vector();
		while (true) {
			byte b[] = new byte[1048576];
			v.add(b);
			System.out.println("free memory: " + (Runtime.getRuntime().freeMemory() / MB));
		}
	}
}
