package com.example.myapplication.phone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class PhoneNumberViewAdapter extends RecyclerView.Adapter<PhoneNumberViewAdapter.ViewHolder> {

    private List<PhoneNumber> phoneNumbers;

    public PhoneNumberViewAdapter(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView phone_number;
        private final TextView first_name;
        private final TextView last_name;
        private final Button removeButton;

        public ViewHolder(View view) {
            super(view);
            phone_number = (TextView) view.findViewById(R.id.phone_number);
            first_name = (TextView) view.findViewById(R.id.first_name);
            last_name = (TextView) view.findViewById(R.id.last_name);
            removeButton = (Button) view.findViewById(R.id.remove_button);

            this.removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    delete(getAdapterPosition());
                }
            });
        }

        public TextView getPhoneNumberTextView(){
            return phone_number;
        }

        public TextView getFirstNameTextView(){
            return first_name;
        }

        public TextView getLastNameTextView(){
            return last_name;
        }

        private void delete(int position){
            try {
                phoneNumbers.remove(position);
                notifyItemRemoved(position);
            } catch (IndexOutOfBoundsException ex) {
                ex.printStackTrace();
            }
        }

        private void add(int position, PhoneNumber phoneNumber) {
            phoneNumbers.add(position, phoneNumber);
            notifyItemInserted(position);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.phone_number_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getPhoneNumberTextView().setText(phoneNumbers.get(position).phoneNumber);
        viewHolder.getFirstNameTextView().setText(phoneNumbers.get(position).firstName);
        viewHolder.getLastNameTextView().setText(phoneNumbers.get(position).lastName);
    }

    @Override
    public int getItemCount() {
        return phoneNumbers.size();
    }

    private void removeItem(int position){
        phoneNumbers.remove(position);
        notifyItemChanged(position);
        notifyItemRangeChanged(position, 1);
    }

}
