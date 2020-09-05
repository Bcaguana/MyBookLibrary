package com.example.mybooklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BookActivity extends AppCompatActivity {

    TextView txtBookName, txtAuthor, txtPages, txtDescription;
    ImageView imgBookPicture;
    Button btnCurrentRead, btnWantToRead, btnAlreadyRead, btnAddToFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        initViews();

        //TODO: Get data from recyclerview here!
        Book book = new Book(123,"1Q84", "Hakumi Murakumi",1350, "https://images-na.ssl-images-amazon.com/images/I/41FdmYnaNuL._SX322_BO1,204,203,200_.jpg",
                "A work of maddening brilliance", "Long Description");

        setData(book);
    }

    private void setData(Book book) {
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());

        Glide.with(this).asBitmap()
                .load(book.getImageUrl())
                .into(imgBookPicture);
    }

    private void initViews(){
        txtBookName = findViewById(R.id.txtBookName);
        txtAuthor = findViewById(R.id.txtAuthor);
        txtPages = findViewById(R.id.txtPages);
        txtDescription = findViewById(R.id.txtDescriptionText);

        imgBookPicture = findViewById(R.id.imgBookPicture);

        btnCurrentRead = findViewById(R.id.btnCurrentlyReading);
        btnWantToRead = findViewById(R.id.btnAddToWantToRead);
        btnAlreadyRead = findViewById(R.id.btnAddToAlreadyRead);
        btnAddToFavorites = findViewById(R.id.btnAddToFavorites);
    }
}