package co.edu.usbcali.banco.dto;

import java.math.BigDecimal;

public class ResultadoCuentaDTO {
	private Long count;
	private BigDecimal min;
	private BigDecimal max;
	private Double avg;
	
	
	
	public ResultadoCuentaDTO() {
		super();
	}
	
	public ResultadoCuentaDTO(Long count, BigDecimal min, BigDecimal max, Double avg) {
		super();
		this.count = count;
		this.min = min;
		this.max = max;
		this.avg = avg;
	}


	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public BigDecimal getMin() {
		return min;
	}
	public void setMin(BigDecimal min) {
		this.min = min;
	}
	public BigDecimal getMax() {
		return max;
	}
	public void setMax(BigDecimal max) {
		this.max = max;
	}
	public Double getAvg() {
		return avg;
	}
	public void setAvg(Double avg) {
		this.avg = avg;
	}
	
	

}
