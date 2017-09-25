package kz.kbtu.networkingexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ImageView imageView = (ImageView) findViewById(R.id.image);

        Picasso.with(this).load("http://i.imgur.com/DvpvklR.png")
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(imageView);


    }
}
