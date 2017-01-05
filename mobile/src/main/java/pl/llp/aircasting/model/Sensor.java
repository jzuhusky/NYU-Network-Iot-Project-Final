package pl.llp.aircasting.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import pl.llp.aircasting.model.internal.MeasurementLevel;

import pl.llp.aircasting.model.events.SensorEvent;

import com.google.common.base.Strings;

import java.io.Serializable;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class Sensor implements Serializable {
	@Expose
	@SerializedName("sensor_name")
	private String sensorName;
	@Expose
	@SerializedName("sensor_package_name")
	private String packageName;
	private String measurementType;
	private String shortType;
	private String unit;
	private String symbol;
	private final Map<MeasurementLevel, Integer> thresholds = newHashMap();

	protected boolean enabled = true;
	private String address = "none";

	public Sensor(SensorEvent event) {
		this(event.getPackageName(), event.getSensorName(), event.getMeasurementType(), event.getShortType(),
				event.getUnit(), event.getSymbol(), event.getVeryLow(), event.getLow(), event.getMid(), event.getHigh(),
				event.getVeryHigh(), event.getAddress());
	}

	public Sensor(MeasurementStream stream) {
		this(stream.getPackageName(), stream.getSensorName(), stream.getMeasurementType(), stream.getShortType(),
				stream.getUnit(), stream.getSymbol(), stream.getThresholdVeryLow(), stream.getThresholdLow(),
				stream.getThresholdMedium(), stream.getThresholdHigh(), stream.getThresholdVeryHigh(),
				stream.getAddress());
	}

	public Sensor(String packageName, String name, String type, String shortType, String unit, String symbol,
			int veryLow, int low, int mid, int high, int veryHigh) {
		this(packageName, name, type, shortType, unit, symbol, veryLow, low, mid, high, veryHigh, "none");
	}

	public Sensor(String packageName, String name, String type, String shortType, String unit, String symbol,
			int veryLow, int low, int mid, int high, int veryHigh, String address) {
		this.packageName = packageName;
		this.sensorName = name;
		this.measurementType = type;
		this.shortType = shortType;
		this.unit = unit;
		this.symbol = symbol;

		thresholds.put(MeasurementLevel.VERY_HIGH, veryHigh);
		thresholds.put(MeasurementLevel.HIGH, high);
		thresholds.put(MeasurementLevel.MID, mid);
		thresholds.put(MeasurementLevel.LOW, low);
		thresholds.put(MeasurementLevel.VERY_LOW, veryLow);
		this.address = address;
	}

	public Sensor(String name) {
		sensorName = name;
	}

	public String getSensorName() {
		return sensorName;
	}

	public String getMeasurementType() {
		return measurementType;
	}

	public String getShortType() {
		return shortType;
	}

	public String getUnit() {
		return unit;
	}

	public String getSymbol() {
		return symbol;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public int getThreshold(MeasurementLevel level) {
		return thresholds.get(level);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Sensor))
			return false;

		Sensor sensor = (Sensor) o;

		if (sensorName != null ? !sensorName.equals(sensor.sensorName) : sensor.sensorName != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return sensorName != null ? sensorName.hashCode() : 0;
	}

	@Override
	public String toString() {
		return new StringBuilder().append(getMeasurementType()).append(" - ").append(getSensorName()).append(" (")
				.append(getSymbol()).append(")").toString();
	}

	public String getPackageName() {
		return packageName;
	}

	public boolean matches(Sensor sensor) {
		return sensor != null && Strings.nullToEmpty(sensor.getSensorName()).equals(getSensorName())
				&& Strings.nullToEmpty(sensor.getMeasurementType()).equals(getMeasurementType());
	}

	public String getAddress() {
		return address;
	}

	public MeasurementLevel level(double value) {
		for (MeasurementLevel measurementLevel : MeasurementLevel.OBTAINABLE_LEVELS) {
			if (value > getThreshold(measurementLevel)) {
				return measurementLevel;
			}
		}
		return MeasurementLevel.TOO_LOW;
	}

	void toggle() {
		enabled = !enabled;
	}

	void disable() {
		enabled = false;
	}

	void enable() {
		enabled = true;
	}
}
