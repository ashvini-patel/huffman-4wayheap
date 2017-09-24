
public class FreqDatum {
	long freq;
    int key;
	public FreqDatum right;
	public FreqDatum left;

    public FreqDatum(Long val, int key){
        this.key=key;
        this.freq=val;
    }
    public FreqDatum(Long val){
        this.freq=val;
        this.key=-1;
    }

}
