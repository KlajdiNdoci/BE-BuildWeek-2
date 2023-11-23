import { useState } from "react";
import { Button, Container, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

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
      <Container fluid="sm">
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
            />
          </Form.Group>
          <Button className="mt-3" type="submit">
            Submit form
          </Button>
        </Form>
      </Container>
    </>
  );
};
export default Home;
