package com.example.fifawolrdcup.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fifawolrdcup.Models.FixtureData;
import com.example.fifawolrdcup.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FixtureAdapter extends RecyclerView.Adapter<FixtureViewHolder>{
 Context  context;
 List<FixtureData> list;

 public FixtureAdapter(Context context, List<FixtureData> list) {
  this.context = context;
  this.list = list;
 }

 @Override
 public FixtureViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
  return new FixtureViewHolder(LayoutInflater.from(context).inflate(R.layout.list_fixture,parent,false));
 }

 @Override
 public void onBindViewHolder(@NonNull FixtureViewHolder holder, int position) { // show flag of home and away country
  final FixtureData data  = list.get(position);
  Picasso.get().load("https://countryflagsapi.com/png/" + data.homeName.toLowerCase()).into(holder.imageView_home);
  Picasso.get().load("https://countryflagsapi.com/png/" + data.awayName.toLowerCase()).into(holder.imageView_away);

  holder.textView_home.setText(data.homeName);
  holder.textView_away.setText(data.awayName);

  holder.textView_match.setText(data.homeName + " vs " + data.awayName);

  SimpleDateFormat dateFormat = new SimpleDateFormat("EEE,d,MMM");
  SimpleDateFormat timeZone = new SimpleDateFormat("hh:mm a");

  SimpleDateFormat givenFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  try {
    Date date = givenFormat.parse(data.date);
   holder.textView_time.setText(dateFormat.format(date) + "\n" +timeZone.format(date));
  } catch (ParseException e) {
   e.printStackTrace();
  }


 }

 @Override
 public int getItemCount() {
  return list.size();
 }
}
 class FixtureViewHolder extends RecyclerView.ViewHolder{
  ImageView imageView_home, imageView_away;
     TextView textView_home, textView_away,textView_time,textView_match;
  public FixtureViewHolder(@NonNull View itemView)
  {
   super(itemView);
   imageView_home = itemView.findViewById(R.id.imageview_home);
   imageView_away = itemView.findViewById(R.id.imageview_awaycountry);
   textView_home = itemView.findViewById(R.id.textview_home);
   textView_away = itemView.findViewById(R.id.textview_awaycountry);
   textView_time = itemView.findViewById(R.id.textview_time);
   textView_match = itemView.findViewById(R.id.textview_match);
  }
 }
