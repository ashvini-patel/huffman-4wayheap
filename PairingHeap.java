import java.util.*;
import java.util.Map.Entry;

public class PairingHeap {

	public FreqDatum root;
	ArrayList<PairingHeap> children;
	int size=0;

	PairingHeap(){
		 root=null;
		 children=new ArrayList<PairingHeap>();
	}

	PairingHeap(FreqDatum n){
		 root=n;
		 children=new ArrayList<PairingHeap>();
	}


	public void add(FreqDatum element) {
        size++;
		PairingHeap newNode=new PairingHeap(element);
		if (root == null)
			root = element;
		else
			root = compareAndLink(root,newNode);

	}

	public FreqDatum compareAndLink(FreqDatum root, PairingHeap newNode) {
		if (newNode == null)
			return root;
		else{

			if(root.freq<newNode.root.freq){

				children.add(newNode);
			}
				else
				{

					PairingHeap oldroot=new PairingHeap(root);
                    newNode.children.add(oldroot);
                    root=newNode.root;

			    }



		}

		return root;
	}



	public FreqDatum removeMin() {
        size--;
		FreqDatum min=root;
		if(children.isEmpty()){
			return min;
		}
		else{

			FreqDatum newRoot=combineSiblings(children);
            root=newRoot;
            return min;
		}



	}

	public FreqDatum combineSiblings(ArrayList<PairingHeap> childList) {


		ArrayList<PairingHeap> MeldList=childList;

		while(MeldList.size()>1)
		{
			ArrayList<PairingHeap> Pass=new ArrayList<PairingHeap>((int) Math.ceil(MeldList.size()/2));
			int count=(int) Math.ceil((float)MeldList.size()/2);

			if(MeldList.size()%2==1)
			  count--;
			for(int i=0;i<count;i++)
				Pass.add(meld(MeldList.get(2*i),MeldList.get((2*i)+1)));
			if(MeldList.size()%2==1)
				Pass.add(MeldList.get(MeldList.size()-1));
			MeldList=Pass;

		}
		children=MeldList.get(0).children;
		return MeldList.get(0).root;

	}

	public PairingHeap meld(PairingHeap pairingHeap, PairingHeap pairingHeap2) {

		if(pairingHeap.root.freq<pairingHeap2.root.freq){

			pairingHeap.children.add(pairingHeap2);

		    return pairingHeap;

		}
		else
		{
			pairingHeap2.children.add(pairingHeap);
			return pairingHeap2;
		}

	}
	public void PairTree(HashMap<String,Long> hm){
		  FreqDatum left;
	        FreqDatum right;
	        FreqDatum newDatum;
        for(Entry<String,Long>entry: hm.entrySet()){
            int x=Integer.parseInt(entry.getKey());
            Long b=entry.getValue();
            FreqDatum d=new FreqDatum(b,x);
                add(d);
        }
        while(size!=1){
            left=removeMin();
            right=removeMin();
            newDatum=new FreqDatum(left.freq+right.freq);
            add(newDatum);
    }
    }
}





