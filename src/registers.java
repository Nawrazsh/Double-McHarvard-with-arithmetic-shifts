public class registers {
	static byte[] registersFile = new byte[64];
	static byte SREG = (byte) 0;
	static int registersFileIndex = 8;

	public static void printregistersFile() {
		System.out.println("@ Register file content");
		for (int i = 0; i < 64; i++) {
			if (i % 15 == 0 && i != 0)
				System.out.println();
			System.out.print("r" + i + ":" + registersFile[i] + " ");
		}
		System.out.println();
		System.out.println("the content of pc register:" + dataPath.pc);
		print_SREG();
		System.out.println();
	}

	public static byte getRegister(int in) {
		return registersFile[in];
	}

	public static void Carry(int in) {
		SREG = (byte) (SREG & 0b11101111);
		if (in > 127 || in < -128)
			SREG += 16;
	}

	public static void Overflow(byte r1, byte r2, int a) {
		SREG = (byte) (SREG & 0b11110111);
		if (a == 0) {
			if (r1 * r2 > 0)
				if ((r1 > 0 && (byte) (r1 + r2) < 0)
						|| (r1 < 0 && (byte) (r1 + r2) > 0))
					SREG += 8;
		}
		if (a == 1)
			if (r1 * r2 < 0)
				if (((byte) (r1 - r2) > 0 && (r2 > 0))
						|| ((byte) (r1 - r2) < 0 && (r2 < 0))) {
					SREG += 8;
				}
	}

	public static void Negative(byte in) {
		SREG = (byte) (SREG & 0b11111011);
		if (in < 0)
			SREG += 4;

	}

	public static void Sign() {
		SREG = (byte) (SREG & 0b11111101);
		byte of = (byte) ((SREG & 0B00001000) >> 3);
		byte n = (byte) ((SREG & 0B00000100) >> 2);
		if ((n ^ of) == 1)
			SREG += 2;

	}

	public static void Zero(byte in) {
		SREG = (byte) (SREG & 0b11111110);
		if (in == 0)
			SREG += 1;

	}

	public static void print_SREG() {
		String x = Integer.toBinaryString(SREG);
		while (x.length() < 8)
			x = "0" + x;
		System.out.println("the content of the SREG ==> " + x);
	}

}
