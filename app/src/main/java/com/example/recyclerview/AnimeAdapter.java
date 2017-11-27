package com.example.recyclerview;



//TODO 4.1 (in a separate XML File) Design your list item layout

//TODO 4.2 go back to activity_main.xml and put in the recycler view widget

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>  {

    private MainActivity.AnimeJsonData[] data;
    private static int viewHolderCount = 0;
    Context parentContext;
    AnimeViewHolder holder;

    //TODO 4.4 - Constructor
    public AnimeAdapter(Context context, MainActivity.AnimeJsonData[] data){
        // Constructor : needs data and and context for the recycler view
        // Get references to widget in the individual view holder
        this.parentContext = context;
        this.data = data;
    }


    //TODO 4.5 - onCreateViewHolder : Called every single time when we add a new view holder
    @Override
    public AnimeAdapter.AnimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutIDForListItem = R.layout.recycler_item;
        LayoutInflater inflater = LayoutInflater.from(parentContext);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIDForListItem,parent,shouldAttachToParentImmediately);

        holder = new AnimeViewHolder(view);

        return holder;
    }


    //TODO 4.7 - onBindViewHolder : give individual widgets data
    @Override
    public void onBindViewHolder(AnimeViewHolder holder, int position) {
        holder.bind(position);
    }

    //TODO 4.8 - getItemCount : return number in list available
    @Override
    public int getItemCount() {
        return data.length;
    }




    class AnimeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView characterName;
        TextView itemNumber;
        ImageView picture;
        ImageView pictureAlert;
        AnimeViewHolder(View v){
            //TODO 4.3 Invoke the superclass constructor
            // and get references to the various widgets in the List Item Layout
            super(v);

            //TODO 4.6 - write a bind method to attach content
            //            to the respective widgets
            picture = (ImageView) v.findViewById(R.id.item_image);
            characterName = (TextView) v.findViewById(R.id.item_text);
            itemNumber = (TextView) v.findViewById(R.id.item_count);

            v.setOnClickListener(this);
        }

        void bind(int position){

            //TODO 4.6A - get the filename of the image
            MainActivity.AnimeJsonData a = data[position];
            String filename = a.file ;

            String packageName = parentContext.getPackageName();
            String typeOfResource = "drawable";

            int resID = parentContext.getResources().getIdentifier(filename, typeOfResource, packageName);
            //TODO 4.6 B pass the resource ID to the image widget
            picture.setImageResource(resID);

            //TODO 4.6 C pass the character name to the characterName widget
            characterName.setText(a.name) ;

            //TODO 4.6 D display the position number
            itemNumber.setText(a.anime) ;

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();

            String anime = data[position].anime;
            String name = data[position].name;

            String filename = data[position].file ;

            String packageName = parentContext.getPackageName();
            String typeOfResource = "drawable";

            int resID = parentContext.getResources().getIdentifier(filename, typeOfResource, packageName);
            // make alert dialog
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(parentContext);
            alertDialogBuilder.setTitle(anime);
            alertDialogBuilder.setIcon(resID);
            alertDialogBuilder.setCancelable(true);
            alertDialogBuilder.setMessage(name);

            alertDialogBuilder.show();
        }
    }
}