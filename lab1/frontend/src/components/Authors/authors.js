import React from "react";

const authors = (props) => {
    const mystyle = {
        color: "black",
        backgroundColor: "white",
        padding: "2px",
        fontFamily: "Bahnschrift",
        marginLeft:"15vh",
        marginTop:"9vh",
        border:"2px solid black"

    };
    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div  >
                    <table  style={mystyle}>
                        <thead>
                        <tr style={mystyle}>
                            <th scope={"col"}>Name</th>
                            <th scope={"col"}>Surname</th>
                            <th scope={"col"}>Country</th>
                            <th scope={"col"}>Continent</th>
                        </tr>
                        </thead>
                        <tbody style={mystyle}>
                        {props.authors.map((term) => {
                            return (
                                <tr style={mystyle}>
                                    <td style={mystyle}>{term.name}</td>
                                    <td style={mystyle}>{term.surname}</td>
                                    <td style={mystyle}>{term.country.name}</td>
                                    <td style={mystyle}>{term.country.continent}</td>
                                </tr>
                            );
                        })}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}

export default authors;
