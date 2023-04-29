import React from 'react';
import { Link } from 'react-router-dom';
import '../App/App.css';

const BookItem = ({ book, deleteBook, markBookAsTaken }) => {
    const handleDelete = () => {
        deleteBook(book.id);
    };

    const handleMarkAsTaken = () => {
        markBookAsTaken(book.id);
    };

    const mystyle = {
        color: 'black',
        backgroundColor: 'white',
        border: '2px solid black',
        padding: '20px',
        fontFamily: 'Bahnschrift',
    };

    return (
        <tr style={mystyle}>
            <td style={mystyle}>{book.name}</td>
            <td style={mystyle}>{book.category}</td>
            <td style={mystyle}>{`${book.author.name} ${book.author.surname}`}</td>
            <td style={mystyle}>{book.author.country.name}</td>
            <td style={mystyle}>{book.availableCopies}</td>
            <td style={mystyle} className="d-flex justify-content-around">
                <Link to={`/books/edit/${book.id}`} className="btn">
                    Edit
                </Link>
                <button className="btn" onClick={handleDelete}>
                    Delete
                </button>
                <button className="btn" onClick={handleMarkAsTaken}>
                    Mark As Taken
                </button>
            </td>
        </tr>
    );
};

export default BookItem;
