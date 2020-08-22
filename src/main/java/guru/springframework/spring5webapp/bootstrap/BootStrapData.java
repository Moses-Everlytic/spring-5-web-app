package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(
            AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository
        ) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        
        System.out.println("Started in Bootstrap");

        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setCity("Pretoria");
        publisher.setState("Gauteng");

        publisherRepository.save(publisher);

        System.out.println("Publisher Count: " + publisherRepository.count());

        Author ericAuthor = new Author("Eric", "Evans");
        Book dddBook = new Book("Domain Driven Design", "123123");

        ericAuthor.getBooks().add(dddBook);
        dddBook.getAuthors().add(ericAuthor);

        dddBook.setPublisher(publisher);
        publisher.getBooks().add(dddBook);

        authorRepository.save(ericAuthor);
        bookRepository.save(dddBook);
        publisherRepository.save(publisher);

        Author kamoAuthor = new Author("kamo", "Simplicity");
        Book selfMadeBook = new Book("Self Made", "89045435");

        kamoAuthor.getBooks().add(selfMadeBook);
        selfMadeBook.getAuthors().add(kamoAuthor);

        selfMadeBook.setPublisher(publisher);
        publisher.getBooks().add(selfMadeBook);

        authorRepository.save(kamoAuthor);
        bookRepository.save(selfMadeBook);
        publisherRepository.save(publisher);

        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher Number of Books: " + publisher.getBooks().size());
    }
    
}