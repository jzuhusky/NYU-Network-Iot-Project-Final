package pl.llp.aircasting.model.events;

import pl.llp.aircasting.event.AirCastingEvent;
import pl.llp.aircasting.model.MeasurementStream;

import java.util.Date;

public class SensorEvent extends AirCastingEvent {
	private String packageName;
	private String sensorName;
	private String shortType;
	private String unit;
	private String symbol;
	private String measurementType;
	private int veryLow;
	private int low;
	private int mid;
	private int high;
	private int veryHigh;
	private double value;
	private double measuredValue;

	private long creationTime = new Date().getTime();

	private String address = "none";

	public String getSensorName() {
		return sensorName;
	}

	public String getUnit() {
		return unit;
	}

	public String getSymbol() {
		return symbol;
	}

	public double getValue() {
		return value;
	}

	public String getMeasurementType() {
		return measurementType;
	}

	public int getVeryLow() {
		return veryLow;
	}

	public int getLow() {
		return low;
	}

	public int getMid() {
		return mid;
	}

	public int getHigh() {
		return high;
	}

	public int getVeryHigh() {
		return veryHigh;
	}

	public String getShortType() {
		return shortType;
	}

	public double getMeasuredValue() {
		return measuredValue;
	}

	public SensorEvent(String packageName, String sensorName, String measurementType, String shortType, String unit,
			String symbol, int veryLow, int low, int mid, int high, int veryHigh, double value, String address) {
		this(packageName, sensorName, measurementType, shortType, unit, symbol, veryLow, low, mid, high, veryHigh,
				value);
		this.address = address;
	}

	public SensorEvent(String packageName, String sensorName, String measurementType, String shortType, String unit,
			String symbol, int veryLow, int low, int mid, int high, int veryHigh, double value, long timeStamp) {
		this(packageName, sensorName, measurementType, shortType, unit, symbol, veryLow, low, mid, high, veryHigh,
				value);
		this.creationTime = timeStamp;
	}

	public SensorEvent(String packageName, String sensorName, String measurementType, String shortType, String unit,
			String symbol, int veryLow, int low, int mid, int high, int veryHigh, double value, double measuredValue) {
		this.packageName = packageName;
		this.sensorName = sensorName;
		this.measurementType = measurementType;
		this.shortType = shortType;
		this.unit = unit;
		this.symbol = symbol;
		this.veryLow = veryLow;
		this.low = low;
		this.mid = mid;
		this.high = high;
		this.veryHigh = veryHigh;
		this.measuredValue = measuredValue;
		this.value = value;
	}

	public SensorEvent(String packageName, String sensorName, String measurementType, String shortType, String unit,
			String symbol, int veryLow, int low, int mid, int high, int veryHigh, double value) {

		this(packageName, sensorName, measurementType, shortType, unit, symbol, veryLow, low, mid, high, veryHigh,
				value, value);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		SensorEvent event = (SensorEvent) o;

		if (high != event.high)
			return false;
		if (low != event.low)
			return false;
		if (mid != event.mid)
			return false;
		if (Double.compare(event.value, value) != 0)
			return false;
		if (veryHigh != event.veryHigh)
			return false;
		if (veryLow != event.veryLow)
			return false;
		if (measurementType != null ? !measurementType.equals(event.measurementType) : event.measurementType != null) {
			return false;
		}
		if (sensorName != null ? !sensorName.equals(event.sensorName) : event.sensorName != null)
			return false;
		if (shortType != null ? !shortType.equals(event.shortType) : event.shortType != null)
			return false;
		if (symbol != null ? !symbol.equals(event.symbol) : event.symbol != null)
			return false;
		if (unit != null ? !unit.equals(event.unit) : event.unit != null)
			return false;

		return true;
	}

	public String getPackageName() {
		return packageName;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = sensorName != null ? sensorName.hashCode() : 0;
		result = 31 * result + (shortType != null ? shortType.hashCode() : 0);
		result = 31 * result + (unit != null ? unit.hashCode() : 0);
		result = 31 * result + (symbol != null ? symbol.hashCode() : 0);
		result = 31 * result + (measurementType != null ? measurementType.hashCode() : 0);
		result = 31 * result + veryLow;
		result = 31 * result + low;
		result = 31 * result + mid;
		result = 31 * result + high;
		result = 31 * result + veryHigh;
		temp = value != +0.0d ? Double.doubleToLongBits(value) : 0L;
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "SensorEvent{" + "sensorName='" + sensorName + '\'' + ", shortType='" + shortType + '\'' + ", unit='"
				+ unit + '\'' + ", symbol='" + symbol + '\'' + ", measurementType='" + measurementType + '\''
				+ ", veryLow=" + veryLow + ", low=" + low + ", mid=" + mid + ", high=" + high + ", veryHigh=" + veryHigh
				+ ", value=" + value + '}';
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setDate(Date date) {
		this.creationTime = date.getTime();
	}

	public Date getDate() {
		return new Date(creationTime);
	}

	public MeasurementStream stream() {
		return new MeasurementStream(this.getPackageName(), this.getSensorName(), this.getMeasurementType(),
				this.getShortType(), this.getUnit(), this.getSymbol(), this.getVeryLow(), this.getLow(), this.getMid(),
				this.getHigh(), this.getVeryHigh(), this.getAddress());
	}
}
