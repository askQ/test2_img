package com.example.test2_img;

import java.io.FileNotFoundException;
import android.app.Activity; 
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable; 
import android.net.Uri; 
import android.os.Bundle;
import android.util.Log; 
import android.view.View;
import android.view.ViewGroup; 
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView; 
import android.widget.ImageView.ScaleType;
import android.widget.TableRow;
import android.widget.TextView;
public class Test_img extends Activity { 
	/** Called when the activity is first created. */
	private int index = 0; 
	private TableRow tupian;
	private TableRow shanchu; 
	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_test_img);
		Button button = (Button)findViewById(R.id.b01); 
		button.setText("选择图片");
		button.setOnClickListener(new Button.OnClickListener(){ 
			@Override public void onClick(View v) { 
				Intent intent = new Intent(); 
				/* 开启Pictures画面Type设定为image */ 
				intent.setType("image/*"); 
				/* 使用Intent.ACTION_GET_CONTENT这个Action */ 
				intent.setAction(Intent.ACTION_GET_CONTENT);
				/* 取得相片后返回本画面 */
				startActivityForResult(intent, 1); } }); }
	@Override protected void onActivityResult(int requestCode, int resultCode, Intent data) { 
		if (resultCode == RESULT_OK) { 
			Uri uri = data.getData(); Log.e("uri", uri.toString()); 
			ContentResolver cr = this.getContentResolver();
			try { Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri)); // String str = "iv0"+Integer.toString(aa);
			// ImageView imageView = (ImageView) findViewById(R.id.);
			tupian = (TableRow) findViewById(R.id.tupian); 
			shanchu = (TableRow) findViewById(R.id.shanchu); 
			//tupian.addView(AddImageView(), 1); 
			tupian.addView(AddImageView(), 100 , 100);
			shanchu.addView(AddButton(), 100 , 50); 
			ImageView imageView1 = (ImageView) findViewById(index);
			ImageView(imageView1 , bitmap ); } 
			catch (FileNotFoundException e) { 
				Log.e("Exception", e.getMessage(),e); } } 
		super.onActivityResult(requestCode, resultCode, data); } 
	// 动态添加 图片
	private void ImageView(ImageView imageView , Bitmap bitmap ) { 
		Bitmap mBitmap = Bitmap.createScaledBitmap(bitmap, 50, 50, true); 
		imageView.setImageBitmap(mBitmap); } 
	protected View AddImageView() {
		index++; ImageView btn = new ImageView(this); 
		btn.setId(index);
		btn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		// btn.setScaleType(FIT_XY); 
		return btn; }
protected View AddButton() { 
	// index1++; 
	Button btn = new Button(this);
	btn.setId(index);
	btn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)); 
	btn.setOnClickListener(new Button.OnClickListener(){ 
		@Override public void onClick(View v) { 
			int i = v.getId() ; 
			shanchu.removeView(v) ;
			tupian.removeView(tupian.findViewById(i)) ; } });
	btn.setText("删除"); return btn; } 
			

	}
