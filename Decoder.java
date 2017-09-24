import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class DecoderNode{
	int data;
	String code;
	DecoderNode left;
	DecoderNode right;
	public DecoderNode() {
		data=-1;
		code="";
		left=null;
		right=null;
	}
}

class huffManTree{
	DecoderNode root;

	public huffManTree() {
		// TODO Auto-generated constructor stub
		root=new DecoderNode();
	}

	void decodeTree(ArrayList<DecoderNode> DecoderNode){
		for(int i=0;i<DecoderNode.size();i++){
			DecoderNode node=this.root;
			String code=DecoderNode.get(i).code;
			for(int j=0;j<code.length();j++){
				if(code.charAt(j)=='0'){
					if(node.left == null)  node.left = new DecoderNode();
					node=node.left;
				}
				else {
					if(node.right == null) node.right=new DecoderNode();
					node=node.right;
				}
			}
			node.data=DecoderNode.get(i).data;
		}
	}

	void decoder(String BINFILE) throws IOException{
		FileInputStream fis=new FileInputStream(BINFILE);
		String decoderWriter="decoded.txt";
		FileWriter fw = new FileWriter(decoderWriter);
		BufferedWriter bw = new BufferedWriter(fw);
		byte[] buffer=null;
		buffer=new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<buffer.length;i++){
			byte b1 = buffer[i];
			String s1 = String.format("%8s", Integer.toBinaryString(b1 & 0xFF)).replace(' ', '0');
			sb.append(s1);
		}
		DecoderNode temp=this.root;
		long l=sb.length();
		for(int i = 0; i <= l; i++){
			if(temp.data != -1){
				StringBuilder st = new StringBuilder();
				st.append(temp.data);
				st.append("\n");
				bw.write(st.toString());
				temp=this.root;
				i--;
			}
			else if (i!=l){
				if(sb.charAt(i)=='0') temp=temp.left;
				else temp=temp.right;
			}
		}
	bw.close();
	}
}



public class Decoder {

	  public static void main(String args[]){
			long startTime = java.lang.System.currentTimeMillis();

		   String BinaryFile=args[0];
		   FileInputStream fstream = null;
		try {
			fstream = new FileInputStream(args[1]);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		   DataInputStream in = new DataInputStream(fstream);
           BufferedReader br = new BufferedReader(new InputStreamReader(in));
           ArrayList<DecoderNode> DecoderNodes=new ArrayList<>();
		   String str;
	          try {
				while ((str= br.readLine()) != null)   {
				         String[] tokens = str.split(" ");
						 DecoderNode root=new DecoderNode();
						 root.code=tokens[1];
						 root.data=Integer.parseInt(tokens[0]);
						 DecoderNodes.add(root);

   }
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				in.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				br.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	  		huffManTree hft=new huffManTree();
			hft.decodeTree(DecoderNodes);
			try {
				hft.decoder(BinaryFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long stopTime = java.lang.System.currentTimeMillis();
			System.out.println("Time taken for decoding is: "+ (float) ((stopTime-startTime)/1000)+" s");


	}

}



