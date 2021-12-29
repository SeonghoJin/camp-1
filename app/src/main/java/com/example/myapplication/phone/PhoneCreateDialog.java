package com.example.myapplication.phone;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class PhoneCreateDialog {

    private Context context;

    public PhoneCreateDialog(Context context) {
        this.context = context;
    }

    public void show(PhoneCreateCallback phoneCreateCallback) {

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this.context);
        bottomSheetDialog.setContentView(R.layout.phone_number_create_bottom_sheet_view);
        BottomSheetBehavior bottomSheetBehavior = bottomSheetDialog.getBehavior();
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        bottomSheetDialog.show();
//        bottomSheetBehavior.
//        View bottomSheet = bottomSheetDialog.findViewById(R.id.design_bottom_sheet);
//        bottomSheet.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
//        View view = getView();
//        view.post(() -> {
//            View parent = (View) view.getParent();
//            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) (parent).getLayoutParams();
//            CoordinatorLayout.Behavior behavior = params.getBehavior();
//            BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) behavior;
//            bottomSheetBehavior.setPeekHeight(view.getMeasuredHeight());
//            ((View)bottomSheet.getParent()).setBackgroundColor(Color.TRANSPARENT)
//
//        });
//
    }
}