package www.sdxuyan.tk.AMap;

import java.util.Vector;

import www.sdxuyan.tk.AMap.R;
import www.sdxuyan.tk.util.AMapUtil;
import www.sdxuyan.tk.util.Constants;
import www.sdxuyan.tk.util.Intro;
import www.sdxuyan.tk.util.ToastUtil;
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
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.SupportMapFragment;

public class MainActivity extends FragmentActivity implements
		OnMarkerClickListener, InfoWindowAdapter, OnInfoWindowClickListener,
		OnGestureListener {
	private AMap aMap;
	// private OnLocationChangedListener mListener;
	// private LocationManagerProxy mAMapLocationManager;

	private Marker SDIE;
	private Marker QUANCHENGGUANGCHANG;
	private Marker DAMINGHU;

	private Vector<Marker> sights = new Vector<Marker>();

	// private Button nextButton;// ����ƶ�����һ����
	// private Button beforeButton;// �ƶ���ǰһ����

	private int flag = 0;

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

		detector = new GestureDetector(this);
		flipper = (ViewFlipper) this.findViewById(R.id.myViewFlipper);

		flipper.addView(addTextView(Intro.SDIE));
		flipper.addView(addTextView("������"));
		flipper.addView(addTextView("Ȫ�ǹ㳡"));
	}

	private View addTextView(String text) {

		LayoutInflater layoutInflater = LayoutInflater.from(this);
		LinearLayout resultView = (LinearLayout) layoutInflater.inflate(
				R.layout.itro_l, null);
		((TextView) resultView.findViewById(R.id.textView2))
				.setText(text);
		return resultView;
		// TextView iv = new TextView(this);
		// iv.setText(text);
		// iv.setTextSize(30.0f);
		// return iv;
	}

	/**
	 * ��ʼ��AMap����
	 */
	private void init() {
		if (aMap == null) {
			aMap = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map)).getMap();
			if (AMapUtil.checkReady(this, aMap)) {

				setUpMap();
			}

		}
	}

	private void setUpMap() {
		// �Զ���ϵͳ��λС����
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
		// ����Ϊtrue��ʾϵͳ��λ��ť��ʾ����Ӧ�����false��ʾ���أ�Ĭ����false
		aMap.setOnMarkerClickListener(this);// ���õ��marker�¼�������
		aMap.setInfoWindowAdapter(this);
		aMap.setOnInfoWindowClickListener(this);// ���õ��infoWindow�¼�������
		addMarkersToMap();
		sights.add(SDIE);
		sights.add(DAMINGHU);
		sights.add(QUANCHENGGUANGCHANG);
		aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sights.get(flag)
				.getPosition(), 17));
		sights.get(flag).showInfoWindow();
		// nextButton = (Button) findViewById(R.id.next);
		// nextButton.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// if (flag < sights.size() - 1) {
		// sights.get(flag).setVisible(false);
		// flag++;
		// sights.get(flag).setVisible(true);
		// aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sights
		// .get(flag).getPosition(), 17));
		// sights.get(flag).showInfoWindow();
		// }
		// // DAMINGHU.setVisible(true);
		// // aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
		// // DAMINGHU.getPosition(), 15));
		// // DAMINGHU.showInfoWindow();
		// // QUANCHENGGUANGCHANG.remove();
		//
		// }
		// });
		// beforeButton = (Button) findViewById(R.id.before);
		// beforeButton.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// if (flag > 0) {
		// sights.get(flag).setVisible(false);
		// flag--;
		// sights.get(flag).setVisible(true);
		// aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sights
		// .get(flag).getPosition(), 17));
		// sights.get(flag).showInfoWindow();
		// }
		//
		// }
		// });

	}

	@Override
	protected void onPause() {
		super.onPause();
		// deactivate();
	}

	/**
	 * ����ͼ�����marker
	 * */
	private void addMarkersToMap() {
		SDIE = aMap.addMarker(Constants.SDIE);
		QUANCHENGGUANGCHANG = aMap.addMarker(Constants.QUANCHENGGUANGCHANG);
		QUANCHENGGUANGCHANG.setVisible(false);
		Constants.DAMINGHU.visible(false);
		DAMINGHU = aMap.addMarker(Constants.DAMINGHU);

		// DAMINGHU.setVisible(false);
		// drawMarkers();// ���10������ϵͳĬ��icon��marker��
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
		ToastUtil.show(this, "�����˾���" + arg0.getTitle());

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
			if (flag < sights.size() - 1) {
				sights.get(flag).setVisible(false);
				flag++;
				sights.get(flag).setVisible(true);
				aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sights
						.get(flag).getPosition(), 17));
				sights.get(flag).showInfoWindow();
				this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,
						R.anim.push_left_in));
				this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
						R.anim.push_left_out));
				this.flipper.showNext();
			}

			return true;
		} else if (e1.getX() - e2.getX() < -120) {
			if (flag > 0) {
				sights.get(flag).setVisible(false);
				flag--;
				sights.get(flag).setVisible(true);
				aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sights
						.get(flag).getPosition(), 17));
				sights.get(flag).showInfoWindow();
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
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * ��ʱȥ����λ����
	 * */
	// /**
	// * �˷����Ѿ�����
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
	// * ��λ�ɹ���ص�����
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
	// * ���λ
	// */
	// @Override
	// public void activate(OnLocationChangedListener listener) {
	// mListener = listener;
	// if (mAMapLocationManager == null) {
	// mAMapLocationManager = LocationManagerProxy.getInstance(this);
	// }
	// /*
	// * mAMapLocManager.setGpsEnable(false);//
	// * 1.0.2�汾��������������true��ʾ��϶�λ�а���gps��λ��false��ʾ�����綨λ��Ĭ����true
	// */
	// // Location API��λ����GPS�������϶�λ��ʽ��ʱ�������5000����
	// mAMapLocationManager.requestLocationUpdates(
	// LocationProviderProxy.AMapNetwork, 5000, 10, this);
	//
	// }
	//
	// /**
	// * ֹͣ��λ
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