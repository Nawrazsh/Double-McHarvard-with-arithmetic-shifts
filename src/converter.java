import java.io.*;
import java.util.Arrays;
public class converter {
	public static int getMachineLanguage(String in){
		int out;
		switch(in){
		case "r0": out =0;break;
		case "r1": out =1;break;
		case "r2": out =2;break;
		case "r3": out =3;break;
		case "r4": out =4;break;
		case "r5": out =5;break;
		case "r6": out =6;break;
		case "r7": out =7;break;
		case "r8": out =8;break;
		case "r9": out =9;break;
		case "r10": out =10;break;
		case "r11": out =11;break;
		case "r12": out =12;break;
		case "r13": out =13;break;
		case "r14": out =14;break;
		case "r15": out =15;break;
		case "r16": out =16;break;
		case "r17": out =17;break;
		case "r18": out =18;break;
		case "r19": out =19;break;
		case "r20": out =20;break;
		case "r21": out =21;break;
		case "r22": out =22;break;
		case "r23": out =23;break;
		case "r24": out =24;break;
		case "r25": out =25;break;
		case "r26": out =26;break;
		case "r27": out =27;break;
		case "r28": out =28;break;
		case "r29": out =29;break;
		case "r30": out =30;break;
		case "r31": out =31;break;
		case "r32": out =32;break;
		case "r33": out =33;break;
		case "r34": out =34;break;
		case "r35": out =35;break;
		case "r36": out =36;break;
		case "r37": out =37;break;
		case "r38": out =38;break;
		case "r39": out =39;break;
		case "r40": out =40;break;
		case "r41": out =41;break;
		case "r42": out =42;break;
		case "r43": out =43;break;
		case "r44": out =44;break;
		case "r45": out =45;break;
		case "r46": out =46;break;
		case "r47": out =47;break;
		case "r48": out =48;break;
		case "r49": out =49;break;
		case "r50": out =40;break;
		case "r51": out =51;break;
		case "r52": out =52;break;
		case "r53": out =53;break;
		case "r54": out =54;break;
		case "r55": out =55;break;
		case "r56": out =56;break;
		case "r57": out =57;break;
		case "r58": out =58;break;
		case "r59": out =59;break;
		case "r60": out =60;break;
		case "r61": out =61;break;
		case "r62": out =62;break;
		case "r63": out =63;break;
		case "add": out =0;break;
		case "sub": out =1;break;
		case "mul": out =2;break;
		case "movi": out =3;break;
		case "beqz": out =4;break;
		case "andi": out =5;break;
		case "eor": out =6;break;
		case "br": out =7;break;
		case "sal": out =8;break;
		case "sar": out =9;break;
		case "ldr": out =10;break;
		case "str": out =11;break;
		default:
		if(Integer.parseInt(in)>=-32||Integer.parseInt(in)<=31)
			out=Integer.parseInt(in);
		else out=5555;
		}
		return out;
	}
	public static void readText () throws Exception{
		File file = new File("assembly.txt");  
	    BufferedReader br = new BufferedReader(new FileReader(file));	
	    String st;
		String out = "";
		while ((st = br.readLine()) != null)
			out+=st+" ";
		String[] array= out.toLowerCase().split("( )|(,)"); 
		 out="";
		for(int i=0;i<array.length;i++){
			if(getMachineLanguage(array[i])==5555){
				return ;
			}
			else
				out=out+getMachineLanguage(array[i])+" ";	
		}
		array= out.split(" "); 
		Short instruction=0;
		for(int i=0;i<array.length;i+=3){
			instruction=(short)((Integer.parseInt(array[i])<<12)+(Integer.parseInt(array[i+1])<<6)+(byte)((Integer.parseInt(array[i+2])&0b00111111)));
			memory.pushInstruction(instruction);
		}
		
		

	}
	
}
