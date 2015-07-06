package com.eyes.uciha.sarringan;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

import sponsorpay.Sponsorpayappstemplate;
import sponsorpay.User;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eyes.uciha.sarringan.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.ironsource.mobilcore.CallbackResponse;
import com.ironsource.mobilcore.MobileCore;
import com.ironsource.mobilcore.OnReadyListener;
import com.ironsource.mobilcore.MobileCore.AD_UNITS;
import com.ironsource.mobilcore.MobileCore.LOG_TYPE;



public class FirstActivity extends Activity implements OnClickListener {
	Button wall;
	Button b;
	Button C;
	ViewPager viewPager = null;
	Context c = null;
	TextView all = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MobileCore.init(this, "8YKF89U9YHXZUIMHFEQ0UDC1T9TOY", LOG_TYPE.DEBUG,
				AD_UNITS.ALL_UNITS);
		// MobileCore.getSlider().setContentViewWithSlider(this, R.layout.menu);
		// Look up the AdView as a resource and load a request.
	/*	MobileCore.setStickeezReadyListener(new OnReadyListener() {
			@Override
			public void onReady(AD_UNITS adUnit) {
				if (adUnit.equals(AD_UNITS.STICKEEZ)) {
					// do something
					MobileCore.showStickee(FirstActivity.this);
				}
			}
		});
		MobileCore.showStickee(this);*/
		// FlurryAgent.onStartSession(this,"7M7ZNVSCV84THW2GD7XK");
		// Hashtable<String, String> flags = new Hashtable<String, String>();
		// flags.put(TapjoyConnectFlag.ENABLE_LOGGING, "true");
		// String tapjoyAppID
		// ="2be384c1-9356-45ca-b9ac-3ee08c4faa8f";//.."828a51c2-5c2a-45d2-bc93-82c7d8bbbb63";
		// String tapjoySecretKey = "bvf3I2plMAvV0AJ1IDok";;
		/*
		 * // NOTE: This is the only step required if you're an advertiser.
		 * TapjoyConnect.requestTapjoyConnect(getApplicationContext(),
		 * tapjoyAppID, tapjoySecretKey, flags, new TapjoyConnectNotifier() {
		 * 
		 * @Override public void connectSuccess() {
		 * 
		 * }
		 * 
		 * @Override public void connectFail() {
		 * 
		 * } });
		 */
		//MobileCore.init(this, "8YKF89U9YHXZUIMHFEQ0UDC1T9TOY", LOG_TYPE.DEBUG);
		// MobileCore.getSlider().setContentViewWithSlider(this,
		// R.layout.activity_main);
		
		usercoinupdate();
		c = this;
		sponsorpayupdate();
		viewPager = (ViewPager) findViewById(R.id.view_pager);
		ImagePagerAdapter adapter = new ImagePagerAdapter();
		viewPager.setAdapter(adapter);

		wall = (Button) findViewById(R.id.wall);
		wall.setOnClickListener(this);
		b = (Button) findViewById(R.id.img_btn_image_pager_set_as_wp);
		b.setOnClickListener(this);
		C = (Button) findViewById(R.id.geteye);
		C.setOnClickListener(this);
		all = (TextView) findViewById(R.id.totalscore);
		all.setText("Current Score " + User.Coins_points);
		
		AdView adView = new AdView(this);
	    adView.setAdSize(AdSize.BANNER);
	    adView.setAdUnitId("ca-app-pub-2884314377778347/4786124319");

	    // Add the AdView to the view hierarchy. The view will have no size
	    // until the ad is loaded.
	    LinearLayout layout = (LinearLayout) findViewById(R.id.a);
	    layout.addView(adView);

	    // Create an ad request. Check logcat output for the hashed device ID to
	    // get test ads on a physical device.
	    AdRequest adRequest = new AdRequest.Builder()
	        .build();

