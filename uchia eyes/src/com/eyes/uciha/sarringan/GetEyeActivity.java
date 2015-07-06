package com.eyes.uciha.sarringan;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import sponsorpay.Sponsorpayappstemplate;
import sponsorpay.User;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eyes.uciha.sarringan.R;

import com.ironsource.mobilcore.MobileCore;
import com.ironsource.mobilcore.OnReadyListener;
import com.ironsource.mobilcore.MobileCore.AD_UNITS;
import com.ironsource.mobilcore.MobileCore.LOG_TYPE;


public class GetEyeActivity extends Activity implements OnClickListener{
	Button wall;
	Button b;
	Button C;
	LinearLayout viewPager = null;
   Context c=null;
   EditText all=null;
  public static int position=2;
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_eye);
    MobileCore.init(this, "8YKF89U9YHXZUIMHFEQ0UDC1T9TOY", LOG_TYPE.DEBUG,
			AD_UNITS.ALL_UNITS);
	// MobileCore.getSlider().setContentViewWithSlider(this, R.layout.menu);
	// Look up the AdView as a resource and load a request.
	MobileCore.setStickeezReadyListener(new OnReadyListener() {
		@Override
		public void onReady(AD_UNITS adUnit) {
			if (adUnit.equals(AD_UNITS.STICKEEZ)) {
				// do something
				MobileCore.showStickee(GetEyeActivity.this);
			}
		}
	});
	MobileCore.showStickee(this);
   // MobileCore.init(this,"8YKF89U9YHXZUIMHFEQ0UDC1T9TOY", LOG_TYPE.DEBUG);
	// MobileCore.getSlider().setContentViewWithSlider(this, R.layout.activity_eye);
    usercoinupdate();
    c=this;
    sponsorpayupdate();
 //   viewPager  = new LinearLayout(c);
    viewPager = (LinearLayout)findViewById(R.id.gif_linear_layout);
    Context context = GetEyeActivity.this;
    WebView imageView = new WebView(context);
	imageView.getSettings().setLoadWithOverviewMode(true);
	imageView.getSettings().setUseWideViewPort(true);
	imageView.getSettings().setSupportZoom(false);
	imageView.loadUrl("file:///android_asset/"+mImages[position]  + ".html");
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
 
   viewPager.addView(imageView);
	wall = (Button) findViewById(R.id.wall);
	wall.setOnClickListener(this);
	 b=(Button) findViewById(R.id.img_btn_image_pager_set_as_wp);
	b.setOnClickListener(this);
	
		all=(EditText)findViewById(R.id.totalscorea);
	//	all.setText("Current Score " + User.Coins_points);
   
  }
  private int[] mImages = new int[] {
	        1,2,3,4,6,7,8,9,10,11,12
	        
	       
	    };
  private void usercoinupdate() {
	// TODO Auto-generated method stub
	  SharedPreferences prefs = this.getSharedPreferences(
    	      "com.example.app", Context.MODE_PRIVATE);
   User.Coins_points= prefs.getLong("userpoint", 1000);
   User.Coins_points=User.Coins_points+100;
}
  private void usercoincomit() {
		// TODO Auto-generated method stub
		  SharedPreferences prefs = this.getSharedPreferences(
	    	      "com.example.app", Context.MODE_PRIVATE);
	Editor ed=prefs.edit();
	ed.putLong("userpoint",User.Coins_points);
	ed.commit();
	   
	}
