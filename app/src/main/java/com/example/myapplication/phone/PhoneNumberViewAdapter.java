package com.example.myapplication.phone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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

    public PhoneNumberViewAdapter(List<PhoneNumber> phoneNumbers) {
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
        String name = currentPhoneNumber.lastName + currentPhoneNumber.firstName;
        viewBinderHelper.setOpenOnlyOne(true);
        viewBinderHelper.bind(viewHolder.swipeRevealLayout, name);
        viewBinderHelper.closeLayout(name);
        viewHolder.bindNameTextView(name);
    }

    @Override
    public int getItemCount() {
        return phoneNumbers.size();
    }

    public void insert(PhoneNumber phoneNumber){
        this.phoneNumbers.add(phoneNumber);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private SwipeRevealLayout swipeRevealLayout;
        private RelativeLayout swipeAppendLayout;
        private ImageButton removeButton;
        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            swipeRevealLayout = view.findViewById(R.id.phone_item_layout);
            swipeAppendLayout = view.findViewById(R.id.swipe_append_layout);
            removeButton = view.findViewById(R.id.phone_number_remove_button);

            swipeAppendLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    delete(getAdapterPosition());
                }
            });

            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    delete(getAdapterPosition());
                }
            });
        }

        public void bindNameTextView(String name){
            this.name.setText(name);
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
