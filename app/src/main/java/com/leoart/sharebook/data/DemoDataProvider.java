package com.leoart.sharebook.data;

import com.leoart.sharebook.R;
import com.leoart.sharebook.contacts.Contact;
import com.leoart.sharebook.models.BookModel;
import com.leoart.sharebook.models.DiscoverModel;

import java.util.ArrayList;
import java.util.List;

public class DemoDataProvider {

    public static ArrayList<DiscoverModel> createDiscoverModels(){
        ArrayList<DiscoverModel> discoverModels = new ArrayList<>();

        DiscoverModel discoverModel = new DiscoverModel();
        discoverModel.setCategoryTitle("New Books");
        discoverModel.setBooks(createBooks());
        discoverModels.add(discoverModel);


        DiscoverModel discoverModel2 = new DiscoverModel();
        discoverModel2.setCategoryTitle("Top Books");
        discoverModel2.setBooks(createBooks());
        discoverModels.add(discoverModel2);

        DiscoverModel discoverModel3 = new DiscoverModel();
        discoverModel3.setCategoryTitle("Blockbuster Books");
        discoverModel3.setBooks(createBooks());
        discoverModels.add(discoverModel3);

        DiscoverModel discoverModel4 = new DiscoverModel();
        discoverModel4.setCategoryTitle("Featured Books");
        discoverModel4.setBooks(createBooks());
        discoverModels.add(discoverModel4);

        return discoverModels;
    }

    public static ArrayList<BookModel> createBooks() {
        ArrayList<BookModel> books = new ArrayList<>();
        BookModel bookModel = new BookModel();
        bookModel.setName("Harry Potter and the Philosopher's Stone");
        bookModel.setAuthor("J. K. Rowling");
        bookModel.setStatus("available");
        bookModel.setCoverResource(R.mipmap.hp1);
        books.add(bookModel);
        bookModel.setName("Harry Potter and the Chamber of Secrets");
        bookModel.setCoverResource(R.mipmap.hp1);
        books.add(bookModel);
        bookModel = new BookModel();
        bookModel.setName("Harry Potter and the Prisoner of Azkaban");
        bookModel.setCoverResource(R.mipmap.hp1);
        books.add(bookModel);
        bookModel = new BookModel();
        bookModel.setName("Harry Potter and the Goblet of Fire");
        bookModel.setCoverResource(R.mipmap.hp1);
        books.add(bookModel);
        bookModel = new BookModel();
        bookModel.setName("Harry Potter and the Order of the Phoenix");
        bookModel.setCoverResource(R.mipmap.harry_potter_cover5);
        books.add(bookModel);
        bookModel = new BookModel();
        bookModel.setName("Harry Potter and the Half-Blood Prince");
        bookModel.setCoverResource(R.mipmap.hp1);
        books.add(bookModel);
        bookModel = new BookModel();
        bookModel.setName("Harry Potter and the Deathly Hallows");
        bookModel.setCoverResource(R.mipmap.hp1);
        books.add(bookModel);
        return books;
    }

    public static List<String> genCountriest() {
        List<String> countries = new ArrayList<String>();
        countries.add("Ukraine");
        countries.add("France");
        countries.add("Germany");
        countries.add("Poland");
        countries.add("Israel");
        return countries;
    }

    public static ArrayList<Contact> createContacts(){
        ArrayList<Contact> result = new ArrayList<>();

        Contact contact = new Contact();
        for(int i = 0; i < 10; i++){
            contact.setUserName("Contact" + i);
            contact.setFirstName("name" + i);
            result.add(contact);
        }

        return result;
    }

}
