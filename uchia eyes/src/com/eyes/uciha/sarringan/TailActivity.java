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
import android.content.Context;
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

public class TailActivity extends Activity {
	public static long  Totalscore =500;
	public static long  partnerscore =0;
	public static int ratio=0;
	public int currentscore =0;
	
	public static Bitmap bitmap1=null;
	public static Bitmap bitmap2=null;
	public static Bitmap bitmap3=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.slot_machine_layout);
        LinearLayout la = (LinearLayout)findViewById(R.id.layout);
        la.setBackgroundResource(R.drawable.b2);
        initWheel(R.id.slot_1,1);
      //  initWheel(R.id.slot_2,2);
     //   initWheel(R.id.slot_3,3);
        TextView text1 = (TextView) findViewById(R.id.score);
        text1.setText("You'r point " + User.Coins_points);
        final Context c =this;
        Button mix = (Button)findViewById(R.id.btn_mix);
        mix.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	 TextView text = (TextView) findViewById(R.id.pwd_status);
            	// text.setText("..");
            	 if(User.Coins_points>10)
            	 {
                mixWheel(R.id.slot_1);
                User.Coins_points=User.Coins_points-10;
                v.setVisibility(View.GONE);
            	 }
            	 else
            	 {
            		  Toast.makeText(c, "U Dont have any coin to play", Toast.LENGTH_LONG).show();
            	 }//  mixWheel(R.id.slot_3);
            	 
            	// v.setVisibility(View.GONE);
               
            }
        });
        Button share = (Button)findViewById(R.id.btn_share);
        share.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	TextView text = (TextView) findViewById(R.id.pwd_status);
          // 	 text.setText("..");
           	 if(User.Coins_points>10)
        	 {
            mixWheel(R.id.slot_1);
            User.Coins_points=User.Coins_points-10;
          
        	 }
        	 else
        	 {
        		  Toast.makeText(c, "U Dont have any coin to play", Toast.LENGTH_LONG).show();
        	 }
             
                }
        });
        Button shae = (Button)findViewById(R.id.btn_shout);
        shae.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	finish();
            }
        });
       // updateStatus();
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
             // updateStatus();
            }
        }
    };
    
    /**
     * Updates status
     */
    private void updateStatus() {
        TextView text = (TextView) findViewById(R.id.pwd_status);
        
        test();
       
        
        	
      
            text.setText("Your Tailed Beast point : " + partnerscore+" ");
           // text1.setText("You have : " + Totalscore+" coins");
            TextView text1 = (TextView) findViewById(R.id.score);
            text1.setText("You'r point " + User.Coins_points);
            
            
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
       // int value1 = getWheel(R.id.slot_2).getCurrentItem();
       // int value2 = getWheel(R.id.slot_3).getCurrentItem();
        ////
        partnerscore =0;
        SlotMachineAdapter s = new SlotMachineAdapter(this, 1);
        bitmap1=getbit(value,s.items);
    	partnerscore =(value+1)*10;
       // bitmap2=getbit(value1,s.itemsa);
      //  bitmap3=getbit(value2,s.itemsb);
      /*  if(value==6)
        {
        	Toast.makeText(this, "U got sakura, get the hell out of here", Toast.LENGTH_LONG).show();
        	partnerscore =partnerscore-100;
        }
        else if(value>6 || value<6)
        {   if(value==4)
        	{
        	Toast.makeText(this, "U got shikamaru, you have a great chance to  win", Toast.LENGTH_LONG).show();
        	partnerscore =partnerscore+100;
        	}
           else if(value>4)
        		partnerscore =partnerscore+10;
           else if(value<4)
       		partnerscore =partnerscore-10;
        		
        }
        
        if(value1<=2)
    	{
    		partnerscore =partnerscore-10;
    	}
    	else
    		partnerscore =partnerscore+15;
        
        
        if(value2<=2)
    	{
    		partnerscore =partnerscore-10;
    	}
    	else
    		partnerscore =partnerscore+25;
        */
       // Toast.makeText(this, ""+value, Toast.LENGTH_LONG).show();
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
              
                "tail/1.jpg",
                
                "tail/2.jpg",
                
                "tail/3.jpg",
                
                "tail/4.jpg",
                
                "tail/5.jpg",
                
                "tail/6.jpg",
               
                "tail/7.jpg",
                "tail/8.jpg",
                
                
                "tail/9.jpg"
                
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
