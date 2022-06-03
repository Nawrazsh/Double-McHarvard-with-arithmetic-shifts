
public class pipeline {
	public static short fetchedInstruction;
	public static short[] decodedInstruction;
	public static boolean controlHazardFlage = false;
	public static int numberOfCycles=0;
	public static void piplining(){
		boolean No_more_ins = false;
		while (true) {
			if (controlHazardFlage) {
				// jump to pc that exceeded the last instruction
				if (dataPath.pc > memory.instructionMemoryIndex - 2) {
					System.out.println("*********************************************************************************************************");
					break;
				}
				// jump to last instruction
				if (dataPath.pc == memory.instructionMemoryIndex - 2) {
					System.out.println("*********************************************************************************************************");
					System.out.println();
					System.out.println("                cycle number : " + ++numberOfCycles);
					System.out.println();
					dataPath.fetch();
					System.out.println("*********************************************************************************************************");
					System.out.println();
					System.out.println("                cycle number : " + ++numberOfCycles);
					System.out.println();
					dataPath.decode();
					System.out.println("*********************************************************************************************************");
					System.out.println();
					System.out.println("                cycle number : " + ++numberOfCycles);
					System.out.println();
					dataPath.excute();
					break;
				}
				// jump to any instruction rather than the last one
				System.out.println("*********************************************************************************************************");
				System.out.println();
				System.out.println("                cycle number : " + ++numberOfCycles);
				System.out.println();
				dataPath.fetch();
				System.out.println("*********************************************************************************************************");
				System.out.println();
				System.out.println("                cycle number : " + ++numberOfCycles);
				System.out.println();
				dataPath.decode();
				dataPath.fetch();
				controlHazardFlage = false;
				// the first cycle
			} else if (dataPath.pc == -1) {
				System.out.println();
				System.out.println("                cycle number : " + ++numberOfCycles);
				System.out.println();
				dataPath.fetch();
			}
			// the second cycle
			else if (dataPath.pc == 0) {
				
				System.out.println("*********************************************************************************************************");
				System.out.println();
				System.out.println("                cycle number : " + ++numberOfCycles);
				System.out.println();
				dataPath.decode();
				dataPath.fetch();
			}
			// last cycle
			else if (No_more_ins) {
				
				System.out.println("*********************************************************************************************************");
				System.out.println();
				System.out.println("                cycle number : " + ++numberOfCycles);
				System.out.println();
				dataPath.excute();
				System.out.println();
				System.out.println("the content of pc  ==> " + (dataPath.pc));
				System.out.println();
				System.out.println("*********************************************************************************************************");
				break;
			}
			// any cycle
			else {
				
				System.out.println("*********************************************************************************************************");
				System.out.println();
				System.out.println("                cycle number : " + ++numberOfCycles);
				System.out.println();
				dataPath.excute();
				// no control hazard
				if (controlHazardFlage == false) {
					dataPath.decode();
					// last by one instruction
					if (dataPath.pc < memory.instructionMemoryIndex - 1) {
						dataPath.fetch();
					}
					// no more instructions
					else {
						System.out.println("the content of pc  ==> "
								+ (dataPath.pc));
						No_more_ins = true;
					}
				}
			}
		}
	}
}
