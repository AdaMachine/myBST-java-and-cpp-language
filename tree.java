
public class tree {
	private Node root;


	public tree(int r) {
		this.root=new Node(r);
	}
	public Node getNoderoot() {
		return this.root;
	}
	public int root() {
		return this.root.value;
	}
	public void insert(int v, Node current) { 
		if(v<current.value)
			if(current.l==null)
				current.l=new Node(v);
			else
				insert(v, current.l);
		else
			if(current.r==null)
				current.r=new Node(v);
			else
				insert(v, current.r);
	}
	public void print(Node current) {
		if(current.l!=null)print(current.l);
		System.out.print(current.value + " ");
		if(current.r!=null)print(current.r);
	}
	public boolean isaleaf(int k,Node current) {
		if(k!=current.value) {
			if(current.l!=null)
				return isaleaf(k, current.l);
			if(current.r!=null)
				return isaleaf(k, current.r);
		}else {
			if(current.l==null&&current.r==null)return true;
		}
		return false;
	}
	public int size(Node current) {
		if(current==null)return 0;
		else
			return  1+size(current.l)+size(current.r);
	}
	public boolean contains(int k, Node current) {
		if (current.value==k)return true;
		else {
			if(k<current.value&&current.l!=null)return contains(k, current.l);
			if(k>current.value&&current.r!=null)return contains(k, current.r);
		}return false;
	}
	public int parent(int k, Node current) {
		if (!contains(k, current)||size(current)<2||k==root()) return 0;
		else return rec_find_parent(k, current);
	}
	private int rec_find_parent(int k, Node current) {
		if(current.value>k) {
			if (current.l.value==k)
				return current.value;
			else return rec_find_parent(k, current.l);
		}else {
			if (current.r.value==k)
				return current.value;
			else return rec_find_parent(k, current.r);
		}
	}
	public int left(int k, Node current) {
		if (!contains(k, current)||size(current)<2) return 0;
		else if (k==root()&&current.l!=null) {
			return current.l.value;
		}
		else if (k==root()&&current.l==null) {
			return 0;
		}
		else return rec_find_left(k, current);
	}
	private int rec_find_left(int k, Node current) {
		if (k!=current.value) {
			if(current.value>k)
				if(current.l!=null) {
					return rec_find_left(k, current.l);
				}else return 0;
			else
				if(current.r!=null) {
					return rec_find_left(k, current.r);
				}else return 0;
		}else if (current.l!=null)return (current.l.value);
		else return 0;
	}
	public int right(int k, Node current) {
		if (!contains(k, current)||size(current)<2) return 0;
		else if (k==root()&&current.r!=null) {
			return current.l.value;
		}
		else if (k==root()&&current.r==null) {
			return 0;
		}
		else return rec_find_right(k, current);
	}
	private int rec_find_right(int k, Node current) {
		if (k!=current.value) {
			if(current.value>k)
				if(current.l!=null) {
					return rec_find_right(k, current.l);
				}else return 0;
			else
				if(current.r!=null) {
					return rec_find_right(k, current.r);
				}else return 0;
		}else if (current.r!=null)return (current.r.value);
		else return 0;
	}
	
	public boolean remove(int k, Node current) {
		if (contains(k, current)) { 
			deleteRec(k, current);
			return true;
		}
		else return false;
	}
	 Node deleteRec(int k,Node current) 
	    { 
	        /* Base Case: If the tree is empty */
	        if (current== null)  return current; 
	  
	        /* Otherwise, recur down the tree */
	        if (k < current.value) 
	            current.l = deleteRec(k,current.l); 
	        else if (k > current.value) 
	        	current.r= deleteRec(k,current.r); 
	  
	        // if key is same as root's key, then This is the node 
	        // to be deleted 
	        else
	        { 
	            // node with only one child or no child 
	            if (current.l == null) 
	                return current.r; 
	            else if (current.r== null) 
	                return current.l; 
	  
	            // node with two children: Get the inorder successor (smallest 
	            // in the right subtree) 
	            current.value= closestfromright(current.r); 
	  
	            // Delete the inorder successor 
	            current.r= deleteRec( current.value,current.r); 
	        } 
	  
	        return current; 
	    } 
	private int closestfromright(Node current) {
		if (current.r==null)return current.value;
		else return closestfromright(current.r);
	}
	public static void main(String[] args) {
		tree t0=new tree(2);
		t0.insert(1, t0.root);
		System.out.println(t0.size(t0.getNoderoot()));
		System.out.println(t0.remove(1, t0.getNoderoot()));
		System.out.println(t0.size(t0.getNoderoot()));
	}
}

//	private void rec_remove(int k,Node current){
//		if (k!=current.value) {
//			if (k<current.value) {
//				if (current.l!=null)
//					rec_remove(k, current.l);
//			}else{
//				if (current.r!=null)
//					rec_remove(k, current.r);
//			}
//		}
//		else if (isaleaf(k, current)&&root()!=k) current=null;
////		else if ((current.l!=null&&current.r==null)||(current.l==null&&current.r!=null)) {
////			if (current.l!=null&&current.r==null) {
////				Node temp = current.l;
////				rec_find_parent_Node(k,current).l=temp;
////				current=null;
////			}
////			if (current.l==null&&current.r!=null) {
////				Node temp = current.r;
////				rec_find_parent_Node(k,current).r=temp;
////				current=null;
////			}
////		}
////		else {
////			Node temp = closestfromright(current.l);
////			rec_find_parent_Node(k,current).l=temp;
////			current=null;
////		}
//		
//	}
//	private Node rec_find_parent_Node(int k, Node current) {
//		if(current.value>k) {
//			if (current.l.value==k)
//				return current;
//			else return rec_find_parent_Node(k, current.l);
//		}else {
//			if (current.r.value==k)
//				return current;
//			else return rec_find_parent_Node(k, current.r);
//		}
//	}