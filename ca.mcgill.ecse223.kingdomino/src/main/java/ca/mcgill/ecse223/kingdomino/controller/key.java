package ca.mcgill.ecse223.kingdomino.controller;

public class key implements Comparable<key>{
	
	    private final int flag1;
	    private final int flag2;

	    public key(int flag1,int flag2) {
	        this.flag1 = flag1;
	        this.flag2 = flag2;
	    }
	    public int get1 () {
	    	return this.flag1;
	    }
	    public int get2 () {
	    	return this.flag2;
	    }

	    @Override
	    public boolean equals(Object object) {
	        if (!(object instanceof key)) {
	            return false;
	        }

	        key otherKey = (key) object;
	        return this.flag1 == otherKey.flag1 && this.flag2 == otherKey.flag2;
	    }

	    @Override
	    public int hashCode() {
	        int result = 17; // any prime number
	       // result = 31 * result + (this.flag1).hashCode();
	        result = 31 * result + (this.flag1);
	        return result;
	    }

		@Override
		public int compareTo(key o) {
			// TODO Auto-generated method stub
			
			return (this.flag1)- o.flag1;
		}
	

}
