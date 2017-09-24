import java.util.ArrayList;
import java.util.HashMap;

public class HeapTraverse {
public void Traverser(FreqDatum Root,HashMap<Integer, StringBuilder> hMap, ArrayList<String>arr) {
	FreqDatum root=Root;
	if(root==null)
		return;
	else if(root.left==null && root.right==null){
		StringBuilder sBuilder=new StringBuilder();
		for(int i=0;i<arr.size();i++)
		sBuilder.append(arr.get(i));
		 hMap.put(root.key,sBuilder);
	}
	else{
		arr.add("0");
        Traverser(root.left,hMap,arr);
        arr.set(arr.size()-1, "1");
        Traverser(root.right,hMap,arr);
        arr.remove(arr.size()-1);
	}
}

}
