package com.example.myapplication.permission;

import android.app.Activity;
import android.content.Context;

public abstract class Permission {

   private Activity activity;

   public Permission(Activity activity){
      this.activity = activity;
   }

   protected Context getContext(){
      return this.activity.getApplicationContext();
   }

   protected Activity getActivity(){
      return this.activity;
   }

   abstract void onRequestPermissionResult(int requestCode, String permissions[], int[] grantResults);
   abstract void request();
}
