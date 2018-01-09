package com.libproject.elibrary.controller;

import com.libproject.elibrary.model.Book;
import com.libproject.elibrary.model.Comment;
import com.libproject.elibrary.model.User;
import com.libproject.elibrary.service.BookService;
import com.libproject.elibrary.service.CommentService;
import com.libproject.elibrary.service.UserService;
import org.apache.commons.io.FilenameUtils;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Controller
@RequestMapping("/books")
public class BookController {

    //Max size for book's file upload
    private final long maxBookSizeFile = 20971520;

    //Max height of image in pixels
    private final int maxCoverHeight = 800;

    //Max width of image in pixels
    private int maxCoverWidth = 600;

    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @RequestMapping("/all")
    public String books(ModelMap modelMap) {
        Collection<Book> books = new HashSet<>(bookService.findAllBooks());
        modelMap.addAttribute("books", books);
        return "books";
    }

    @RequestMapping(value = "/admin-booklist", method = RequestMethod.GET)
    public String getBooksAsTable(ModelMap modelMap) {
        Collection<Book> books = new HashSet<>(bookService.findAllBooks());
        modelMap.addAttribute("books", books);
        return "booksList";
    }

    @RequestMapping(value = "/book-{id}", method = RequestMethod.GET)
    public String getBookById(@PathVariable("id") Integer id, ModelMap modelMap) {
        Book book = bookService.findById(id);
        modelMap.addAttribute("book", book);
        Comment comment = new Comment();
        modelMap.addAttribute("newComment", comment);
        List<Comment> comments =  book.getComments();

        List<Comment> comments1 = new ArrayList<>();
        modelMap.addAttribute("comments", comments);
        return "book";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddNewBookForm(ModelMap modelMap) {
        Book newBook = new Book();
        modelMap.addAttribute("newBook", newBook);
        return "newBook";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveBook(@Valid @ModelAttribute("newBook") Book newBook,
                           BindingResult result,
                           @RequestParam("bookFile") MultipartFile bookFile,
                           @RequestParam("coverFile") MultipartFile coverFile,
                           ModelMap modelMap,
                           HttpServletRequest request) {

        if (result.hasErrors()) {
            return "newBook";
        }

        if (coverFile.isEmpty()) {
            modelMap.addAttribute("uploadCoverError", "uploaded file is null");
            return "newBook";
        }

        //Check the extension of cover file
        String coverFileExtension = FilenameUtils.getExtension(coverFile.getOriginalFilename());
        if (!(coverFileExtension.equalsIgnoreCase("jpeg") | coverFileExtension.equalsIgnoreCase("jpg") |
                coverFileExtension.equalsIgnoreCase("jpe") | coverFileExtension.equalsIgnoreCase("png") |
                coverFileExtension.equalsIgnoreCase("pns"))) {
            modelMap.addAttribute("uploadCoverError",
                    "wrong extension of cover file ! Allows: *.jpeg, *.jpg *.jpe, *.png, *.pns.");
            return "newBook";
        }

        //Check the resolution of upload image
        try {
            MBFImage fImage = ImageUtilities.readMBF(coverFile.getInputStream());
            if (fImage.getHeight() > maxCoverHeight | fImage.getWidth() > maxCoverWidth) {
                modelMap.addAttribute("uploadCoverError",
                        "wrong resolution! Current: " + fImage.getHeight() + "px x " + fImage.getWidth() + "px.");
                return "newBook";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Check the extension of book file
        String bookFileExtension = FilenameUtils.getExtension(bookFile.getOriginalFilename());
        if (!(bookFileExtension.equalsIgnoreCase("txt") | bookFileExtension.equalsIgnoreCase("rtf") |
                bookFileExtension.equalsIgnoreCase("doc") | bookFileExtension.equalsIgnoreCase("odt") |
                bookFileExtension.equalsIgnoreCase("pdf"))) {
            modelMap.addAttribute("uploadBookError",
                    "wrong extension of book file ! Allows: *.txt, *.rtf *.doc, *.odt, *.pdf.");
            return "newBook";
        }

        //Check size of upload book file
        if (bookFile.getSize() > maxBookSizeFile | bookFile.isEmpty()) {
            modelMap.addAttribute("uploadBookError", "uploaded file exceeds max size 20Mb");
            return "newBook";
        }

        try {

            String rootDirectory = request.getSession().getServletContext().getRealPath("/");

            byte[] coverFileBytes = coverFile.getBytes();
            Path coversPath = Paths.get(rootDirectory + "resources\\covers\\" + coverFile.getOriginalFilename());
            Files.write(coversPath, coverFileBytes);

            byte[] bookFileBytes = bookFile.getBytes();
            Path booksPath = Paths.get(rootDirectory + "resources\\books\\" + bookFile.getOriginalFilename());
            Files.write(booksPath, bookFileBytes);

        } catch (IOException e) {
            e.printStackTrace();
        }


        User user = getCurrentAuthenticationUser();

        newBook.setUser(user);
        newBook.setCover(coverFile.getOriginalFilename());
        newBook.setLink(bookFile.getOriginalFilename());
        newBook.setDate(new Date());
        bookService.saveBook(newBook);

        return "redirect:/books/admin-booklist";
    }

    @RequestMapping(value = "/delete-book-{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(bookService.findById(id));
        return "redirect:/books/admin-booklist";
    }

    @RequestMapping(value = {"/edit-book-{id}"}, method = RequestMethod.GET)
    public String editBook(@PathVariable Integer id, ModelMap modelMap) {
        Book book;
        book = bookService.findById(id);

       modelMap.addAttribute("editBook", book);
        return "editBook";
    }

    @RequestMapping(value = {"/edit-book-{id}"}, method = RequestMethod.POST)
    public String updateBook(@Valid @ModelAttribute("editBook") Book book,
                             BindingResult result,
                             @RequestParam(name = "bookFile", required = false) MultipartFile bookFile,
                             @RequestParam(name = "coverFile", required = false) MultipartFile coverFile,
                             ModelMap modelMap,
                             HttpServletRequest request) {
        if (result.hasErrors()) {
            return "editBook";
        }
        book.setDate(new Date());

        String rootDirectory = request.getSession().getServletContext().getRealPath("/");

        if (!coverFile.isEmpty()) {
            //Check the extension of cover file
            String coverFileExtension = FilenameUtils.getExtension(coverFile.getOriginalFilename());
            if (!(coverFileExtension.equalsIgnoreCase("jpeg") | coverFileExtension.equalsIgnoreCase("jpg") |
                    coverFileExtension.equalsIgnoreCase("jpe") | coverFileExtension.equalsIgnoreCase("png") |
                    coverFileExtension.equalsIgnoreCase("pns"))) {
                modelMap.addAttribute("uploadCoverError",
                        "wrong extension of cover file ! Allows: *.jpeg, *.jpg *.jpe, *.png, *.pns.");
                return "editBook";
            }

            //Check the resolution of upload image
            try {

                MBFImage fImage = ImageUtilities.readMBF(coverFile.getInputStream());
                if (fImage.getHeight() > maxCoverHeight | fImage.getWidth() > maxCoverWidth) {
                    modelMap.addAttribute("uploadCoverError",
                            "wrong resolution! Current: " + fImage.getHeight() + "px x " + fImage.getWidth() + "px.");
                    return "editBook";
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                byte[] coverFileBytes = coverFile.getBytes();
                Path coversPath = Paths.get(rootDirectory + "resources\\covers\\" + coverFile.getOriginalFilename());
                Files.write(coversPath, coverFileBytes);
            } catch(IOException e) {
                e.printStackTrace();
            }
            //Delete previous cover
            if (!coverFile.getOriginalFilename().equals(book.getCover())) {
                bookService.removeFile(rootDirectory + "resources\\covers\\" + book.getCover());
            }else {
                modelMap.addAttribute("uploadCoverError", "cover with same name are exist");
                return "editBook";
            }
            book.setCover(coverFile.getOriginalFilename());
        }

        if (!bookFile.isEmpty()) {
            //Check the extension of book file
            String bookFileExtension = FilenameUtils.getExtension(bookFile.getOriginalFilename());
            if (!(bookFileExtension.equalsIgnoreCase("txt") | bookFileExtension.equalsIgnoreCase("rtf") |
                    bookFileExtension.equalsIgnoreCase("doc") | bookFileExtension.equalsIgnoreCase("odt") |
                    bookFileExtension.equalsIgnoreCase("pdf"))) {
                modelMap.addAttribute("uploadBookError",
                        "wrong extension of book file ! Allows: *.txt, *.rtf *.doc, *.odt, *.pdf.");
                return "editBook";
            }

            //Check size of upload book file
            if (bookFile.getSize() > maxBookSizeFile | bookFile.isEmpty()) {
                modelMap.addAttribute("uploadBookError", "uploaded file exceeds max size 20Mb");
                return "editBook";
            }

            try {
                byte[] bookFileBytes = bookFile.getBytes();
                Path booksPath = Paths.get(rootDirectory + "resources\\books\\" + bookFile.getOriginalFilename());
                Files.write(booksPath, bookFileBytes);
            } catch (IOException e){
                e.printStackTrace();
            }
            //Delete previous book
            if (!bookFile.getOriginalFilename().equals(book.getLink())) {
                bookService.removeFile(rootDirectory + "resources\\books\\" + book.getLink());
            }else {
                modelMap.addAttribute("uploadBookError", "book with same name are exist");
                return "editBook";
            }
            book.setLink(bookFile.getOriginalFilename());
        }

        bookService.updateBook(book);

        return "redirect:/books/admin-booklist";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String getSearchBook() {
        return "search";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView searchBook(@RequestParam String textSearch, @RequestParam Boolean byTitle, @RequestParam Boolean byDescription) {
        ModelAndView modelAndView = new ModelAndView();

        List<Book> findingBooks = new ArrayList<>(bookService.searchByText(textSearch, byTitle, byDescription));
        modelAndView.addObject("searchResult", findingBooks);

        if (findingBooks.isEmpty()) {
            modelAndView.addObject("searchMessage", "Books were not found! Refine your search...");
        }

        modelAndView.setViewName("search");
        return modelAndView;
    }

    @InitBinder
    public void initialiseBinder(WebDataBinder dataBinder) {
        dataBinder.setAllowedFields("id", "title", "author", "publishing", "description", "cover", "link");
    }

    private User getCurrentAuthenticationUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByLogin(authentication.getName());
        return user;
    }
}

