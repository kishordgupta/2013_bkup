package com.eyes.uciha.sarringan;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import com.eyes.uciha.sarringan.R;

import sponsorpay.User;


import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.OnWheelClickedListener;
import kankan.wheel.widget.OnWheelScrollListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.AbstractWheelAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FightActivity extends Activity {
	public static long  Totalscore =500;
	public static long  partnerscore =0;
	public static int ratio=0;
	public long currentscore =0;
	
	public static Bitmap bitmap1=null;
	public static Bitmap bitmap2=null;
	public static Bitmap bitmap3=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fightlayout);
        LinearLayout la = (LinearLayout)findViewById(R.id.layout);
        la.setBackgroundResource(R.drawable.k);
        initWheel(R.id.slot_1,1);
        initWheel(R.id.slot_2,1);
        initWheel(R.id.slot_3,1);
        TextView text1 = (TextView) findViewById(R.id.score);
        text1.setText("You'r point " + User.Coins_points);
        currentscore =MainActivity.partnerscore+JutsuActivity.partnerscore+EyesActivity.partnerscore+JinActivity.partnerscore+KageActivity.partnerscore+TailActivity.partnerscore;
        if(Eye1Activity.flag_partner){
        	
        ImageView  im_partner1 =(ImageView)findViewById(R.id.team1);        
        ImageView  im_partner2 =(ImageView)findViewById(R.id.team2);
        ImageView  im_partner3 =(ImageView)findViewById(R.id.team3);
        im_partner1.setImageBitmap(MainActivity.bitmap1);
        im_partner2.setImageBitmap(MainActivity.bitmap2);
        im_partner3.setImageBitmap(MainActivity.bitmap3);
        im_partner1.setVisibility(View.VISIBLE);
        im_partner2.setVisibility(View.VISIBLE);
        im_partner3.setVisibility(View.VISIBLE);
        }
        if(Eye1Activity.flag_jutsu){
        ImageView  im_jutsu =(ImageView)findViewById(R.id.team4);
        im_jutsu.setImageBitmap(JutsuActivity.bitmap1);
        
        im_jutsu.setVisibility(View.VISIBLE);
        }
        
        if(Eye1Activity.flag_eyes){
        ImageView  im_eyes =(ImageView)findViewById(R.id.team5);
        im_eyes.setImageBitmap(EyesActivity.bitmap1);
        
		 im_eyes.setVisibility(View.VISIBLE);
        }
        
        if(Eye1Activity.flag_jhinchurki){
        ImageView  im_jhinchurki =(ImageView)findViewById(R.id.team6);
        im_jhinchurki.setImageBitmap(JinActivity.bitmap1);
        
		 im_jhinchurki.setVisibility(View.VISIBLE);
        }
        if(Eye1Activity.flag_kage){
        ImageView  im_kage1 =(ImageView)findViewById(R.id.team7);
        ImageView  im_kage2 =(ImageView)findViewById(R.id.team8);
        im_kage1.setImageBitmap(KageActivity.bitmap1);
        im_kage2.setImageBitmap(KageActivity.bitmap2);
       // im_partner3.setImageBitmap(MainActivity.bitmap3);
        im_kage1.setVisibility(View.VISIBLE);
        im_kage2.setVisibility(View.VISIBLE);
        }
        if(Eye1Activity.flag_Tail){
        	ImageView  im_Tail =(ImageView)findViewById(R.id.team9);
im_Tail.setImageBitmap(TailActivity.bitmap1);
            
			im_Tail.setVisibility(View.VISIBLE);}
        
        
        
        final Context c =this;
        Button mix = (Button)findViewById(R.id.btn_mix);
        mix.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	 TextView text = (TextView) findViewById(R.id.pwd_status);
            	// text.setText("..");
            	// if(User.Coins_points>100)
            	 {
                mixWheel(R.id.slot_1);
                mixWheel(R.id.slot_2);
                mixWheel(R.id.slot_3);
                User.Coins_points=User.Coins_points+10;
                v.setVisibility(View.GONE);
            	 }
            	// else
            	 {
            	//	  Toast.makeText(c, "U Dont have any coin to play", Toast.LENGTH_LONG).show();
            	 }//  mixWheel(R.id.slot_3);
               
            }
        });
       
        Button shae = (Button)findViewById(R.id.btn_shout);
        shae.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	  mixWheel(R.id.slot_1);
                  mixWheel(R.id.slot_2);
                  mixWheel(R.id.slot_3);
                  User.Coins_points=User.Coins_points-10;
            	//finish();
            }
        });
       // updateStatus();
    }
    
    private void initWheel1(int id, int i) {
		// TODO Auto-generated method stub
    	   WheelView wheel = getWheel(id);
    	   wheel.setVisibility(View.GONE);
	}

	// Wheel scrolled flag
    private boolean wheelScrolled = false;
    
    // Wheel scrolled listener
    OnWheelScrollListener scrolledListener = new OnWheelScrollListener() {
        public void onScrollingStarted(WheelView wheel) {
            wheelScrolled = true;
        }
        public void onScrollingFinished(WheelView wheel) {
            wheelScrolled = false;
           updateStatus();
           
        }
    };
    
    // Wheel changed listener
    private OnWheelChangedListener changedListener = new OnWheelChangedListener() {
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            if (!wheelScrolled) {
            //  updateStatus();
           }
        }
    };
    
    /**
     * Updates status
     */
    private void updateStatus() {
        TextView text = (TextView) findViewById(R.id.pwd_status);
        
        test();
       String title="";
       String texta="";
        if(partnerscore<currentscore)
        {
        	title="Congratualtion, you win";
        	texta="You win by " +(currentscore-partnerscore)+ " point";
        	User.Coins_points=User.Coins_points+(currentscore-partnerscore);
        }
        else
        {
        	title="loser !!!!!!!!";
        	texta="You lost by " +(partnerscore-currentscore)+ " point";
        	User.Coins_points=User.Coins_points-(partnerscore-currentscore);
        }
      /* AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
		dialogBuilder.setTitle(title).setMessage(texta).setCancelable(true);
		dialogBuilder.show();
		dialogBuilder.setOnCancelListener(new OnCancelListener() {
			
			@Override
			public void onCancel(DialogInterface arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});*/
          text.setText(texta);
        //  text1.setText("You have : " + U+" coins");
          TextView text1 = (TextView) findViewById(R.id.score);
          text1.setText("You'r point" + User.Coins_points);
            
            
              //  finish();
    }

    /**
     * Initializes wheel
     * @param id the wheel widget Id
     */
    private void initWheel(int id,int i) {
        WheelView wheel = getWheel(id);
        wheel.setViewAdapter(new SlotMachineAdapter(this,i));
        wheel.setCurrentItem((int)(Math.random() * 10));
        
        wheel.addChangingListener(changedListener);
        wheel.addScrollingListener(scrolledListener);
       
        wheel.setCyclic(true);
        wheel.setEnabled(false);
    }
    
    /**
     * Returns wheel by Id
     * @param id the wheel Id
     * @return the wheel with passed Id
     */
    private WheelView getWheel(int id) {
        return (WheelView) findViewById(id);
    }
    public Bitmap getbit(int i, String[] itemas){
    	String id=itemas[i];
	InputStream is = null;
	try {
		is = getAssets().open(id);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    Bitmap bitmap = BitmapFactory.decodeStream(is);
    Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 60, 60, true);
    bitmap.recycle();
    return scaled;}
    /**	
     * Tests wheels
     * @return true 
     */
    private boolean test() {
        int value = getWheel(R.id.slot_1).getCurrentItem();
        int value1 = getWheel(R.id.slot_2).getCurrentItem();
        int value2 = getWheel(R.id.slot_3).getCurrentItem();
        ////
        partnerscore =0;
       
    	partnerscore =(value+10)*10+(value1+7)*15+(value2+9)*20;
     
        return testWheelValue(R.id.slot_2, value) && testWheelValue(R.id.slot_3, value);
    }
    
    /**
     * Tests wheel value
     * @param id the wheel Id
     * @param value the value to test
     * @return true if wheel value is equal to passed value
     */
    private boolean testWheelValue(int id, int value) {
        return getWheel(id).getCurrentItem() == value;
    }
    
    /**
     * Mixes wheel
     * @param id the wheel id
     */
    private void mixWheel(int id) {
        WheelView wheel = getWheel(id);
        wheel.scroll(-350 + (int)(Math.random() * 50), 2000);
    }
    
    /**
     * Slot machine adapter
     */
    private class SlotMachineAdapter extends AbstractWheelAdapter {
        // Image size
        final int IMAGE_WIDTH = 57;
        final int IMAGE_HEIGHT = 57;
        
        // Slot machine symbols
        public  String items[] = new String[] {
              
                
                
                "a/1.jpg",
                
                "a/2.jpg",
                
                "a/3.jpg",
                
                "a/4.jpg",
                
                "a/5.jpg",
               
                "a/6.jpg",
                "a/7.jpg",
                
                
                "a/8.jpg",
                "a/9.jpg",
                "a/10.jpg",
                
                "a/11.jpg",
                "a/12.jpg",
                
                
                "a/13.jpg",
                "a/14.jpg"
                
        };
        
        
        public final String itemsa[] = new String[] {
                
        		
                 "part/p/2.jpg",
                 
                 "part/p/5.jpg",
                
                 "part/p/8.jpg",
                 
                 "part/vp/3.jpg",
                 
                 "part/vp/6.jpg",
                
                 "part/wp/3.jpg",
                 
                 "part/wp/6.jpg"
               
                
                
                
        };
        
        public final String itemsb[] = new String[] {
                
        		 
                 "part/p/3.jpg",
                 
                 "part/p/6.jpg",
                
                 "part/vp/1.jpg",
                 
                 "part/vp/4.jpg",
                
                 "part/wp/1.jpg",
                
                 "part/wp/4.jpg"
                
             
                
        };
        // Cached images
        private List<SoftReference<Bitmap>> images;
        
        // Layout inflater
        private Context context;
        
        /**
         * Constructor
         */
        public SlotMachineAdapter(Context context, int i) {
            this.context = context;
            if(i==1)
            {
            images = new ArrayList<SoftReference<Bitmap>>(items.length);
            for (String id : items) {
                images.add(new SoftReference<Bitmap>(loadImage(id)));
            }
            }
            if(i==2)
            {
            	 items=itemsa;
            images = new ArrayList<SoftReference<Bitmap>>(itemsa.length);
            for (String id : itemsa) {
                images.add(new SoftReference<Bitmap>(loadImage(id)));
            }
            }
            if(i==3)
            {items=itemsb;
            images = new ArrayList<SoftReference<Bitmap>>(itemsb.length);
            for (String id : itemsb) {
                images.add(new SoftReference<Bitmap>(loadImage(id)));
            }
            }
           
        }
        
        /**
         * Loads image from resources
         */
        private Bitmap loadImage(String id) {
        	InputStream is = null;
			try {
				is = getAssets().open(id);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            Bitmap scaled = Bitmap.createScaledBitmap(bitmap, IMAGE_WIDTH, IMAGE_HEIGHT, true);
            bitmap.recycle();
            return scaled;
        }

        @Override
        public int getItemsCount() {
            return items.length;
        }
      
   
        // Layout params for image view
        final LayoutParams params = new LayoutParams(IMAGE_WIDTH, IMAGE_HEIGHT);
        
        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            ImageView img;
            if (cachedView != null) {
                img = (ImageView) cachedView;
            } else {
                img = new ImageView(context);
            }
            img.setLayoutParams(params);
            SoftReference<Bitmap> bitmapRef = images.get(index);
            Bitmap bitmap = bitmapRef.get();
            if (bitmap == null) {
                bitmap = loadImage(items[index]);
                images.set(index, new SoftReference<Bitmap>(bitmap));
            }
            img.setImageBitmap(bitmap);
            
            return img;
        }
    }
}
