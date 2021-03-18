package com.example.lifelapseapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifelapseapp.CharacterDetailActivity;
import com.example.lifelapseapp.R;
import com.example.lifelapseapp.model.ApiCharacter;
import com.example.lifelapseapp.services.AsyncImageManager;

import java.util.List;

import static com.example.lifelapseapp.constants.Constants.CHARACTER_TAG;

/**
 * Recyclerview adapter with recyclerview list and context to perform
 * activity start and toast operations
 */
public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {

    private List<ApiCharacter> characters;
    Context context;

    public MainRecyclerViewAdapter(List<ApiCharacter> characters, Context context) {
        this.characters = characters;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(characters.get(position).getName());
        AsyncImageManager manager = new AsyncImageManager(characters.get(position).getImage(), holder.imageView);
        manager.execute();
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    /**
     * View Holder class for recycler view
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private CardView card;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_view_image);
            textView = itemView.findViewById(R.id.item_view_name);
            card = itemView.findViewById(R.id.item_view_card);
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), CharacterDetailActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(CHARACTER_TAG, characters.get(getAdapterPosition()));
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
