import axios from '../custom-axios/axios';

const BookService = {

    fetchCategories: () => {
        return axios.get('/books/categories');
    },

    fetchAuthors: () => {
        return axios.get('/authors');
    },

    editBook: (id, name, category, author, availableCopies) => {
        return axios.put(`/books/update/${id}`, {
            name,
            category,
            author,
            availableCopies
        });
    },

    addBook: (name, category, author, availableCopies) => {
        return axios.post('/books/add', {
            name,
            category,
            author,
            availableCopies
        });
    },

    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    },

    markBookAsTaken: (id) => {
        return axios.post(`/books/markastaken/${id}`);
    },

    fetchBooks: () => {
        return axios.get('/books');
    },

    fetchBook: (id) => {
        return axios.get(`/books/${id}`)
    }


}
export default BookService;

