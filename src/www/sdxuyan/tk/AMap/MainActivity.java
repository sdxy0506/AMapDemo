package www.sdxuyan.tk.AMap;

import java.util.Vector;

import www.sdxuyan.tk.AMap.R;
import www.sdxuyan.tk.util.AMapUtil;
import www.sdxuyan.tk.util.Constants;
import www.sdxuyan.tk.util.Intro;
import www.sdxuyan.tk.util.ToastUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.InfoWindowAdapter;
import com.amap.api.maps.AMap.OnInfoWindowClickListener;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.SupportMapFragment;

public class MainActivity extends FragmentActivity implements
		OnMarkerClickListener, InfoWindowAdapter, OnInfoWindowClickListener,
		OnGestureListener {
	private AMap aMap;
	// private OnLocationChangedListener mListener;
	// private LocationManagerProxy mAMapLocationManager;

	private Vector<Marker> markers = new Vector<Marker>();

	private int flag = 0;// 当前景点的标号

	private ViewFlipper flipper;
	private GestureDetector detector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.test);
		init();
		detector = new GestureDetector(this, this);
	}

	private View addTextView(String text) {

		LayoutInflater layoutInflater = LayoutInflater.from(this);
		LinearLayout resultView = (LinearLayout) layoutInflater.inflate(
				R.layout.intro, null);
		((TextView) resultView.findViewById(R.id.textView2)).setText(text);
		return resultView;
	}

	/**
	 * 初始化AMap对象
	 */
	private void init() {
		if (aMap == null) {
			aMap = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map)).getMap();
			if (AMapUtil.checkReady(this, aMap)) {

				setUpMap();
			}

		}
		flipper = (ViewFlipper) this.findViewById(R.id.viewFlipperIntro);
		flipper.addView(addTextView(Intro.SDIE));
		flipper.addView(addTextView("大明湖"));
		flipper.addView(addTextView("泉城广场"));
	}

	private void setUpMap() {
		// 自定义系统定位小蓝点
		// MyLocationStyle myLocationStyle = new MyLocationStyle();
		// myLocationStyle.myLocationIcon(BitmapDescriptorFactory
		// .fromResource(R.drawable.location_marker));
		// myLocationStyle.strokeColor(Color.BLUE);
		// myLocationStyle.strokeWidth(5);
		// aMap.setMyLocationStyle(myLocationStyle);
		// mAMapLocationManager = LocationManagerProxy
		// .getInstance(MainActivity.this);
		// aMap.setLocationSource(this);
		// aMap.setMyLocationEnabled(true);//
		// 设置为true表示系统定位按钮显示并响应点击，false表示隐藏，默认是false
		aMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
		aMap.setInfoWindowAdapter(this);
		aMap.setOnInfoWindowClickListener(this);// 设置点击infoWindow事件监听器
		addMarkersToMap();
		aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markers.get(flag)
				.getPosition(), 17));
		markers.get(flag).showInfoWindow();
		drawPolyline();

	}

	private void drawPolyline() {
		aMap.addPolyline((new PolylineOptions())
				.add(Constants.SDIE.getPosition(),
						new LatLng(36.64832, 117.070725),
						Constants.DAMINGHU.getPosition(),
						Constants.QUANCHENGGUANGCHANG.getPosition()).width(5)
				.color(Color.RED));
	}

	@Override
	protected void onPause() {
		super.onPause();
		// deactivate();
	}

	/**
	 * 往地图上添加marker
	 * */
	private void addMarkersToMap() {
		markers.add(aMap.addMarker(Constants.SDIE));
		markers.add(aMap.addMarker(Constants.DAMINGHU));
		markers.add(aMap.addMarker(Constants.QUANCHENGGUANGCHANG));
	}

	@Override
	public boolean onMarkerClick(Marker arg0) {
		// arg0.showInfoWindow();
		return false;
	}

	@Override
	public View getInfoContents(Marker arg0) {
		return null;
	}

	@Override
	public View getInfoWindow(Marker arg0) {
		return null;
	}

	@Override
	public void onInfoWindowClick(Marker arg0) {
		ToastUtil.show(this, "你点击了景点" + arg0.getTitle());

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return this.detector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		if (e1.getX() - e2.getX() > 120) {
			if (flag < markers.size() - 1) {
				markers.get(flag).setVisible(false);
				flag++;
				markers.get(flag).setVisible(true);
				aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markers
						.get(flag).getPosition(), 17));
				markers.get(flag).showInfoWindow();
				this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,
						R.anim.push_left_in));
				this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
						R.anim.push_left_out));
				this.flipper.showNext();
			}

			return true;
		} else if (e1.getX() - e2.getX() < -120) {
			if (flag > 0) {
				markers.get(flag).setVisible(false);
				flag--;
				markers.get(flag).setVisible(true);
				aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markers
						.get(flag).getPosition(), 17));
				markers.get(flag).showInfoWindow();
				this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,
						R.anim.push_right_in));
				this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
						R.anim.push_right_out));
				this.flipper.showPrevious();
			}

			return true;
		}

		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		ToastUtil.show(this, "你点击了viewFlipper");
		return false;
	}

	/**
	 * 暂时去掉定位功能
	 * */
	// /**
	// * 此方法已经废弃
	// */
	// @Override
	// public void onLocationChanged(Location location) {
	// }
	//
	// @Override
	// public void onProviderDisabled(String provider) {
	// }
	//
	// @Override
	// public void onProviderEnabled(String provider) {
	// }
	//
	// @Override
	// public void onStatusChanged(String provider, int status, Bundle extras) {
	// }
	//
	// /**
	// * 定位成功后回调函数
	// */
	// @Override
	// public void onLocationChanged(AMapLocation aLocation) {
	// if (mListener != null) {
	// mListener.onLocationChanged(aLocation);
	//
	// }
	// }
	//
	// /**
	// * 激活定位
	// */
	// @Override
	// public void activate(OnLocationChangedListener listener) {
	// mListener = listener;
	// if (mAMapLocationManager == null) {
	// mAMapLocationManager = LocationManagerProxy.getInstance(this);
	// }
	// /*
	// * mAMapLocManager.setGpsEnable(false);//
	// * 1.0.2版本新增方法，设置true表示混合定位中包含gps定位，false表示纯网络定位，默认是true
	// */
	// // Location API定位采用GPS和网络混合定位方式，时间最短是5000毫秒
	// mAMapLocationManager.requestLocationUpdates(
	// LocationProviderProxy.AMapNetwork, 5000, 10, this);
	//
	// }
	//
	// /**
	// * 停止定位
	// */
	// @Override
	// public void deactivate() {
	// mListener = null;
	// if (mAMapLocationManager != null) {
	// mAMapLocationManager.removeUpdates(this);
	// mAMapLocationManager.destory();
	// }
	// mAMapLocationManager = null;
	// }
}