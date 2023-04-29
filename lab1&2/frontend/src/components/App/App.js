import './App.css';
import {Component} from "react";
import BookService from "../../repository/bookRepo";
import {Routes, Route, BrowserRouter, Navigate, Router,Redirect} from 'react-router-dom';
import Authors from "../Authors/authors.js"
import Books from "../Books/BookList"
import BooksAdd from "../Books/BookAdd"
import BooksEdit from "../Books/BookEdit"
import Header from '../Header/Header';
import Categories from "../Categories/CategoriesList"

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            authors:[],
            countries: [],
            books:[],
            categories:[],
            selectedBook: {},
            isLoading: true
        }
    }


    componentDidMount() {
        this.fetchData()
        setTimeout(() => {
            this.setState({ isLoading: false });
        }, 500);

    }



    fetchAuthors = () => {
        BookService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            });

    }

    fetchBooks = () => {
        BookService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });

    }

        fetchData = () => {
        this.fetchBooks();
        this.fetchAuthors();
        this.fetchCategories()
    }

    render() {
        return (
            <div className="app">
                <BrowserRouter>

                    <Header/>
                    <Routes>
                        <Route path="/authors" element={ <Authors authors={this.state.authors} /> } />
                        <Route path="/categories" element={ <Categories categories={this.state.categories} /> } />
                        <Route path={"/books/add"} e element={
                            <BooksAdd   categories={this.state.categories}
                                        authors={this.state.authors}
                                        addBook={this.addBook}/>}/>
                        <Route path={"/books/edit/:id"} element={
                            <BooksEdit categories={this.state.categories}
                                       authors={this.state.authors}
                                         editBook={this.editBook}
                                         />}/>

                        <Route path={"/books"} element={ <Books books={this.state.books} deleteBook={this.deleteBook}
                                                                markBookAsTaken={this.markBookAsTaken}  />}/>

                    </Routes>

                </BrowserRouter>
            </div>
        );
    }

    loadBooks = () => {
        BookService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });
    }



    deleteBook = (id) => {
        BookService.deleteBook(id)
            .then(() => {
                this.loadBooks();
            });
    }

    addBook = ( name, category, author, availableCopies) => {
        BookService.addBook( name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    getBook = (id) => {
        BookService.fetchBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            })
    }

    editBook = (id, name, category, author, availableCopies) => {
        BookService.editBook(id, name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

     markBookAsTaken = (id) => {
        BookService.markBookAsTaken(id)
            .then((response) => {
                this.fetchBooks();
            });
    }

     fetchCategories = () => {
        BookService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            })
    }


}

export default App;

