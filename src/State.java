
public class State {
	int val;
	int transit;
	boolean accept;
	char read;
	char pop;
	String push;
	String rule;
	
	
	public int getTransit() {
		return transit;
	}

	public void setTransit(int transit) {
		this.transit = transit;
	}
	
	public char getRead() {
		return read;
	}

	public void setRead(char read) {
		this.read = read;
	}

	public char getPop() {
		return pop;
	}

	public void setPop(char pop) {
		this.pop = pop;
	}

	public String getPush() {
		return push;
	}

	public void setPush(String push) {
		this.push = push;
	}

	
	public State() {
		// TODO Auto-generated constructor stub
	}
	
	public State(int val, boolean accept) {
		// TODO Auto-generated constructor stub
		this.val = val;
		this.accept = accept;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public boolean isAccept() {
		return accept;
	}

	public void setAccept(boolean accept) {
		this.accept = accept;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	
	public String toString() {
		String temp = "[";
		if(this.getRead() == '-') temp += "eps,";
		else temp += this.getRead()+",";
		
		if(this.getPop() == '-') temp += "eps->";
		else temp += this.getPop()+"->";
		
		if(this.getPush().contains("-")) temp += "eps]";
		else temp += this.getPush() + "]";
		
		return temp;
	}
	
	
	public static void main(String[] args) {
		State st = new State()
		// TODO Auto-generated method stub

	}

}
