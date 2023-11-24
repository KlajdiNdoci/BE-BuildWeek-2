import { useState } from "react";
import { Button, Container, Form } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";

const Home = () => {
  const [email, setEmail] = useState();
  const [password, setPassword] = useState();
  const nav = useNavigate();
  const login = async (e) => {
    e.preventDefault();
    try {
      const risp = await fetch(`http://localhost:3001/auth/login`, {
        method: "POST",
        body: JSON.stringify({ email: email, password: password }),
        headers: {
          "content-type": "application/json",
        },
      });
      if (risp.ok) {
        const data = await risp.json();
        localStorage.setItem("token", JSON.stringify(data));
        setEmail("");
        setPassword("");
        nav("/utenti");
      }
    } catch (error) {
      console.log(error);
    }
  };
  return (
    <>
      <Container fluid="sm" className="pt-5">
        <div className="inserimento_dati w-50 mx-auto border p-4  shadow bg-light" style={{ borderRadius: "20px" }}>
          <div className="mb-5">
            <h2>Login</h2>
          </div>

          <Form onSubmit={login}>
            <Form.Group className="mt-3">
              <Form.Label>Email</Form.Label>
              <Form.Control
                required
                value={email}
                type="text"
                placeholder="email"
                onChange={(e) => {
                  setEmail(e.target.value);
                }}
                style={{ boxShadow: "none" }}
                className="input"
              />
            </Form.Group>
            <Form.Group className="mt-3">
              <Form.Label>Password</Form.Label>
              <Form.Control
                required
                value={password}
                type="password"
                placeholder="password"
                onChange={(e) => {
                  setPassword(e.target.value);
                }}
                style={{ boxShadow: "none" }}
                className="input"
              />
            </Form.Group>
            <div className="d-flex justify-content-between mt-3 mb-2">
              <Button className="mt-3" type="submit" variant="outline-primary" style={{ width: "100px" }}>
                Login
              </Button>
              <Link to={"/registrazione"}>
                <button id="reg" style={{ color: "grey", width: "100px", paddingBottom: "10px" }}>
                  <div class="text">
                    <span>Sign</span>
                    <span>up</span>
                  </div>
                  <div class="clone">
                    <span>Sign</span>
                    <span>up</span>
                  </div>
                  <svg
                    width="20px"
                    xmlns="http://www.w3.org/2000/svg"
                    class="h-6 w-6"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke="currentColor"
                    stroke-width="2"
                  >
                    <path stroke-linecap="round" stroke-linejoin="round" d="M14 5l7 7m0 0l-7 7m7-7H3"></path>
                  </svg>
                </button>
              </Link>
            </div>
          </Form>
        </div>
      </Container>
    </>
  );
};
export default Home;
