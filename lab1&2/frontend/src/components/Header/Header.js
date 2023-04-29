import React from 'react';
import {Link} from 'react-router-dom';
import "../App/App.css"

const Header = () => {

    const mm = {
        color: "black",
        padding: "20px",
        fontFamily: "Bahnschrift",
        marginLeft:"0",
        marginTop:"0"


    };
    const linkk = {
        marginLeft:"2vh",
        marginTop:"2vh"
    };
    return (

        <nav style={mm} className="navbar navbar-expand-lg bg-body-tertiary border-bottom border-primary-subtle">
            <div className="kelebeks">
                <div className="kelebek">
                    <img src="https://i.ibb.co/LvcMPvH/pngegg.png" width="100" alt="kelebeklerr"/>
                </div>
                <div className="kelebek">
                    <img
                        src="https://i.ibb.co/m0Nts0m/kisspng-glasswing-butterfly-insect-clip-art-patins-5b4a666cc41cc0-7007119915316025408033.png"
                        width="75" alt="kelebeklerr"/>
                </div>
                <div className="kelebek">
                    <img src="https://i.pinimg.com/originals/73/25/b5/7325b5da1c12314824328579aeed59f4.gif" width="75"
                         alt="kelebeklerr"/>
                </div>
                <div className="kelebek">
                    <img src="https://i.ibb.co/26DzHgS/pngegg-1.png" width="75" alt="kelebeklerr"/>
                </div>
                <div className="kelebek">
                    <img src="https://i.ibb.co/LvcMPvH/pngegg.png" width="100" alt="kelebeklerr"/>
                </div>
                <div className="kelebek">
                    <img
                        src="https://i.ibb.co/m0Nts0m/kisspng-glasswing-butterfly-insect-clip-art-patins-5b4a666cc41cc0-7007119915316025408033.png"
                        width="75" alt="kelebeklerr"/>
                </div>
                <div className="kelebek">
                    <img src="https://i.pinimg.com/originals/73/25/b5/7325b5da1c12314824328579aeed59f4.gif" width="75"
                         alt="kelebeklerr"/>
                </div>
                <div className="kelebek">
                    <img src="https://i.ibb.co/26DzHgS/pngegg-1.png" width="75" alt="kelebeklerr"/>
                </div>
                <div className="kelebek">
                    <img src="https://i.ibb.co/LvcMPvH/pngegg.png" width="100" alt="kelebeklerr"/>
                </div>
                <div className="kelebek">
                    <img src="https://i.pinimg.com/originals/73/25/b5/7325b5da1c12314824328579aeed59f4.gif" width="75"
                         alt="kelebeklerr"/>
                </div>
            </div>
            <div className="container-fluid">

                <div className=" navbar-collapse">
                    <ul className="navbar-nav">
                        <li  style={linkk} className="nav-item">
                            <Link  to="/books"> Books </Link>
                        </li>
                        <li style={linkk} className="nav-item">
                            <Link to="/categories"> Categories </Link>
                        </li>
                        <li style={linkk} className="nav-item">
                            <Link to="/authors"> Authors </Link>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    );
}

export default Header;