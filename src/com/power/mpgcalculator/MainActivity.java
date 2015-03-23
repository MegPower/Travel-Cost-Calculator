package com.power.mpgcalculator;

import java.text.NumberFormat;

//import android.R;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.power.mpgcalculator.R;

public class MainActivity extends Activity {

	private ImageView fordcrownvic;
	
	private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
	private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();
	
	private double cost = 0.0;
	private double miles = 0.0;
	private double mpg = 0.0;
	private double gasPrice = 0.0;
	private TextView distanceMilesDisplayTextView;
	private TextView costDisplayTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		fordcrownvic = (ImageView) findViewById(R.id.fordcrownvic);
		fordcrownvic.setOnClickListener(crownVicListener);	
		
		distanceMilesDisplayTextView = (TextView) findViewById(R.id.distanceMilesDisplayTextView);
		costDisplayTextView = (TextView) findViewById(R.id.costDisplayTextView);
		
		EditText distanceMilesEditText = (EditText) findViewById(R.id.distanceMilesEditText);
		distanceMilesEditText.addTextChangedListener(distanceMilesListener);
		
		SeekBar mpgSeekBar = (SeekBar) findViewById(R.id.mpgSeekBar);
		mpgSeekBar.setOnSeekBarChangeListener(mpgSeekBarListener);
		
		SeekBar gasPriceSeekBar = (SeekBar) findViewById(R.id.gasPriceSeekBar);
		gasPriceSeekBar.setOnSeekBarChangeListener(gasPriceSeekBarListener);
	}

	//do math for cost
	private void updateCost()
	{
		
		cost = (miles/mpg) * gasPrice;
		costDisplayTextView.setText(currencyFormat.format(cost));
		
	}
	
	private OnSeekBarChangeListener mpgSeekBarListener = new OnSeekBarChangeListener(){
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
		{
			mpg = progress;
			updateCost();
		}//end onProgressChanged
		
		@Override
		public void onStartTrackingTouch(SeekBar seekBar)
		{	
		}
		
		@Override
		public void onStopTrackingTouch(SeekBar seekBar)
		{	
		}
		
	};
	
	private OnSeekBarChangeListener gasPriceSeekBarListener = new OnSeekBarChangeListener(){
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
		{
			gasPrice = progress;
			updateCost();
		}//end onProgressChanged
		
		@Override
		public void onStartTrackingTouch(SeekBar seekBar)
		{	
		}
		
		@Override
		public void onStopTrackingTouch(SeekBar seekBar)
		{	
		}
		
	};
	
	//textWatcher for miles
	private TextWatcher distanceMilesListener = new TextWatcher()
	{
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count)
		{
			try
			{
				miles = Double.parseDouble(s.toString());
			}
			catch (NumberFormatException e)
			{
				miles = 0.0;
			}
			distanceMilesDisplayTextView.setText(Double.toString(miles));
			updateCost();
		}
		
		@Override
		public void afterTextChanged(Editable s)
		{
			
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after)
		{
			
		}
		
	};
	
	//click for image to redirect to site
	public OnClickListener crownVicListener = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_VIEW);
			intent.addCategory(Intent.CATEGORY_BROWSABLE);
			intent.setData(Uri.parse("http://en.wikipedia.org/wiki/Ford_Crown_Victoria"));
			startActivity(intent);
		}
	};//end on ClickListener
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
