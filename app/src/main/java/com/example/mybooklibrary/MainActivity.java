package com.example.mybooklibrary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnAllBooks, btnCurrentlyReading, btnAlreadyRead, btnWishlist, btnFavorite, btnAbout;
    private TextView txtMyLibrary, txtDeveloper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        //makeDatabase();

        btnAllBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent allBooksIntent = new Intent(MainActivity.this, AllBooksActivity.class);
                startActivity(allBooksIntent);
            }
        });

        btnCurrentlyReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CurrentlyReadingBooksActivity.class);
                startActivity(intent);
            }
        });

        btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AlreadyReadBooksActivity.class);
                startActivity(intent);
            }
        });

        btnWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WantToReadBooksActivity.class);
                startActivity(intent);
            }
        });

        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FavoriteBooksActivity.class);
                startActivity(intent);
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(getString(R.string.app_name));
                builder.setMessage("Designed and Developed by Brandon at BranditCo\n" +
                        "Check out my website: ");
                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setPositiveButton("Visit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //TODO: Show website
                        Intent intent = new Intent(MainActivity.this, WebsiteActivity.class);
                        intent.putExtra("url", "https://google.com/");
                        startActivity(intent);
                    }
                });
                builder.create().show();
            }
        });

        Utils.getInstance();
    }

    private void makeDatabase() {
        ArrayList<Book> allBooks = new ArrayList<>();
        allBooks.add(new Book(1,"1Q84", "Hakumi Murakumi",1350, "https://images-na.ssl-images-amazon.com/images/I/41FdmYnaNuL._SX322_BO1,204,203,200_.jpg",
                "A work of maddening brilliance", "Long Description"));
        allBooks.add(new Book(2,"1Q85", "Fake Author",1350, "https://images-na.ssl-images-amazon.com/images/I/41FdmYnaNuL._SX322_BO1,204,203,200_.jpg",
                "A work of maddening brilliance", "Long Description"));
        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
        databaseHelper.addOne(allBooks.get(0));
        databaseHelper.addOne(allBooks.get(1));
    }

    /**Initialize the Buttons in the launch page
     *
     */
    private void initViews(){
        btnAllBooks = findViewById(R.id.btnAllBooks);
        btnCurrentlyReading = findViewById(R.id.btnCurrentlyReading);
        btnAlreadyRead = findViewById(R.id.btnAddToAlreadyRead);
        btnWishlist = findViewById(R.id.btnWishList);
        btnFavorite = findViewById(R.id.btnFavoriteBooks);
        btnAbout = findViewById(R.id.btnAbout);
    }
}