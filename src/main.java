public class main {
	
	public static void main(String[] args) throws Exception {
//		to read from the text file
		converter.readText();
//		the pipelining part
		pipeline.piplining();
		System.out.println();
		System.out.println("                 $$ the program is now end $$");
		System.out.println();
		System.out.println("*********************************************************************************************************");
		System.out.println();
		System.out.println("the clock cycles ==> " + pipeline.numberOfCycles);
//		printing the content of the registers and the data and the instructions memories
		registers.printregistersFile();
		memory.printDataMemory();
		System.out.println();
		memory.printInstructionMemory();
	}
}
