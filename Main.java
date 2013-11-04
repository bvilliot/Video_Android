package com.example.ejemplo_video;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

public class Main extends Activity {

	private Button video1 = null;
	private Button video2 = null;
	private Button video3 = null;
	private Button back = null;
	private Button pause = null;
	private Button stop = null;
	private LinearLayout view1 = null;
	private LinearLayout view2 = null;
	private RelativeLayout parent = null;
	private VideoView video = null;
	private Map<String,Integer> videos = new HashMap<String,Integer>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);	
	    // Initialisa la lista de vidéos
	    initList();
	    view1 = (LinearLayout) findViewById(R.id.View1);
	    view2 = (LinearLayout) findViewById(R.id.View2);
		parent = (RelativeLayout)view1.getParent();
	    video1 = (Button) findViewById(R.id.Video1);
	    video2 = (Button) findViewById(R.id.Video2);
	    video3 = (Button) findViewById(R.id.Video3);
	    back = (Button) findViewById(R.id.Back);
	    // Estos botones permiten de mostrar cómo se utilizan los controles sobre el video
        // pause = (Button) findViewById(R.id.Pause);
	    // stop = (Button) findViewById(R.id.Stop);
	    video = (VideoView) findViewById(R.id.video);	 
	    // Por defecto no vimos la video
	    view2.setVisibility(View.GONE);
	    video1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	changeView("video1");
            }
	    });
	    video2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	changeView("video2");
            }
	    });
	    video3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	changeView("video3");
            }
	    });
	    back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	changeView(null);
            }
	    });
//	    pause.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//            	video("pause");
//            }
//	    });
//	    stop.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//            	video("stop");
//            }
//	    });
	}

	 public void changeView(String s){

		 //Si el string no es nulo, inicializamos el video y lo leimos
         if(s!=null){
        	 	view1.setVisibility(View.GONE);
		        view2.setVisibility(View.VISIBLE);
		        int numVideo = videos.get(s);
		        parent.refreshDrawableState();
		        
		        //Añadir un controller sobre el video
		        video.setMediaController(new MediaController(this));
		        
		        //Selectionar el camino de la video
		        Uri uri = Uri.parse("android.resource://"+getPackageName()+ "/" +numVideo);
		        
		        //Añadir el camino selectionado en el video  
		        video.setVideoURI(uri);
		        
		        //Leer el video
		        video.start();
		        
	        }
         	//Sino volvemos en el menu
	        else{
        	 	view1.setVisibility(View.VISIBLE);
		        view2.setVisibility(View.GONE);
		        parent.refreshDrawableState();
	        }
	 }

	public void video(String s){
		if(s == "pause"){
			if(video.isPlaying())
				video.pause();
			else
				video.start();
		}
		else{
			video.stopPlayback();
		}
	}
	
	void initList(){
		//Cambiar los nombres de estos videos con los que quiere
		videos.put("video1",R.raw.trophy_faceplant_petite);
		videos.put("video2",R.raw.trophy_faceplant_moyenne);
		videos.put("video3",R.raw.trophy_faceplant_grande);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
