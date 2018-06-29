public class Range {

	private float lowerBound;
	private float upperBound;

	public Range(float min, float max) {
		lowerBound = min;
		upperBound = max;
	}

	public float getLower() {
		return lowerBound;
	}

	public float getUpper() {
		return upperBound;
	}

	public void setLower(float min) {
		lowerBound = min;
	}

	public void setUpper(float max) {
		upperBound = max;
	}
	
	public boolean inRange(float num){
		return (num >=lowerBound && num <= upperBound);
	}
}
