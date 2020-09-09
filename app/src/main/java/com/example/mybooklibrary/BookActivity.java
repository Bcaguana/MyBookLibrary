package com.example.mybooklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";

    TextView txtBookName, txtAuthor, txtPages, txtDescription;
    ImageView imgBookPicture;
    Button btnCurrentRead, btnWantToRead, btnAlreadyRead, btnAddToFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        initViews();

        //Gets the Id of the book selected in the AllBooksActivity to extract info on it from the Utils class
        Intent intent = getIntent();
        if (null != intent){
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (bookId != -1){
                Book incomingBook = Utils.getInstance().getBookById(bookId);
                if (null != incomingBook) {
                    setData(incomingBook);
                    
                    handleAlreadyRead(incomingBook);
                    handleWantToReadBooks(incomingBook);
                    handleCurrentlyReadingBooks(incomingBook);
                    handleFavoriteBooks(incomingBook);
                }
            }
        }
    }

    private void handleFavoriteBooks(final Book book) {
        ArrayList<Book> favoriteBooks = Utils.getInstance().getFavoriteBooks();

        boolean existInFavoriteBooks = false;

        for(Book b: favoriteBooks) {
            if (b.getId() == book.getId())
                existInFavoriteBooks = true;
        }
        if (existInFavoriteBooks){
            btnAddToFavorites.setEnabled(false);
        }else {
            btnAddToFavorites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance().addToFavoriteBooks(book)){
                        Intent intent = new Intent(BookActivity.this, FavoriteBooksActivity.class);
                        startActivity(intent);
                        Toast.makeText(BookActivity.this,"Book added to Favorites List", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(BookActivity.this,"Book was not added to Favorites List", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleCurrentlyReadingBooks(final Book book) {
        ArrayList<Book> currentlyReadingBooks = Utils.getInstance().getCurrentlyReadingBooks();

        boolean existInCurrentlyReadingBooks = false;

        for(Book b: currentlyReadingBooks) {
            if (b.getId() == book.getId())
                existInCurrentlyReadingBooks = true;
        }
        if (existInCurrentlyReadingBooks){
            btnCurrentRead.setEnabled(false);
        }else {
            btnCurrentRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance().addToCurrentlyReading(book)){
                        Intent intent = new Intent(BookActivity.this, CurrentlyReadingBooksActivity.class);
                        startActivity(intent);
                        Toast.makeText(BookActivity.this,"Book added to Currently Reading List", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(BookActivity.this,"Book was not added to Currently Reading List", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleWantToReadBooks(final Book book) {
        ArrayList<Book> wantToReadBooks = Utils.getInstance().getWantToReadBooks();

        boolean existInWantToReadBooks = false;

        for(Book b: wantToReadBooks) {
            if (b.getId() == book.getId())
                existInWantToReadBooks = true;
        }
        if (existInWantToReadBooks){
            btnWantToRead.setEnabled(false);
        }else {
            btnWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance().addToWantToRead(book)){
                        Intent intent = new Intent(BookActivity.this, WantToReadBooksActivity.class);
                        startActivity(intent);
                        Toast.makeText(BookActivity.this,"Book added to Want to Read List", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(BookActivity.this,"Book was not added to Want to Read List", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * Enable and Disable Button
     * Adds book to Utils instance of AlreadyReadBooks ArrayList if it does not exist there already
     * @param book
     */
    private void handleAlreadyRead(final Book book) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance().getAlreadyReadBooks();

        boolean existInAlreadyReadBooks = false;

        for(Book b: alreadyReadBooks) {
            if (b.getId() == book.getId())
                existInAlreadyReadBooks = true;
        }

        if (existInAlreadyReadBooks) {
            btnAlreadyRead.setEnabled(false);
        }else {
            btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(BookActivity.this, AlreadyReadBooksActivity.class);
                    startActivity(intent);
                    if (Utils.getInstance().addToAlreadyRead(book)){
                        Toast.makeText(BookActivity.this,"Book added to Already Read List", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(BookActivity.this,"Book was not added to Already Read List", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * Receives Book object and displays the data about the book in the TextViews
     * Displays picture of book in ImageView with Glide
     * @param book
     */
    private void setData(Book book) {
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());

        Glide.with(this).asBitmap()
                .load(book.getImageUrl())
                .into(imgBookPicture);
    }

    /**
     * Initializes TextViews and ImageViews and Buttons
     * This happens first
     */
    private void initViews(){
        txtBookName = findViewById(R.id.txtBookName);
        txtAuthor = findViewById(R.id.txtAuthor);
        txtPages = findViewById(R.id.txtPages);
        txtDescription = findViewById(R.id.txtDescriptionText);

        imgBookPicture = findViewById(R.id.imgBookPicture);

        btnCurrentRead = findViewById(R.id.btnAddToCurrentlyReading);
        btnWantToRead = findViewById(R.id.btnAddToWantToRead);
        btnAlreadyRead = findViewById(R.id.btnAddToAlreadyRead);
        btnAddToFavorites = findViewById(R.id.btnAddToFavorites);
    }
}