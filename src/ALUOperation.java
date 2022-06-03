public class ALUOperation {
	public static void add(short[] in) {
		byte r1 = (byte) in[4];
		byte r2 = registers.getRegister(in[2]);
		registers.registersFile[in[1]] += registers.getRegister(in[2]);
		registers.SREG = 0;
		registers.Carry((int) (r1 + r2));
		registers.Overflow(r1, r2, 0);
		registers.Negative((byte) (r1 + r2));
		registers.Sign();
		registers.Zero((byte) (r1 + r2));
		System.out.println("the content of r" + in[1] + " ==> " + registers.getRegister(in[1]));
		registers.print_SREG();
	}
	public static void sub(short[] in) {
		byte r1 = (byte) in[4];
		byte r2 = registers.getRegister(in[2]);
		registers.registersFile[in[1]] -= registers.getRegister(in[2]);
		registers.SREG = 0;
		registers.Carry((int) (r1 - r2));
		registers.Overflow(r1, r2, 1);
		registers.Negative((byte) (r1 - r2));
		registers.Sign();
		registers.Zero((byte) (r1 - r2));
		System.out.println("the content of r" + in[1] + " ==> " + registers.getRegister(in[1]));
		registers.print_SREG();
	}
	public static void mul(short[] in) {
		byte r1 = (byte) in[4];
		byte r2 = registers.getRegister(in[2]);
		registers.registersFile[in[1]] *= registers.getRegister(in[2]);
		registers.SREG = 0;
		registers.Carry((int) (r1 * r2));
		registers.Negative((byte) (r1 * r2));
		registers.Zero((byte) (r1 * r2));
		System.out.println("the content of r" + in[1] + " ==> " + registers.getRegister(in[1]));
		System.out.println("the content of pc  ==> " + dataPath.pc);
		registers.print_SREG();
	}
	public static void eor(short[] in) {
		byte r1 = (byte) in[4];
		byte r2 = registers.getRegister(in[2]);
		registers.registersFile[in[1]] ^= registers.getRegister(in[2]);
		registers.SREG = 0;
		registers.Negative((byte) (r1 ^ r2));
		registers.Zero((byte) (r1 ^ r2));
		System.out.println("the content of r" + in[1] + " ==> " + registers.getRegister(in[1]));
		registers.print_SREG();
	
	}
	public static void br(short[] in) {
		pipeline.controlHazardFlage = true;
		byte r2 = registers.getRegister(in[2]);
		dataPath.pc = (short) ((in[4] << 8) + r2);
		System.out.println("the content of pc  ==> " + (dataPath.pc));
	}
	public static void movi(short[] in) {
		registers.registersFile[in[1]] = (byte) in[3];
		System.out.println("the content of r" + in[1] + " ==> " + registers.getRegister(in[1]));
	}
	public static void beqz(short[] in) {
		pipeline.controlHazardFlage = true;
		if (in[4] == 0)
			dataPath.pc += in[3] - 1;
//		to be at locaticon
		System.out.println("the content of pc  ==> " + (dataPath.pc));
	}
	public static void andi(short[] in) {
		registers.registersFile[in[1]] = (byte) (in[4] & (in[3]));
		registers.SREG = 0;
		registers.Negative((byte) (registers.getRegister(in[1])));
		registers.Zero((byte) (registers.getRegister(in[1])));
		System.out.println("the content of r" + in[1] + " ==> " + registers.getRegister(in[1]));
		registers.print_SREG();
	}
	public static void ldr(short[] in) {
		registers.registersFile[in[1]] = memory.dataMemory[in[3]];
		System.out.println("the content of r" + in[1] + " ==> " + registers.getRegister(in[1]));
	}
	public static void str(short[] in) {
		memory.dataMemory[in[3]] = registers.getRegister(in[1]);
		System.out.println("data memory of address : " + in[3] + " ==> " + registers.getRegister(in[1]));

	}
	public static void sal(short[] in) {
		registers.registersFile[in[1]] = (byte) (registers.getRegister(in[1]) << in[3]);
		registers.SREG = 0;
		registers.Negative((byte) (registers.getRegister(in[1])));
		registers.Zero((byte) (registers.getRegister(in[1])));
		System.out.println("the content of r" + in[1] + " ==> " + registers.getRegister(in[1]));
		registers.print_SREG();
	}
	public static void sar(short[] in) {
		registers.registersFile[in[1]] = (byte) (registers.getRegister(in[1]) >> in[3]);
		registers.SREG = 0;
		registers.Negative((byte) (registers.getRegister(in[1])));
		registers.Zero((byte) (registers.getRegister(in[1])));
		System.out.println("the content of r" + in[1] + " ==> " + registers.getRegister(in[1]));
		registers.print_SREG();
		}
}