	    // Start loading the ad in the background.
	    adView.loadAd(adRequest);


	}

	private void usercoinupdate() {
		// TODO Auto-generated method stub
		SharedPreferences prefs = this.getSharedPreferences("com.example.app",
				Context.MODE_PRIVATE);
		User.Coins_points = prefs.getLong("userpoint", 500);
		User.Coins_points = User.Coins_points + 5;
	}

	private void usercoincomit() {
		// TODO Auto-generated method stub
		SharedPreferences prefs = this.getSharedPreferences("com.example.app",
				Context.MODE_PRIVATE);
		Editor ed = prefs.edit();
		ed.putLong("userpoint", User.Coins_points);
		ed.commit();

	}

	private void sponsorpayupdate() {
		// TODO Auto-generated method stub
		final sponsorpay.Sponsorpayappstemplate sp = new Sponsorpayappstemplate(
				this);
		sp.onRequestNewCoinsClick();
		usercoincomit();

	}

	private void setAsWallPaper() {
		// TODO Auto-generated method stub
		MainActivity.ratio = viewPager.getCurrentItem();
		startActivityForResult(new Intent(getApplicationContext(),
				Eye1Activity.class), 1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		sponsorpayupdate();
		all.setText("Current Score " + User.Coins_points);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		usercoincomit();
		MobileCore.showOfferWall((Activity) c, new CallbackResponse() {
			@Override
			public void onConfirmation(TYPE arg0) {
				((Activity) c).finish();
			}
		});
		// super.onBackPressed();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		usercoincomit();
		super.onDestroy();
	}

	private class ImagePagerAdapter extends PagerAdapter {
		private int[] mImages = new int[] { 1, 2, 3, 4, 6, 7, 8, 9, 10, 11, 12

		};

		@Override
		public int getCount() {
			return mImages.length;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == ((WebView) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			Context context = FirstActivity.this;
			InputStream stream = null;
	/*		try {
				stream = getAssets().open(mImages[position] + ".gif");
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			
			WebView imageView = new WebView(context);
			imageView.getSettings().setLoadWithOverviewMode(true);
			imageView.getSettings().setUseWideViewPort(true);
			imageView.getSettings().setSupportZoom(false);
	/*		imageView.loadUrl("file:///android_asset/"+mImages[position] + ".gif");//*/		imageView.loadUrl("file:///android_asset/"+mImages[position]  + ".html");
		imageView.setBackgroundColor(Color.TRANSPARENT);
		imageView.setWebViewClient(new WebViewClient()
		{
		    @Override
		    public void onPageFinished(WebView view, String url)
		    {
		    	
		        if (Build.VERSION.SDK_INT >= 11&Build.VERSION.SDK_INT<19) view.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
		    }
		});
			/*imageView.loadUrl(("file:\\\android_asset\"+mImages[position] + ".gif");
			  try {
					stream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			RelativeLayout.LayoutParams wvParams = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
		
			imageView.setLayoutParams(wvParams);

			Display display = getWindowManager().getDefaultDisplay();
			int currentapiVersion = android.os.Build.VERSION.SDK_INT;
			Point size = new Point();
			if (currentapiVersion >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
				// Do something for API 17 only (4.2)
				// getRealSize()
				display.getSize(size);
			} else if (currentapiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB_MR2) {
				// Do something for API 13 and above , but below API 17 (API 17
				// will trigger the above block
				// getSize()
				display.getSize(size);
			} else {
				// do something for phones running an SDK before Android 3.2
				// (API 13)
				// getWidth(), getHeight()
				size.x = display.getWidth();
				size.y = display.getHeight();
			}

			((ViewPager) container).setPageMargin(-(int) (size.x * 0.35f));
			((ViewPager) container).addView(imageView, wvParams);
			return imageView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView((WebView) object);
		}
	}

	public class GifWebView extends View {
		private Movie mMovie;
		InputStream mStream;
		long mMoviestart;

		// private int height;
		// private int width;

		public GifWebView(Context context, InputStream stream) {
			super(context);
			mStream = stream;
			mMovie = Movie.decodeStream(mStream);
           try {
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawColor(Color.TRANSPARENT);
			super.onDraw(canvas);
			final long now = SystemClock.uptimeMillis();

			if (mMoviestart == 0) {
				mMoviestart = now;
			}
			final int relTime = (int) ((now - mMoviestart) % mMovie.duration());
			double scalex = (double) this.getWidth() / (double) mMovie.width();
			double scaley = (double) this.getHeight()
					/ (double) mMovie.height();
			canvas.scale((float) scalex * 0.60f, (float) scaley * 0.60f);
			mMovie.setTime(relTime);
			mMovie.draw(canvas, this.getWidth() * 0.25f,
					this.getHeight() * 0.10f);
			
			this.invalidate();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.wall) {
			setAsWallPaper();
		}
		if (v.getId() == R.id.img_btn_image_pager_set_as_wp) {
			sponsorpay.Sponsorpayappstemplate sp = new Sponsorpayappstemplate(c);
			if (GetNetworkStatus.isNetworkAvailable(c))
				sp.onLaunchOfferwallClick();
			else {
				Toast.makeText(c, "Network need not jutsu", Toast.LENGTH_LONG)
						.show();
			}
		}
		if (v.getId() == R.id.geteye) {
			GetEyeActivity.position = viewPager.getCurrentItem();

			int fol = (int) User.Coins_points / 1000;
			if (fol >= GetEyeActivity.position) {
				startActivityForResult(new Intent(getApplicationContext(),
						GetEyeActivity.class), 1);
			} else {
				AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(c);
				dialogBuilder
						.setTitle("opps")
						.setMessage(
								"You Dont have enough point to get this eye")
						.setCancelable(true);
				dialogBuilder.show();
			}
		}
	}
}