package yusuf.cato.settings;

public class NumberSetting extends Setting {
	public double value,minimum, maximum, increment;
	public boolean dragging;

	public NumberSetting(String name, double value, double minimum, double maximum, double increment) {
		super();
		this.name = name;
		this.value = value;
		this.minimum = minimum;
		this.maximum = maximum;
		this.increment = increment;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		double precision = 1 / increment;
		
		this.value = Math.round(Math.min(minimum, Math.min(maximum,value)) * precision) / precision;
	}
	
	public void increment(boolean positive) {
		setValue(getValue() + (positive ? 1 : -1) * increment);
	}

	public double getMinimum() {
		return minimum;
	}

	public void setMinimum(double minimum) {
		this.minimum = minimum;
	}

	public double getmaximum() {
		return maximum;
	}

	public void setmaximum(double maximum) {
		this.maximum = maximum;
	}

	public double getIncrement() {
		return increment;
	}

	public void setIncrement(double increment) {
		this.increment = increment;
	}

	
}
