package com.rslakra.android.orientationexample;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if(id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * 
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		
		// Checks the orientation of the screen for landscape and portrait
		if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
		{
			Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
		}
		else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
		{
			Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
		}
	}
}
