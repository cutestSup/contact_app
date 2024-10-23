package com.midterm.taquanghuu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private ArrayList<Order> orderList;
    private Context context;

    public OrderAdapter(ArrayList<Order> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.tvOrderSummary.setText("Order No: " + (position + 1) + "\n" +
                "Quantity: " + order.getQuantity() +
                "\nWhipped Cream: " + (order.isAddWhippedCream() ? "Yes" : "No") +
                "\nChocolate: " + (order.isAddChocolate() ? "Yes" : "No") +
                "\nPrice: $" + order.getPrice());

        holder.tvOrderStatus.setText(order.getStatus());
        holder.btnEdit.setOnClickListener(v -> {
            order.setStatus("Đã thanh toán");
            notifyItemChanged(position);
        });
        holder.btnDelete.setOnClickListener(v -> {
            orderList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, orderList.size());
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public void updateOrderList(ArrayList<Order> newList) {
        orderList = newList;
        notifyDataSetChanged();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView tvOrderSummary, tvOrderStatus;
        Button btnEdit, btnDelete;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderSummary = itemView.findViewById(R.id.tvOrderSummary);
            tvOrderStatus = itemView.findViewById(R.id.tvOrderStatus);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
