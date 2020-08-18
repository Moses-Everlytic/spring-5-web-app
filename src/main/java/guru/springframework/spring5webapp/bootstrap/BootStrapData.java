package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
      
        Author ericAuthor = new Author("Eric", "Evans");
        Book dddBook = new Book("Domain Driven Design", "123123");

        ericAuthor.getBooks().add(dddBook);
        dddBook.getAuthors().add(ericAuthor);

        authorRepository.save(ericAuthor);
        bookRepository.save(dddBook);

        Author kamoAuthor = new Author("kamo", "Simplicity");
        Book selfMadeBook = new Book("Self Made", "89045435");

        kamoAuthor.getBooks().add(selfMadeBook);
        selfMadeBook.getAuthors().add(kamoAuthor);

        authorRepository.save(kamoAuthor);
        bookRepository.save(selfMadeBook);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
    }
    
}