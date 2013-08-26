package www.sdxuyan.tk.util;

import com.amap.api.maps.model.LatLng;

public class Sights {
	//景点名称
	private String name;
	//景点坐标
	private LatLng latLng;
	//景点简介
	private String intro;
	public Sights(String name, LatLng latLng, String intro) {
		super();
		this.name = name;
		this.latLng = latLng;
		this.intro = intro;
	}
	public String getName() {
		return name;
	}
	public LatLng getLatLng() {
		return latLng;
	}
	public String getIntro() {
		return intro;
	}
}
