import java.io.Serializable;


public class Pattern implements Serializable {
	
	private String pattern;
	
	public Pattern( String p ) {
		pattern = p;
	}
	
	public String getPattern() {
		return pattern;
	}

	@Override
	public int hashCode() {
		return pattern.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Pattern)) {
			return false;
		}
		Pattern p = (Pattern) obj;
		return pattern.equals(p.getPattern());
	}
	

}
