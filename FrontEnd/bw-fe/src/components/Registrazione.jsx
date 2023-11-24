import { useState } from "react";
import { Button, Container, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

const Registrazione = () => {
  const [email, setEmail] = useState();
  const [password, setPassword] = useState();
  const [username, setUsername] = useState();
  const [nome, setNome] = useState();
  const [cognome, setCognome] = useState();
  const nav = useNavigate();
  const login = async (e) => {
    e.preventDefault();
    try {
      const risp = await fetch(`http://localhost:3001/auth/register`, {
        method: "POST",
        body: JSON.stringify({ email: email, password: password, username: username, nome: nome, cognome: cognome }),
        headers: {
          "content-type": "application/json",
        },
      });
      if (risp.ok) {
        const data = await risp.json();
        localStorage.setItem("token", JSON.stringify(data));
        setEmail("");
        setPassword("");
        nav("/");
      }
    } catch (error) {
      console.log(error);
    }
  };
  return (
    <>
      <Container fluid="">
        <Form onSubmit={login}>
          <Form.Group className="mt-3">
            <Form.Label>Username</Form.Label>
            <Form.Control
              required
              value={username}
              type="text"
              placeholder="username"
              onChange={(e) => {
                setUsername(e.target.value);
              }}
            />
          </Form.Group>
          <Form.Group className="mt-3">
            <Form.Label>Nome</Form.Label>
            <Form.Control
              required
              value={nome}
              type="text"
              placeholder="nome"
              onChange={(e) => {
                setNome(e.target.value);
              }}
            />
          </Form.Group>
          <Form.Group className="mt-3">
            <Form.Label>Cognome</Form.Label>
            <Form.Control
              required
              value={cognome}
              type="text"
              placeholder="cognome"
              onChange={(e) => {
                setCognome(e.target.value);
              }}
            />
          </Form.Group>
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
export default Registrazione;
