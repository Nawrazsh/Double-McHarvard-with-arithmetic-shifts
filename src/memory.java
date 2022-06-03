public class memory {
	static short[] instructionMemory = new short[1024];
	static int instructionMemoryIndex =0;
	static byte[] dataMemory = new byte[1024];
	static int dataMemoryIndex =0;
	public static void pushInstruction(short in){
		if(instructionMemoryIndex <=1024){
			instructionMemory[instructionMemoryIndex++]=in;
		}
	}
	public static short popInstruction(short in){
		if(in <=1024){
			return instructionMemory[in];
		}
		return 0;
	}	
	public static void printInstructionMemory(){
		System.out.println("*********************************************************************************************************");
		System.out.println();
		System.out.println("@ Instruction memory content");
		for(int i=0;i<1024;i++){
			if(i%15==0&&i!=0)
				System.out.println();
			System.out.print(instructionMemory[i]+" ");
		}
		System.out.println();

	}
	public static void printDataMemory(){
		System.out.println("*********************************************************************************************************");
		System.out.println();
		System.out.println("@ Data memory content");
		for(int i=0;i<1024;i++){
			if(i%15==0&&i!=0)
				System.out.println();
			System.out.print(dataMemory[i]+" ");
		}
		System.out.println();
	}
}
