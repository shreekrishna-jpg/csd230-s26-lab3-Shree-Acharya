package csd230.s26.lab1.repositories;

import com.github.javafaker.Faker;
import csd230.s26.lab1.entities.BookEntity;
import csd230.s26.lab1.entities.NotebookEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Use real MySQL, not H2
@Transactional(propagation = Propagation.NOT_SUPPORTED) // Don't rollback so data persists for inspection
class ProductRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private NotebookRepository notebookRepository;

    @Test
    void testSaveAndRetrieveBook() {
        Faker faker = new Faker();

        // 1. Create a fake book using the setter/constructor format that matches your app
        BookEntity book = new BookEntity(
                faker.book().author(),
                faker.book().title(),
                29.99,
                10
        );
        book.setProductId(UUID.randomUUID().toString());

        // 2. Save to database
        bookRepository.save(book);
        Long savedId = book.getId();
        assertNotNull(savedId, "ID should be generated upon saving");

        // 3. Retrieve and Verify
        BookEntity foundBook = bookRepository.findById(savedId).orElseThrow();
        assertEquals(book.getTitle(), foundBook.getTitle());
        assertEquals(book.getAuthor(), foundBook.getAuthor());

        System.out.println("Successfully verified book: " + foundBook.getTitle());
    }

    @Test
    void testCrudSaveAndDeleteBook() {
        // 1. Create a transient Book entity
        BookEntity book = new BookEntity(
                "CRUD Test Author",
                "Temporary CRUD Test Title",
                15.99,
                20
        );
        book.setProductId(UUID.randomUUID().toString());

        // 2. Save the entity to MySQL
        bookRepository.save(book);
        Long id = book.getId();
        assertNotNull(id, "Database should auto-generate an ID upon saving");

        // 3. Delete the entity from the repository
        bookRepository.delete(book);

        // 4. Verify that the entity no longer exists in the database
        Optional<BookEntity> deletedBook = bookRepository.findById(id);
        assertTrue(deletedBook.isEmpty(), "The book record should be completely gone from the database");

        System.out.println("✅ CRUD Test Passed: Successfully verified Save and Delete cycles.");
    }

    @Test
    void testSaveAndRetrieveNicheNotebook() {
        // 1. Instantiate using the empty constructor to sidestep constructor mismatches
        NotebookEntity notebook = new NotebookEntity();

        // 2. Set fields explicitly using setters
        notebook.setProductId(UUID.randomUUID().toString());
        notebook.setTitle("Lab Niche Verification Log");
        notebook.setPrice(14.50);
        notebook.setCopies(60);

        // Unique Niche properties
        notebook.setBrand("Sault Stationery Co.");
        notebook.setIsEcoFriendly(true);
        notebook.setPageCount(150);
        notebook.setPaperRuling("Lined");

        // 3. Save to your single table architecture
        notebookRepository.save(notebook);
        Long notebookId = notebook.getId();
        assertNotNull(notebookId, "ID must be assigned to the Niche entity");

        // 4. Retrieve the record cleanly from the database
        NotebookEntity foundNotebook = notebookRepository.findById(notebookId).orElseThrow();

        // 5. Run clear assertions on the retrieved object values
        assertNotNull(foundNotebook.getBrand(), "Brand field should not be null in DB");
        assertEquals("Sault Stationery Co.", foundNotebook.getBrand());
        assertEquals(150, foundNotebook.getPageCount());
        assertEquals("Lined", foundNotebook.getPaperRuling());
        assertTrue(foundNotebook.getIsEcoFriendly());

        System.out.println("✅ Niche Test Passed: Polymorphic storage verified for NotebookEntity.");
    }
}