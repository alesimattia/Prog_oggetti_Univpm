package com.univpm.COVID19stats.model;

public class Response {
    private Bundle max;
    private Bundle min;

    public Response() {
		this.max = null;
		this.min = null;
	}
	public Bundle getMax() {
		return max;
	}
	public void setMax(Bundle max) {
		this.max = max;
	}
	public Bundle getMin() {
		return min;
	}
	public void setMin(Bundle min) {
		this.min = min;
	}

}
