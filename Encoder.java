import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Encoder {

	public static void main(String[] args) 
	{
		String inputfile=args[0];
		File f=new File(inputfile);
		FreqTable input = new FreqTable();
		HashMap<String,Long> freq_table = input.GenFreqTable(f);
		/*
		// Binary Heap
		BinaryHeap binaryHeap = new BinaryHeap();
		long startTimeBH = java.lang.System.nanoTime();
		for (int runCount = 0;runCount<10;runCount++)
		{
			binaryHeap.BinHuffmanTree(freq_table);
		}

		long endTimeBH = java.lang.System.nanoTime();
		System.out.println("Average time using Binary Heap is "+  (float)((endTimeBH-startTimeBH)/1000000000)/10 + "s");

		//PairingHeap
				PairingHeap ph = new PairingHeap();
				long startTimePH = java.lang.System.nanoTime();
				for (int runCount = 0;runCount<10;runCount++)
				{
					ph.PairTree(freq_table);

				}
				long endTimePH = java.lang.System.nanoTime();
				System.out.println("Average time using Pairing Heap is "+  (float)((endTimePH-startTimePH)/1000000000)/10 + "s");


		 */
		//FourWayHeap
		FreqDatum fd = null;
		FourWayHeap Fheap = new FourWayHeap();
		long startTimeEncoder = java.lang.System.currentTimeMillis();
		//for (int runCount = 0;runCount<10;runCount++)
		//{
			fd=Fheap.buildTree(freq_table);

		//}
		long endTimeFH = java.lang.System.currentTimeMillis();
		//System.out.println("Average time using 4-Way Heap is "+  (float)(endTimeFH-startTimeEncoder)/10000 + "s");

		CodeTable table=new CodeTable();
		HashMap<Integer,StringBuilder> hMap=table.GenCodeTable(fd);
 		try {
 			 File file = new File("encoded.bin");
 			FileOutputStream outputStream       = new FileOutputStream(file);
 	        StringBuilder sBuilder=new StringBuilder();

 			 if (!file.exists())
 					file.createNewFile();

 			  try{
 				  BufferedReader reader = new BufferedReader(new FileReader(f));
 			      String line;


 				    while ((line =reader.readLine())!= null){
 				    	if(!line.equals(""))
 				    		sBuilder.append(hMap.get(Integer.parseInt(line)).toString());
 				    }
 				  reader.close();
 	for(int i=0;i<sBuilder.length();i=i+8)
 		outputStream.write(Integer.parseInt(sBuilder.substring(i, i+8),2));

 	outputStream.close();
 			  }
 			  catch (Exception e){

 			  }

 		} catch (IOException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
		long endTimeEncoder = java.lang.System.currentTimeMillis();
		System.out.println("Time taken for encoding is:  "+(float)((endTimeEncoder-startTimeEncoder)/1000)+"s");


	}

}
 class CodeTable {

	public HashMap<Integer,StringBuilder> GenCodeTable(FreqDatum newValue) {
		// TODO Auto-generated method stub
		HashMap<Integer,StringBuilder> hMap=new HashMap<>();

		ArrayList<String> aList=new ArrayList<>();
		HeapTraverse code=new HeapTraverse();
	    ((HeapTraverse) code).Traverser(newValue,hMap,aList);
	    File file = new File("code_table.txt");
	    if (!file.exists()) {
	        try {
				if (file.createNewFile()) {
				    PrintWriter writer = new PrintWriter("code_table.txt", "UTF-8");
				    for (Map.Entry<Integer,StringBuilder> entry :hMap.entrySet()) {
					    writer.println(entry.getKey() + " " + entry.getValue());
					}

				    writer.close();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    return hMap;


	}

}