
public class Item {
	
	public int id;
	public int value;
	public int weight;
	public boolean take;
	
	public Item(int id, int value, int weight, boolean take){
		this.value = value;
		this.weight = weight;
		this.id = id;
		this.take = take;
	}
	
	public Item(int id, int value, int weight){
		this(id, value, weight, false);
	}
	
	@Override
	public String toString(){
		//return "Id:" + id + " Value:" + value + " Weight:" + weight + " Take: " + take;
		//return take+"";
		int intTake = -1;
		if(take == true) intTake = 1;
		else intTake = 0;
		return intTake + "";
	}
	
}
