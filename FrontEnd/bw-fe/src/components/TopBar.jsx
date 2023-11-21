import { Image } from "react-bootstrap";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import logo from "../assets/IMG_3505-PhotoRoom.png-PhotoRoom.png";
import { Link, useLocation } from "react-router-dom";

const TopBar = () => {
  const loc = useLocation();
  return (
    <Navbar expand="lg" className="bg-body-tertiary">
      <Container>
        <Image src={logo} width="100px" />
        <Navbar.Brand href="#home">Epic Energy</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Link to={"/"} className={loc.pathname === "/" && "selected"}>
              Home
            </Link>
            <Link to={"/utenti"} className={loc.pathname === "/utenti" && "selected"}>
              Utenti
            </Link>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};

export default TopBar;
