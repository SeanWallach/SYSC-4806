package Entities.Controllers;

import Entities.Book;
import Entities.Owner;
import Entities.Repositories.BookRepo;
import Entities.Repositories.OwnerRepo;


import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OwnerController {
    @Autowired
    private BookRepo books;

    @Autowired
    private OwnerRepo owners;


    //-----------------------------------Owner creation------------------------------------------//
    //adds owner to model and sends to ownerCreation html page.
    @GetMapping("/createOwner")
    public String createOwner(Model model) {
        return "ownerCreation";
    }

    //
    @PostMapping("/createOwner")
    public String ownerSubmit(@RequestParam(value="username") String username,
                              @RequestParam(value="password") String password) {
        Owner owner = new Owner();
        owner.setUsername(username);
        owner.setPassword(password);
        owners.save(owner);

        return "loginPage";
    }


    //-------------------Methods to create and display books that owners make-------------------//
    @GetMapping("/createBook")
    public String createBook(Model model){
        model.addAttribute("newBook", new Book());
        return "bookCreation";


    }

    @PostMapping("/createBook")
    public String publishBook(@ModelAttribute Book book, @RequestParam(value="image") MultipartFile Multifile, Model model) throws FileNotFoundException {

        String bucketName = "sysc4806-images";
        String key = "This is the file name";

        BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAQ2XALK5GHOQOALW2", "B/BB7tqVDUUmbHULvDigqySIJRtPyszhntl8hG/O");

        File file_s3 = new File("src/main/resources/uploads/temp.png");
        try (OutputStream os = new FileOutputStream(file_s3)) {
            os.write(Multifile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion("us-east-2")
                    .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                    .build();


            PutObjectRequest request = new PutObjectRequest(bucketName, key, file_s3);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("image/png");
            metadata.addUserMetadata("title", "TestTitle");
            request.setMetadata(metadata);

            request.setCannedAcl(CannedAccessControlList.PublicRead);

            s3Client.putObject(request);

            URL image_url = s3Client.getUrl(bucketName, key);

            book.setPicture(image_url);

        } catch (SdkClientException e) {
            e.printStackTrace();
        }


        books.save(book);
        model.addAttribute("createdBook", book);
        model.addAttribute("library", books.findAll());
        return "ownerHomepage";
    }

    @GetMapping("/editBook")
    public String editBook(@RequestParam(value = "bookId") long isbn , Model model){
        System.out.println(isbn);
        Book book = books.findById(isbn);
        model.addAttribute("theBook", book);
        return"editBook";
    }
    @PostMapping("/editBook")
    public String submitEdit(@ModelAttribute Book editedBook, Model model){
        Book bookToEdit = books.findById(editedBook.getISBN());
        bookToEdit.setAuthor(editedBook.getAuthor());
        bookToEdit.setPublisher(editedBook.getPublisher());
        bookToEdit.setDescription(editedBook.getDescription());
        bookToEdit.setInventory(editedBook.getInventory());
        bookToEdit.setPrice(editedBook.getPrice());
        bookToEdit.setName(editedBook.getName());
        books.save(bookToEdit);
        model.addAttribute("library", books.findAll());
        return "ownerHomepage";
    }
}
