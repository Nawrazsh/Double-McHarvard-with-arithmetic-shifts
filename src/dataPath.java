public class dataPath {
	static short pc = -1;

	public static void fetch() {
		pc++;

		if (pc < memory.instructionMemoryIndex) {
			pipeline.fetchedInstruction = memory.popInstruction(pc);
			System.out.println("the content of pc  ==> " + dataPath.pc);
			printTheFetchedInstruction();
		}
	}

	public static void decode() {
		printTheDecodedInstruction();
		short in = pipeline.fetchedInstruction;
		short OPCODE = (short) ((in & 0b1111000000000000) >> 12);
		short R1 = (short) ((in & 0b0000111111000000) >> 6);
		short R2 = (short) ((in & 0b0000000000111111));
		byte IMMEDIATE = (byte) ((in & 0b0000000000111111));
		if ((IMMEDIATE >> 5) == 1)
			IMMEDIATE = (byte) -((IMMEDIATE ^ 0b111111) + 1);
		byte valueR1 = registers.getRegister(R1);
		pipeline.decodedInstruction = new short[] { OPCODE, R1, R2, IMMEDIATE,
				valueR1 };
	}

	//
	public static void excute() {
		short[] in = pipeline.decodedInstruction;
		printTheExcutedInstruction();
		switch (in[0]) {
		case 0:
			ALUOperation.add(in);
			break;
		case 1:
			ALUOperation.sub(in);
			break;
		case 2:
			ALUOperation.mul(in);
			break;
		case 3:
			ALUOperation.movi(in);
			break;
		case 4:
			ALUOperation.beqz(in);
			break;
		case 5:
			ALUOperation.andi(in);
			break;
		case 6:
			ALUOperation.eor(in);
			break;
		case 7:
			ALUOperation.br(in);
			break;
		case 8:
			ALUOperation.sal(in);
			break;
		case 9:
			ALUOperation.sar(in);
			break;
		case 10:
			ALUOperation.ldr(in);
			break;
		case 11:
			ALUOperation.str(in);
			break;

		}
	}

	public static void printTheFetchedInstruction() {
		System.out
				.println("             the input parameters for the fetch stage is the pc ( "
						+ dataPath.pc + " )");
		short in = pipeline.fetchedInstruction;
		byte IMMEDIATE = (byte) ((in & 0b0000000000111111)); 
		if ((IMMEDIATE >> 5) == 1) // negative
			IMMEDIATE = (byte) -((IMMEDIATE ^ 0b111111) + 1);
		switch ((in & 0b1111000000000000) >> 12) {
		case 0:
			System.out
					.println("# the instruction that been fetched =======> add r"
							+ ((in & 0b0000111111000000) >> 6)
							+ ",r"
							+ ((in & 0b0000000000111111)));
			break;
		case 1:
			System.out
					.println("# the instruction that been fetched =======> sub r"
							+ ((in & 0b0000111111000000) >> 6)
							+ ",r"
							+ ((in & 0b0000000000111111)));
			break;
		case 2:
			System.out
					.println("# the instruction that been fetched =======> mul r"
							+ ((in & 0b0000111111000000) >> 6)
							+ ",r"
							+ ((in & 0b0000000000111111)));
			break;
		case 6:
			System.out
					.println("# the instruction that been fetched =======> eor r"
							+ ((in & 0b0000111111000000) >> 6)
							+ ",r"
							+ ((in & 0b0000000000111111)));
			break;
		case 7:
			System.out
					.println("# the instruction that been fetched =======> br r"
							+ ((in & 0b0000111111000000) >> 6)
							+ ","
							+ ((in & 0b0000000000111111)));
			break;
		case 3:
			System.out
					.println("# the instruction that been fetched =======> movi r"
							+ ((in & 0b0000111111000000) >> 6)
							+ ","
							+ IMMEDIATE);
			break;
		case 4:
			System.out
					.println("# the instruction that been fetched =======> beqz r"
							+ ((in & 0b0000111111000000) >> 6)
							+ ","
							+ IMMEDIATE);
			break;
		case 5:
			System.out
					.println("# the instruction that been fetched =======> andi r"
							+ ((in & 0b0000111111000000) >> 6)
							+ ","
							+ IMMEDIATE);
			break;
		case 8:
			System.out
					.println("# the instruction that been fetched =======> sal r"
							+ ((in & 0b0000111111000000) >> 6)
							+ ","
							+ IMMEDIATE);
			break;
		case 9:
			System.out
					.println("# the instruction that been fetched =======> sar r"
							+ ((in & 0b0000111111000000) >> 6)
							+ ","
							+ IMMEDIATE);
			break;
		case 10:
			System.out
					.println("# the instruction that been fetched =======> ldr r"
							+ ((in & 0b0000111111000000) >> 6)
							+ ","
							+ IMMEDIATE);
			break;
		case 11:
			System.out
					.println("# the instruction that been fetched =======> str r"
							+ ((in & 0b0000111111000000) >> 6)
							+ ","
							+ IMMEDIATE);
			break;
		}
	}

	public static void printTheExcutedInstruction() {
		short[] in = pipeline.decodedInstruction;
		switch (in[0]) {
		case 0:
			System.out
					.println("             the input parameters for the excute satge are the opcode "
							+ in[0] + " and R1 " + in[1] + " and R2 " + in[2]);

			System.out
					.println("# the instruction that been excuted =======> add r"
							+ in[1] + ",r" + in[2]);
			break;
		case 1:
			System.out
					.println("             the input parameters for the excute satge are the opcode "
							+ in[0] + " and R1 " + in[1] + " and R2 " + in[2]);
			System.out
					.println("# the instruction that been excuted =======> sub r"
							+ in[1] + ",r" + in[2]);
			break;
		case 2:
			System.out
					.println("             the input parameters for the excute satge are the opcode "
							+ in[0] + " and R1 " + in[1] + " and R2 " + in[2]);
			System.out
					.println("# the instruction that been excuted =======> mul r"
							+ in[1] + ",r" + in[2]);
			break;
		case 6:
			System.out
					.println("             the input parameters for the excute satge are the opcode "
							+ in[0] + " and R1 " + in[1] + " and R2 " + in[2]);
			System.out
					.println("# the instruction that been excuted =======> eor r"
							+ in[1] + ",r" + in[2]);
			break;
		case 7:
			System.out
					.println("             the input parameters for the excute satge are the opcode "
							+ in[0] + " and R1 " + in[1] + " and R2 " + in[2]);
			System.out
					.println("# the instruction that been excuted =======> br r"
							+ in[1] + "," + in[2]);
			break;
		case 3:
			System.out
					.println("             the input parameters for the excute satge are the opcode "
							+ in[0]
							+ " and R1 "
							+ in[1]
							+ " and the IMMEDIATE " + in[3]);
			System.out
					.println("# the instruction that been excuted =======> movi r"
							+ in[1] + "," + in[3]);
			break;
		case 4:
			System.out
					.println("             the input parameters for the excute satge are the opcode "
							+ in[0]
							+ " and R1 "
							+ in[1]
							+ " and the IMMEDIATE " + in[3]);
			System.out
					.println("# the instruction that been excuted =======> beqz r"
							+ in[1] + "," + in[3]);
			break;
		case 5:
			System.out
					.println("             the input parameters for the excute satge are the opcode "
							+ in[0]
							+ " and R1 "
							+ in[1]
							+ " and the IMMEDIATE " + in[3]);
			System.out
					.println("# the instruction that been excuted =======> andi r"
							+ in[1] + "," + in[3]);
			break;
		case 8:
			System.out
					.println("             the input parameters for the excute satge are the opcode "
							+ in[0]
							+ " and R1 "
							+ in[1]
							+ " and the IMMEDIATE " + in[3]);
			System.out
					.println("# the instruction that been excuted =======> sal r"
							+ in[1] + "," + in[3]);
			break;
		case 9:
			System.out
					.println("             the input parameters for the excute satge are the opcode "
							+ in[0]
							+ " and R1 "
							+ in[1]
							+ " and the IMMEDIATE " + in[3]);
			System.out
					.println("# the instruction that been excuted =======> sar r"
							+ in[1] + "," + in[3]);
			break;
		case 10:
			System.out
					.println("             the input parameters for the excute satge are the opcode "
							+ in[0]
							+ " and R1 "
							+ in[1]
							+ " and the IMMEDIATE " + in[3]);
			System.out
					.println("# the instruction that been excuted =======> ldr r"
							+ in[1] + "," + in[3]);
			break;
		case 11:
			System.out
					.println("             the input parameters for the excute satge are the opcode "
							+ in[0]
							+ " and R1 "
							+ in[1]
							+ " and the IMMEDIATE " + in[3]);
			System.out
					.println("# the instruction that been excuted =======> str r"
							+ in[1] + "," + in[3]);
			break;
		}
	}

	public static void printTheDecodedInstruction() {
		short in = pipeline.fetchedInstruction;
		System.out
				.println("             the input parameters for the decode satge is the instruction ( "
						+ in + " )");
		byte IMMEDIATE = (byte) ((in & 0b0000000000111111));
		if ((IMMEDIATE >> 5) == 1)
			IMMEDIATE = (byte) -((IMMEDIATE ^ 0b111111) + 1);
		switch ((in & 0b1111000000000000) >> 12) {
		case 0:
			System.out
					.println("# the instruction that been decoded =======> add r"
							+ ((in & 0b0000111111000000) >> 6)
							+ ",r"
							+ ((in & 0b0000000000111111)));
			break;
		case 1:
			System.out
					.println("# the instruction that been decoded =======> sub r"
							+ ((in & 0b0000111111000000) >> 6)
							+ ",r"
							+ ((in & 0b0000000000111111)));
			break;
		case 2:
			System.out
					.println("# the instruction that been decoded =======> mul r"
							+ ((in & 0b0000111111000000) >> 6)
							+ ",r"
							+ ((in & 0b0000000000111111)));
			break;
		case 6:
			System.out
					.println("# the instruction that been decoded =======> eor r"
							+ ((in & 0b0000111111000000) >> 6)
							+ ",r"
							+ ((in & 0b0000000000111111)));
			break;
		case 7:
			System.out
					.println("# the instruction that been decoded =======> br r"
							+ ((in & 0b0000111111000000) >> 6)
							+ ","
							+ ((in & 0b0000000000111111)));
			break;
		case 3:
			System.out
					.println("# the instruction that been decoded =======> movi r"
							+ ((in & 0b0000111111000000) >> 6)
							+ ","
							+ IMMEDIATE);
			break;
		case 4:
			System.out
					.println("# the instruction that been decoded =======> beqz r"
							+ ((in & 0b0000111111000000) >> 6)
							+ ","
							+ IMMEDIATE);
			break;
		case 5:
			System.out
					.println("# the instruction that been decoded =======> andi r"
							+ ((in & 0b0000111111000000) >> 6)
							+ ","
							+ IMMEDIATE);
			break;
		case 8:
			System.out
					.println("# the instruction that been decoded =======> sal r"
							+ ((in & 0b0000111111000000) >> 6)
							+ ","
							+ IMMEDIATE);
			break;
		case 9:
			System.out
					.println("# the instruction that been decoded =======> sar r"
							+ ((in & 0b0000111111000000) >> 6)
							+ ","
							+ IMMEDIATE);
			break;
		case 10:
			System.out
					.println("# the instruction that been decoded =======> ldr r"
							+ ((in & 0b0000111111000000) >> 6)
							+ ","
							+ IMMEDIATE);
			break;
		case 11:
			System.out
					.println("# the instruction that been decoded =======> str r"
							+ ((in & 0b0000111111000000) >> 6)
							+ ","
							+ IMMEDIATE);
			break;
		}
	}
}
