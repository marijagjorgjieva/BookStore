import React from 'react';

const CategoriesList = ({ categories}) => {

    const renderCategories = () => {
        return categories.map(cat => {
            return (
                <li  style={mystyle} class="list-group-item">
                    <span >{cat}</span>
                </li>
            );
        });
    }

    const mystyle = {
        color: "black",
        backgroundColor: "white",
        padding: "2px",
        fontFamily: "Bahnschrift",
        marginLeft:"15vh",
        marginTop:"1vh",
        border:"2px solid black"

    };
    
    return (
        <div className="container mt-5">
            <h1 style={mystyle}>Categories</h1>
            <ul className="list-group">
                {renderCategories()}
            </ul>
        </div>
    );
}

export default CategoriesList;