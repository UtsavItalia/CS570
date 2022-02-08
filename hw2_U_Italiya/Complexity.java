//Name: Utsav Italiya
//CWID: 10475248

public class Complexity {

	public static void method1(int n) {
		int count = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.println("Operation: " + count);
				count++;
			}
		}
	}

	public static void method2(int n) {
		int count = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					System.out.println("Operation: " + count);
					count++;
				}
			}
		}
	}

	public static void method3(int n) {
		int count = 1;
		for (int i = 1; i < n; i = i * 2) {
			System.out.println("Operation: " + count);
			count++;
//			break;
		}
	}

	public static void method4(int n) {
		int count = 1;
		for (int i = 1; i < n; i = i * 2) {
			for (int j = 0; j < n; j++) {
				System.out.println("Operation: " + count);
				count++;
//				break;
			}

		}
	}

	public static void method5(int n) {
		int count = 1;
		for (int i = 2; i < n; i = (int) Math.pow(i, 2)) {
			System.out.println("Operation: " + count);
			count++;
		}
	}

	public static int method6(int n) {
		if (1 <= n) {
			return (n * method6(n - 1));
		} else {
			return 1;
		}

	}

	public static void main(String[] args) {
		method1(3);
		method2(3);
		method3(100);
		method4(8);
		method5(1600000000);
		System.out.print(method6(5));
	}

}
