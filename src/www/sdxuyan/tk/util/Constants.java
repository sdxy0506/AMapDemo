package www.sdxuyan.tk.util;

import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;

public class Constants {

	public static final int POISEARCH = 1000;

	public static final int ERROR = 1001;
	public static final int FIRST_LOCATION = 1002;

	public static final int ROUTE_START_SEARCH = 2000;
	public static final int ROUTE_END_SEARCH = 2001;
	public static final int ROUTE_SEARCH_RESULT = 2002;
	public static final int ROUTE_SEARCH_ERROR = 2004;

	public static final int REOCODER_RESULT = 3000;
	public static final int DIALOG_LAYER = 4000;
	public static final int POISEARCH_NEXT = 5000;

	public static final int BUSLINE_RESULT = 6000;
	public static final int BUSLINE_DETAIL_RESULT = 6001;
	public static final int BUSLINE_ERROR_RESULT = 6002;

	// public static final LatLng SDIE = new LatLng(36.644744, 117.069769);//
	// 山东财经大学(燕山校区)
	//public static final LatLng QUANCHENGGUANGCHANG = new LatLng(36.661506,117.021244);// 泉城广场
	//public static final LatLng DAMINGHU = new LatLng(36.672115, 117.024027);// 大明湖(南一门)

	public static final MarkerOptions SDIE = new MarkerOptions()
			.position(new LatLng(36.644744, 117.069769)).title("山东财经大学(燕山校区)")
			.snippet("二环东路").icon(BitmapDescriptorFactory.defaultMarker());
	public static final MarkerOptions QUANCHENGGUANGCHANG = new MarkerOptions()
			.position(new LatLng(36.661506, 117.021244)).title("泉城广场")
			.snippet("泉标").icon(BitmapDescriptorFactory.defaultMarker());
	public static final MarkerOptions DAMINGHU = new MarkerOptions()
			.position(new LatLng(36.672115, 117.024027)).title("大明湖").snippet("大明湖南一门")
			.icon(BitmapDescriptorFactory.defaultMarker());
}
