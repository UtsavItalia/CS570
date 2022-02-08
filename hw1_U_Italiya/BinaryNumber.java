//Name: Utsav Italiya
//CWID: 10475248

import java.util.Arrays;

public class BinaryNumber {

	private int data[];
	private boolean overflow;

	// Constructor that generates an binary number array of given length
	public BinaryNumber(int length) {
		data = new int[length];
		for (int i = 0; i < length; i++) {
			data[i] = 0;
		}
	}

	// Constructor that converts entered string into integer and corresponding
	// binary number.
	public BinaryNumber(String str) {

		int strLen = str.length();
		// First each character of string is converted into integer and then it is
		// stored in the array.
		data = new int[strLen];
		for (int i = 0; i < strLen; i++) {
			data[i] = Character.getNumericValue(str.charAt(i));
		}
	}

	// gives length of a binary number
	public int getLength() {
		return data.length;
	}

	// shows digit stored on given index. if index is out of bound it will throw an
	// ArrayIndexOutOfBoundsException.
	public int getDigit(int index) throws ArrayIndexOutOfBoundsException {
		return data[index];
	}

	// for shifting binary number to a given amount of positions.
	public void shiftR(int amount) {
		int shiftArr[] = new int[amount + data.length];
		for (int i = amount; i < shiftArr.length; i++) {
			shiftArr[i] = data[i - amount];
		}
		// Arrays.copyOf is used for copying elements from larger array to smaller array
		data = Arrays.copyOf(shiftArr, shiftArr.length);
	}

	// to do addition two binary numbers
	public void add(BinaryNumber aBinaryNumber) throws Exception {
		int carry = 0;
		int answer = 0;

		if (data.length == aBinaryNumber.data.length) {
			for (int i = 0; i < (data.length); i++) {
				answer = data[i] + aBinaryNumber.data[i] + carry;

				if (answer == 3) {
					carry = 1;
					data[i] = 1;
				} else if (answer == 2) {
					carry = 1;
					data[i] = 0;
				} else {
					data[i] = answer;
					carry = 0;
				}
			}
		} else {
			throw new Exception("\nCannot Add Two numbers with different length.");
		}

		if (carry == 0) {
			clearOverflow();
		} else {
			overflow = true;
		}

	}

	// to convert all elements of array and create a corresponding binary number
	@Override
	public String toString() {
		if (overflow == true) {
			return "Overflow";
		}
		String str = "";
		for (int i = 0; i < data.length; i++) {
			str = str + data[i];
		}
		return str;
	}

	// convert binary to decimal
	public int toDecimal() {
		int decimal = 0;
		for (int i = 0; i < data.length; i++) {
			decimal += (int) (data[i] * (Math.pow(2, i)));
		}
		if (overflow == true) {
			decimal += Math.pow(2, data.length);
		}
		return decimal;
	}

	public void clearOverflow() {
		overflow = false;
	}

	public static void main(String args[]) {
		BinaryNumber b1 = new BinaryNumber(6);
		b1 = new BinaryNumber("1101");

		BinaryNumber b2 = new BinaryNumber("1011");

		System.out.println("length of b1: " + b1.getLength());
		System.out.println("length of b2: " + b2.getLength());

		System.out.println("\n3rd digit: " + b1.getDigit(2));
		System.out.println("3rd digit: " + b2.getDigit(2));

		System.out.println("\nb1 before shifting: " + b1.toString());
		b1.shiftR(3);
		System.out.println("b1 after shifting: " + b1.toString());

		System.out.println("\nb2 before shifting: " + b2.toString());
		b2.shiftR(3);
		System.out.println("b2 after shifting: " + b2.toString());

		try { // I have used -1 to show index out of bound error.
			System.out.print("Element of Given Index: " + b1.getDigit(6));
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("\nThe index you have entered is invalid: OutOfBoundError");
		}

		System.out.print("\n\nBinary of B1 before addition: " + b1.toString());
		System.out.println("\nBinary of B2 before addition: " + b2.toString());
		
		System.out.print("\n\nBinary of B1 before addition: " + b1.toDecimal());
		System.out.println("\nBinary of B2 before addition: " + b2.toDecimal());
		
		System.out.print("\n" + b1.toDecimal() + " + " + b2.toDecimal() + " = ");

		try {
			b1.add(b2);
			System.out.println(b1.toDecimal());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
