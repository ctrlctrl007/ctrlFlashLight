package com.ctrl.superpower;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ImageView mSwich;
	private Camera mCamera;
	private Boolean isOpen = false;
	private long firstTime=0;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                long secondTime=System.currentTimeMillis();
                if(secondTime-firstTime>2000){
                    Toast.makeText(MainActivity.this,"再按一次返回键退出",Toast.LENGTH_SHORT).show();
                    firstTime=secondTime;
                    return true;
                }else{
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mSwich = (ImageView) findViewById(R.id.swich);
		mSwich.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!isOpen){
					mCamera = Camera.open();
					Parameters parameters = mCamera.getParameters();
					parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
					mCamera.setParameters(parameters);
					mCamera.startPreview();
					isOpen = true;
					mSwich.setImageDrawable(getResources().getDrawable(R.drawable.openlight));
				}
				else{
					mCamera.stopPreview();
					mCamera.release();
					mSwich.setImageDrawable(getResources().getDrawable(R.drawable.close));
					isOpen = false;
				}
			}
			
		});
		
	}
}
