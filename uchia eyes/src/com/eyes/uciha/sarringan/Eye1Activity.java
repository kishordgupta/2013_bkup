package com.eyes.uciha.sarringan;

import com.eyes.uciha.sarringan.R;
import com.ironsource.mobilcore.MobileCore;
import com.ironsource.mobilcore.OnReadyListener;
import com.ironsource.mobilcore.MobileCore.AD_UNITS;
import com.ironsource.mobilcore.MobileCore.LOG_TYPE;

import sponsorpay.Sponsorpayappstemplate;
import sponsorpay.User;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Eye1Activity extends Activity implements OnClickListener{
	public static long  Totalscore =500;
	public static long  partnerscore =500;
	public static int ratio=0;
	public int currentscore =0;
	Button partner;
	Button jutsu;
	Button eyes;
	Button jhinchurki;
	Button kage;
	Button Tail;
	
	public static Boolean flag_partner=false;
	public static Boolean flag_jutsu=false;
	public static Boolean flag_eyes=false;
	public static Boolean flag_jhinchurki=false;
	public static Boolean flag_kage=false;
	public static Boolean flag_Tail=false;
	
	Button fighButton;
	Button poButton;
	
	ImageView im_partner1;
	ImageView im_partner2;
	ImageView im_partner3;
	ImageView im_jutsu;
	ImageView im_eyes;
	ImageView im_jhinchurki;
	ImageView im_kage1;
	ImageView im_kage2;
	ImageView im_Tail;
	
	TextView tex_partner;
	TextView tex_jutsu;
	TextView tex_eyes;
	TextView tex_jhinchurki;
	TextView tex_kage;
	TextView tex_Tail;
	TextView text1 ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listofp);
     /*   MobileCore.init(this,"8YKF89U9YHXZUIMHFEQ0UDC1T9TOY", LOG_TYPE.DEBUG);
   	    MobileCore.getSlider().setContentViewWithSlider(this, R.layout.listofp);
     */
    	
        
        final Context c =this;
       
        flag_partner=false;
    	 flag_jutsu=false;
    	 flag_eyes=false;
    	 flag_jhinchurki=false;
    	 flag_kage=false;
    	 flag_Tail=false;
    	
    	
        partner =(Button)findViewById(R.id.btn_mix_team);
        jutsu =(Button)findViewById(R.id.btn_mix_jutsu);
        eyes =(Button)findViewById(R.id.btn_mix_eye);
        jhinchurki =(Button)findViewById(R.id.btn_mix_jinchu);
        kage =(Button)findViewById(R.id.btn_mix_kage);
        Tail =(Button)findViewById(R.id.btn_mix_tail);
        
        partner.setOnClickListener(this);
        jutsu.setOnClickListener(this);
        eyes.setOnClickListener(this);
        jhinchurki.setOnClickListener(this);
        kage.setOnClickListener(this);
        Tail.setOnClickListener(this);
        
        tex_partner =(TextView)findViewById(R.id.pwd_teamscore);
        tex_jutsu =(TextView)findViewById(R.id.pwd_jutsi_status);
        tex_eyes =(TextView)findViewById(R.id.pwd_eye_status);
        tex_jhinchurki =(TextView)findViewById(R.id.pwd_jinchur_status);
        tex_kage =(TextView)findViewById(R.id.pwd_kage_status);
        tex_Tail =(TextView)findViewById(R.id.pwd_tail_status);
        
        
        im_partner1 =(ImageView)findViewById(R.id.team1);
        im_partner2 =(ImageView)findViewById(R.id.team2);
        im_partner3 =(ImageView)findViewById(R.id.team3);
        im_jutsu =(ImageView)findViewById(R.id.img_jutsu);
        im_eyes =(ImageView)findViewById(R.id.img_eye);
        im_jhinchurki =(ImageView)findViewById(R.id.img_jhin);
        im_kage1 =(ImageView)findViewById(R.id.kageimg1);
        im_kage2 =(ImageView)findViewById(R.id.kageimg2);
        im_Tail =(ImageView)findViewById(R.id.img_tail);
        
        fighButton =(Button)findViewById(R.id.btn_fight);
        poButton =(Button)findViewById(R.id.btn_getpoint);
        fighButton.setOnClickListener(this);
        poButton.setOnClickListener(this);
        
        
        text1 = (TextView) findViewById(R.id.score);
        text1.setText("You'r point " + User.Coins_points);
    }
  
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_mix_team:
            startActivityForResult(new Intent(this, MainActivity.class), 1);
			break;
		case R.id.btn_mix_jutsu:
			 startActivityForResult(new Intent(this, JutsuActivity.class), 3);	
			break;
		case R.id.btn_mix_eye:
			startActivityForResult(new Intent(this, EyesActivity.class), 4);	
			break;
		case R.id.btn_mix_jinchu:
			startActivityForResult(new Intent(this, JinActivity.class), 5);	
			break;
		case R.id.btn_mix_kage:
			startActivityForResult(new Intent(this, KageActivity.class), 6);	
			break;
		case R.id.btn_mix_tail:
			 startActivityForResult(new Intent(this, TailActivity.class), 2);
			break;
		case R.id.btn_fight:
			 startActivityForResult(new Intent(this, FightActivity.class), 12);
			break;
		case R.id.btn_getpoint:
			  final sponsorpay.Sponsorpayappstemplate sp = new Sponsorpayappstemplate(this);
		      sp.onLaunchOfferwallClick();
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 1:
               im_partner1.setImageBitmap(MainActivity.bitmap1);
               im_partner2.setImageBitmap(MainActivity.bitmap2);
               im_partner3.setImageBitmap(MainActivity.bitmap3);
               im_partner1.setVisibility(View.VISIBLE);
               im_partner2.setVisibility(View.VISIBLE);
               im_partner3.setVisibility(View.VISIBLE);
               tex_partner.setText("Partners strength " +MainActivity.partnerscore);
               flag_partner=true;
			break;
		case 2:
			im_Tail.setImageBitmap(TailActivity.bitmap1);
            
			im_Tail.setVisibility(View.VISIBLE);
            
            tex_Tail.setText("Tails Chakra " +TailActivity.partnerscore);
            
            flag_Tail=true;
            
			break;
		case 3:
            im_jutsu.setImageBitmap(JutsuActivity.bitmap1);
            
            im_jutsu.setVisibility(View.VISIBLE);
            
            tex_jutsu.setText("Jutsu power " +JutsuActivity.partnerscore);
            
            flag_jutsu=true;
			break;
		case 4:
			 im_eyes.setImageBitmap(EyesActivity.bitmap1);
	            
			 im_eyes.setVisibility(View.VISIBLE);
	            
	            tex_eyes.setText("occular power " +EyesActivity.partnerscore);
	            flag_eyes=true;
			break;
		case 5:
			im_jhinchurki.setImageBitmap(JinActivity.bitmap1);
            
			 im_jhinchurki.setVisibility(View.VISIBLE);
	            
	            tex_jhinchurki.setText("Jhinchurki power " +JinActivity.partnerscore);
	            flag_jhinchurki=true;
			break;
		case 6:
			 im_kage1.setImageBitmap(KageActivity.bitmap1);
             im_kage2.setImageBitmap(KageActivity.bitmap2);
            // im_partner3.setImageBitmap(MainActivity.bitmap3);
             im_kage1.setVisibility(View.VISIBLE);
             im_kage2.setVisibility(View.VISIBLE);
             //im_partner3.setVisibility(View.VISIBLE);
             flag_kage=true;
             tex_kage.setText("Kages strength " +KageActivity.partnerscore);
			break;
	

		default:
			break;
		}
		 usercoincomit() ;
		 text1.setText("You'r point" + User.Coins_points);
	}
	  private void usercoincomit() {
			// TODO Auto-generated method stub
			  SharedPreferences prefs = this.getSharedPreferences(
		    	      "com.example.app", Context.MODE_PRIVATE);
		Editor ed=prefs.edit();
		ed.putLong("userpoint",User.Coins_points);
		ed.commit();
		   
		}
}
