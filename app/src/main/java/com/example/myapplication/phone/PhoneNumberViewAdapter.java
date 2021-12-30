package com.example.myapplication.phone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.myapplication.R;

import java.util.List;

public class PhoneNumberViewAdapter extends RecyclerView.Adapter<PhoneNumberViewAdapter.ViewHolder> {

    private List<PhoneNumber> phoneNumbers;
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
    private Context context;

    public PhoneNumberViewAdapter(List<PhoneNumber> phoneNumbers, Context context) {
        this.context = context;
        this.phoneNumbers = phoneNumbers;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.phone_number_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        PhoneNumber currentPhoneNumber = phoneNumbers.get(position);
        String name = currentPhoneNumber.firstName + currentPhoneNumber.lastName;
        viewBinderHelper.setOpenOnlyOne(true);
        viewBinderHelper.bind(viewHolder.swipeRevealLayout, name);
        viewBinderHelper.closeLayout(name);
        viewHolder.bindNameTextView(name);
        viewHolder.bindCallButtonText(currentPhoneNumber.phoneNumber);
    }

    @Override
    public int getItemCount() {
        return phoneNumbers.size();
    }

    public void insert(PhoneNumber phoneNumber){
        this.phoneNumbers.add(phoneNumber);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private SwipeRevealLayout swipeRevealLayout;
        private LinearLayout swipeAppendLayout;
        private RelativeLayout callButtonWrapper;
        private RelativeLayout removeButtonWrapper;
        private Button callButton;
        private ImageButton removeButton;
        private ImageView defaultImage;
        private TextView defaultWord;
        private Call call;
        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            swipeRevealLayout = view.findViewById(R.id.phone_item_layout);
            swipeAppendLayout = view.findViewById(R.id.swipe_append_layout);
            callButtonWrapper = view.findViewById(R.id.phone_number_call_button_wrapper);
            callButton = view.findViewById(R.id.phone_number_call_button);
            removeButtonWrapper = view.findViewById(R.id.phone_number_remove_button_wrapper);
            removeButton = view.findViewById(R.id.phone_number_remove_button);
            defaultImage = view.findViewById(R.id.default_profile_image);
            defaultWord = view.findViewById(R.id.default_profile_word);


            callButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    call.call(context);
                }
            });

            removeButtonWrapper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    delete(getAdapterPosition());
                }
            });

            removeButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    delete(getAdapterPosition());
                }
            });
        }

        public void bindNameTextView(String name) {
            try {
                this.name.setText(name);
                String ellipseName = Name.toEllipseName(name);
                System.out.println(ellipseName);
                if(ellipseName.length() > 0){
//                    setVisible(defaultWord);
                    setInvisible(defaultImage);
                    defaultWord.setText(ellipseName);
                    return;
                }
//                setInvisible(defaultWord);
                setVisible(defaultImage);

            } catch (Exception e){
                e.printStackTrace();
            }

        }

        public void setInvisible(View view){
            view.setVisibility(View.INVISIBLE);
        }

        public void setVisible(View view){
            view.setVisibility(View.VISIBLE);
        }

        public void bindCallButtonText(String call){
            this.callButton.setText(call);
            this.call = new Call(call);
        }

        private void delete(int position){
            try {
                phoneNumbers.remove(position);
                notifyItemRemoved(position);
            } catch (IndexOutOfBoundsException ex) {
                ex.printStackTrace();
            }
        }

    }

}
