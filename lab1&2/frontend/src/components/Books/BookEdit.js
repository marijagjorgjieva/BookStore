import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import BookService from '../../repository/bookRepo';

const BookEdit = ({ categories, authors, editBook }) => {
    const [book, setBook] = useState({
        id: 0,
        name: '',
        category: categories[0],
        author: authors[0].id,
        availableCopies: 0,
    });
    const navigate = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        const fetchBook = async (id) => {
            try {
                const { data } = await BookService.fetchBook(id);
                setBook({
                    id: data.id,
                    name: data.name,
                    category: data.category,
                    author: data.author.id,
                    availableCopies: data.availableCopies,
                });
            } catch (error) {
                console.error('Failed to fetch book', error);
            }
        };
        fetchBook(id);
    }, [id]);

    const handleChange = (event) => {
        setBook((prevBook) => ({
            ...prevBook,
            [event.target.name]: event.target.value,
        }));
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        const { name, category, author, availableCopies } = book;
        editBook(book.id, name, category, author, availableCopies);
        navigate('/books');
    };

    return (
        <div className="container mt-3">
            <div className="row">
                <div className="col-md-5">
                    <form onSubmit={handleSubmit}>
                        <div className="form-group">
                            <label htmlFor="name">Book title</label>
                            <input
                                type="text"
                                className="form-control"
                                id="name"
                                name="name"
                                required
                                placeholder="Enter Book Title"
                                value={book.name}
                                onChange={handleChange}
                            />
                        </div>
                        <div className="form-group">
                            <label>Category</label>
                            <select
                                name="category"
                                className="form-control"
                                value={book.category}
                                onChange={handleChange}
                            >
                                {categories.map((category) => (
                                    <option key={category} value={category}>
                                        {category.charAt(0) + category.substring(1).toLowerCase()}
                                    </option>
                                ))}
                            </select>
                        </div>
                        <div className="form-group">
                            <label>Author</label>
                            <select
                                name="author"
                                className="form-control"
                                value={book.author}
                                onChange={handleChange}
                            >
                                {authors.map((author) => (
                                    <option key={author.id} value={author.id}>
                                        {`${author.name} ${author.surname}`}
                                    </option>
                                ))}
                            </select>
                        </div>
                        <div className="form-group">
                            <label htmlFor="availableCopies">Available Copies</label>
                            <input
                                type="text"
                                className="form-control"
                                id="availableCopies"
                                name="availableCopies"
                                placeholder="Available Copies"
                                value={book.availableCopies}
                                onChange={handleChange}
                            />
                        </div>
                        <button type="submit" className="btn btn-primary">
                            Submit
                        </button>
                        <button type="button" className="btn btn-secondary mx-1" onClick={() => navigate(-1)}>
                            Cancel
                        </button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default BookEdit;
