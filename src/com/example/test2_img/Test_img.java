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
	private TableRow word; 
	 public String UserID = "123123";
	
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		
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
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) { 
		
		if (resultCode == RESULT_OK) { 
		
			Uri uri = data.getData(); Log.e("uri", uri.toString()); 
		
			ContentResolver cr = this.getContentResolver();
			
			try { 
				Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
				// String str = "iv0"+Integer.toString(aa);
			
			// ImageView imageView = (ImageView) findViewById(R.id.);
			tupian = (TableRow) findViewById(R.id.tupian); 
			word=(TableRow)findViewById(R.id.word); 
			shanchu = (TableRow) findViewById(R.id.shanchu); 
			
			//tupian.addView(AddImageView(), 1); 
			
			tupian.addView(AddImageView(), 300 , 300);
			word.addView(ShowWord(),300,300);
			shanchu.addView(AddButton(), 300 , 50); 
			
			ImageView imageView1 = (ImageView) findViewById(index);
			
			ImageView_bi(imageView1 , bitmap );
			
//			TextView textview1 = (TextView) findViewById(0);
			
//			textview1.setText("Example TextView by corn");
			} 
			
			catch (FileNotFoundException e) { 
			
				Log.e("Exception", e.getMessage(),e); } } 
		
			super.onActivityResult(requestCode, resultCode, data); } 
	
	// 动态添加 图片
	
	private void ImageView_bi(ImageView imageView , Bitmap bitmap ) { 
	
		Bitmap mBitmap = Bitmap.createScaledBitmap(bitmap,100,100, true); 
		imageView.setImageBitmap(mBitmap); } 

	
	protected View AddImageView() {
		
		index++; 
		ImageView btn = new ImageView(this); 
		btn.setId(index);
		btn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		return btn; }

	protected View ShowWord() {
	//	index++;
		// TODO Auto-generated method stub
		TextView btn=new TextView(this);
		btn.setId(index);
		btn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		btn.setText(UserID); //這一行是可以顯示文字的
		return btn;
		
		
	}

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
	btn.setText("删除");
	return btn; } 
			

	}