private void sponsorpayupdate() {
	// TODO Auto-generated method stub
	  final sponsorpay.Sponsorpayappstemplate sp = new Sponsorpayappstemplate(this);
	  sp.onRequestNewCoinsClick();
	  usercoincomit() ;
}

  
  private File setAsWallPaper() {
		// TODO Auto-generated method stub
	//	MainActivity.ratio=viewPager.getCurrentItem();
	//	startActivityForResult(new Intent(getApplicationContext(), Eye1Activity.class),1);
	  InputStream stream = null;
	    try {
	        stream = getAssets().open(mImages[position]+".png");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    Bitmap  bitmap = BitmapFactory.decodeStream(stream); 
	  Bitmap mutableBitmap =bitmap.copy(Bitmap.Config.ARGB_8888, true);
	    bitmap=getPoster(mutableBitmap);
	      Calendar cal=Calendar.getInstance();
	       
	 		File dir = Environment.getExternalStorageDirectory();
	 		 File output = new File(dir, cal.getTime().toString().replace(".", "")+"lilait.jpg");
	 		 FileOutputStream os = null;
	 		
	 		try {
	 			os = new FileOutputStream(output);
	 		} catch (FileNotFoundException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
	 		 if(os!=null&&bitmap!=null)
	 		  {
	 		 bitmap.compress(CompressFormat.PNG, 100, os);
	 		  }
	 		    try {
	 				os.flush();
	 			} catch (IOException e) {
	 				// TODO Auto-generated catch block
	 				e.printStackTrace();
	 			}
	 		    try {
	 				os.close();
	 			} catch (IOException e) {
	 				// TODO Auto-generated catch block
	 				e.printStackTrace();
	 			}
	 		    return output;
	 		  
	}
  private  Bitmap getPoster( Bitmap background ) {
	
	  Canvas canvas = new Canvas(background);
	  String THE_QUOTE=position+"00000" + "  Eye Level";
	  Typeface font = Typeface.createFromAsset(getAssets(), "DK Crayon Crumble.ttf");
	  font = Typeface.create(font, Typeface.BOLD);
	  Paint paint = new Paint();
	  paint.setTypeface(font);
	  paint.setAntiAlias(true);
	  paint.setColor(Color.WHITE);
	  paint.setStyle(Style.FILL);
	  paint.setShadowLayer(2.0f, 1.0f, 1.0f, Color.BLACK);
	  float fontSize = 25;// getFontSize(background.getWidth(), THE_QUOTE, paint); //You'll have to define a way to find a size that fits, or just use a constant size.
      String texts=all.getText().toString();
	  paint.setTextSize(fontSize);
	  canvas.drawText("Master " + texts + " Uchia", (background.getWidth() - paint.measureText(THE_QUOTE)) / 2,
		       20, paint); 
	  canvas.drawText(THE_QUOTE, (background.getWidth() - paint.measureText(THE_QUOTE)) / 2,
	      background.getHeight() - 40, paint); //You might want to do something different. In my case every image has a filler in the bottom which is 50px. 
	  return background;
	  }
  @Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	// TODO Auto-generated method stub
	super.onActivityResult(requestCode, resultCode, data);
	all.setText("Current Score " + User.Coins_points);
}
@Override
public void onBackPressed() {
	// TODO Auto-generated method stub
	usercoincomit();
	super.onBackPressed();
}

@Override
protected void onDestroy() {
	// TODO Auto-generated method stub
	usercoincomit();
	super.onDestroy();
}
 public class GifWebView extends View {
	    private Movie mMovie;
	    InputStream mStream;
	    long mMoviestart;
	//	private int height;
	//	private int width;

	    public GifWebView(Context context, InputStream stream) {
	        super(context);
	        mStream = stream;
	        mMovie = Movie.decodeStream(mStream);
	        
	      /*  Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE))
					.getDefaultDisplay();
					height=display.getHeight();
					width= display.getWidth();*/
				/*	RelativeLayout.LayoutParams wvParams = new RelativeLayout.LayoutParams(
				              RelativeLayout.LayoutParams.MATCH_PARENT,
				              RelativeLayout.LayoutParams.MATCH_PARENT);
					this.setLayoutParams(wvParams);*/
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
            double scaley = (double) this.getHeight() / (double) mMovie.height();
            canvas.scale((float) scalex*0.60f, (float) scaley*0.60f);
	        mMovie.setTime(relTime);
	        mMovie.draw(canvas, this.getWidth()*0.25f,this.getHeight()*0.10f);
	        
	        this.invalidate();
	    }
	}

@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	if(v.getId() == R.id.wall)
	{  Share.share(setAsWallPaper(), c,"");
		
	}
	if(v.getId()==R.id.img_btn_image_pager_set_as_wp)
	{
		setAsWallPaper();
	}
}
}